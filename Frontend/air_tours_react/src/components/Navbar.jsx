import { Link } from "react-router-dom";
import { UserContext } from '../UserContext'
import { useContext } from "react"

export default function Navbar() {
    const { userContextData, setUserContextData } = useContext(UserContext);


    return (
        <div>
            <nav>
                <ul>
                    <li>
                        <Link to="/">Login</Link>
                    </li>
                    <li>
                        <Link to="/register">Register</Link>
                    </li>
                    <li>
                        <Link to="/home">Home</Link>
                    </li>
                    <li>
                        {userContextData.firstname}
                    </li>
                </ul>
            </nav>
        </div >
    )
}