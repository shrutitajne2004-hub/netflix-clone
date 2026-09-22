import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { AuthProvider } from './context/AuthContext';

import Navbar from './components/Navbar';
import Home from './pages/Home';
import Login from './pages/Login';
import Register from './pages/Register';
import MovieDetails from './pages/MovieDetails';
import Watchlist from './pages/Watchlist';
import SearchResults from './pages/SearchResults';

import './index.css';

function App() {
  return (
    <AuthProvider>
      <Router>
        <Navbar />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/movie/:id" element={<MovieDetails />} />
          <Route path="/watchlist" element={<Watchlist />} />
          <Route path="/search" element={<SearchResults />} />
          <Route path="*" element={
            <div className="page-wrapper">
              <div className="empty-state">
                <div className="empty-state__icon">🎬</div>
                <div className="empty-state__title">Page not found</div>
                <div className="empty-state__desc">The page you're looking for doesn't exist.</div>
              </div>
            </div>
          } />
        </Routes>
      </Router>
    </AuthProvider>
  );
}

export default App;
