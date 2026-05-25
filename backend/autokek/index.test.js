import { describe, expect, test } from "vitest";

describe("GET /autok", () => {
  test("Adatok száma: 3", async () => {
    let resp = await fetch("http://localhost:88/autok");
    let json = await resp.json();
    expect(json.length).toBe(3);
  });
});