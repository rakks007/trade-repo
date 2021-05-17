import { Component, OnInit } from '@angular/core';
import { TradeList } from 'src/app/models/trade-list.model';
import { TradeService } from 'src/app/services/trade.service';

@Component({
  selector: 'app-trade-list',
  templateUrl: './trade-list.component.html',
  styleUrls: ['./trade-list.component.css']
})
export class TradeListComponent implements OnInit {

  trade?: TradeList;
  currentTrade?: TradeList;
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
          this.trade = data;
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

  setActiveTrade(trade: TradeList, index: number): void {
    this.currentTrade = trade;
    this.currentIndex = index;
  }
}
