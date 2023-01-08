import { useEffect, useState } from "react";
import FlightCard from './FlightCard';
import './OpenFlights.css'

export default function OpenFlights(props) {

    return (
        <div className="open-flights-container">

            <h2>Take a seat and fly!</h2>

            <table className="open-flights-table">
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

                    {props.data.map((flight) => (
                        <FlightCard key={flight.id} flight={flight} />
                    ))}
                </tbody>
            </table>
        </div>
    )
}