import { useEffect, useState } from "react";
import FlightCard from './FlightCard';
import './OpenFlights.css'

export default function OpenFlights(props) {


    return (
        <section className="open-flights">
            {props.data.map((flight) => (
                <FlightCard key={flight.id} flight={flight} />
            ))}



        </section>
    )
}