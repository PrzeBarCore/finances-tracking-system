import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class CommonDataService {
  constructor(private httpClient: HttpClient) {}

  public getUnits() {
    return this.httpClient.get<string[]>(`http://localhost:8080/common/units`);
  }

  public getTransactionTypes() {
    return this.httpClient.get<string[]>(
      `http://localhost:8080/common/transactionTypes`
    );
  }
}
