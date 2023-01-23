import { useEffect, useState, useContext } from "react";
import axios from "axios";
import TourLocationRow from "./TourLocationRow"
import AddTourForm from "./AddTourForm";
import { UserContext } from "../UserContext";
import "./TourLocations.css"


function TourLocations() {
    const { userContextData, setUserContextData } = useContext(UserContext)
    const [feedback, setFeedback] = useState('')
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
        <div className="tour-locations-table-container">
            <table className="tour-locations-table">
                <thead>
                    <tr>
                        <th>Tour name</th>
                        <th>Location</th>
                        <th>Duration</th>
                        <th>Option</th>
                    </tr>
                </thead>
                <tbody>
                    {tourLocations.map((tour) => (
                        <TourLocationRow key={tour.id} tour={tour} setTourLocations={setTourLocations} setFeedback={setFeedback} />
                    ))}
                </tbody>
            </table>
            <AddTourForm setTourLocations={setTourLocations} setFeedback={setFeedback} />
            <h4>{feedback}</h4>
        </div>

    )
}

export default TourLocations