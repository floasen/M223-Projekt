import { useState } from "react";
import api from "../services/api";

export default function EditMotorbikeForm({ bike, onCancel, onSaved }) {
  const [form, setForm] = useState({ ...bike });

  const handleChange = (e) =>
    setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = (e) => {
    e.preventDefault();
    api.put(`/motorcycles/${bike.id}`, form)
      .then(() => onSaved())
      .catch((err) => {
        console.error("Fehler beim Bearbeiten:", err);
      });
  };

  return (
    <form onSubmit={handleSubmit} className="edit-motorbike-form">
      <h3>Motorrad bearbeiten</h3>
      <input name="brand" value={form.brand} onChange={handleChange} />
      <input name="model" value={form.model} onChange={handleChange} />
      <input name="date" value={form.date} onChange={handleChange} />
      <input name="odo" value={form.odo} onChange={handleChange} />
      <input name="price" value={form.price} onChange={handleChange} />
      <textarea name="description" value={form.description} onChange={handleChange} />
      <input name="sellerEmail" value={form.sellerEmail} onChange={handleChange} required/>
      <button type="submit">Speichern</button>
      <button type="button" onClick={onCancel}>Abbrechen</button>
    </form>
  );
}
