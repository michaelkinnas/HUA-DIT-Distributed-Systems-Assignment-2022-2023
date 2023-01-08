import { useEffect, useState } from "react";



export default function OpenFlights({ flight }) {


    return (
        <tr className="data-row">
            <td>{flight.name}</td>
            <td>{flight.tour.location}</td>
            <td>{flight.tour.duration} hours</td>
            <td>{flight.aircraft.type}</td>
            <td>{flight.pilot.firstname} {flight.pilot.lastname}</td>
            <td>{flight.users.map((user) => (
                <p key={user.id}>{user.firstname} {user.lastname}</p>
            ))}</td>
            <td><button type="button" >Take a seat!</button></td>
        </tr>
    )
}