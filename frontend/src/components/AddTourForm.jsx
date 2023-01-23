import { useEffect, useState, useContext } from "react";
import axios from "axios";
import { UserContext } from "../UserContext";


export default function AddTourForm({ setTourLocations, setFeedback }) {

    const { userContextData, setUserContextData } = useContext(UserContext);

    const [addTourForm, setAddTourForm] = useState({
        name: '',
        location: '',
        duration: 0
    })

    const handleChange = (event) => {
        const name = event.target.name;
        const value = event.target.value;

        setAddTourForm((values) => ({
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
                const response = await axios.post(process.env.REACT_APP_AUTHORITY_URL + process.env.REACT_APP_ADD_TOUR_URL, addTourForm, config)

                if (response.status === 200) {
                    setTourLocations(response.data)
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
        callAPI();
    }


    return (
        <div className="add-aircraft">
            <form className="add-aircraft-form">
                <label htmlFor="name">Tour name:</label>
                <input type="text" name="name" id="name" className="add-aircraft-input-text" onChange={handleChange} value={addTourForm.name} />

                <label htmlFor="location">Location:</label>
                <input type="text" name="location" id="location" className="add-aircraft-input-text" onChange={handleChange} value={addTourForm.location} />

                <label htmlFor="duration">Duration:</label>
                <input type="number" min="0" name="duration" id="duration" className="add-aircraft-input-text" onChange={handleChange} value={addTourForm.duration} />

                <input value="Submit" type="submit" name="register" id="register" className="add-tour-button" onClick={handleSubmit} />
            </form>
        </div>
    )
}