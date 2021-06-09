import { Component, OnInit } from '@angular/core';
import { HeaderServiceService } from '../header-service.service';

@Component({
  selector: 'app-operations',
  templateUrl: './operations.component.html',
  styleUrls: ['./operations.component.css']
})
export class OperationsComponent implements OnInit {

  constructor(private headerService: HeaderServiceService) {
    this.headerService.setTitle("Historique des Opérations");
    this.headerService.setIcon("table_chart");

  }

  ngOnInit(): void {

  }

}
