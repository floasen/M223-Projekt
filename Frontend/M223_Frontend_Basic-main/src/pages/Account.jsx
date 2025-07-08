import { useEffect, useState } from "react";
import AuthService from "../services/auth.service";
import api from "../services/api";
import AddMotorbikeForm from "../modules/AddMotorbikeForm";
import EditMotorbikeForm from "../modules/EditMotorbikeForm";
import MotorbikeCard from "../modules/MotorbikeCard";


export default function Account() {
  const [motorcycles, setMotorcycles] = useState([]);
  const [editId, setEditId] = useState(null);

  const user = AuthService.getCurrentUser();

  // Eigene Motorräder laden
  const loadMyMotorcycles = () => {
    api.get("/motorcycles/mine")
      .then((res) => setMotorcycles(res.data))
      .catch((err) => console.error("Fehler beim Laden der Motorräder", err));
  };

  useEffect(() => {
    loadMyMotorcycles();
  }, []);

  // Motorrad löschen
  const handleDelete = (id) => {
    api.delete("/motorcycles/" + id)
      .then(() => {
        loadMyMotorcycles();
        alert("Motorrad gelöscht.");
      })
      .catch((err) => {
        console.error(err);
        alert("Fehler beim Löschen.");
      });
  };

const handleBlock = (id) => {
    api.patch("/motorcycles/" + id + "/block")
      .then(() => {
        setMotorcycles((prevMotorcycles) =>
          prevMotorcycles.map((bike) =>
            bike.id === id ? { ...bike, isBlocked: true } : bike
          )
        );
      })
      .catch((err) => {
        console.error(err);
        alert("Fehler beim Sperren des Motorrads.");
      });
  };

  // Entsperren eines Motorrads
  const handleUnblock = (id) => {
    api.patch("/motorcycles/" + id + "/unblock")
      .then(() => {
        setMotorcycles((prevMotorcycles) =>
          prevMotorcycles.map((bike) =>
            bike.id === id ? { ...bike, isBlocked: false } : bike
          )
        );
      })
      .catch((err) => {
        console.error(err);
        alert("Fehler beim Entsperren des Motorrads.");
      });
  };

  return (
    <div className="account-page">
      <h2>Hallo {user?.username}, deine Motorräder:</h2>

      {/* Motorrad hinzufügen */}
      <AddMotorbikeForm onAdded={loadMyMotorcycles} />

      {/* Motorrad-Liste */}
      <div className="motorbike-list">
        {motorcycles.map((bike) =>
          editId === bike.id ? (
            <EditMotorbikeForm
              key={bike.id}
              bike={bike}
              onCancel={() => setEditId(null)}
              onSaved={() => {
                setEditId(null);
                loadMyMotorcycles();
              }}
            />
          ) : (
            <MotorbikeCard
              key={bike.id}
              bike={bike}
              isEditable
              onEdit={() => setEditId(bike.id)}
              onDelete={() => handleDelete(bike.id)}
              onBlock={() => handleBlock(bike.id)}
              onUnblock={() => handleUnblock(bike.id)}
            />
          )
        )}
      </div>
    </div>
  );
}
