import { server } from "../apiClient"

export const getProducts = async () => {
    const res = await server.get("/products")
    return res.data
}

export async function createProduct(productData) {
    const res = await server.post("/products", productData)
    return res.data
}