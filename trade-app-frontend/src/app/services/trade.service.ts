import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Trade } from '../models/trade.model';


const baseUrl = 'http://localhost:8080/trade';

@Injectable({
  providedIn: 'root'
})

export class TradeService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<Trade[]> {
    return this.http.get<Trade[]>(baseUrl);
  }

  create(data: any): Observable<any> {
    return this.http.post(baseUrl, data);
  }
}
