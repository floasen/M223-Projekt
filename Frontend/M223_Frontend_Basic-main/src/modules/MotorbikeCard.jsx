import "../styles/MotorbikeCard.css";

export default function MotorbikeCard({ bike, isEditable, onEdit, onDelete, onBlock, onUnblock }) {
  return (
    <div className="motorbike-card">
      <div className="left-column">
        <h3>{bike.brand}</h3>
        <p>{bike.model}</p>
      </div>
      
      <div className="right-column">
        <div className="top-info">
          <div className="info-left">
            <p><strong>Jahr:</strong> {bike.date}</p>
            <p><strong>Odo:</strong> {bike.odo} km</p>
            <p><strong>Preis:</strong> CHF {bike.price}</p>
            <p><strong>Verkäufer:</strong> {bike.sellerUsername}</p>
            <p><strong>Email:</strong> {bike.sellerEmail}</p>
          </div>
          <div className="info-right">
            <p><strong>Beschreibung:</strong> {bike.description}</p>
          </div>
        </div>

        {isEditable && (
          <div className="buttons">
            <button className="button" onClick={onEdit}>Bearbeiten</button>
            <button className="button" onClick={onDelete}>Löschen</button>
            {/* Wenn das Motorrad blockiert ist, zeige den "Entsperren"-Button */}
            {bike.isBlocked ? (
              <button className="button" onClick={onUnblock}>Entsperren</button>
            ) : (
              <button className="button" onClick={onBlock}>Sperren</button>
            )}
          </div>
        )}
      </div>
    </div>
  );
}
