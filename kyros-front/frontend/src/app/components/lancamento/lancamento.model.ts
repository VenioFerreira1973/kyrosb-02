import { Cliente } from "../cliente/cliente.model"
import { Produto } from "../produto/produto.model"

export interface Lancamento {
    id?: number
    cliente: Cliente
    produto: Produto
    quantidadeVendida: number
    dataVenda: Date
    valorTotalVenda: number
}

