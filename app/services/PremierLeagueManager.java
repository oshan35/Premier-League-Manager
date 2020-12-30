package services;

import entities.FootballClub;
import entities.Match;
import entities.SportsClub;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class PremierLeagueManager implements LeagueManager {
    public ArrayList<FootballClub> sportsClubArrayList;
    public ArrayList<Match> matchList;
    //    private final String clubDataFilePath="D:\\xl-comet\\CourseworkLevel05\\src\\com\\company\\clubdata.txt";
//    private final String matchDataFilePath="D:\\xl-comet\\CourseworkLevel05\\src\\com\\company\\matchData.txt";
    private final String clubDataFilePath="clubdata.txt";
    private final String matchDataFilePath="matchData.txt";

    public PremierLeagueManager() throws IOException, ClassNotFoundException {
        sportsClubArrayList=new ArrayList<FootballClub>();
        matchList=new ArrayList<Match>();
        readObjects();
        readMatch();
    }

    //Adding a new club to arrayList
    @Override
    public void addNewClub(FootballClub club){
        if (sportsClubArrayList.contains(club)){
            System.out.println("Club already exsists");
        }else {
            sportsClubArrayList.add(club);
        }
    }

    //deleting a club from arrayList
    @Override
    public void deleteClub(String clubID){
        //searchig through the arrayList for club
        for (SportsClub club:sportsClubArrayList){
            if (club.getClubId().equals(clubID)){
                sportsClubArrayList.remove(club);
                break;
            }
        }

    }


    //showing varies stat about clubs
    @Override
    public void showStatistic(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Search club -1 ");
        System.out.println("Print the List -2 ");
        int choiceOne=sc.nextInt();
        if (choiceOne==1){
            System.out.print("Enter the club ID: ");
            String clubID=sc.next();
            for (FootballClub club:sportsClubArrayList){
                if (club.getClubId().equals(clubID)){
                    System.out.println(club.toString());
                    break;
                }
            }
        }else if (choiceOne==2){
            for (SportsClub club:sportsClubArrayList){
                System.out.println(club.toString());
            }
        }
    }

    //displaying premeierLeag table
    @Override
    public void showPremierLeagTable(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Dicending Order - 1");
        System.out.println("Assending Order - 2");
        int choice=sc.nextInt();
        if (choice==2){
            Collections.reverse(sportsClubArrayList);
        }else{
            Collections.sort(sportsClubArrayList);
        }

        //Printing the table
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%20s %15s %20s %10s %10s %10s %20s %20s", "clubName", "Points", "PlayedMatchCount", "wins", "Losses","draws","Goals For","Goals Against");
        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
        for (FootballClub club:sportsClubArrayList){
            System.out.printf("%20s %15d %20d %10d %10d %10d %20d %20d",club.getClubName(),club.getClubPoints(),club.getPlayedMatchCount(),
                    club.getWinCount(),club.getLossCount(),club.getDrawCont(),club.getGoalsFor(),club.getGoalsAgainst());
            System.out.println();
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public void addMatch(Match match){
        if (matchList.contains(match)){
            System.out.println("Match has already added!");
        }else{
            matchList.add(match);
            saveMatchList();
        }
    }

    public FootballClub search(String clubId){
        for (FootballClub club:sportsClubArrayList){
            if (clubId.equals(club.getClubId())){
                return club;
            }
        }
        return null;
    }

    public void saveClubList(){

        try {

            FileOutputStream clubFile = new FileOutputStream(clubDataFilePath);
            ObjectOutputStream clubObj = new ObjectOutputStream(clubFile);
            for (FootballClub club:sportsClubArrayList){
                clubObj.writeObject(club);
            }

            clubObj.close();
            System.out.println("The Object  was succesfully written to a file");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void saveMatchList(){
        try {

            FileOutputStream fileOut = new FileOutputStream(matchDataFilePath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            for (Match match:matchList){
                objectOut.writeObject(match);
            }
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    private void readObjects() throws IOException, ClassNotFoundException {
        try{

            FileInputStream fileIn = new FileInputStream(clubDataFilePath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            while(true){
                FootballClub obj = (FootballClub) objectIn.readObject();
                sportsClubArrayList.add(obj);
            }


        }catch (Exception e){
            System.out.println("Club data didn't read!");
        }

    }

    private void readMatch(){
        try {
            FileInputStream matchFile=new FileInputStream(matchDataFilePath);
            ObjectInputStream matchObj=new ObjectInputStream(matchFile);
            while(true){
                Match match=(Match) matchObj.readObject();
                matchList.add(match);
            }

        }catch (Exception e){
            System.out.println("matches didn't read");
        }
    }





    public FootballClub getRandemClub(){
        Random randClub=new Random();
        return sportsClubArrayList.get(randClub.nextInt(sportsClubArrayList.size()));
    }
}
