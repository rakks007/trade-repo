import { Component, OnInit } from '@angular/core';
import { Trade } from 'src/app/models/trade.model';
import { TradeService } from 'src/app/services/trade.service';

@Component({
  selector: 'app-add-trade',
  templateUrl: './add-trade.component.html',
  styleUrls: ['./add-trade.component.css']
})

export class AddTradeComponent implements OnInit {
  trade: Trade = {
    title: '',
    description: '',
    published: false
  };
  submitted = false;

  constructor(private tradeService: TradeService) { }

  ngOnInit(): void {
  }

  saveTrade(): void {
    const data = {
      title: this.trade.title,
      description: this.trade.description
    };

    this.tradeService.create(data)
      .subscribe(
        response => {
          console.log(response);
          this.submitted = true;
        },
        error => {
          console.log(error);
        });
  }

   newTrade(): void {
    this.submitted = false;
    this.trade = {
      title: '',
      description: '',
      published: false
    };
  }

}
