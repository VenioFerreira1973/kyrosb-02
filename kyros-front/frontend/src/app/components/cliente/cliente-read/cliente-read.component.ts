import { ClienteService } from '../cliente.service';
import { Cliente } from '../cliente.model';

import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-cliente-read',
  templateUrl: './cliente-read.component.html',
  styleUrls: ['./cliente-read.component.css']
})

export class ClienteReadComponent implements OnInit {

  clientes: Cliente[] = []
  displayedColumns = ['id', 'nome', 'dataNascimento','endereco', 'cpfCnpj', 'tipoPessoa', 'action']


  constructor(private clienteService: ClienteService) { }

  ngOnInit(): void {
    this.clienteService.read().subscribe((clientes: any) => {
      this.clientes = clientes.clientes
    })
  }


}

