import { useEffect, useState, useContext } from "react";
import axios from "axios";
import AircraftRow from "./AircraftRow";
import AddAircraftForm from "./AddAircraftForm";
import { UserContext } from "../UserContext";
import './Aircraft.css'

function Aircraft() {
    const { userContextData, setUserContextData } = useContext(UserContext)
    const [aircraft, setAircraft] = useState([])
    const [feedback, setFeedback] = useState('')


    useEffect(() => {
        const config = {
            headers: { Authorization: `Bearer ${userContextData.accessToken}` }
        }

        async function callAPI() {
            try {
                const response = await axios.get(process.env.REACT_APP_AUTHORITY_URL + process.env.REACT_APP_GET_AIRCRAFT_URL, config)
                setAircraft(response.data)
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
        callAPI()
    }, [])


    return (
        <div className="aircraft-table-container">
            <table className="aircraft-table">
                <thead>
                    <tr>
                        <th>Type</th>
                        <th>Registration</th>
                        <th>Number of seats</th>
                        <th>Option</th>
                    </tr>
                </thead>
                <tbody>
                    {aircraft.map((aircraft) => (
                        <AircraftRow key={aircraft.id} aircraft={aircraft} setAircraft={setAircraft} setFeedback={setFeedback} />
                    ))}
                </tbody>
            </table>
            <AddAircraftForm setAircraft={setAircraft} />
            <h4>{feedback}</h4>
        </div>

    )
}

export default Aircraft