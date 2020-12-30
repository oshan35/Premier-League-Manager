import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import {MatTableDataSource} from '@angular/material/table';
import {ClubServices} from "../services/club-services.service";



export interface ClubData{
  clubId: string;
  clubName: string;
  playedMatchCount: string;
  wins:string;
  draws: string;
  losses: string;
}

let clubDataList: ClubData[] =[];


@Component({
  selector: 'app-clubs',
  templateUrl: './clubs.component.html',
  styleUrls: ['./clubs.component.css']
})

export class ClubsComponent{

  constructor(private clubServices:ClubServices){
    this.getClubData()

  }

  // ngOnInit(): void {
  // }
  displayedColumns: string[] = ['clubId', 'clubName', 'playedMatchCount', 'wins','draws','losses'];

  dataSource:any;





  async getClubData(){
    try {
      const data: any = await this.clubServices.getClubData();
      console.log(data[0]["clubName"]);
      for (var i in data){
        console.log(data)
        clubDataList.push({clubId:data[i]["clubId"],clubName:data[i]["clubName"],playedMatchCount:data[i]["playedMatchCount"],wins:data[i]["winCount"],draws:data[i]["drawCont"],losses:data[i]["lossCount"]})
      }
      this.dataSource=new MatTableDataSource(clubDataList);
      console.log("hello")

    } catch (error) {
      console.log(`[ERROR] ==> AppComponent==>getData`, error);
      alert("Faild to get data!")
    }
  }





  applyFilter(filterValue: string){
    this.dataSource.filter=filterValue.trim().toLowerCase();
  }

  onSubmit(f:NgForm,){

    var findClub = f.value.search;
    for(var i in clubDataList){
      if(clubDataList[i].clubName== findClub){
        this.dataSource=new MatTableDataSource([clubDataList[i]])
        console.log(clubDataList[i].clubId)
        break;
      }else if(findClub==""){
        this.dataSource=new MatTableDataSource(clubDataList);
      }
    }

    console.log("done")

  }

  InsertionSort(){
    this.dataSource=new MatTableDataSource(clubDataList)
    for(var i=1;i<clubDataList.length;i++){
      var itemValue=clubDataList[i].wins;
      var item=clubDataList[i];
      var j=i-1;
      while(j>=0 && clubDataList[j].wins>itemValue){
        clubDataList[j+1]=clubDataList[j];
        j--;
      }
      clubDataList[j+1]=item;

    }
    this.dataSource=new MatTableDataSource(clubDataList)
  }

  sortingDecendingOrder(){
    this.dataSource=new MatTableDataSource(clubDataList)
    for(var i=1;i<clubDataList.length;i++){
      var itemValue=clubDataList[i].wins;
      var item=clubDataList[i];
      var j=i-1;
      while(j>=0 && clubDataList[j].wins>itemValue){
        clubDataList[j+1]=clubDataList[j];
        j--;
      }
      clubDataList[j+1]=item;

    }
    this.dataSource=new MatTableDataSource(clubDataList.reverse())
  }
}
