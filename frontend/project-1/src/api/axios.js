import axios from "axios";

/**
 * Central Axios instance.
 * Uses environment-based backend URL.
 */
const apiClient = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL || "http://localhost:8080/api",
  headers: {
    "Content-Type": "application/json",
  },
});

export default apiClient;
