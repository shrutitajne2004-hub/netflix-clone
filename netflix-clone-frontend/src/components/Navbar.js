import React, { useState, useEffect } from 'react';
import { Link, useNavigate, NavLink } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

const Navbar = () => {
  const { user, logout, isAuthenticated } = useAuth();
  const [scrolled, setScrolled] = useState(false);
  const [searchQuery, setSearchQuery] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    const handleScroll = () => setScrolled(window.scrollY > 20);
    window.addEventListener('scroll', handleScroll);
    return () => window.removeEventListener('scroll', handleScroll);
  }, []);

  const handleSearch = (e) => {
    e.preventDefault();
    if (searchQuery.trim()) {
      navigate(`/search?q=${encodeURIComponent(searchQuery.trim())}`);
      setSearchQuery('');
    }
  };

  const handleLogout = () => {
    logout();
    navigate('/');
  };

  return (
    <nav className={`navbar ${scrolled ? 'scrolled' : ''}`}>
      <Link to="/" className="navbar__logo">NETCLONE</Link>

      <div className="navbar__links">
        <NavLink to="/" end>Home</NavLink>
        {isAuthenticated && <NavLink to="/watchlist">My Watchlist</NavLink>}
      </div>

      <form onSubmit={handleSearch} className="navbar__search">
        <span>🔍</span>
        <input
          type="text"
          placeholder="Search movies..."
          value={searchQuery}
          onChange={(e) => setSearchQuery(e.target.value)}
          id="navbar-search-input"
        />
      </form>

      <div className="navbar__actions">
        {isAuthenticated ? (
          <div className="navbar__user">
            <div className="navbar__avatar">
              {user.username?.charAt(0).toUpperCase()}
            </div>
            <span>{user.username}</span>
            <button className="btn btn-ghost btn-sm" onClick={handleLogout} id="logout-btn">
              Sign Out
            </button>
          </div>
        ) : (
          <>
            <Link to="/login" className="btn btn-ghost btn-sm" id="login-link">Sign In</Link>
            <Link to="/register" className="btn btn-primary btn-sm" id="register-link">Get Started</Link>
          </>
        )}
      </div>
    </nav>
  );
};

export default Navbar;
