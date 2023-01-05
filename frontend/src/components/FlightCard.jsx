import { useEffect, useState } from "react";



export default function OpenFlights({ flight }) {


    return (
        <tr className="data-row">
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
    )
}