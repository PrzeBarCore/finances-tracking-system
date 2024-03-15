import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Receipt } from '../model/receipt';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ReceiptService {
  private receiptsUrl: string;

  constructor(private http: HttpClient) {
    this.receiptsUrl = 'http://localhost:8080/receipts';
  }

  public findReceipt(id: number): Observable<Receipt> {
    return this.http.get<Receipt>(this.receiptsUrl);
  }

  public saveReceipt(receipt: Receipt) {
    return this.http.post(this.receiptsUrl, receipt, { observe: 'response' });
  }
}
