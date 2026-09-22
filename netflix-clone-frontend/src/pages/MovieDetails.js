import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { moviesAPI, watchlistAPI, reviewsAPI } from '../api/axiosConfig';
import { useAuth } from '../context/AuthContext';
import MovieRow from '../components/MovieRow';

const StarRating = ({ value, onChange }) => (
  <div className="star-rating">
    {[1, 2, 3, 4, 5].map(star => (
      <span
        key={star}
        className="star"
        onClick={() => onChange(star)}
        style={{ color: star <= value ? '#ffd700' : '#555' }}
        role="button"
        aria-label={`Rate ${star} star${star > 1 ? 's' : ''}`}
      >★</span>
    ))}
  </div>
);

const MovieDetails = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const { isAuthenticated } = useAuth();
  const [movie, setMovie] = useState(null);
  const [reviews, setReviews] = useState([]);
  const [similarMovies, setSimilarMovies] = useState([]);
  const [loading, setLoading] = useState(true);
  const [inWatchlist, setInWatchlist] = useState(false);
  const [wlLoading, setWlLoading] = useState(false);
  const [reviewForm, setReviewForm] = useState({ rating: 0, comment: '' });
  const [reviewError, setReviewError] = useState('');
  const [reviewLoading, setReviewLoading] = useState(false);
  const [reviewSuccess, setReviewSuccess] = useState(false);

  useEffect(() => {
    const load = async () => {
      try {
        setLoading(true);
        const [movieRes, reviewRes, similarRes] = await Promise.all([
          moviesAPI.getById(id),
          reviewsAPI.getByMovie(id),
          moviesAPI.getSimilar(id),
        ]);
        setMovie(movieRes.data);
        setReviews(reviewRes.data);
        setSimilarMovies(similarRes.data || []);

        if (isAuthenticated) {
          const wlRes = await watchlistAPI.getAll();
          setInWatchlist(wlRes.data.some(item => item.movieId === parseInt(id)));
        }
      } catch (err) {
        if (err.response?.status === 404) navigate('/');
      } finally {
        setLoading(false);
      }
    };
    load();
  }, [id, isAuthenticated, navigate]);

  const toggleWatchlist = async () => {
    if (!isAuthenticated) { navigate('/login'); return; }
    setWlLoading(true);
    try {
      if (inWatchlist) {
        await watchlistAPI.remove(id);
        setInWatchlist(false);
      } else {
        await watchlistAPI.add(id);
        setInWatchlist(true);
      }
    } catch (err) { /* ignore */ }
    finally { setWlLoading(false); }
  };

  const submitReview = async (e) => {
    e.preventDefault();
    if (!isAuthenticated) { navigate('/login'); return; }
    if (reviewForm.rating === 0) { setReviewError('Please select a rating'); return; }
    setReviewError('');
    setReviewLoading(true);
    try {
      const res = await reviewsAPI.add(id, reviewForm);
      setReviews(prev => [res.data, ...prev]);
      setReviewForm({ rating: 0, comment: '' });
      setReviewSuccess(true);
      // refresh average rating
      const movieRes = await moviesAPI.getById(id);
      setMovie(movieRes.data);
      setTimeout(() => setReviewSuccess(false), 3000);
    } catch (err) {
      setReviewError(err.response?.data?.message || 'Failed to submit review');
    } finally {
      setReviewLoading(false);
    }
  };

  if (loading) return <div className="page-wrapper"><div className="loading"><div className="spinner" /></div></div>;
  if (!movie) return null;

  const genres = movie.genres
    ? (Array.isArray(movie.genres) ? movie.genres : Array.from(movie.genres))
    : [];

  const netflixSearchUrl = `https://www.netflix.com/search?q=${encodeURIComponent(movie.title)}`;

  return (
    <div className="page-wrapper movie-details">
      <div className="movie-details__hero">
        {movie.posterUrl ? (
          <img className="movie-details__hero-img" src={movie.posterUrl} alt={movie.title} />
        ) : (
          <div className="movie-details__hero-img" style={{
            background: 'linear-gradient(135deg, #1a1a2e, #16213e, #0f3460)'
          }} />
        )}
        <div className="movie-details__hero-overlay" />
      </div>

      <div className="movie-details__content">
        <h1 className="movie-details__title">{movie.title}</h1>
        <div className="movie-details__meta">
          <span className="movie-details__rating">
            ⭐ {movie.averageRating > 0 ? movie.averageRating.toFixed(1) : 'Not yet rated'}
          </span>
          <span>{movie.releaseYear}</span>
          <span>{reviews.length} review{reviews.length !== 1 ? 's' : ''}</span>
        </div>
        {genres.length > 0 && (
          <div className="movie-details__genres">
            {genres.map(g => <span key={g} className="genre-tag">{g}</span>)}
          </div>
        )}
        <p className="movie-details__desc">{movie.description}</p>
        <div className="movie-details__actions">
          <button
            id="movie-details-watchlist-btn"
            className={`btn ${inWatchlist ? 'btn-secondary' : 'btn-primary'}`}
            onClick={toggleWatchlist}
            disabled={wlLoading}
          >
            {wlLoading ? '…' : inWatchlist ? '✓ In Watchlist' : '+ Add to Watchlist'}
          </button>
          {movie.videoUrl && (
            <a href={movie.videoUrl} target="_blank" rel="noreferrer" className="btn btn-secondary">
              ▶ Play Trailer
            </a>
          )}
          <a
            id="view-on-netflix-btn"
            href={netflixSearchUrl}
            target="_blank"
            rel="noreferrer"
            className="btn btn-outline"
          >
            𝐍 View on Netflix
          </a>
        </div>
      </div>

      <div className="reviews-section">
        <h2 className="reviews-section__title">Reviews ({reviews.length})</h2>

        {isAuthenticated && (
          <div className="review-form">
            <div className="review-form__title">Write a Review</div>
            {reviewSuccess && <div className="alert alert-success" id="review-success">✓ Review submitted!</div>}
            {reviewError && <div className="alert alert-error" id="review-error">⚠ {reviewError}</div>}
            <form onSubmit={submitReview} id="review-form">
              <StarRating value={reviewForm.rating} onChange={r => setReviewForm(p => ({ ...p, rating: r }))} />
              <textarea
                id="review-comment"
                className="form-input"
                placeholder="Share your thoughts about this movie…"
                value={reviewForm.comment}
                onChange={e => setReviewForm(p => ({ ...p, comment: e.target.value }))}
                rows={3}
                style={{ resize: 'vertical', marginBottom: 12 }}
                maxLength={1000}
              />
              <button id="review-submit" className="btn btn-primary btn-sm" type="submit" disabled={reviewLoading}>
                {reviewLoading ? 'Submitting…' : 'Submit Review'}
              </button>
            </form>
          </div>
        )}

        {reviews.length === 0 ? (
          <div className="empty-state">
            <div className="empty-state__icon">💬</div>
            <div className="empty-state__title">No reviews yet</div>
            <div className="empty-state__desc">Be the first to review this movie!</div>
          </div>
        ) : (
          reviews.map(r => (
            <div key={r.id} className="review-card" id={`review-${r.id}`}>
              <div className="review-card__header">
                <div>
                  <div className="review-card__author">{r.username}</div>
                  <div className="review-card__stars">{'★'.repeat(r.rating)}{'☆'.repeat(5 - r.rating)}</div>
                </div>
                <div className="review-card__date">
                  {r.createdAt ? new Date(r.createdAt).toLocaleDateString() : ''}
                </div>
              </div>
              {r.comment && <p className="review-card__comment">{r.comment}</p>}
            </div>
          ))
        )}
      </div>

      {similarMovies.length > 0 && (
        <div style={{ paddingBottom: '40px' }}>
          <MovieRow title="More Like This" movies={similarMovies} />
        </div>
      )}
    </div>
  );
};

export default MovieDetails;

