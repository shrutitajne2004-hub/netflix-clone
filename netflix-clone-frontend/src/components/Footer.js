import React from 'react';
import { Link } from 'react-router-dom';

const API_BASE_URL = process.env.REACT_APP_API_BASE_URL || 'http://localhost:8080';

const Footer = () => (
  <footer className="footer">
    <div className="footer__top">
      <div className="footer__logo">NETCLONE</div>
      <div className="footer__links">
        <Link to="/">Home</Link>
        <Link to="/watchlist">Watchlist</Link>
        <a href={`${API_BASE_URL}/swagger-ui.html`} target="_blank" rel="noreferrer">API Docs</a>
      </div>
    </div>
    <div className="footer__bottom">
      <span>© 2026 Netflix Clone — Phase 1 Resume Project</span>
      <span>Spring Boot 4 · React 19 · JWT Auth</span>
    </div>
  </footer>
);

export default Footer;
