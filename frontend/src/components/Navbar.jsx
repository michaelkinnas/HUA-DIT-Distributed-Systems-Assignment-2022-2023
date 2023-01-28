import { Link } from "react-router-dom";
import { UserContext } from '../UserContext'
import { useContext } from "react"
import './Navbar.css'
import logo from '../graphics/logo.png'

export default function Navbar() {
    const { userContextData, setUserContextData } = useContext(UserContext);

    const logout = () => {
        setUserContextData({});
    }

    return (
        <nav className="navbar">
            <div className="left-logo">
                <Link to="/home"><img src={logo} className="logo-png" /></Link><Link to="/home" className="home-link">Home</Link>
            </div>
            <div className="center-items">
                {(userContextData.roles && userContextData.roles.includes("ROLE_ADMIN")) && <Link to="/admin" className="navbar-button">Admin</Link>}
                {(userContextData.roles && userContextData.roles.includes("ROLE_PILOT")) && <Link to="/pilot" className="navbar-button">Pilot</Link>}
            </div>
            <div className="user-name">
                {userContextData.firstName} {userContextData.lastName}, {userContextData.accessToken ? <Link to="/" onClick={logout} className="logout-link">Logout</Link> : <Link to="/" >Login</Link>}
            </div>
        </nav >
    )
}