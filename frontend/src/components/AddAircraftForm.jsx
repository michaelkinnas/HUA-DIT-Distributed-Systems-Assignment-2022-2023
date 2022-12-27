import { useEffect, useState } from "react";
import axios from "axios";
import { axiosPost } from "../utils/axiosPost";


export default function AddAircraftForm() {
    const [error, setError] = useState('')

    const [registerForm, setRegisterForm] = useState({
        type: '',
        registration: '',
        seats: ''
    })

    const handleChange = (event) => {
        const name = event.target.name;
        const value = event.target.value;
        setRegisterForm((values) => ({
            ...values,
            [name]: value
        }))
        setError('') //clear error message
    }

    const handleSubmit = (event) => {
        event.preventDefault();

        if (registerForm.email === '' || registerForm.password === '') {
            setError('You must provide an email and password')
        } else {
            const ADD_AIRCRAFT_URL = 'http://localhost:8080/authentication/register' //TODO set add aircraft url server endpoint

            async function fetchData() {
                const response = await axiosPost(ADD_AIRCRAFT_URL, registerForm)

                if (response.status === 200) {
                    console.log(response.data);
                }
            }
            fetchData();
        }
    }


    return (
        <div className="add-aircraft">
            <form className="add-aircraft-form">
                <label htmlFor="type">Model name:</label>
                <input type="text" name="type" id="type" className="add-aircraft-input-text" onChange={handleChange} value={registerForm.type} />

                <label htmlFor="registration">Registration:</label>
                <input type="text" name="registration" id="registration" className="add-aircraft-input-text" onChange={handleChange} value={registerForm.registration} />

                <label htmlFor="seats">Number of seats:</label>
                <input type="text" name="seats" id="seats" className="add-aircraft-input-text" onChange={handleChange} value={registerForm.seats} />

                <input value="Register" type="submit" name="register" id="register" className="register-button" onClick={handleSubmit} />
            </form>
            <div className="error-feedback">
                <b>{error}</b>
            </div>
        </div>
    )
}