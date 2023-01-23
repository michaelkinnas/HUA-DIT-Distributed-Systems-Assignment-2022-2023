import { useEffect, useState, useContext } from "react";
import axios from "axios";
import { UserContext } from "../UserContext";



export default function OpenFlights({ flight, setFlights, setFeedback }) {
    const { userContextData, setUserContextData } = useContext(UserContext)

    function handleRegister() {
        const config = {
            headers: { Authorization: `Bearer ${userContextData.accessToken}` }
        }

        const payload = {
            "id": userContextData.id,
            "email": userContextData.email,
            "firstname": userContextData.firstname,
            "lastname": userContextData.lastname
        }

        async function callApi() {
            try {
                const response = await axios.post(`${process.env.REACT_APP_AUTHORITY_URL}${process.env.REACT_APP_ACTIVE_FLIGHT_REGISTER}${flight.id}`, payload, config)
                setFlights(response.data)
            } catch (error) {
                if (error.response) {
                    console.log('Data: ' + error.response.data);
                    console.log('Status: ' + error.response.status);
                    console.log('Headers: ' + error.response.headers);
                    if (error.response.data.message) {
                        setFeedback(error.response.data.message);
                    }
                }
            }
        }
        callApi()
    }

    function handleUnRegister() {
        const config = {
            headers: { Authorization: `Bearer ${userContextData.accessToken}` }
        }

        const payload = {
            "id": userContextData.id,
            "email": userContextData.email,
            "firstname": userContextData.firstname,
            "lastname": userContextData.lastname
        }

        async function callApi() {
            try {
                const response = await axios.post(`${process.env.REACT_APP_AUTHORITY_URL}${process.env.REACT_APP_ACTIVE_FLIGHT_UNREGISTER}${flight.id}`, payload, config)
                setFlights(response.data)
            } catch (error) {
                if (error.response) {
                    console.log('Data: ' + error.response.data);
                    console.log('Status: ' + error.response.status);
                    console.log('Headers: ' + error.response.headers);
                    if (error.response.data.message) {
                        setFeedback(error.response.data.message);
                    }
                }
            }
        }
        callApi()
    }


    return (
        <tr className="data-row">
            <td>{flight.name}</td>
            <td>{flight.tour.location}</td>
            <td>{flight.tour.duration} hours</td>
            <td>{flight.aircraft.type}</td>
            <td>{flight.pilot.firstname} {flight.pilot.lastname}</td>
            <td>{flight.users.map((user) => (
                <p key={user.id}>{user.firstname} {user.lastname}</p>
            ))}</td>
            <td>{flight.users.some(user => user.id === userContextData.id) ? <button type="button" onClick={handleUnRegister}>Unregister</button> : <button type="button" onClick={handleRegister}>Take a seat!</button>}</td>
        </tr>
    )
}