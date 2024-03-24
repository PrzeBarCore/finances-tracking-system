import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ReceiptTransaction } from 'src/app/common/model/receiptTransaction';
import { Receipt } from 'src/app/receipt/model/receipt';

@Injectable({
  providedIn: 'root',
})
export class TransactionService {
  private basicUrl = 'http://localhost:8080/transactions';
  constructor(private httpClient: HttpClient) {}

  public findTransaction(id: number): Observable<Receipt> {
    return this.httpClient.get<Receipt>(this.basicUrl + `/${id}`);
  }

  deleteTransaction(id: number) {
    console.log('Sending delete request for transaction');
    return this.httpClient.delete<boolean>(this.basicUrl + `/${id}`);
  }

  public findReceipt(id: number): Observable<ReceiptTransaction> {
    return this.httpClient.get<ReceiptTransaction>(
      this.basicUrl + `/receipt/${id}`
    );
  }

  public saveReceipt(receipt: ReceiptTransaction) {
    return this.httpClient.post(this.basicUrl + `/receipt`, receipt, {
      observe: 'response',
    });
  }
}
