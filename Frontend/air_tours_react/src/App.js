import { Link, Routes, Route, Navigate, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { UserContext } from './UserContext'
import { axiosPost } from "./utils/axiosPost";
import Login from "./components/Login";
import Register from "./components/Register";
import Index from "./components/Home";
import PrivateRoute from "./privateRoute/privateRoute";

//TODO CONDITIONAL ROUTING ACCORDING TO IF THE USER IS LOGEDIN OR NOT
function App() {
  const [userContextData, setUserContextData] = useState({})
  const [isUserLogedIn, setIsUserLogedIn] = useState(false)

  return (
    <>
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

      <UserContext.Provider value={{ userContextData, setUserContextData }}>
        <Routes>
          <Route path="/" element={<Login toggleUserLogedIn={setIsUserLogedIn} userLogedInStatus={isUserLogedIn} />} />
          <Route path="/register" element={<Register />} />

          <Route path="/home" element={
            <PrivateRoute>
              <Index />
            </PrivateRoute>
          } />

        </Routes>
      </UserContext.Provider >
    </>

  );
}

export default App;
