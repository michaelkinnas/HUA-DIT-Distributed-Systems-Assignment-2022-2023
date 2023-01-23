import React, { useState } from "react"
import { Link } from "react-router-dom";
import axios from "axios";
import './Register.css'


function Register() {
    const [feedback, setFeedback] = useState('')
    const [registerForm, setRegisterForm] = useState({
        email: '',
        password: '',
        firstname: '',
        lastname: ''
    })

    const handleChange = (event) => {
        setFeedback('')
        const name = event.target.name;
        const value = event.target.value;
        setRegisterForm((values) => ({
            ...values,
            [name]: value
        }))
    }

    const handleSubmit = (event) => {
        event.preventDefault();

        if (registerForm.email === '' || registerForm.password === '') {
            setFeedback('You must provide an email and password')
        } else {
            async function fetchData() {
                try {
                    const response = await axios.post(`${process.env.REACT_APP_AUTHORITY_URL}${process.env.REACT_APP_REGISTER_URL}`, registerForm)
                    if (response.status) {
                        setFeedback('Registration successfull')
                    }
                } catch (error) {
                    if (error.response) {
                        console.log(error.response.data);
                        console.log(error.response.status);
                        console.log(error.response.headers);
                        if (error.response.data.message) {
                            setFeedback(error.response.data.message);
                        }

                    }

                }


            }
            fetchData();
        }
    }

    return (
        <div className="register-component">
            <h2>Register new user</h2>
            <form className="register-form">
                <input type="text" name="email" id="email" className="register-input-text" onChange={handleChange} value={registerForm.email} placeholder="email" />
                <input type="password" name="password" id="password" className="register-input-text" onChange={handleChange} value={registerForm.password} placeholder="password" />
                <input type="text" name="firstname" id="firstname" className="register-input-text" onChange={handleChange} value={registerForm.firstname} placeholder="First name" />
                <input type="text" name="lastname" id="lastname" className="register-input-text" onChange={handleChange} value={registerForm.lastname} placeholder="Last name" />
                <input value="Register" type="submit" name="register" id="register" className="register-button" onClick={handleSubmit} />
            </form>
            <div className="register-error-feedback">
                <b>{feedback}</b>
            </div>
            <Link to="/" className="return-link">Return</Link>
        </div>
    )
}




export default Register;