import { Link, Routes, Route } from "react-router-dom";
import { useEffect, useState } from "react";
import { UserContext } from './UserContext'
import './App.css'
import Login from "./components/Login";
import Register from "./components/Register";
import Home from "./components/Home";
import LogedUserRoute from "./protectedRoutes/logedUserRoute";
import Navbar from "./components/Navbar";
import AdminRoute from "./protectedRoutes/adminRoute";
import AdminPanel from "./components/AdminPanel";
import NoAdminRightsPage from "./components/NoAdminRightsPage";

function App() {
  const [userContextData, setUserContextData] = useState({})

  return (
    <>
      <UserContext.Provider value={{ userContextData, setUserContextData }}>
        {/* <Navbar /> */}

        <Routes>
          {/* <Route path="/" element={<Login toggleUserLogedIn={setIsUserLogedIn} userLogedInStatus={isUserLogedIn} />} /> */}
          <Route path="/" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/noadminrights" element={<NoAdminRightsPage />} />

          <Route path="/home" element={<LogedUserRoute><Home /></LogedUserRoute>} />

          <Route path="/admin" element={<AdminRoute><AdminPanel /></AdminRoute>} />

        </Routes>
      </UserContext.Provider >
    </>

  );
}

export default App;
