import React, { useContext } from "react";
import { Navigate } from "react-router-dom";
import { UserContext } from "../UserContext";


const AdminRoute = ({ children }) => {
    const { userContextData, setUserContextData } = useContext(UserContext);

    //TODO Check if json field roles contains an ADMIN role (second condition)
    return ((userContextData.accessToken) && (userContextData.roles.includes('ROLE_ADMIN'))) ? children : <Navigate to="/noadminrights" />;
}

export default AdminRoute