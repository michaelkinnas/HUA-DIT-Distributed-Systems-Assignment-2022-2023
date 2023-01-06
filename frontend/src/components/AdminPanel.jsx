import { useEffect, useState } from "react";
import AddAircraftForm from './AddAircraftForm'
import AddTourForm from './AddTourForm'
import Aircraft from "./Aircraft";
import AdjustUserRoles from "./AdjustUserRoles";



export default function AdminPanel() {
    const [adminFunction, setAdminFunction] = useState(1)

    let activeAdminFunctionPage
    if (adminFunction === "1") {
        activeAdminFunctionPage = <AdjustUserRoles />
    } else if (adminFunction === "2") {
        activeAdminFunctionPage = <AddTourForm />
    } else {
        activeAdminFunctionPage = <Aircraft />
    }

    const handleSelection = (event) => {
        setAdminFunction(event.currentTarget.id)
    }

    return (
        <div className="admin-panel">
            <h4>Admin functions</h4>
            <div className="button-row">
                <button type="button" id="1" onClick={handleSelection}>User Roles</button>
                <button type="button" id="2" onClick={handleSelection}>Tours</button>
                <button type="button" id="3" onClick={handleSelection}>Aircraft</button>
            </div>
            <div className="admin-function-page">
                {activeAdminFunctionPage}
            </div>
        </div>
    )
}