import { Cliente } from '../cliente.model';
import { ClienteService } from '../cliente.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cliente-create',
  templateUrl: './cliente-create.component.html',
  styleUrls: ['./cliente-create.component.css']
})

export class ClienteCreateComponent implements OnInit {

  cliente: Cliente = {
    nome: '',
    endereco: '',
    cpfCnpj: '',
    dataNascimento: null,
    tipoPessoa: ''
  }

  constructor(private clienteService: ClienteService,
      private router: Router) { }

  ngOnInit(): void {
    
  }

  createCliente(): void {
    this.clienteService.create(this.cliente).subscribe(() => {
      this.clienteService.showMessage('Cliente criado!')
      this.router.navigate(['/clientes'])
    })

  }

  cancel(): void {
    this.router.navigate(['/clientes'])
  }
}
