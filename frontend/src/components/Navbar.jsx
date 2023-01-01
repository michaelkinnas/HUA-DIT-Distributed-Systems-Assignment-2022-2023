import { Link } from "react-router-dom";
import { UserContext } from '../UserContext'
import { useContext } from "react"

export default function Navbar() {
    const { userContextData, setUserContextData } = useContext(UserContext);

    const logout = () => {
        setUserContextData({});
    }

    return (
        <div>
            <nav>
                <ul>
                    <li>
                        {userContextData.accessToken ? <Link to="/" onClick={logout}>Logout</Link> : <Link to="/" >Login</Link>}
                    </li>
                    <li>
                        <Link to="/register">Register</Link>
                    </li>
                    <li>
                        <Link to="/home">Home</Link>
                    </li>
                </ul>
                <div className="user-name">
                    {userContextData.firstname} {userContextData.lastname}
                </div>
            </nav>
        </div >
    )
}