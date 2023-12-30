import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

import { ProdutoDeleteComponent } from './components/produto/produto-delete/produto-delete.component';
import { ProdutoUpdateComponent } from './components/produto/produto-update/produto-update.component';
import { ProdutoCreateComponent } from './components/produto/produto-create/produto-create.component';
import { ProdutoCrudComponent } from "./views/produto-crud/produto-crud.component";

import { ClienteDeleteComponent } from './components/cliente/cliente-delete/cliente-delete.component';
import { ClienteUpdateComponent } from './components/cliente/cliente-update/cliente-update.component';
import { ClienteCrudComponent } from "./views/cliente-crud/cliente-crud.component";
import { ClienteCreateComponent } from './components/cliente/cliente-create/cliente-create.component';

import { LancamentoDeleteComponent } from './components/lancamento/lancamento-delete/lancamento-delete.component';
import { LancamentoUpdateComponent } from './components/lancamento/lancamento-update/lancamento-update.component';
import { LancamentoCrudComponent } from "./views/lancamento-crud/lancamento-crud.component";
import { LancamentoCreateComponent } from './components/lancamento/lancamento-create/lancamento-create.component';


import { HomeComponent } from "./views/home/home.component";


const routes: Routes = [
  {
    path: "",
    component: HomeComponent
  },
  {
    path: "produtos",
    component: ProdutoCrudComponent
  },
  {
    path: "produtos/create",
    component: ProdutoCreateComponent
  },
  {
    path: "produtos/update/:id",
    component: ProdutoUpdateComponent
  },
  {
    path: "produtos/delete/:id",
    component: ProdutoDeleteComponent
  },
  {
    path: "clientes",
    component: ClienteCrudComponent
  },
  {
    path: "clientes/create",
    component: ClienteCreateComponent
  },
  {
    path: "clientes/update/:id",
    component: ClienteUpdateComponent
  },
  {
    path: "clientes/delete/:id",
    component: ClienteDeleteComponent
  },
  {
    path: "lancamentos",
    component: LancamentoCrudComponent
  },

  {
    path: "lancamentos/create",
    component: LancamentoCreateComponent
  },
  {
    path: "lancamentos/update/:id",
    component: LancamentoUpdateComponent
  },
  {
    path: "lancamentos/delete/:id",
    component: LancamentoDeleteComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
