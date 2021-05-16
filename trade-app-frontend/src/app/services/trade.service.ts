import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TradeList } from '../models/trade-list.model';



const baseUrl = 'http://localhost:8080/trade';

@Injectable({
  providedIn: 'root'
})

export class TradeService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<TradeList> {
    return this.http.get<TradeList>(baseUrl+'/list');
  }

  create(data: any): Observable<any> {
    return this.http.post(baseUrl+'/save', data);
  }
}
