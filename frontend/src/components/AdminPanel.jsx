import { useEffect, useState } from "react";
import TourLocations from "./TourLocations"
import Aircraft from "./Aircraft";
import AdjustUserRoles from "./AdjustUserRoles";
import Navbar from "./Navbar";
import "./AdminPanel.css"



export default function AdminPanel() {
    const [adminFunction, setAdminFunction] = useState(1)

    let activeAdminFunctionPage
    if (adminFunction === "1") {
        activeAdminFunctionPage = <AdjustUserRoles />
    } else if (adminFunction === "2") {
        activeAdminFunctionPage = <TourLocations />
    } else if (adminFunction === "3") {
        activeAdminFunctionPage = <Aircraft />
    }

    const handleSelection = (event) => {
        setAdminFunction(event.currentTarget.id)
    }

    return (
        <div className="admin-page">
            <Navbar />
            <div className="admin-panel">
                <h3>Admin functions</h3>
                <div className="button-row">
                    <button className="admin-button" type="button" id="1" onClick={handleSelection}>User Roles</button>
                    <button className="admin-button" type="button" id="2" onClick={handleSelection}>Tour Locations</button>
                    <button className="admin-button" type="button" id="3" onClick={handleSelection}>Aircraft</button>
                </div>
                <div className="admin-function-page">
                    {activeAdminFunctionPage}
                </div>
            </div>
        </div>
    )
}