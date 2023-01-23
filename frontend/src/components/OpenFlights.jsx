import { useEffect, useState, useContext } from "react";
import axios from "axios";
import { UserContext } from "../UserContext";
import OpenFlightRow from './OpenFlightRow';
import './OpenFlights.css'

export default function OpenFlights() {
    const { userContextData, setUserContextData } = useContext(UserContext)
    const [flights, setFlights] = useState([])

    useEffect(() => {
        const config = {
            headers: { Authorization: `Bearer ${userContextData.accessToken}` }
        }

        async function callApi() {
            try {
                const response = await axios.get(`${process.env.REACT_APP_AUTHORITY_URL}${process.env.REACT_APP_ACTIVE_FLIGHTS}`, config)
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
    }, [])


    return (
        <div className="open-flights-container">
            <h2>Take a seat and fly!</h2>
            <table className="open-flights-table">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Location</th>
                        <th>Duration</th>
                        <th>Aircraft</th>
                        <th>Pilot</th>
                        <th>Seats</th>
                        <th>Options</th>
                    </tr>
                </thead>
                <tbody>
                    {flights.data.map((flight) => (
                        <OpenFlightRow key={flight.id} flight={flight} />
                    ))}
                </tbody>
            </table>
        </div>
    )
}