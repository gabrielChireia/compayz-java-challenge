import { useEffect, useState } from 'react'
import './App.css'
import { Button, Input, NumberInput, Space, Table, Text, Title } from '@mantine/core'
import { createClient, getClients } from './api/client/clientApi'
import { SelectProducts } from './components/SelectProducts'
import { createProduct, getProducts } from './api/product/productApi'
import { createOrders } from './api/order/orderApi'
import { IMaskInput } from 'react-imask';
import { notifications } from '@mantine/notifications'
import { errorNotification, successNotification } from './shared/notification'


function App() {
  const [page, setPage] = useState("inicio")
  // Client
  const [name, setName] = useState('')
  const [cpf, setCpf] = useState('')
  const [phone, setPhone] = useState('')
  const [email, setEmail] = useState('')
  const [clients, setClients] = useState([])
  const [showClientErrors, setShowClientErrors] = useState(false)
  // Pedidos
  const [produtosSelecionados, setProdutosSelectionados] = useState([])
  const [produtosWQuantity, setProdutosWQuantity] = useState([])
  const [order, setOrder] = useState({ clientName: '', description: '', products: [] })
  // Produtos
  const [description, setDescription] = useState('')
  const [quantity, setQuantity] = useState('')
  const [price, setPrice] = useState('')
  const [products, setProducts] = useState([])

  const handleSubmitCreateClient = async event => {
    try {
      event.preventDefault()

      if (!isCPFValid(cpf) || !isValidPhone(phone) || !isEmailValid(email)) {
        setShowClientErrors(true)
        return
      }

      const res = await createClient({
        name, cpf, phone, email
      })
      loadClients()
      setName('')
      setCpf('')
      setPhone('')
      setEmail('')
      setShowClientErrors(false)

      successNotification(res.name)
    } catch (error) {
      errorNotification(error)
    } finally {
    }
  }

  const handleSubmitCreateProduct = async event => {
    try {
      event.preventDefault()
      const res = await createProduct({
        description, quantity, price
      })
      loadProducts()
      setDescription('')
      setQuantity('')
      setPrice('')
      successNotification(res.order.description)
    } catch (error) {
      errorNotification(error)
    }
  }

  const handleSubmitCreateOrder = async event => {
    try {
      event.preventDefault()
      const res = await createOrders({
        issueDate: new Date().toISOString(),
        ...order
      })
      setDescription('')
      setProducts([])
      setOrder({ clientName: '', description: '', products: [] })
      successNotification(res.description)
    } catch (error) {
      errorNotification(error)
    }
  }

  const loadClients = async () => {
    const res = await getClients()
    setClients(res)
  }

  const loadProducts = async () => {
    const res = await getProducts()
    setProducts(res)
  }

  const clientTable = clients.map((client) => (
    <tr bgcolor="white" key={client.id}>
      <td>{client.name}</td>
      <td>{client.cpf}</td>
      <td>{client.phone}</td>
      <td>{client.email}</td>
    </tr>
  ))

  const productTable = products.map((product) => (
    <tr bgcolor="white" key={product.id}>
      <td>{product.description}</td>
      <td>{product.quantity}</td>
      <td>
        {new Intl.NumberFormat("pt-BR", { style: 'currency', currency: "BRL" }).format(product.price)}</td>
    </tr>
  ))

  const isCPFValid = (value) => {
    const cpf = value.replace(/[^\d]+/g, '');


    let soma = 0;
    let resto;

    if (cpf == '00000000000') return false;
    for (let i = 1; i <= 9; i++) {
      soma = soma + parseInt(cpf.substring(i - 1, i)) * (11 - i);
      resto = (soma * 10) % 11;
    }

    if ((resto == 10) || (resto == 11)) resto = 0;
    if (resto != parseInt(cpf.substring(9, 10))) return false;

    soma = 0;
    for (let j = 1; j <= 10; j++) {
      soma = soma + parseInt(cpf.substring(j - 1, j)) * (12 - j);
      resto = (soma * 10) % 11;
    }

    if ((resto == 10) || (resto == 11)) resto = 0;
    if (resto != parseInt(cpf.substring(10, 11))) return false;
    return true;
  }

  const isValidPhone = (value) => {
    const phone = value.replace(/[^\d]+/g, '');

    return phone.length === 11
  }

  const isEmailValid = (email) => {
    const validRegex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/
    console.log(email)

    if (validRegex.test(email)) {
      return true
    }

    return false
  }

  useEffect(() => {
    setOrder(s => ({
      ...s, products: produtosWQuantity.map(pwq => ({
        product: {
          id: pwq.id
        },
        quantity: pwq.quantity
      }))
    }))
  }, [
    produtosWQuantity
  ])

  useEffect(() => {
    setProdutosWQuantity(produtosSelecionados.map(ps => {
      const changedItem = produtosWQuantity.find(pwq => pwq.id === ps.id)
      if (changedItem) return ({
        ...ps,
        quantity: changedItem.quantity
      })
      return ({
        ...ps,
        quantity: 0
      })
    }))
  }, [produtosSelecionados])

  useEffect(() => {
    loadClients()
    loadProducts()
  }, [])

  return (
    <>
      {page === "inicio" && <>
        <div>
          <Title>Cadastro</Title>
        </div>
        <br />
        <div style={{ display: "flex" }}>
          <Button onClick={() => setPage("clientes")}>Clientes</Button>
          <Space w={"sm"}></Space>
          <Button onClick={() => setPage("produtos")}>Produtos</Button>
          <Space w={"sm"}></Space>
          <Button onClick={() => setPage("orders")}>Pedidos</Button>
        </div>
      </>
      }

      {page === "clientes" && <><Title>Cadastro de Clientes</Title>
        <Space h={"sm"}></Space>
        <Input
          onChange={e => setName(e.target.value)}
          variant='filled'
          placeholder='Digite seu nome'
          value={name}
        />
        <Space h={"sm"}></Space>

        <Input
          error={!isCPFValid(cpf) && showClientErrors}
          component={IMaskInput}
          onChange={e => setCpf(e.target.value)}
          variant='filled'
          placeholder='Digite seu cpf'
          value={cpf}
          mask="000.000.000-00"
        />
        {(!isCPFValid(cpf) && showClientErrors) && <Input.Error>CPF Invalido, verifique a formatação</Input.Error>}
        <Space h={"sm"}></Space>
        <Input
          error={!isValidPhone(phone) && showClientErrors}
          component={IMaskInput}
          onChange={e => setPhone(e.target.value)}
          variant='filled'
          placeholder='Digite seu telefone'
          value={phone}
          mask="(00) 00000-0000"
        />
        {(!isValidPhone(phone) && showClientErrors) && <Input.Error>Telefone Inválido, verifique a formatação</Input.Error>}
        <Space h={"sm"}></Space>
        <Input
          error={!isEmailValid(email) && showClientErrors}
          onChange={e => setEmail(e.target.value)}
          variant='filled'
          placeholder='Digite seu email'
          value={email}
        />
        {(!isEmailValid(email) && showClientErrors) && <Input.Error>Email inválido, verifique a formatação</Input.Error>}
        <Space h={"sm"}></Space>
        <Button onClick={handleSubmitCreateClient}>Salvar</Button>
        <Space h={"sm"}></Space>
        <Button onClick={() => setPage("inicio")}>Voltar</Button>
        <Space h={"xl"}></Space>
        <h2 className="h3 mb-4">Lista de Clientes Cadastrados</h2>
        <div>
          <Table>
            <thead>
              <tr bgcolor="white">
                <th>Nome</th>
                <th>CPF</th>
                <th>Telefone</th>
                <th>Email</th>
              </tr>
            </thead>
            <tbody>{clientTable}</tbody>
          </Table>
        </div>
      </>
      }

      {
        page === "produtos" && <>< Title >Cadastro de Produtos</Title >
          <Space h={"sm"}></Space>
          <Input
            onChange={e => setDescription(e.target.value)}
            variant='filled'
            placeholder='Digite o nome do produto'
            value={description}
          />
          <Space h={"sm"}></Space>
          <Input
            onChange={e => setQuantity(e.target.value)}
            variant='filled'
            placeholder='Digite a quantidade a ser guardada'
            value={quantity}
          />
          <Space h={"sm"}></Space>
          <Input
            onChange={e => setPrice(e.target.value)}
            variant='filled'
            placeholder='Digite o valor do produto'
            value={price}
          />
          <Space h={"sm"}></Space>
          <Button onClick={handleSubmitCreateProduct}>Salvar</Button>
          <Space h={"sm"}></Space>
          <Button onClick={() => setPage("inicio")}>Voltar</Button>
          <Space h={"xl"}></Space>
          <h2 className="h3 mb-4">Lista de Produtos Cadastrados</h2>
          <div>
            <Table>
              <thead>
                <tr bgcolor="white">
                  <th>Descrição</th>
                  <th>Quantidade</th>
                  <th>Preço</th>
                </tr>
              </thead>
              <tbody>{productTable}</tbody>
            </Table>
          </div>
          <Space h={"sm"}></Space>
          <button className="btn btn-secondary" onClick={() => setPage("inicio")}>Voltar</button>
        </>
      }

      {
        page === "orders" && <>< Title >Cadastro de Pedidos</Title >
          <Space h={"sm"}></Space>
          <Input
            onChange={e => setOrder(s => ({ ...s, clientName: e.target.value }))}
            variant='filled'
            placeholder='Digite o nome do cliente fazendo o pedido'
            value={order.clientName}
          />
          <Space h={"sm"}></Space>
          <Input
            onChange={e => setOrder(s => ({ ...s, description: e.target.value }))}
            variant='filled'
            placeholder='Digite uma descrição para o pedido'
            value={order.description}
          />
          <Space h={"sm"}></Space>
          <SelectProducts
            value={produtosSelecionados}
            onChange={(prods) => {
              setProdutosSelectionados(prods.map(p => ({
                id: p.id,
                description: p.description,
              })))
            }}
          />
          {produtosWQuantity?.map((ps, i) => {
            return (
              <>
                <Space h='md' />
                <div key={ps.id} style={{ display: 'flex' }}>
                  <Input disabled value={ps.description} />
                  <Space w='md' />
                  <NumberInput
                    id={ps.id}
                    placeholder='Quantity'
                    value={produtosWQuantity.filter((_, j) => i === j)[0].quantity}
                    onChange={
                      quantity => {
                        const newProducts = [...produtosWQuantity]
                        newProducts[i].quantity = quantity

                        setProdutosWQuantity([...newProducts])
                      }
                    }
                  />
                </div>
              </>)
          })}
          <Space h={"sm"}></Space>
          <Button onClick={handleSubmitCreateOrder}>Salvar</Button>
          <Space h={"sm"}></Space>
          <Button onClick={() => setPage("inicio")}>Voltar</Button>
        </>
      }
    </>
  )

}

export default App
