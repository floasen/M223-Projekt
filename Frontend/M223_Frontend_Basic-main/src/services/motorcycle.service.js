import api from "./api";

const getMyMotorcycleById = (id) => api.get(`/api/motorcycles/mine`).then(res => res.data.find(m => m.id === parseInt(id)));
const updateMotorcycle = (id, data) => api.put(`/api/motorcycles/${id}`, data);
const createMotorcycle = (data) => api.post(`/api/motorcycles`, data);
const blockMotorcycle = (id) => api.patch(`/api/motorcycles/${id}/block`);
const unblockMotorcycle = (id) => api.patch(`/api/motorcycles/${id}/unblock`);

export default {
  getMyMotorcycleById,
  updateMotorcycle,
  createMotorcycle,
  blockMotorcycle,
  unblockMotorcycle
};
