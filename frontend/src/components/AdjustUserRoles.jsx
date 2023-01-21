import React, { useContext, useState, useEffect } from "react"
import { UserContext } from "../UserContext";
import axios from 'axios';
import UserRoleControlsRow from "./UserRoleControlsRow"
import "./AdjustUserRoles.css"


function AdjustUserRoles() {
    const { userContextData, setUserContextData } = useContext(UserContext)
    const [users, setUsers] = useState([])

    useEffect(() => {
        const config = {
            headers: { Authorization: `Bearer ${userContextData.accessToken}` }
        }

        async function callAPI() {
            try {
                const response = await axios.get(process.env.REACT_APP_AUTHORITY_URL + process.env.REACT_APP_GET_USERS_URL, config)
                setUsers(response.data)
            } catch (error) {
                console.log(error.response.data.message) //how to get body from axios error (really?)
            }
        }
        callAPI()
    }, [])

    return (
        <div className="admin-userRoles-table-container">
            <table className="admin-userRoles-table">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Email</th>
                        <th>User</th>
                        <th>Pilot</th>
                        <th>Admin</th>
                        <th>Controls</th>
                    </tr>
                </thead>
                <tbody>
                    {users.map((user) => (
                        <UserRoleControlsRow key={user.id} user={user} setUsers={setUsers} />
                    ))}
                </tbody>
            </table>
        </div >
    )
}


export default AdjustUserRoles;