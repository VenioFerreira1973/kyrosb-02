import { Lancamento } from './../lancamento.model';
import { Router, ActivatedRoute } from "@angular/router";
import { LancamentoService } from "../lancamento.service";
import { Component, OnInit } from "@angular/core";
import { LancamentoInput } from '../lancamento.input.model';
import { Cliente } from '../../cliente/cliente.model';
import { Produto } from '../../produto/produto.model';
import { ClienteService } from '../../cliente/cliente.service';
import { ProdutoService } from '../../produto/produto.service';

@Component({
  selector: 'app-lancamento-update',
  templateUrl: './lancamento-update.component.html',
  styleUrls: ['./lancamento-update.component.css']
})

export class LancamentoUpdateComponent implements OnInit {

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
    private router: Router,
    private route: ActivatedRoute) { }
    
  ngOnInit(): void {

    const id = +this.route.snapshot.paramMap.get("id");
    this.lancamentoService.readById(id).subscribe((lancamento) => {
      this.lancamento = lancamento;
      
    });
  }

  updateLancamentoInput(): void {

    this.lancamentoInput.clienteId = this.lancamento.cliente?.id;
    this.lancamentoInput.produtoId = this.lancamento.produto?.id;
    this.lancamentoInput.dataVenda = this.lancamento.dataVenda;
    this.lancamentoInput.quantidadeVendida = this.lancamento.quantidadeVendida;
    this.lancamentoInput.valorTotalVenda = this.lancamento.valorTotalVenda;


    this.lancamentoService.update(this.lancamentoInput, this.lancamento.id).subscribe(() => {
      this.lancamentoService.showMessage("Lancamento atualizado com sucesso!");
      this.router.navigate(["/lancamentos"]);
    });
  }

  updateClienteNome(event: any): void {
    if (this.lancamento.cliente) {
      this.lancamento.cliente.nome = event.target.value;
    }
  }
  
  updateProdutoNome(event: any): void {
    if (this.lancamento.produto) {
      this.lancamento.produto.nome = event.target.value;
    }
  }

  cancel(): void {
    this.router.navigate(["/lancamentos"]);
  }

  calcularValorTotal(): void {
    if (this.lancamento.produto) {
      this.lancamento.valorTotalVenda = this.lancamento.produto.valorUnidade * this.lancamento.quantidadeVendida;
    } else {
      this.lancamento.valorTotalVenda = null;
    }
  }
}
