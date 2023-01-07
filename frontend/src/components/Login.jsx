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

    const handleChange = (event) => {
        const name = event.target.name;
        const value = event.target.value;
        setLoginForm((values) => ({
            ...values,
            [name]: value
        }))
        setError('') //clear error message
    }

    const [error, setError] = useState('')
    const navigate = useNavigate()

    const handleSubmit = (event) => {
        event.preventDefault();

        if (loginForm.email === '' || loginForm.password === '') {
            setError('You must provide an email and password')
        } else {
            const LOGIN_URL = 'http://localhost:8080/authentication/login'

            async function fetchData() {
                // const response = await axiosPost(URL, loginForm)
                try {
                    const response = await axios.post(process.env.REACT_APP_AUTHORITY_URL + process.env.REACT_APP_LOGIN_URL, loginForm);

                    const userData = {
                        id: response.data.id,
                        email: response.data.email,
                        firstname: response.data.firstname,
                        lastname: response.data.lastname,
                        accessToken: response.data.accessToken,
                        tokenType: response.data.tokenType,
                        roles: response.data.roles
                    }
                    setUserContextData(userData)
                    navigate("/home") //TEMP SOLUTION

                    // console.log(response.data);
                } catch (error) {
                    setError(error.response.data.message) //how to get body from axios error (really?)
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
                <b>{error}</b>
            </div>
        </div>
    )
}

export default Login;

