import { useEffect, useState, useContext } from "react";
import axios from "axios";
import TourLocationRow from "./TourLocationRow"
import AddTourForm from "./AddTourForm";
import { UserContext } from "../UserContext";

function TourLocations() {
    const { userContextData, setUserContextData } = useContext(UserContext)
    const [tourLocations, setTourLocations] = useState([])


    useEffect(() => {
        const config = {
            headers: { Authorization: `Bearer ${userContextData.accessToken}` }
        }

        async function callAPI() {
            try {
                const response = await axios.get(process.env.REACT_APP_AUTHORITY_URL + process.env.REACT_APP_GET_TOURS_URL, config)
                setTourLocations(response.data)

            } catch (error) {
                console.log(error.response.data.message)
            }
        }
        callAPI()
    }, [])


    return (
        <div>
            <AddTourForm setTourLocations={setTourLocations} />
            <table>
                <tbody>
                    <tr>
                        <th>Tour name</th>
                        <th>Location</th>
                        <th>Duration</th>
                        <th>Option</th>
                    </tr>
                    {tourLocations.map((tour) => (
                        <TourLocationRow key={tour.id} tour={tour} setTourLocations={setTourLocations} />
                    ))}
                </tbody>
            </table>
        </div>

    )
}

export default TourLocations