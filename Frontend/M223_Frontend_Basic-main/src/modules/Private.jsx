import {useEffect, useState } from "react"
import axios from "axios"
 
export default function Public() {
    const [liste, setListe] = useState([])
    const [item, setItem] = useState('')
 
    useEffect(() => {
        axios.get("http://localhost:8080/public")  
        .then((response) => {
            setListe(response.data)
        })
    }, [])

 
    return (
        <div>
            <h1>Private</h1>
            <hr />
            <ul>
                {liste.map((item) => {
                    return <li key={item}>{item}</li>
                })}
            </ul>
        </div>
    )
}
 