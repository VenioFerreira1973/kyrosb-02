import { LancamentoInput } from './lancamento.input.model';
import { Injectable } from "@angular/core";
import { MatSnackBar } from "@angular/material/snack-bar";
import { HttpClient } from "@angular/common/http";
import { Lancamento } from "./lancamento.model";
import { Observable, EMPTY } from "rxjs";
import { map, catchError } from "rxjs/operators";

@Injectable({
  providedIn: "root",
})
export class LancamentoService {
  baseUrl = "http://localhost:8080/lancamentos";

  constructor(private snackBar: MatSnackBar, private http: HttpClient) {}

  showMessage(msg: string, isError: boolean = false): void {
    this.snackBar.open(msg, "X", {
      duration: 2000,
      horizontalPosition: "right",
      verticalPosition: "top",
      panelClass: isError ? ["msg-error"] : ["msg-success"],
    });
  }

  create(lancamentoInput: LancamentoInput): Observable<Lancamento> {
    return this.http.post<LancamentoInput>(`${this.baseUrl}/efetuar-lancamento/`, lancamentoInput).pipe(
      map((obj) => obj),
      catchError((e) => this.errorHandler(e))
    );
  }

  read(): Observable<Lancamento[]> {
    return this.http.get<Lancamento[]>(this.baseUrl).pipe(
      map((obj) => obj),
      catchError((e) => this.errorHandler(e))
    );
  }

  readById(id: number): Observable<Lancamento> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.get<Lancamento>(url).pipe(
      map((obj) => obj),
      catchError((e) => this.errorHandler(e))
    );
  }

  update(lancamentoInput: LancamentoInput, id: number): Observable<Lancamento> {
    const url = `${this.baseUrl}/update/${id}`;
    return this.http.put<Lancamento>(url, lancamentoInput).pipe(
      map((obj) => obj),
      catchError((e) => this.errorHandler(e))
    );
  }

  delete(id: number): Observable<Lancamento> {
    const url = `${this.baseUrl}/delete/${id}`;
    return this.http.delete<Lancamento>(url).pipe(
      map((obj) => obj),
      catchError((e) => this.errorHandler(e))
    );
  }

  errorHandler(e: any): Observable<any> {
    this.showMessage("Ocorreu um erro!", true);
    return EMPTY;
  }
}
