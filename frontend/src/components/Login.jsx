import React, { useContext, useState } from "react"
import { useNavigate } from "react-router-dom";
import { UserContext } from "../UserContext";
import axios from 'axios';


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


                // if (response.status === 200) {
                //     console.log(response.data);

                //     const userData = {
                //         email: response.data.email,
                //         firstname: response.data.firstname,
                //         lastname: response.data.lastname,
                //         accessToken: response.data.accessToken,
                //         tokenType: response.data.tokenType,
                //         roles: response.data.roles
                //     }
                //     setUserContextData(userData)
                //     navigate("/home") //TEMP SOLUTION
                // }
            }
            fetchData();

        }
    }


    return (
        <div className="login-component">


            <form className="login-form">
                <label htmlFor="email">e-mail:</label>
                <input type="text" name="email" id="email" className="login-input-text" onChange={handleChange} value={loginForm.email} />

                <label htmlFor="password">Password:</label>
                <input type="text" name="password" id="password" className="login-input-text" onChange={handleChange} value={loginForm.password} />

                <input value="Login" type="submit" name="login" id="login" className="login-button" onClick={handleSubmit} />
            </form>
            <div className="login-error-feedback">
                <b>{error}</b>
            </div>
        </div>
    )
}

export default Login;

