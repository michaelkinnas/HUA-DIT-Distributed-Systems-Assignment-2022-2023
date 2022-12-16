import React, { useContext } from "react";
import { Navigate } from "react-router-dom";
import { UserContext } from "../UserContext";

const LogedUserRoute = ({ children }) => {
    const { userContextData, setUserContextData } = useContext(UserContext);
    return userContextData.accessToken ? children : <Navigate to="/" />;
}

export default LogedUserRoute