import { Component, OnInit } from '@angular/core';
import { Trade } from 'src/app/models/trade.model';
import { TradeService } from 'src/app/services/trade.service';

@Component({
  selector: 'app-trade-list',
  templateUrl: './trade-list.component.html',
  styleUrls: ['./trade-list.component.css']
})
export class TradeListComponent implements OnInit {

  trades?: Trade[];
  currentTrade?: Trade;
  currentIndex = -1;
  tradeId = '';
  constructor(private tradeService: TradeService) { }

  ngOnInit(): void {
    this.retrieveTrades();
  }

   retrieveTrades(): void {
    this.tradeService.getAll()
      .subscribe(
        data => {
          this.trades = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

  refreshList(): void {
    this.retrieveTrades();
    this.currentTrade = undefined;
    this.currentIndex = -1;
  }

  setActiveTrade(trade: Trade, index: number): void {
    this.currentTrade = trade;
    this.currentIndex = index;
  }
}
