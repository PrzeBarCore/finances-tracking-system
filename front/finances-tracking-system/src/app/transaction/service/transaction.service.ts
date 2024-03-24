import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class TransactionService {
  constructor(private httpClient: HttpClient) {}
  deleteTransaction(id: number) {
    console.log('Sending delete request for transaction');
    return this.httpClient.delete<boolean>(
      `http://localhost:8080/transactions/${id}`
    );
  }
}
