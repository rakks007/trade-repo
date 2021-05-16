import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TradeListComponent } from './components/trade-list/trade-list.component';
import { AddTradeComponent } from './components/add-trade/add-trade.component';


const routes: Routes = [
  { path: 'list', component: TradeListComponent },
  { path: 'add', component: AddTradeComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
