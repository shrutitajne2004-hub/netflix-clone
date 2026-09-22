import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import { watchlistAPI } from '../api/axiosConfig';

const FALLBACK_POSTER = null;

const MovieCard = ({ movie, inWatchlist = false, onWatchlistChange }) => {
  const navigate = useNavigate();
  const { isAuthenticated } = useAuth();
  const [imgError, setImgError] = useState(false);
  const [inList, setInList] = useState(inWatchlist);
  const [loading, setLoading] = useState(false);

  const handleClick = () => navigate(`/movie/${movie.id}`);

  const handleWatchlistToggle = async (e) => {
    e.stopPropagation();
    if (!isAuthenticated) { navigate('/login'); return; }
    setLoading(true);
    try {
      if (inList) {
        await watchlistAPI.remove(movie.id);
        setInList(false);
      } else {
        await watchlistAPI.add(movie.id);
        setInList(true);
      }
      if (onWatchlistChange) onWatchlistChange(movie.id, !inList);
    } catch (err) {
      // Silently handle duplicate/not-found
    } finally {
      setLoading(false);
    }
  };

  const genreNames = movie.genres
    ? (Array.isArray(movie.genres) ? movie.genres : Array.from(movie.genres)).slice(0, 2)
    : [];

  return (
    <div className="movie-card" onClick={handleClick} id={`movie-card-${movie.id}`}>
      {!imgError && movie.posterUrl ? (
        <img
          className="movie-card__poster"
          src={movie.posterUrl}
          alt={movie.title}
          onError={() => setImgError(true)}
          loading="lazy"
        />
      ) : (
        <div className="movie-card__poster-fallback">
          <span style={{ fontSize: '2rem' }}>🎬</span>
          <span style={{ textAlign: 'center', padding: '0 8px' }}>{movie.title}</span>
        </div>
      )}

      <div className="movie-card__overlay">
        <div className="movie-card__title">{movie.title}</div>
        <div className="movie-card__meta">
          <span className="movie-card__rating">
            ⭐ {movie.averageRating > 0 ? movie.averageRating.toFixed(1) : 'N/A'}
          </span>
          <span>{movie.releaseYear}</span>
          <button
            className={`movie-card__add-btn ${inList ? 'added' : ''}`}
            onClick={handleWatchlistToggle}
            disabled={loading}
            id={`watchlist-btn-${movie.id}`}
            title={inList ? 'Remove from Watchlist' : 'Add to Watchlist'}
          >
            {loading ? '…' : inList ? '✓' : '+'}
          </button>
        </div>
        {genreNames.length > 0 && (
          <div style={{ display: 'flex', gap: '4px', marginTop: '6px', flexWrap: 'wrap' }}>
            {genreNames.map(g => (
              <span key={g} className="genre-tag" style={{ fontSize: '0.65rem', padding: '2px 6px' }}>{g}</span>
            ))}
          </div>
        )}
      </div>
    </div>
  );
};

export default MovieCard;
