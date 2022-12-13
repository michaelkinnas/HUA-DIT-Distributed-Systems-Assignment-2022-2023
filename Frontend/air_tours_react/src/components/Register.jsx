import React, { useContext, useState } from "react"
import { UserContext } from "../UserContext";



function Register() {

    const { userContextData, setUserContextData } = useContext(UserContext)

    const [registerForm, setRegisterForm] = useState({
        email: '',
        password: '',
        firstname: '',
        lastname: ''
    })

    const [error, setError] = useState('status text')

    return (
        <div className="register-component">
            <form className="register-form">
                <label htmlFor="email">e-mail:</label>
                <input type="text" name="email" id="email" className="register-input-text" onChange={setRegisterForm} value={registerForm.email} />

                <label htmlFor="password">Password:</label>
                <input type="text" name="password" id="password" className="register-input-text" onChange={setRegisterForm} value={registerForm.password} />

                <label htmlFor="firstname">First Name:</label>
                <input type="text" name="firstname" id="firstname" className="register-input-text" onChange={setRegisterForm} value={registerForm.firstname} />

                <label htmlFor="lastname">Last Name:</label>
                <input type="text" name="lastname" id="lastname" className="register-input-text" onChange={setRegisterForm} value={registerForm.lastname} />

                <input type="submit" name="register" id="register" className="register-button" />
            </form>
            <div className="register-error-feedback">
                <b>{userContextData.email}</b> //test for context change it to error state
            </div>
        </div>
    )
}




export default Register;