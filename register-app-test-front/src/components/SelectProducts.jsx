import { MultiSelect, Select } from "@mantine/core"
import { useEffect, useState } from "react"
import { getProducts } from "../api/product/productApi"

export const SelectProducts = ({ value, onChange }) => {
    const [products, setProducts] = useState([])

    const loadProducts = async () => {
        const products = await getProducts()

        setProducts(products)
    }

    const data = products?.map(p => ({
        value: p.id,
        label: p.description
    }))

    useEffect(() => {
        getProducts()
    }, [])

    return <MultiSelect placeholder="Selecione seus produtos" data={data} onClickCapture={() => loadProducts()} value={value.map(v => v.id)} onChange={(v) => onChange(v.map(id => ({
        id,
        description: products.find(p => p.id === id)?.description
    })))} />
}