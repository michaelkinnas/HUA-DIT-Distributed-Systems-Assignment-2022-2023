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
            const config = {
                headers: { Authorization: `Bearer ${userContextData.accessToken}` }
            }

            try {
                const response = await axios.get(`${process.env.REACT_APP_AUTHORITY_URL}${process.env.REACT_APP_ACTIVE_FLIGHTS}`, config)
                setFlights(response.data)
            } catch (error) {
                console.log(error.response.data.message)
            }
        }
        callApi()
    }, [])


    return (
        <div className="open-flights-container">

            <h2>Take a seat and fly!</h2>

            <table className="open-flights-table">
                <tbody>
                    <tr>
                        <th>Name</th>
                        <th>Location</th>
                        <th>Duration</th>
                        <th>Aircraft</th>
                        <th>Pilot</th>
                        <th>Seats</th>
                        <th>Options</th>
                    </tr>

                    {flights.data.map((flight) => (
                        <OpenFlightRow key={flight.id} flight={flight} />
                    ))}
                </tbody>
            </table>
        </div>
    )
}