import axios from 'axios';

const API_BASE_URL = process.env.REACT_APP_API_BASE_URL || 'http://localhost:8080';

const axiosInstance = axios.create({
  baseURL: `${API_BASE_URL}/api`,
  headers: {
    'Content-Type': 'application/json',
  },
  timeout: 10000,
});

// Request interceptor — attach JWT automatically
axiosInstance.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('jwt_token');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

// Response interceptor — handle 401 globally
axiosInstance.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.status === 401) {
      localStorage.removeItem('jwt_token');
      localStorage.removeItem('user');
      window.location.href = '/login';
    }
    return Promise.reject(error);
  }
);

// ── Auth ──────────────────────────────────────────
export const authAPI = {
  register: (data) => axiosInstance.post('/auth/register', data),
  login: (data) => axiosInstance.post('/auth/login', data),
};

// ── Movies ────────────────────────────────────────
export const moviesAPI = {
  getAll: (params) => axiosInstance.get('/movies', { params }),
  getById: (id) => axiosInstance.get(`/movies/${id}`),
  getSimilar: (id) => axiosInstance.get(`/movies/${id}/similar`),
  search: (params) => axiosInstance.get('/movies', { params }),
  create: (data) => axiosInstance.post('/movies', data),
  update: (id, data) => axiosInstance.put(`/movies/${id}`, data),
  delete: (id) => axiosInstance.delete(`/movies/${id}`),
};

// ── Watchlist ─────────────────────────────────────
export const watchlistAPI = {
  getAll: () => axiosInstance.get('/watchlist'),
  add: (movieId) => axiosInstance.post(`/watchlist/${movieId}`),
  remove: (movieId) => axiosInstance.delete(`/watchlist/${movieId}`),
};

// ── Reviews ───────────────────────────────────────
export const reviewsAPI = {
  getByMovie: (movieId) => axiosInstance.get(`/movies/${movieId}/reviews`),
  add: (movieId, data) => axiosInstance.post(`/movies/${movieId}/reviews`, data),
};

export default axiosInstance;
