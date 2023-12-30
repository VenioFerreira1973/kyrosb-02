import { Lancamento } from './../lancamento.model';
import { Router, ActivatedRoute } from "@angular/router";
import { LancamentoService } from "../lancamento.service";
import { Component, OnInit } from "@angular/core";

@Component({
  selector: 'app-lancamento-delete',
  templateUrl: './lancamento-delete.component.html',
  styleUrls: ['./lancamento-delete.component.css']
})

export class LancamentoDeleteComponent implements OnInit {
  lancamento: Lancamento;

  constructor(
    private lancamentoService: LancamentoService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.lancamentoService.readById(id).subscribe((lancamento) => {
      this.lancamento = lancamento;
    });
  }

  deleteLancamento(): void {
    this.lancamentoService.delete(this.lancamento.id).subscribe(() => {
      this.lancamentoService.showMessage("Lancamento excluido com sucesso!");
      this.router.navigate(["/lancamentos"]);
    });
  }

  cancel(): void {
    this.router.navigate(["/lancamentos"]);
  }
}
