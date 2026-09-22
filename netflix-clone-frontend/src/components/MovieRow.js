import React, { useRef } from 'react';
import { Link } from 'react-router-dom';
import MovieCard from './MovieCard';

const MovieRow = ({ title, movies, genre, watchlistIds = [] }) => {
  const trackRef = useRef(null);

  const scroll = (dir) => {
    if (trackRef.current) {
      trackRef.current.scrollBy({ left: dir * 700, behavior: 'smooth' });
    }
  };

  if (!movies || movies.length === 0) return null;

  return (
    <section className="movie-row">
      <div className="movie-row__header">
        <h2 className="movie-row__title">{title}</h2>
        {genre && (
          <Link to={`/search?genre=${encodeURIComponent(genre)}`} className="movie-row__see-all">
            See all →
          </Link>
        )}
      </div>

      <div style={{ position: 'relative' }}>
        <button
          onClick={() => scroll(-1)}
          style={{
            position: 'absolute', left: 8, top: '50%', transform: 'translateY(-50%)',
            zIndex: 5, background: 'rgba(0,0,0,0.7)', color: 'white',
            border: 'none', width: 40, height: 40, borderRadius: '50%',
            cursor: 'pointer', fontSize: '1.2rem', backdropFilter: 'blur(4px)',
          }}
          aria-label="Scroll left"
        >‹</button>

        <div className="movie-row__track" ref={trackRef}>
          {movies.map(movie => (
            <MovieCard
              key={movie.id}
              movie={movie}
              inWatchlist={watchlistIds.includes(movie.id)}
            />
          ))}
        </div>

        <button
          onClick={() => scroll(1)}
          style={{
            position: 'absolute', right: 8, top: '50%', transform: 'translateY(-50%)',
            zIndex: 5, background: 'rgba(0,0,0,0.7)', color: 'white',
            border: 'none', width: 40, height: 40, borderRadius: '50%',
            cursor: 'pointer', fontSize: '1.2rem', backdropFilter: 'blur(4px)',
          }}
          aria-label="Scroll right"
        >›</button>
      </div>
    </section>
  );
};

export default MovieRow;
