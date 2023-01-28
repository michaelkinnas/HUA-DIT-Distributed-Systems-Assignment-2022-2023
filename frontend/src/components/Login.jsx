import React, { useContext, useState } from "react"
import { useNavigate } from "react-router-dom";
import { UserContext } from "../UserContext";
import { Link } from "react-router-dom";
import axios from 'axios';
import logo from '../graphics/logo.png'
import './login.css'


function Login() {
    const { userContextData, setUserContextData } = useContext(UserContext); //use this to update user context
    const [loginForm, setLoginForm] = useState({
        email: '',
        password: ''
    })
    const [feedback, setFeedback] = useState('')
    const navigate = useNavigate()

    const handleChange = (event) => {
        const name = event.target.name;
        const value = event.target.value;
        setLoginForm((values) => ({
            ...values,
            [name]: value
        }))
        setFeedback('') //clear error message
    }

    const handleSubmit = (event) => {
        event.preventDefault();

        if (loginForm.email === '' || loginForm.password === '') {
            setFeedback('You must provide an email and password')
        } else {

            async function fetchData() {
                try {
                    const response = await axios.post(`${process.env.REACT_APP_AUTHORITY_URL}${process.env.REACT_APP_LOGIN_URL}`, loginForm);

                    console.log("User data " + response.data.firstName)
                    const userData = {
                        id: response.data.id,
                        email: response.data.email,
                        firstName: response.data.firstName,
                        lastName: response.data.lastName,
                        accessToken: response.data.token,
                        tokenType: response.data.type,
                        roles: response.data.roles
                    }
                    setUserContextData(userData)
                    navigate("/home")


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
            fetchData();

        }
    }


    return (
        <div className="login-component">
            <img src={logo}></img>
            <h1 className="main-title">Air Tours</h1>
            <h4>Enjoy Greece from above!</h4>
            <form className="login-form">
                <input type="text" name="email" id="email" placeholder="e-mail" className="login-input-text" onChange={handleChange} value={loginForm.email} />
                <input type="password" name="password" id="password" placeholder="password" className="login-input-text" onChange={handleChange} value={loginForm.password} />
                <input value="Login" type="submit" name="login" id="login" className="login-button" onClick={handleSubmit} />
            </form>
            <hr width="330px" />
            <p className="register-paragraph">Not a member? <Link to="/register">Register</Link> now!</p>
            <div className="login-errors">
                <b>{feedback}</b>
            </div>

        </div>
    )
}

export default Login;

