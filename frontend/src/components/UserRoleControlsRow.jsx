import React, { useContext, useState } from "react"
import { UserContext } from "../UserContext";
import axios from 'axios';

function UserRoleControlsRow({ user, setUsers }) {
    const { userContextData, setUserContextData } = useContext(UserContext)
    const [selectedRole, setSelectedRole] = useState("0")

    function handleAddRole() {

        const config = {
            headers: { Authorization: `Bearer ${userContextData.accessToken}` }
        }

        if (selectedRole === 0) {
            window.alert('You must select a valid role')
        } else {
            async function callAPI() {

                try {
                    const response = await axios.post(`${process.env.REACT_APP_AUTHORITY_URL}${process.env.REACT_APP_ADD_ROLE_URL}${user.id}`, { "id": selectedRole }, config)
                    setUsers(response.data)
                } catch (error) {
                    console.log(error.response.data.message)
                }
            }
            callAPI()
        }
    }

    function handleRemoveRole() {

        const config = {
            headers: { Authorization: `Bearer ${userContextData.accessToken}` }
        }

        if (selectedRole === 0) {
            window.alert('You must select a valid role')
        } else {
            async function callAPI() {

                try {
                    const response = await axios.post(`${process.env.REACT_APP_AUTHORITY_URL}${process.env.REACT_APP_REMOVE_ROLE_URL}${user.id}`, { "id": selectedRole }, config)
                    setUsers(response.data)
                } catch (error) {
                    console.log(error.response.data.message)
                }
            }
            callAPI()
        }
    }

    return (
        <tr key={user.id}>
            <td>{user.firstname}</td>
            <td>{user.lastname}</td>
            <td>{user.email}</td>
            <td>{user.roles.some(e => e.id === 3) ? <p>YES</p> : <p>NO</p>}</td>
            <td>{user.roles.some(e => e.id === 2) ? <p>YES</p> : <p>NO</p>}</td>
            <td>{user.roles.some(e => e.id === 1) ? <p>YES</p> : <p>NO</p>}</td>
            <td>
                <select name="roles" id="roles" select={selectedRole} onChange={(e) => setSelectedRole(e.target.value)}>
                    <option value="0"> -- select an option -- </option>
                    <option value="1">Admin</option>
                    <option value="2">Pilot</option>
                    <option value="3">User</option>
                </select>
                <input type="button" value="Add" onClick={handleAddRole} />
                <input type="button" value="Remove" onClick={handleRemoveRole} />
            </td>
        </tr>
    )
}

export default UserRoleControlsRow