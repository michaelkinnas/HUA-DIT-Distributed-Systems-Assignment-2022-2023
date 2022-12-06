import React, { useContext } from "react"
import { UserContext } from "../UserContext";



function Index() {
    //testvalue name must be the same as in the UserContext file
    const { testValue } = useContext(UserContext);



    return (
        <div>
            <h2>Index page</h2>
            <h2>{testValue}</h2>
        </div>

    )
}

export default Index;

