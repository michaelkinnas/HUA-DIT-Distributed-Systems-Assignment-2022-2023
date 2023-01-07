import { useEffect, useState, useContext } from "react";
import axios from "axios";
import { UserContext } from "../UserContext";


function TourLocationRow({ tour, setTourLocations }) {
    const { userContextData, setUserContextData } = useContext(UserContext)



    function handleRemove() {
        const config = {
            headers: { Authorization: `Bearer ${userContextData.accessToken}` }
        }

        async function callApi() {
            try {
                const response = await axios.post(`${process.env.REACT_APP_AUTHORITY_URL}${process.env.REACT_APP_DELETE_TOUR_URL}`, { "id": tour.id }, config)
                setTourLocations(response.data)
            } catch (error) {
                console.log(error.response.data.message)
            }
        }
        callApi()
    }

    return (
        <tr>
            <th>{tour.name}</th>
            <th>{tour.location}</th>
            <th>{tour.duration}</th>
            <th><input type="button" value="Remove" onClick={handleRemove} /></th>
        </tr>
    )

}

export default TourLocationRow;