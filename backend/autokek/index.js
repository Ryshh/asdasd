import express from 'express';
import cors from 'cors';
import mysql from 'mysql2/promise';

const app = express();
app.use(cors());
app.use(express.json());

let con = await mysql.createConnection({
    host: 'localhost',
    port: 3306,
    database: 'autok',
    user: 'root',
    password: '',
});

async function getAutok(req, res) { 
    let sql = 'SELECT * FROM autok';
    let [ adat ] = await con.execute(sql, []);
    res.send(adat);
}

async function postAuto(req, res) {
    let { tipus, szin, ev } = req.body;
    if (tipus && szin && ev) {
        let sql = "insert into autok set tipus=?, szin=?, ev=?"
        let [ adat ] = await con.execute(sql, [tipus, szin, ev]);
        res.status(201).send({ msg: "Új autó létrehozva!" })
    } else {
        res.status(400).send({ error : "Hiányos paraméterek!" })
    }
}

async function deleteAuto(req, res) {
    let { az } = req.params;
    let sql = "delete from autok where az=?"
    let [ adat ] = await con.execute(sql, [az]);
    res.status(200).send({ msg: "Autó törölve!" })
}

app.get('/', (req, res) => res.send('<h1>Autók v1.0.0</h1>'));
app.get('/autok', getAutok);
app.post('/auto', postAuto);
app.delete('/auto/:az', deleteAuto)

app.listen(88, err => console.log(err ? err : 'Server on :88'));