import { useEffect, useState } from "react";
import Navbar from "./Navbar";


export default function NoAdminRightsPage() {


    return (
        <div className="no-admin-rights">
            <Navbar />
            <h1>No admin rights!</h1>

        </div>
    )
}