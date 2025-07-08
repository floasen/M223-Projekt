import { useState } from "react";
import AuthService from '../services/auth.service';

export default function SignupForm() {
  const [form, setForm] = useState({ username: "", password: "", email: "" });

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await AuthService.signup(form.username, form.email, form.password);
      alert("Signup erfolgreich!");
    } catch (error) {
      alert("Signup fehlgeschlagen. Bitte versuche es erneut.");
      console.error(error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
        <div>
      <input placeholder="Benutzername" onChange={(e) => setForm({ ...form, username: e.target.value })} />
      </div>
      <div>    
      <input placeholder="E-Mail" onChange={(e) => setForm({ ...form, email: e.target.value })} />
      </div>
      <div>
      <input placeholder="Passwort" type="password" onChange={(e) => setForm({ ...form, password: e.target.value })} />
      </div>
      <div>
      <button type="submit">Registrieren</button>
      </div>
    </form>
  );
}
