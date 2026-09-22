import React, { useEffect, useState, useCallback } from 'react';
import { useSearchParams, useNavigate } from 'react-router-dom';
import { moviesAPI } from '../api/axiosConfig';
import MovieCard from '../components/MovieCard';
import Footer from '../components/Footer';

const GENRES = ['Action', 'Drama', 'Sci-Fi', 'Comedy', 'Thriller', 'Horror', 'Romance', 'Crime'];

const SearchResults = () => {
  const [searchParams, setSearchParams] = useSearchParams();
  const navigate = useNavigate();
  const query = searchParams.get('q') || '';
  const genre = searchParams.get('genre') || '';

  const [movies, setMovies] = useState([]);
  const [loading, setLoading] = useState(false);
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [totalElements, setTotalElements] = useState(0);
  const [localSearch, setLocalSearch] = useState(query);

  const search = useCallback(async (q, g, p) => {
    setLoading(true);
    try {
      const params = { page: p, size: 20 };
      if (q) params.title = q;
      if (g) params.genre = g;
      const res = await moviesAPI.getAll(params);
      setMovies(res.data.content);
      setTotalPages(res.data.totalPages);
      setTotalElements(res.data.totalElements);
    } catch (err) {
      console.error(err);
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    setPage(0);
    search(query, genre, 0);
  }, [query, genre, search]);

  useEffect(() => {
    if (page > 0) search(query, genre, page);
  }, [page, query, genre, search]);

  const handleSearch = (e) => {
    e.preventDefault();
    setSearchParams(localSearch ? { q: localSearch } : {});
  };

  const filterByGenre = (g) => {
    setSearchParams({ genre: g });
    setLocalSearch('');
  };

  const heading = query
    ? `Search results for "${query}"`
    : genre
    ? `Genre: ${genre}`
    : 'All Movies';

  return (
    <div className="page-wrapper">
      <div className="search-page">
        <div className="search-page__header">
          <h1 className="search-page__title">
            <span>{heading}</span>
            {!loading && <span style={{ fontSize: '0.875rem', marginLeft: 12, color: 'var(--text-muted)' }}>
              {totalElements} result{totalElements !== 1 ? 's' : ''}
            </span>}
          </h1>

          {/* Search bar */}
          <form onSubmit={handleSearch} style={{ display: 'flex', gap: 8, marginTop: 16, marginBottom: 24 }}>
            <input
              id="search-input"
              className="form-input"
              style={{ maxWidth: 400 }}
              placeholder="Search movies by title…"
              value={localSearch}
              onChange={e => setLocalSearch(e.target.value)}
            />
            <button id="search-submit" className="btn btn-primary" type="submit">Search</button>
          </form>

          {/* Genre chips */}
          <div style={{ display: 'flex', gap: 8, flexWrap: 'wrap', marginBottom: 24 }}>
            <button
              className={`genre-tag ${!genre ? 'active' : ''}`}
              style={{ cursor: 'pointer', padding: '6px 14px', border: !genre ? '1px solid white' : '1px solid rgba(255,255,255,0.15)' }}
              onClick={() => setSearchParams(query ? { q: query } : {})}
              id="genre-all"
            >All</button>
            {GENRES.map(g => (
              <button
                key={g}
                className="genre-tag"
                style={{
                  cursor: 'pointer', padding: '6px 14px',
                  border: genre === g ? '1px solid white' : '1px solid rgba(255,255,255,0.15)'
                }}
                onClick={() => filterByGenre(g)}
                id={`genre-${g.toLowerCase()}`}
              >{g}</button>
            ))}
          </div>
        </div>

        {loading ? (
          <div className="loading"><div className="spinner" /></div>
        ) : movies.length === 0 ? (
          <div className="empty-state">
            <div className="empty-state__icon">🔍</div>
            <div className="empty-state__title">No results found</div>
            <div className="empty-state__desc">Try a different search term or genre</div>
          </div>
        ) : (
          <>
            <div className="search-results-grid">
              {movies.map(movie => (
                <MovieCard key={movie.id} movie={movie} />
              ))}
            </div>

            {/* Pagination */}
            {totalPages > 1 && (
              <div className="pagination">
                <button
                  className="pagination__btn"
                  disabled={page === 0}
                  onClick={() => setPage(p => p - 1)}
                  id="prev-page"
                >← Prev</button>
                {[...Array(Math.min(totalPages, 5))].map((_, i) => {
                  const pageNum = page < 3 ? i : page - 2 + i;
                  if (pageNum >= totalPages) return null;
                  return (
                    <button
                      key={pageNum}
                      className={`pagination__btn ${pageNum === page ? 'active' : ''}`}
                      onClick={() => setPage(pageNum)}
                      id={`page-${pageNum}`}
                    >{pageNum + 1}</button>
                  );
                })}
                <button
                  className="pagination__btn"
                  disabled={page >= totalPages - 1}
                  onClick={() => setPage(p => p + 1)}
                  id="next-page"
                >Next →</button>
              </div>
            )}
          </>
        )}
      </div>
      <Footer />
    </div>
  );
};

export default SearchResults;
