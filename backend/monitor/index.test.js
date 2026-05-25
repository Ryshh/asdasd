import { vi, test, describe, expect } from "vitest";
import require from "supertest"

vi.mock("./database.js", () => ({
    con: {
        execute: vi.fn()
    }
}))

import { app } from "./app.js";
import { con } from "./database.js";

describe("Műveletek tesztelése", () => {
    test("GET / -> statusCode, text", async () => {
        let res = await require(app).get("/")
        expect(res.statusCode).toBe(200)
        expect(res.text).toBe("<h1>Monitor v1.0.0</h1>")
    })

    test("GET /monitorok -> statusCode, body", async () => {
        con.execute.mockResolvedValue([ { id: 1, tipus: "KTC H32S17 Ívelt Gamer Monitor", meret: 32 }, 3 ])
        let res = await require(app).get("/monitorok")
        
        expect(res.statusCode).toBe(200)
        expect(con.execute).toHaveBeenCalledTimes(1)
        expect(res.body).toStrictEqual({ id: 1, tipus: "KTC H32S17 Ívelt Gamer Monitor", meret: 32 })
    })

    test('POST /monitor -> query, statusCode, body', async () => {
        con.execute.mockResolvedValue([])
        let res = await require(app).post("/monitor")
            .send({
                tipus: "TITAN ARMY C49SHC",
                meret: 49
            })
        
        expect(con.execute).toHaveBeenCalledWith("INSERT INTO monitor(`meret`, `tipus`) VALUES (?,?)", [49, "TITAN ARMY C49SHC"])
        expect(res.statusCode).toBe(201)
        expect(res.body).toStrictEqual({ msg: "Monitor hozzáadva!" })
    })
    
    test('DELETE /monitor/1 -> query, statusCode, body', async () => {
        con.execute.mockResolvedValue([[{ id: 1, tipus: "KTC H32S17 Ívelt Gamer Monitor", meret: 32 }], 3])
        let res = await require(app).delete("/monitor/1")
        
        expect(con.execute).toHaveBeenCalledWith("DELETE FROM monitor WHERE id = ?", ["1"])
        expect(res.statusCode).toBe(200)
        expect(res.body).toStrictEqual({ msg: "Monitor törölve!" })
    })
})