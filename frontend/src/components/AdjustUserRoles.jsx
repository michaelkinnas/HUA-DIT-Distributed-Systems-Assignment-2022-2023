import React, { useContext, useState, useEffect } from "react"
import { UserContext } from "../UserContext";
import axios from 'axios';
import OpenFlights from "./OpenFlights"
import UserRoleControlsRow from "./UserRoleControlsRow"


function AdjustUserRoles() {
    const { userContextData, setUserContextData } = useContext(UserContext)
    const [users, setUsers] = useState([])


    useEffect(() => {
        // const response = axios.get(process.env.AUTHORITY_URL + process.env.ACTIVE_TOURS_URL)

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
        <div>
            <table>
                <tbody>
                    <tr>
                        <th>Name</th>
                        <th>Email</th>
                        <th>User</th>
                        <th>Pilot</th>
                        <th>Admin</th>
                        <th>Controls</th>
                    </tr>
                    {users.map((user) => (
                        <UserRoleControlsRow key={user.id} user={user} setUsers={setUsers} />
                    ))}
                </tbody>
            </table>
        </div >
    )
}


export default AdjustUserRoles;