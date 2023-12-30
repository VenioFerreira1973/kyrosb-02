import { ClienteService } from './../../cliente/cliente.service';
import { ProdutoService } from './../../produto/produto.service';
import { Cliente } from './../../cliente/cliente.model';
import { Lancamento } from '../lancamento.model';
import { Produto } from './../../produto/produto.model';
import { LancamentoService } from '../lancamento.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LancamentoInput } from '../lancamento.input.model';

@Component({
  selector: 'app-lancamento-create',
  templateUrl: './lancamento-create.component.html',
  styleUrls: ['./lancamento-create.component.css']
})


export class LancamentoCreateComponent implements OnInit {

  
  cliente: Cliente = {
    id: null,
    nome: null,
    dataNascimento: null,
    endereco: null,
    cpfCnpj: null,
    tipoPessoa: null
  }

  produto: Produto = {
    id: null,
    nome: null,
    descricao: null,
    status: null,
    valorUnidade: null
  }

  clientesLista: Cliente[] = []; 
  produtosLista: Produto[] = []; 


  lancamento: Lancamento = {
    id: null,
    cliente: null,
    produto: null,
    dataVenda: null,
    quantidadeVendida: null,
    valorTotalVenda: null
  }

  lancamentoInput: LancamentoInput = {
    clienteId: null,
    produtoId: null,
    dataVenda: null,
    quantidadeVendida: null,
    valorTotalVenda: null
  }


  constructor(private lancamentoService: LancamentoService,
    private clienteService: ClienteService,
    private produtoService: ProdutoService,
    private router: Router) { }

  ngOnInit(): void {

    this.clienteService.read().subscribe(
      (clientes) => {
        this.clientesLista = clientes['clientes'];
      },
      (erro) => {
        console.error('Erro ao buscar clientes:', erro);
      })

    this.produtoService.read().subscribe(
      (produtos) => {
        this.produtosLista = produtos['produtos'];
      },
      (erro) => {
        console.error('Erro ao buscar produtos:', erro);
      })

  }

  createLancamentoInput(): void {


    this.lancamentoInput.clienteId = this.lancamento.cliente.id;
    this.lancamentoInput.produtoId = this.lancamento.produto.id;
    this.lancamentoInput.dataVenda = this.lancamento.dataVenda;
    this.lancamentoInput.quantidadeVendida = this.lancamento.quantidadeVendida;
    this.lancamentoInput.valorTotalVenda = this.lancamento.valorTotalVenda;
 
    this.lancamentoService.create(this.lancamentoInput).subscribe(() => {
      this.lancamentoService.showMessage('Lancamento criado!')
      this.router.navigate(['/lancamentos'])
    })

  }

  cancel(): void {
    this.router.navigate(['/lancamentos'])
  }

  
  calcularValorTotal(): void {
    if (this.lancamento.produto) {
      this.lancamento.valorTotalVenda = this.lancamento.produto.valorUnidade * this.lancamento.quantidadeVendida;
    } else {
      this.lancamento.valorTotalVenda = null;
    }
  }
  
}

