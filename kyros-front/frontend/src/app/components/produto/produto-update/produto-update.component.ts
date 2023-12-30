import { Produto } from "../produto.model";
import { Router, ActivatedRoute } from "@angular/router";
import { ProdutoService } from "../produto.service";
import { Component, OnInit } from "@angular/core";

@Component({
  selector: 'app-produto-update',
  templateUrl: './produto-update.component.html',
  styleUrls: ['./produto-update.component.css'],
})
export class ProdutoUpdateComponent implements OnInit {
  
  produto: any = {};

  constructor(
    private produtoService: ProdutoService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const id = +this.route.snapshot.paramMap.get("id");
    this.produtoService.readById(id).subscribe((produto) => {
      this.produto = produto;
    });
  }

  updateProduto(): void {
    this.produtoService.update(this.produto).subscribe(() => {
      this.produtoService.showMessage("Produto atualizado com sucesso!");
      this.router.navigate(["/produtos"]);
    });
  }


  cancel(): void {
    this.router.navigate(["/produtos"]);
  }
}
