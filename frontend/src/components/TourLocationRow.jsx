import { useEffect, useState, useContext } from "react";
import axios from "axios";
import { UserContext } from "../UserContext";


function TourLocationRow({ tour, setTourLocations, setFeedback }) {
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
            <td>{tour.description}</td>
            <td>{tour.location}</td>
            <td>{tour.duration} hrs</td>
            <td><input type="button" value="Remove" onClick={handleRemove} /></td>
        </tr>
    )

}

export default TourLocationRow;