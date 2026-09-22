import React from 'react';
import { useNavigate } from 'react-router-dom';

const Banner = ({ movie }) => {
  const navigate = useNavigate();

  if (!movie) return null;

  const genres = movie.genres
    ? (Array.isArray(movie.genres) ? movie.genres : Array.from(movie.genres))
    : [];

  return (
    <div className="banner">
      {movie.posterUrl ? (
        <img
          className="banner__bg"
          src={movie.posterUrl}
          alt={movie.title}
          style={{ objectPosition: 'top' }}
        />
      ) : (
        <div className="banner__bg" style={{
          background: 'linear-gradient(135deg, #1a1a2e, #16213e, #0f3460)'
        }} />
      )}
      <div className="banner__gradient" />
      <div className="banner__content">
        <span className="banner__badge">⭐ Featured</span>
        <h1 className="banner__title">{movie.title}</h1>
        <div className="banner__meta">
          <span className="banner__rating">⭐ {movie.averageRating > 0 ? movie.averageRating.toFixed(1) : 'New'}</span>
          <span>{movie.releaseYear}</span>
        </div>
        {genres.length > 0 && (
          <div className="banner__genres">
            {genres.map(g => <span key={g} className="genre-tag">{g}</span>)}
          </div>
        )}
        <p className="banner__desc">{movie.description}</p>
        <div className="banner__actions">
          <button
            id="banner-details-btn"
            className="btn btn-primary"
            onClick={() => navigate(`/movie/${movie.id}`)}
          >
            ▶ Watch Now
          </button>
          <button
            id="banner-info-btn"
            className="btn btn-secondary"
            onClick={() => navigate(`/movie/${movie.id}`)}
          >
            ℹ More Info
          </button>
        </div>
      </div>
    </div>
  );
};

export default Banner;
