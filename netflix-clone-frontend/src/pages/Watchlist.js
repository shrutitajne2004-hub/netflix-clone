import React, { useEffect, useState, useCallback } from 'react';
import { useNavigate } from 'react-router-dom';
import { watchlistAPI } from '../api/axiosConfig';
import { useAuth } from '../context/AuthContext';
import MovieCard from '../components/MovieCard';
import Footer from '../components/Footer';

const Watchlist = () => {
  const { isAuthenticated } = useAuth();
  const navigate = useNavigate();
  const [items, setItems] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    if (!isAuthenticated) { navigate('/login'); return; }
    const load = async () => {
      try {
        const res = await watchlistAPI.getAll();
        setItems(res.data);
      } catch (err) {
        console.error(err);
      } finally {
        setLoading(false);
      }
    };
    load();
  }, [isAuthenticated, navigate]);

  const handleRemove = useCallback(async (movieId) => {
    try {
      await watchlistAPI.remove(movieId);
      setItems(prev => prev.filter(item => item.movieId !== movieId));
    } catch (err) { /* ignore */ }
  }, []);

  if (loading) return <div className="page-wrapper"><div className="loading"><div className="spinner" /></div></div>;

  return (
    <div className="page-wrapper">
      <div className="watchlist-page">
        <div className="watchlist-page__header">
          <h1 className="watchlist-page__title">My Watchlist</h1>
          <p style={{ color: 'var(--text-secondary)', fontSize: '0.875rem' }}>
            {items.length} movie{items.length !== 1 ? 's' : ''}
          </p>
        </div>

        {items.length === 0 ? (
          <div className="empty-state">
            <div className="empty-state__icon">🎬</div>
            <div className="empty-state__title">Your watchlist is empty</div>
            <div className="empty-state__desc">
              Browse movies and click the + button to add them here
            </div>
            <button className="btn btn-primary" style={{ marginTop: 20 }} onClick={() => navigate('/')}>
              Browse Movies
            </button>
          </div>
        ) : (
          <div className="watchlist-grid">
            {items.map(item => (
              <div key={item.id} className="watchlist-item" id={`watchlist-item-${item.movieId}`}>
                <MovieCard
                  movie={{
                    id: item.movieId,
                    title: item.movieTitle,
                    posterUrl: item.posterUrl,
                    averageRating: 0,
                    releaseYear: '',
                    genres: [],
                  }}
                  inWatchlist={true}
                />
                <button
                  className="watchlist-item__remove"
                  onClick={() => handleRemove(item.movieId)}
                  id={`remove-watchlist-${item.movieId}`}
                  title="Remove from watchlist"
                >
                  ✕
                </button>
              </div>
            ))}
          </div>
        )}
      </div>
      <Footer />
    </div>
  );
};

export default Watchlist;
