import { useState } from 'react'
import reactLogo from '../assets/react.svg'
import viteLogo from '/vite.svg'

export default function Home() {
  const [motorbikes, setMotorbikes] = useState([]);

  return (
    <div>
      <h1>Motorradmarkt</h1>
      {motorbikes.map((moto) => (
        <div key={moto.id}>
          <h2>{moto.brand} – {moto.model}</h2>
          <p>{moto.year} – {moto.price} Fr</p>
        </div>
      ))}
    </div>
  );
}