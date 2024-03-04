import { server } from "../apiClient";

export async function getClients() {
    const res = await server.get("/clients")
    return res.data
}

export async function createClient(clientData) {
    const res = await server.post("/clients", clientData)
    return res.data
}