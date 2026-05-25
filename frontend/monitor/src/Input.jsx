import { useState } from 'react'

export default function Input({AddMonitor}) {

    const [tipus, setTipus] = useState("monitor")
    const [meret, setMeret] = useState(27)

    return (
        <div className='container hbox'>
            <label htmlFor="tipus">Típus:</label>
            <input id='tipus' type="text" value={tipus} onChange={e => setTipus(e.target.value)} style={{width: 200}}/>

            <label htmlFor="meret">Méret:</label>
            <input id='meret' type="number" value={meret} onChange={e => setMeret(e.target.value)} style={{width: 50, textAlign: "center"}}/>
        
            <input type="button" value="Hozzáad" onClick={() => AddMonitor(tipus, meret)} />
        </div>
    )
}
