import React, { useEffect, useState } from "react";
import favoriteService from "../services/favorite.service";
import MotorbikeCard from "../modules/MotorbikeCard";

export default function Favorites() {
  const [favorites, setFavorites] = useState([]);
  

  useEffect(() => {
    favoriteService.getFavorites().then(setFavorites);
  }, []);

  const removeFavorite = (motorcycleId) => {
    favoriteService.removeFavorite(motorcycleId).then(() => {
      setFavorites(prev => prev.filter(m => m.id !== motorcycleId));
    });
  };

  return (
    <div className="favorites">
      <h2>Your Favorite Motorcycles</h2>
      {favorites.length === 0 ? (
        <p>No favorites yet.</p>
      ) : (
        <ul>
          {favorites.map((bike) => (
            <div key={bike.id} className="favorite-item">
              <MotorbikeCard 
                bike={bike}  // Zeigt das vollständige Motorrad in der Karte an
                isEditable={false}  // Beispiel: keine Bearbeitungsoption in Favoriten
              />
              <button onClick={() => removeFavorite(bike.id)}>Remove</button>
            </div>
          ))}
        </ul>
      )}
    </div>
  );
}
