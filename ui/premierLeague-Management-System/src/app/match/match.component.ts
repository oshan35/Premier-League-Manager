import { Component, OnInit } from '@angular/core';
import { ClubServices } from '../services/club-services.service';
import {MatTableDataSource} from "@angular/material/table";


export interface MatchData{
  team01: string;
  team02: string;
  date: Date;
  team01Goals:string;
  team02Goals: string;
}

let matchDataList: MatchData[] =[];


@Component({
  selector: 'app-match',
  templateUrl: './match.component.html',
  styleUrls: ['./match.component.css']
})
export class MatchComponent{

  constructor(private clubServices:ClubServices) {
    // this.getMatchData();
  }
  dataMeaning="";

  displayedColumns: string[] = ['team01', 'team02', 'matchDate', 'team01Goals','team02Goals'];

  dataSource:any;


  async getMatchData(){
    try {
      const data: any = await this.clubServices.getMatchData();
      console.log(data);

      for (var i in data){
        console.log(typeof data[i]["matchDate"])
        matchDataList.push({team01:data[i]["team01"],team02:data[i]["team02"],date:data[i]["matchDate"],team01Goals:data[i]["team01Goals"],team02Goals:data[i]["team02Goals"]});
      }
      console.log(matchDataList)
      this.dataSource=new MatTableDataSource(matchDataList);
      console.log("hello")

    } catch (error) {
      console.log(`[ERROR] ==> AppComponent==>getMatchData`, error);
      alert("Faild to get data!")
    }
  }



}
