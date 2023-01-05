import { useEffect, useState } from "react";
import FlightCard from './FlightCard';
// import './OpenFlights.css'

export default function OpenFlights(props) {

    return (
        <table className="flight-card-table">
            <tbody>
                <tr className="head-row">
                    <th>Name</th>
                    <th>Location</th>
                    <th>Duration</th>
                    <th>Aircraft</th>
                    <th>Pilot</th>
                    <th>Seats</th>
                    <th>Options</th>
                </tr>

                {props.data.map((flight) => (
                    <FlightCard key={flight.id} flight={flight} />
                ))}
            </tbody>

        </table>
    )
}