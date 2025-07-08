import { useState, useEffect } from "react";
import api from "../services/api";

export default function AddMotorbikeForm({ onAdded }) {
  const [form, setForm] = useState({
    brand: "",
    model: "",
    date: "",
    odo: "",
    price: "",
    sellerEmail: "",
    sellerUsername: "",
    description: "",
  });

  const handleChange = (e) =>
    setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = (e) => {
    e.preventDefault();
    api.post("/motorcycles", form)
      .then(() => {
        onAdded();
        setForm({
          brand: "",
          model: "",
          date: "",
          odo: "",
          price: "",
          sellerEmail: "", 
          sellerUsername: "",
          description: "",
        });
      })
      .catch((err) => {
        console.error("Fehler beim Erstellen:", err);
      });
  };

  return (
    <form onSubmit={handleSubmit} className="add-motorbike-form">
      <h3>Neues Motorrad hinzufügen</h3>
      <input name="brand" placeholder="Marke" value={form.brand} onChange={handleChange} required />
      <input name="model" placeholder="Modell" value={form.model} onChange={handleChange} required />
      <input name="date" placeholder="Jahr" value={form.date} onChange={handleChange} required />
      <input name="odo" placeholder="Kilometerstand" value={form.odo} onChange={handleChange} required />
      <input name="price" placeholder="Preis" value={form.price} onChange={handleChange} required />
      <input name="sellerEmail" placeholder="Email" value={form.sellerEmail} onChange={handleChange} required />
      <textarea name="description" placeholder="Beschreibung" value={form.description} onChange={handleChange} />
      <button type="submit">Hinzufügen</button>
    </form>
  );
}
