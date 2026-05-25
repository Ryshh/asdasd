import express from "express"
import cors from "cors"
import mysql from "mysql2/promise"

const app = express()
app.use(express.json())
app.use(cors())

let con = await mysql.createConnection({
    host: "localhost",
    port: 3306,
    database: "autokak",
    user: "root",
    password: ""
});

async function getAutok(req, res) {
    let sql = "select az, marka, tipus, ev, ajto, url from auto inner join marka using(maz)"
    if (req.query.marka) {
        sql += ` where marka="${req.query.marka}"`
    }
    try {
        const [ json ] = await con.query(sql);
        res.send(json)
    } catch (err) {
        res.send({ error : err })
    }
}

async function getMarkak(req, res) {
    let sql = "select maz, marka, orszag from marka"
    try {
        const [ json ] = await con.query(sql);
        res.send(json)
    } catch (err) {
        res.send({ error : err })
    }
}


async function postAuto(req, res) {
    let { maz, tipus, ev, ajto, url } = req.body
    if ( maz && tipus && ev && ajto && url ) {
        let sql = `insert into auto set maz=${maz}, tipus='${tipus}', ev=${ev}, ajto=${ajto}, url='${url}'`
        try {
            const [ json ] = await con.query(sql);
            res.send({ status : "OK!"})
        } catch (err) {
            res.send({ error : err })
        }
    }
}

async function delAuto(req, res) {
    let { az } = req.params
    if ( az ) {
        let sql = `delete from auto where az=${az}`
        try {
            const [ json ] = await con.query(sql);
            res.send({ status : "OK!"})
        } catch (err) {
            res.send({ error : err })
        }
    }
}


app.get("/", (req, res) => res.send("<h1>Autókák v1.0.0</h1>"))
app.get("/autok", getAutok)
app.get("/markak", getMarkak)
app.post("/auto", postAuto)
app.delete("/auto", delAuto)

app.listen(88, err => console.log(err ? err : "Server on :88"))