import { useEffect, useState } from "react";
import "./FlightCard.css"


export default function OpenFlights({ flight }) {


    return (
        <div className="flight-card">
            <table className="flight-card-table">
                <tbody>
                    <tr>
                        <th>Name</th>
                        <th>Location</th>
                        <th>Duration</th>
                        <th>Aircraft</th>
                        <th>Pilot</th>
                        <th>Seats</th>
                        <th>Options</th>
                    </tr>
                    <tr>
                        <th>{flight.name}</th>
                        <th>{flight.tour.location}</th>
                        <th>{flight.tour.duration} hours</th>
                        <th>{flight.aircraft.type}</th>
                        <th>{flight.pilot.firstname} {flight.pilot.lastname}</th>
                        <th>{flight.users.map((user) => (
                            <p key={user.id}>{user.firstname} {user.lastname}</p>
                        ))}</th>
                        <th><button type="button" >Take a seat!</button></th>
                    </tr>
                </tbody>
            </table>

        </div>
    )
}