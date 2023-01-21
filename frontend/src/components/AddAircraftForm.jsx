import { useEffect, useState, useContext } from "react";
import axios from "axios";
import { UserContext } from "../UserContext";
import './AddAircraftForm.css'



export default function AddAircraftForm({ setAircraft }) {
    const [feedback, setFeedback] = useState('')
    const { userContextData, setUserContextData } = useContext(UserContext);

    const [addAircraftForm, setAddAircraftForm] = useState({
        type: '',
        registration: '',
        numberOfSeats: 0
    })

    const handleChange = (event) => {
        const name = event.target.name;
        const value = event.target.value;

        setAddAircraftForm((values) => ({
            ...values,
            [name]: value
        }))
        setFeedback('') //clear error message
    }

    const handleSubmit = (event) => {
        event.preventDefault();

        const config = {
            headers: {
                Authorization: `Bearer ${userContextData.accessToken}`,
            }
        }

        async function callAPI() {
            try {
                const response = await axios.post(process.env.REACT_APP_AUTHORITY_URL + process.env.REACT_APP_ADD_AIRCRAFT_URL, addAircraftForm, config)

                if (response.status === 200) {
                    setAircraft(response.data)
                    setFeedback('Aircraft added succesfully')
                }

            } catch (error) {
                setFeedback(error.response.data.error)
            }


        }
        callAPI();
    }


    return (
        <div className="add-aircraft-form-container">
            <form className="add-aircraft-form">
                <label htmlFor="type">Model name:</label>
                <input type="text" name="type" id="type" className="add-aircraft-input-text" onChange={handleChange} value={addAircraftForm.type} />

                <label htmlFor="registration">Registration:</label>
                <input type="text" name="registration" id="registration" className="add-aircraft-input-text" onChange={handleChange} value={addAircraftForm.registration} />

                <label htmlFor="numberOfSeats">Number of seats:</label>
                <input type="number" name="numberOfSeats" id="numberOfSeats" className="add-aircraft-input-text" onChange={handleChange} value={addAircraftForm.numberOfSeats} />

                <input value="Submit" type="submit" name="register" id="register" className="add-aircraft-button" onClick={handleSubmit} />
            </form>
            <div className="feedback">
                <b>{feedback}</b>
            </div>
        </div>
    )
}
