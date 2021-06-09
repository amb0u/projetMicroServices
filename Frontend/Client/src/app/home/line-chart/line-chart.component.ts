import { Component, OnInit } from '@angular/core';
import {Chart} from 'chart.js';
import { HttpHomeService } from '../http-home.service';
import { InfosTransfrService } from 'src/app/infos-transfr.service';


@Component({
  selector: 'app-line-chart',
  templateUrl: './line-chart.component.html',
  styleUrls: ['./line-chart.component.css']
})
export class LineChartComponent implements OnInit {
  info_object :  any= {

  };
  constructor(private infosapi : InfosTransfrService,private httpService: HttpHomeService,) {
    this.infosapi.getinfos().subscribe((res :any)=>{
      this.info_object = res;

    },(err)=>{
      console.log(err);

    })
   }

  theData: lineChartData ;
  ishidden = true ;
  isSend = false ;

  getLineData() {
    this.isSend = true ;
    this.httpService.getLineData(this.infosapi.info_object.currentAccountNum).subscribe( data => {
      this.isSend = false ;
      this.theData = data ;
      console.log(this.theData);


      //chart

    // tslint:disable-next-line: align

      let  myLine = new Chart('myline', {
      type: 'line',
      data: {
          labels: this.theData.labels,
          datasets: [{
              label: 'Balance Progress',
              data: this.theData.data,
              backgroundColor: [
                  ' rgba(91, 192, 222,0.1)',
              ],
              borderColor: [
                  'rgb(91, 192, 222)',
              ],
              pointBackgroundColor: [
                'rgb(2, 117, 216)',
                'rgb(2, 117, 216)',
                'rgb(2, 117, 216)',
                'rgb(2, 117, 216)',
            ],
            pointBorderWidth: [
              5,
              5,
              5,
              5
            ],
            pointBorderColor: [
              'rgb(2, 117, 216)',
              'rgb(2, 117, 216)',
              'rgb(2, 117, 216)',
              'rgb(2, 117, 216)',
            ],
              borderWidth: 1
          }]
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
    },
    (err)=>{
      this.isSend = false ;
      this.ishidden = false;
    }

    );
  }



  ngOnInit(): void {

    this.getLineData() ;

  }

}

export interface lineChartData {
  data : number[] ,
  labels : string[] ,
}
