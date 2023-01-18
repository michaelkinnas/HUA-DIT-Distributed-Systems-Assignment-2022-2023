import { useEffect, useState, useContext } from "react";
import axios from "axios";
import AircraftRow from "./AircraftRow";
import AddAircraftForm from "./AddAircraftForm";
import { UserContext } from "../UserContext";

function Aircraft() {
    const { userContextData, setUserContextData } = useContext(UserContext)
    const [aircraft, setAircraft] = useState([])


    useEffect(() => {
        const config = {
            headers: { Authorization: `Bearer ${userContextData.accessToken}` }
        }

        async function callAPI() {
            try {
                const response = await axios.get(process.env.REACT_APP_AUTHORITY_URL + process.env.REACT_APP_GET_AIRCRAFT_URL, config)
                setAircraft(response.data)
            } catch (error) {
                console.log(error.response.data.message)
            }
        }
        callAPI()
    }, [])


    return (
        <div>
            <AddAircraftForm setAircraft={setAircraft} />
            <table>
                <tbody>
                    <tr>
                        <th>Type</th>
                        <th>Registration</th>
                        <th>Number of seats</th>
                        <th>Option</th>
                    </tr>
                    {aircraft.map((aircraft) => (
                        <AircraftRow key={aircraft.id} aircraft={aircraft} setAircraft={setAircraft} />
                    ))}
                </tbody>
            </table>
        </div>

    )
}

export default Aircraft