import { Link, Routes, Route, Navigate } from "react-router-dom";
import { useState } from "react";
import { UserContext } from './UserContext'
import { axiosPost } from "./utils/axiosPost";
import Login from "./components/Login";
import Register from "./components/Register";
import Index from "./components/Index";


//TODO CONDITIONAL ROUTING ACCORDING TO IF THE USER IS LOGEDIN OR NOT


//TEMP STUFF------------------------

const email = 'gougou@gmail.com'
const password = 'bruh210'
const firstname = 'Mike'
const lastname = 'Kinnas'

const REGISTERURL = 'http://localhost:8080/authentication/register'
const LOGINURL = 'http://localhost:8080/authentication/login'

const payload = {
  "email": `${email}`,
  "password": `${password}`,
  "firstname": `${firstname}`,
  "lastname": `${lastname}`
}
//----------------------------------------

function App() {
  const [userContextData, setUserContextData] = useState({})

  return (
    <div>
      <nav>
        <ul>
          <li>
            <Link to="/login">Login</Link>
          </li>
          <li>
            <Link to="/register">Register</Link>
          </li>
          <li>
            <Link to="/index">Index</Link>
          </li>
          <li>
            {userContextData.firstname}
          </li>

        </ul>
      </nav>

      <UserContext.Provider value={{ userContextData, setUserContextData }}>
        <Routes>
          <Route exact path="/" element={userContextData != null ? <Index /> : <Navigate replace to={"/login"} />} />

          <Route path="/login" element={<Login />} />
          <Route path="/index" element={<Index />} />
          <Route path="/register" element={<Register />} />
        </Routes>
      </UserContext.Provider >
    </div >

  );
}

export default App;
