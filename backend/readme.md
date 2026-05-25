````
import express from "express"
import cors from "cors"
import mysql from "mysql2/promise"

let con = await mysql.createConnection({
    host: "localhost",
    port: 3306,
    database: "database",
    user: "root",
    password: ""
});

const app = express()
app.use(cors())
app.use(express.json())

app.listen(88, err => console.log(err ?? "Opened on port :88"))
app.get("/", (req, res) => res.send("<h1>Backend v1.0.0</h1>"))
