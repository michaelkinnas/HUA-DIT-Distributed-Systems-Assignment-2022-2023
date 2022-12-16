import { Link, Routes, Route } from "react-router-dom";
import { useEffect, useState } from "react";
import { UserContext } from './UserContext'
import Login from "./components/Login";
import Register from "./components/Register";
import Home from "./components/Home";
import LogedUserRoute from "./protectedRoutes/logedUserRoute";
import Navbar from "./components/Navbar";
import AdminRoute from "./protectedRoutes/adminRoute";
import AdminPanel from "./components/AdminPanel";

function App() {
  const [userContextData, setUserContextData] = useState({})
  const [isUserLogedIn, setIsUserLogedIn] = useState(false)

  return (
    <>
      <UserContext.Provider value={{ userContextData, setUserContextData }}>
        <Navbar />
        <Routes>
          <Route path="/" element={<Login toggleUserLogedIn={setIsUserLogedIn} userLogedInStatus={isUserLogedIn} />} />
          <Route path="/register" element={<Register />} />

          <Route path="/home" element={
            <LogedUserRoute>
              <Home /> {/* children of <ProtectedRoute> component */}
            </LogedUserRoute>
          } />

          <Route path="/admin" element={
            <AdminRoute>
              {/* TODO CREATE ADMIN PANEL COMPONENT */}
              <AdminPanel />
            </AdminRoute>
          } />

        </Routes>
      </UserContext.Provider >
    </>

  );
}

export default App;
