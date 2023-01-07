import { Link } from "react-router-dom";
import { UserContext } from '../UserContext'
import { useContext } from "react"
import './Navbar.css'

export default function Navbar() {
    const { userContextData, setUserContextData } = useContext(UserContext);

    const logout = () => {
        setUserContextData({});
    }

    return (
        <nav className="navbar">
            <Link to="/home">Home</Link>
            <div>
                {!userContextData.accessToken && <Link to="/register">Register</Link>}
                {userContextData.roles.includes("ROLE_ADMIN") && <Link to="/admin">Admin</Link>}
                {userContextData.roles.includes("ROLE_PILOT") && <Link to="/pilot">Pilot</Link>}
            </div>
            <div className="user-name">
                {userContextData.firstname} {userContextData.lastname} {userContextData.accessToken ? <Link to="/" onClick={logout}>Logout</Link> : <Link to="/" >Login</Link>}
            </div>
        </nav>
    )
}