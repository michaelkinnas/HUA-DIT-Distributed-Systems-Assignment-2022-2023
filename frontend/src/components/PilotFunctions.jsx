import { useEffect, useState, useContext } from "react";
import { UserContext } from "../UserContext";
import axios from "axios";
import Navbar from "./Navbar";
import PilotCurrentFlight from "./PilotCurrentFlight";
import './PilotFunctions.css';

export default function PilotFunctions() {
    const { userContextData, setUserContextData } = useContext(UserContext)
    const [pilotSelectionForm, setPilotSelectionForm] = useState({
        tours: [],
        aircraft: []
    })
    const [selectedTour, setSelectedTour] = useState('')
    const [selectedAircraft, setSelectedAircraft] = useState('')
    const [flightName, setFlightName] = useState('')
    const [feedback, setFeedback] = useState('')

    useEffect(() => {
        const config = {
            headers: { Authorization: `Bearer ${userContextData.accessToken}` }
        }

        async function callAPI() {
            try {
                const tourResponse = await axios.get(process.env.REACT_APP_AUTHORITY_URL + process.env.REACT_APP_GET_TOURS_URL_PILOT, config)
                const aircraftResponse = await axios.get(process.env.REACT_APP_AUTHORITY_URL + process.env.REACT_APP_GET_AIRCRAFT_URL_PILOT, config)

                const data = {
                    tours: tourResponse.data,
                    aircraft: aircraftResponse.data
                }

                setPilotSelectionForm(data)

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

    function handleCreateFlight() {
        async function callAPI() {
            try {
                const payload = {
                    name: flightName,
                    open: true,
                    pilot: {
                        id: userContextData.id
                    },
                    tour: {
                        id: selectedTour
                    },
                    aircraft: {
                        id: selectedAircraft
                    }
                }

                const config = {
                    headers: { Authorization: `Bearer ${userContextData.accessToken}` }
                }

                const response = await axios.post(`${process.env.REACT_APP_AUTHORITY_URL}${process.env.REACT_APP_PILOT_CREATE_FLIGHT_URL}${userContextData.id}`, payload, config)

                if (response.status === 200) {
                    setFeedback('Flight created sucessfully');
                }

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
    }

    return (
        <div className="pilot-page">
            <Navbar />
            <div className="pilot-panel-container">
                <h4>Create a flight</h4>
                <form>
                    <label htmlFor="tours">Tour</label>
                    <select name="tours" id="tours" value={selectedTour} onChange={(e) => setSelectedTour(e.target.value)}>
                        {pilotSelectionForm.tours.map((tour) => (
                            <option key={tour.id} value={tour.id}>{tour.description} - {tour.duration}hrs</option>
                        ))}
                    </select>

                    <label htmlFor="aircraft">Aircraft</label>
                    <select name="aircraft" id="aircraft" value={selectedAircraft} onChange={(e) => setSelectedAircraft(e.target.value)}>
                        {pilotSelectionForm.aircraft.map((aircraft) => (
                            <option key={aircraft.id} value={aircraft.id}>{aircraft.registration} - {aircraft.type}</option>
                        ))}
                    </select>
                    <label htmlFor="flightname">Name</label>
                    <input type="text" name="flightName" id="flightName" value={flightName} onChange={(e) => setFlightName(e.target.value)} />
                    <input type="button" value="Create Flight" onClick={handleCreateFlight} />
                </form>
                <PilotCurrentFlight setFeedback={setFeedback} />
                <h4 className="feedback">{feedback}</h4>
            </div>
        </div>
    )
}