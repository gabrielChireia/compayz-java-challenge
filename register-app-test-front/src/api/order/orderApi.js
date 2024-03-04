import { server } from "../apiClient";

export async function createOrders(orderData) {
    const res = await server.post("/orders", orderData)
    return res.data
}