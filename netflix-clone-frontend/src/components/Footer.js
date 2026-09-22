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
      <span>© 2026 NetClone — Educational Portfolio Project</span>
      <span>Spring Boot 4 · React 19 · JWT Auth</span>
    </div>
    <div className="footer__disclaimer" style={{ fontSize: '0.7rem', opacity: 0.6, textAlign: 'center', padding: '8px 0' }}>
      This is an independent educational/portfolio project built for learning purposes.
      It is not affiliated with, endorsed by, or connected to Netflix, Inc. in any way.
    </div>
  </footer>
);

export default Footer;
