import { useState, useEffect } from 'react'
import api from "../services/api";
import MotorbikeCard from "../modules/MotorbikeCard";
import favoriteService from "../services/favorite.service";

export default function Home() {
  const [bikes, setBikes] = useState([]);

  useEffect(() => {
    api.get("/motorcycles/public")
      .then(res => setBikes(res.data))
      .catch(err => console.error("Fehler beim Laden:", err));
  }, []);


  const addToFavorites = (motorcycleId) => {
    favoriteService.addFavorite(motorcycleId)
      .then(() => {
        alert("Motorrad zu Favoriten hinzugefügt!");
      })
      .catch(err => {
        console.error("Fehler beim Hinzufügen zu Favoriten:", err);
        alert("Fehler beim Hinzufügen zu Favoriten.");
      });
  };

  return (
    <div>
      <h2>Motorradmarkt</h2>
      {bikes.length === 0 ? (
        <p>Keine Motorräder verfügbar.</p>
      ) : (
        bikes.map(bike => (
          <div key={bike.id}>
            <MotorbikeCard bike={bike} />
            <button onClick={() => addToFavorites(bike.id)}>Zu Favoriten hinzufügen</button>
          </div>
        ))
      )}
    </div>
  );
}