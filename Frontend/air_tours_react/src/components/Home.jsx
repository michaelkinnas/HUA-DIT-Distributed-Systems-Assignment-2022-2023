import React, { useContext, useState } from "react"
import { UserContext } from "../UserContext";
import { axiosPost } from "../utils/axiosPost"

function Index() {
    const { userContextData, setUserContextData } = useContext(UserContext)




    return (
        <div>
            {userContextData.accessToken ? <h1> User sucessfully loged in !!!</h1> : <h1>User not logged in</h1>}
            {/* <h1>User sucessfully loged in !!!</h1> */}
            <h2>{userContextData.email}</h2>
            <h2>{userContextData.firstname}</h2>
            <h2>{userContextData.lastname}</h2>
            <h2>{userContextData.accessToken}</h2>
            <h2>{userContextData.tokenType}</h2>
            <h2>{userContextData.roles}</h2>
        </div >
    )
}


export default Index;