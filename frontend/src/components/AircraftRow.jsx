import { useEffect, useState, useContext } from "react";
import axios from "axios";
import { UserContext } from "../UserContext";


function AircraftRow({ aircraft, setAircraft }) {
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
                console.log(error.response.data.message)
            }
        }
        callApi()
    }

    return (
        <tr>
            <th>{aircraft.type}</th>
            <th>{aircraft.registration}</th>
            <th>{aircraft.numberOfSeats}</th>
            <th><input type="button" value="Remove" onClick={handleRemove} /></th>
        </tr>
    )

}

export default AircraftRow;