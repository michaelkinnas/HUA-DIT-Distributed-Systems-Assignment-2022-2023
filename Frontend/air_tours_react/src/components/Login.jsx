import React, { useContext, useState } from "react"
import { useNavigate } from "react-router-dom";
import { UserContext } from "../UserContext";
import { axiosPost } from "../utils/axiosPost"



function Login() {
    const { userContextData, setUserContextData } = useContext(UserContext); //use this to update user context
    const [loginForm, setLoginForm] = useState({
        email: '',
        password: ''
    })
    const [error, setError] = useState('')
    const navigate = useNavigate()

    const handleChange = (event) => {
        const name = event.target.name;
        const value = event.target.value;
        setLoginForm((values) => ({
            ...values,
            [name]: value
        }))
        setError('') //clear error message
    }

    const handleSubmit = (event) => {
        event.preventDefault();

        if (loginForm.email === '' || loginForm.password === '') {
            setError('You must provide an email and password')
        } else {
            const LOGIN_URL = 'http://localhost:8080/authentication/login'

            async function fetchData() {
                const response = await axiosPost(LOGIN_URL, loginForm)

                if (response.status === 200) {
                    console.log(response.data);

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


                    // props.toggleUserLogedIn(true);
                    // console.log(props.userLogedInStatus)
                }
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

