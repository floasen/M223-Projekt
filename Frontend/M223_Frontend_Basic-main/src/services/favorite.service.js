import api from "./api";

const getFavorites = () => api.get("/favorites").then(res => res.data);
const addFavorite = (motorcycleId) => api.post(`/favorites/${motorcycleId}`);
const removeFavorite = (motorcycleId) => api.delete(`/favorites/${motorcycleId}`);

export default {
  getFavorites,
  addFavorite,
  removeFavorite,
};
