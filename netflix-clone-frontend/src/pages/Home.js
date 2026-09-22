import React, { useEffect, useState, useCallback } from 'react';
import Banner from '../components/Banner';
import MovieRow from '../components/MovieRow';
import Footer from '../components/Footer';
import { moviesAPI, watchlistAPI } from '../api/axiosConfig';
import { useAuth } from '../context/AuthContext';

const GENRES = ['Action', 'Drama', 'Sci-Fi', 'Comedy', 'Thriller', 'Horror', 'Romance', 'Crime'];

const Home = () => {
  const { isAuthenticated } = useAuth();
  const [featured, setFeatured] = useState(null);
  const [moviesByGenre, setMoviesByGenre] = useState({});
  const [watchlistIds, setWatchlistIds] = useState([]);
  const [loading, setLoading] = useState(true);

  const loadData = useCallback(async () => {
    try {
      setLoading(true);

      // Load movies by genre in parallel
      const genreResults = await Promise.allSettled(
        GENRES.map(genre =>
          moviesAPI.getAll({ genre, size: 15 }).then(r => ({ genre, movies: r.data.content }))
        )
      );

      const byGenre = {};
      genreResults.forEach(result => {
        if (result.status === 'fulfilled' && result.value.movies?.length > 0) {
          byGenre[result.value.genre] = result.value.movies;
        }
      });
      setMoviesByGenre(byGenre);

      // Pick featured from first available genre
      const allMovies = Object.values(byGenre).flat();
      if (allMovies.length > 0) {
        setFeatured(allMovies[Math.floor(Math.random() * Math.min(allMovies.length, 5))]);
      }

      // Load watchlist if authenticated
      if (isAuthenticated) {
        const wlRes = await watchlistAPI.getAll();
        setWatchlistIds(wlRes.data.map(item => item.movieId));
      }
    } catch (err) {
      console.error('Failed to load home data:', err);
    } finally {
      setLoading(false);
    }
  }, [isAuthenticated]);

  useEffect(() => { loadData(); }, [loadData]);

  if (loading) {
    return (
      <div className="page-wrapper">
        <div className="loading"><div className="spinner" /></div>
      </div>
    );
  }

  return (
    <div className="page-wrapper" style={{ paddingTop: 0 }}>
      <Banner movie={featured} />

      <div style={{ marginTop: '-80px', position: 'relative', zIndex: 1 }}>
        {GENRES.filter(g => moviesByGenre[g]?.length > 0).map(genre => (
          <MovieRow
            key={genre}
            title={genre}
            movies={moviesByGenre[genre]}
            genre={genre}
            watchlistIds={watchlistIds}
          />
        ))}
      </div>

      {Object.keys(moviesByGenre).length === 0 && (
        <div className="empty-state">
          <div className="empty-state__icon">🎬</div>
          <div className="empty-state__title">No movies found</div>
          <div className="empty-state__desc">Make sure the backend is running on port 8080</div>
        </div>
      )}

      <Footer />
    </div>
  );
};

export default Home;
