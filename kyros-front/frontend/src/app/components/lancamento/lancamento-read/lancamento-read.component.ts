import { Component, OnInit } from '@angular/core';

import { Lancamento } from '../lancamento.model';
import { LancamentoService } from '../lancamento.service';

@Component({
  selector: 'app-lancamento-read',
  templateUrl: './lancamento-read.component.html',
  styleUrls: ['./lancamento-read.component.css']
})
export class LancamentoReadComponent implements OnInit {

  lancamentos: Lancamento[]= []
  displayedColumns = ['id', 'nomeCliente', 'nomeProduto','dataVenda', 'quantidadeVendida', 'valorTotalVenda', 'action']


  constructor(private lancamentoService: LancamentoService) { }

  ngOnInit(): void {
    this.lancamentoService.read().subscribe((lancamentos: any) => {
      this.lancamentos = lancamentos.lancamentos
    })
  }

}
