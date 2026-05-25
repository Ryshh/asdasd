import { app } from "./app.js";

app.listen(88, err => console.log(err ?? "Opened on port :88"))