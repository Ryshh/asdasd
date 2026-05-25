import { useState } from 'react'
import './App.css'
import Input from './Input'
import Lista from './Lista'
import { useEffect } from 'react'

export default function App() {
  const [monitorok, setMonitorok] = useState([])


  async function GetMonitorok() {
    let json = await (await fetch("http://localhost:88/monitorok")).json()
    setMonitorok(json)
  }

  async function AddMonitor(tipus, meret) {
    let response = await (await fetch("http://localhost:88/monitor", {
        method: "POST",
        headers: {"Content-Type": "Application/json"},
        body: JSON.stringify({
            tipus: tipus,
            meret: meret
        })
    })).json()

  }

  async function DeleteMonitor(monitor){
    await fetch("http://localhost:88/monitor/" + monitor.id, {method: "DELETE"})
  }


  useEffect(() => {
    GetMonitorok()
  }, [monitorok])

  return (
    <div className='app vbox'>
      <Input AddMonitor={AddMonitor}/>

      <Lista monitorok={monitorok} DeleteMonitor={DeleteMonitor}/>
    </div>
  )
}