import { Component, OnInit } from '@angular/core';
import {Chart} from 'chart.js';
import { HttpHomeService } from '../http-home.service';
import { InfosTransfrService } from 'src/app/infos-transfr.service';

@Component({
  selector: 'app-bar-chart',
  templateUrl: './bar-chart.component.html',
  styleUrls: ['./bar-chart.component.css']
})
export class BarChartComponent implements OnInit {
  info_object :  any= {

  };

  constructor(private infosapi : InfosTransfrService,private httpservice: HttpHomeService) {
    this.infosapi.getinfos().subscribe((res :any)=>{
      this.info_object = res;

    },(err)=>{
      console.log(err);

    })
  }
  thedata: dataBarChart = {
    theData : [ { label: "income" ,  backgroundColor: 'rgb(44, 68, 148)', data: [10, 5 , 20] } ,
                {label: "outcome",  backgroundColor: 'rgb(234, 129, 139)', data: [9, 12 , 30] }
              ] ,
    labels: ['mars' , 'avril' , 'mai']
  } ;

  dta: dataBarChart;

  getbardata() {
    this.httpservice.getBarData(this.infosapi.info_object.currentAccountNum).subscribe(data => {

        this.dta = data ;

        //chart
        let  myBare = new Chart('mybar', {
          type: 'bar',
          data: {
              labels: this.dta.labels ,
              datasets: this.dta.theData
          },
          options: {
              responsive: true,
              maintainAspectRatio: false,
              scales: {
                  yAxes: [{
                      gridLines: {
                        display: false
                      },
                      ticks: {
                          beginAtZero: true
                      }
                  }],
                  xAxes: [{
                    gridLines: {
                      display: true
                    }
                }],

              }
          }
      });

        //end chart
    });
  }

  ngOnInit(): void {

    this.getbardata() ;

}

}

export interface dataBarChart {
  theData: { label: string , backgroundColor: string , data: Number[] } [] ;
  labels: string[] ;
}
