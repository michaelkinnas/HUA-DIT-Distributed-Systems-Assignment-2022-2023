import { Routes, Route } from "react-router-dom";
import { useState } from "react";
import { UserContext } from './UserContext'
import Login from "./components/Login";
import Register from "./components/Register";
import Home from "./components/Home";
import LogedUserRoute from "./protectedRoutes/logedUserRoute";
import AdminRoute from "./protectedRoutes/adminRoute";
import AdminPanel from "./components/AdminPanel";
import NoAdminRightsPage from "./components/NoAdminRightsPage";
import PilotFunctions from "./components/PilotFunctions";
import './App.css'
// import './App_styles.css'

function App() {
  const [userContextData, setUserContextData] = useState({})

  return (
    <div className="app-container">
      <UserContext.Provider value={{ userContextData, setUserContextData }}>

        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/noadminrights" element={<NoAdminRightsPage />} />

          <Route path="/home" element={<LogedUserRoute><Home /></LogedUserRoute>} />

          <Route path="/admin" element={<AdminRoute><AdminPanel /></AdminRoute>} />
          <Route path="/pilot" element={<PilotFunctions />} />

        </Routes>
      </UserContext.Provider >
    </div>

  );
}

export default App;
