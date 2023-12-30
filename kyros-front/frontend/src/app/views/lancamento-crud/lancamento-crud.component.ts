import { HeaderService } from '../../components/template/header/header.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'

@Component({
  selector: 'app-lancamento-crud',
  templateUrl: './lancamento-crud.component.html',
  styleUrls: ['./lancamento-crud.component.css']
})

export class LancamentoCrudComponent implements OnInit {

  constructor(private router: Router, private headerService: HeaderService) {
    headerService.headerData = {
      title: 'Cadastro de Lancamentos',
      icon: 'storefront',
      routeUrl: '/lancamentos'
    }
  }

  ngOnInit(): void {
  }

  navigateToLancamentoCreate(): void {
    this.router.navigate(['/lancamentos/create'])
  }

}
