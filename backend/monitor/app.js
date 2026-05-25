import express from "express"
import cors from "cors"
import { con } from "./database.js"

export const app = express()
app.use(cors())
app.use(express.json())

app.get("/", (req, res) => res.send("<h1>Monitor v1.0.0</h1>"))
app.get("/monitorok", async (req, res) => {
    try
    {
        let sql = "SELECT * FROM monitor order by tipus asc"
        let [json] = await con.execute(sql)
        res.status(200).send(json)
    }
    catch (err)
    {
        console.log(err);
        
        res.status(500).send({ error: "Adatbázis hiba!" })
    }
})
app.post("/monitor", async (req, res) => {
    let { tipus, meret } = req.body
    try
    {
        if (tipus, meret) {
            let sql = "INSERT INTO monitor(`meret`, `tipus`) VALUES (?,?)"
            let [json] = await con.execute(sql, [meret, tipus])
            res.status(201).send({ msg: "Monitor hozzáadva!" })
        }
        else {
            res.status(400).send({ error: "Hibás paraméter!" })
        }
    }
    catch (err)
    {
        console.log(err);
        res.status(500).send({ error: "Adatbázis hiba!" })
    }
})
app.delete("/monitor/:id", async (req, res) => {
    let { id } = req.params
    try
    {
        if (id != null) {
            let teszt = "SELECT * FROM monitor WHERE id = ?"
            let [tesztJson] = await con.execute(teszt, [id])
        
            if (tesztJson.length > 0) {
                let sql = "DELETE FROM monitor WHERE id = ?"
                let [json] = await con.execute(sql, [id])
                res.status(200).send({ msg: "Monitor törölve!" })
            }
            else {
                res.status(400).send({ msg: "Nincs ilyen monitor id!" })
            }
        }
        else {
            res.status(400).send({ error: "Hibás paraméter!" })
        }
    }
    catch (err)
    {
        console.log(err);
        res.status(500).send({ error: "Adatbázis hiba!" })
    }
})