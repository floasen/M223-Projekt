import Layout from './modules/Layout'
import Home from './pages/Home'
import Login from './pages/Login'
import Signup from './pages/Signup'
import NoPage from './pages/NoPage'
import { Routes } from 'react-router-dom'
import { Route } from 'react-router-dom'
import './App.css'

function App() {
  return (
    <Routes>
      <Route path="/" element={<Layout />}>
        <Route index element={<Home />} />
        <Route path="login" element={<Login />} />
        <Route path="signupform" element={<Signup />} />  
        <Route path="*" element={<NoPage />} />
      </Route>
    </Routes>
  )

}

export default App
