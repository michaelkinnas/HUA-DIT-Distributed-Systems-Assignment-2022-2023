import React, { useContext, useState } from "react"
import { UserContext } from "../UserContext";
import axios from "axios";


function Register() {
    // const { userContextData, setUserContextData } = useContext(UserContext)
    const [registerForm, setRegisterForm] = useState({
        email: '',
        password: '',
        firstname: '',
        lastname: ''
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

    const [error, setError] = useState('')

    const handleSubmit = (event) => {
        event.preventDefault();

        if (registerForm.email === '' || registerForm.password === '') {
            setError('You must provide an email and password')
        } else {
            const REGISTER_URL = 'http://localhost:8080/authentication/register'

            async function fetchData() {
                // const response = await axiosPost(REGISTER_URL, registerForm)

                // if (response.status === 200) {
                //     console.log(response.data); //DEBUG purposes
                // }
                try {
                    const response = await axios.post(REGISTER_URL, registerForm)
                    console.log(response.data);
                } catch (error) {
                    setError(error.response.data.message) //how to get body from axios error (really?)
                }


            }
            fetchData();
        }
    }

    return (
        <div className="register-component">
            <form className="register-form">
                <label htmlFor="email">e-mail:</label>
                <input type="text" name="email" id="email" className="register-input-text" onChange={handleChange} value={registerForm.email} />

                <label htmlFor="password">Password:</label>
                <input type="text" name="password" id="password" className="register-input-text" onChange={handleChange} value={registerForm.password} />

                <label htmlFor="firstname">First Name:</label>
                <input type="text" name="firstname" id="firstname" className="register-input-text" onChange={handleChange} value={registerForm.firstname} />

                <label htmlFor="lastname">Last Name:</label>
                <input type="text" name="lastname" id="lastname" className="register-input-text" onChange={handleChange} value={registerForm.lastname} />

                <input value="Register" type="submit" name="register" id="register" className="register-button" onClick={handleSubmit} />
            </form>
            <div className="register-error-feedback">
                <b>{error}</b>
            </div>
        </div>
    )
}




export default Register;