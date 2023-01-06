import React, { useContext, useState, useEffect } from "react"
import { UserContext } from "../UserContext";
import axios from 'axios';
import Navbar from "./Navbar";
import OpenFlights from "./OpenFlights"


function Home() {
    const { userContextData, setUserContextData } = useContext(UserContext)
    const [openFlights, setOpenFlights] = useState([])

    useEffect(() => {
        // const response = axios.get(process.env.AUTHORITY_URL + process.env.ACTIVE_TOURS_URL)

        const config = {
            headers: { Authorization: `Bearer ${userContextData.accessToken}` }
        }

        async function callAPI() {
            try {
                const response = await axios.get(process.env.REACT_APP_AUTHORITY_URL + process.env.REACT_APP_ACTIVE_TOURS_URL, config)
                setOpenFlights(response.data)
                // console.log(response.data)

            } catch (error) {
                console.log(error.response.data.message) //how to get body from axios error (really?)
            }
        }
        callAPI()
    }, [])




    return (

        <div>
            <Navbar />
            {openFlights ? <OpenFlights data={openFlights} /> : <h3>Loading...</h3>}
        </div >
    )
}


export default Home;