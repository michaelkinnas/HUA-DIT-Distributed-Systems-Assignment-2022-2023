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

                // let selectedRoles = []
                // for (let i = 0; i < users.length; i++) {
                //     selectedRoles[i] = {
                //         userId: users[i].id,
                //         selectedRoleId: "0"
                //     }
                // }
                // setSelectedRole(selectedRoles)

            } catch (error) {
                console.log(error.response.data.message) //how to get body from axios error (really?)
            }
        }
        callAPI()
    }, [])

    //update user in users array state with updated user passed up from child
    const updateUser = (updatedUser) => {
        setUsers(
            users.map((user) => {
                return user.id === updatedUser.id ? updatedUser : user
            })
        )
    }

    return (
        <div>
            <table>
                <tbody>
                    <tr>
                        <th>First name</th>
                        <th>Last name</th>
                        <th>Email</th>
                        <th>User</th>
                        <th>Pilot</th>
                        <th>Admin</th>
                        <th>Controls</th>
                    </tr>
                    {users.map((user) => (
                        // <tr key={user.id}>
                        //     <td>{user.firstname}</td>
                        //     <td>{user.lastname}</td>
                        //     <td>{user.email}</td>
                        //     <td>{user.roles.some(e => e.id === 3) ? <p>YES</p> : <p>NO</p>}</td>
                        //     <td>{user.roles.some(e => e.id === 2) ? <p>YES</p> : <p>NO</p>}</td>
                        //     <td>{user.roles.some(e => e.id === 1) ? <p>YES</p> : <p>NO</p>}</td>
                        //     <td>
                        //         <select name="roles" id="roles" defaultValue="0">
                        //             <option value="0"> -- select an option -- </option>
                        //             <option value="3">Admin</option>
                        //             <option value="2">Pilot</option>
                        //             <option value="1">User</option>
                        //         </select>
                        //         <input type="button" value="Add" onClick={handleAddRole} />
                        //         {/* <input type="button" value="Remove" onClick={handleRemoveRole} /> */}
                        //     </td>

                        // </tr>
                        <UserRoleControlsRow key={user.id} user={user} updateUser={updateUser} />
                    ))}
                </tbody>
            </table>
        </div >
    )
}


export default AdjustUserRoles;