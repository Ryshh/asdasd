import mysql from "mysql2/promise"

export const con = await mysql.createConnection({
    host: "localhost",
    port: 3306,
    database: "monitor",
    user: "root",
    password: ""
});