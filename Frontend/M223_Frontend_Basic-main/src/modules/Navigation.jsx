import "../styles/Navigation.css"
import AuthService from "../services/auth.service"

export default function Navigation() {
  return (
    <nav>
      <ul>
        <li>
          <a href="/">Home</a>
        </li>

        <li>
          <a href="/favorites">Favorites</a>
        </li>
        {AuthService.getCurrentUser() ? (
          <>
          <li>
          <a href="/account">Account</a>
        </li>
        <li>
          <a href="Logout">Logout</a>
        </li>
          </>
        ):(
          <>
            <li>
              <a href="/login">Login</a>
            </li>
            <li>
              <a href="/signupform">Signup</a>
            </li>
          </>
        )}
      </ul>
    </nav>
  )
}