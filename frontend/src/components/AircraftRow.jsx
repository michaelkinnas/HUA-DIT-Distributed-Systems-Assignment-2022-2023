import { useEffect, useState, useContext } from "react";
import axios from "axios";
import { UserContext } from "../UserContext";


function AircraftRow({ aircraft, setAircraft, setFeedback }) {
    const { userContextData, setUserContextData } = useContext(UserContext)



    function handleRemove() {
        const config = {
            headers: { Authorization: `Bearer ${userContextData.accessToken}` }
        }

        async function callApi() {
            try {
                const response = await axios.post(`${process.env.REACT_APP_AUTHORITY_URL}${process.env.REACT_APP_DELETE_AIRCRAFT_URL}`, { "id": aircraft.id }, config)
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
        callApi()
    }

    return (
        <tr>
            <td>{aircraft.type}</td>
            <td>{aircraft.registration}</td>
            <td>{aircraft.numberOfSeats}</td>
            <td><input type="button" value="Remove" onClick={handleRemove} /></td>
        </tr>
    )

}

export default AircraftRow;