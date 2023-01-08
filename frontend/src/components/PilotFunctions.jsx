import { useEffect, useState, useContext } from "react";
import { UserContext } from "../UserContext";
import axios from "axios";
import Navbar from "./Navbar";

export default function PilotFunctions() {
    const { userContextData, setUserContextData } = useContext(UserContext)
    const [pilotSelectionForm, setPilotSelectionForm] = useState({
        tours: [],
        aircraft: []
    })
    const [selectedTour, setSelectedTour] = useState('')
    const [selectedAircraft, setSelectedAircraft] = useState('')
    const [flightName, setFlightName] = useState('')

    useEffect(() => {
        const config = {
            headers: { Authorization: `Bearer ${userContextData.accessToken}` }
        }

        async function callAPI() {
            try {
                const tourResponse = await axios.get(process.env.REACT_APP_AUTHORITY_URL + process.env.REACT_APP_GET_TOURS_URL, config)
                const aircraftResponse = await axios.get(process.env.REACT_APP_AUTHORITY_URL + process.env.REACT_APP_GET_AIRCRAFTS_URL, config)

                const data = {
                    tours: tourResponse.data,
                    aircraft: aircraftResponse.data
                }

                // console.log(data)
                setPilotSelectionForm(data)


            } catch (error) {
                console.log(error.response.data.message)
            }
        }
        callAPI()
    }, [])

    function handleCreateFlight() {

        // console.log(userContextData)
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

                const response = await axios.post(`${process.env.REACT_APP_AUTHORITY_URL}${process.env.REACT_APP_PILOT_CREATE_TOUR_URL}${userContextData.id}`, payload, config)


            } catch (error) {
                console.log(error.response.data.message)
            }
        }
        callAPI()
    }

    return (
        <div className="pilot-page">
            <Navbar />
            <div className="pilot-panel">
                <h4>Create a flight</h4>
                <form>
                    <label htmlFor="tours">Tour</label>
                    <select name="tours" id="tours" value={selectedTour} onChange={(e) => setSelectedTour(e.target.value)}>
                        {pilotSelectionForm.tours.map((tour) => (
                            <option key={tour.id} value={tour.id}>{tour.name}</option>
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

            </div>
        </div>
    )
}