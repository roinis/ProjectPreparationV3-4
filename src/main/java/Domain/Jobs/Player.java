package Domain.Jobs;
import Domain.Events.*;
import Domain.User.*;
import Domain.System.*;
import Domain.Game.*;

import Domain.Events.TewwtEvent;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Player extends Job implements Subject {
    public Player.Position getPosition() {
        return position;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public enum Position{ST,CF,CAM,LM,CM,RM,CDM,RW,LW,RB,LB,CB,GK}
    private Position position;
    private Team team;
    private List<Observer> observers;
    private LocalDate dateOfBirth;
    private List<String> tweets;


    public Player(Member member, Position position, LocalDate dateOfBirth) {
        super(member);
        this.team = null;
        this.position = position;
        this.dateOfBirth = dateOfBirth;
        this.jobName="player";
        tweets=new ArrayList<>();
        observers=new ArrayList<>();
        AlphaSystem alphaSystem= AlphaSystem.getSystem();
        alphaSystem.AddtoMemory(7,this);
    }

    public Team getTeam() {
        return team;
    }

    public String getStringBirthDate(){
        return dateOfBirth.toString();
    }

    private void setTeam(Team team) {
        this.team = team;
    }

    public String getPositionName(){
        return position.name();
    }

    public void addTweet(String tweet){
        tweets.add(tweet);
        notifyObserver(new TewwtEvent(tweet));
        AlphaSystem.getSystem().getDB().addPlayerTweetToDB(this,tweet);
    }

    public void deleteTweet(int index){
        tweets.remove(index);
    }

    @Override
    public void register(Observer newObserver) {
        observers.add(newObserver);
        AlphaSystem.getSystem().getDB().addPlayerObserverToDB(this,newObserver);
    }

    @Override
    public void unregister(Observer deleteObserver) {
        int observerIndex = observers.indexOf(deleteObserver);
        observers.remove(observerIndex);
    }

    @Override
    public void notifyObserver(Event newEvent) {
        for (Observer observer:observers) {
            observer.update(newEvent);
        }
    }

    public boolean addToTeam(Team team){
        if(this.team!=null){
            System.out.println("A player already has a team");
            return false;
        }
        setTeam(team);
        return true;
    }

    public boolean removeFromTeam(){
        if(team==null){
            System.out.println("A player doesn't has a team");
            return false;
        }
        setTeam(null);
        return true;
    }

    public void editFullName(String newName){
        getMember().setFull_name(newName);
    }

    public boolean editPosition(String newPosition){
        if(setPosition(newPosition))
            return true;
        return false;
    }

    public void editBirthDay(int year,int month,int day){
        setDateOfBirth(year,month,day);
    }

    private void setDateOfBirth(int year, int month,int day) {
        this.dateOfBirth =LocalDate.of(year,month,day);
    }

    private boolean setPosition(String position) {
        switch (position){
            case "ST":
                this.position = Position.ST;
                return true;
            case "CF":
                this.position = Position.CF;
                return true;
            case "CAM":
                this.position = Position.CAM;
                return true;
            case "LM":
                this.position = Position.LM;
                return true;
            case "CM":
                this.position = Position.CM;
                return true;
            case "RM":
                this.position = Position.RM;
                return true;
            case "CDM":
                this.position = Position.CDM;
                return true;
            case "RW":
                this.position = Position.RW;
                return true;
            case "LW":
                this.position = Position.LW;
                return true;
            case "RB":
                this.position = Position.RB;
                return true;
            case "LB":
                this.position = Position.LB;
                return true;
            case "CB":
                this.position = Position.CB;
                return true;
            case "GK":
                this.position = Position.GK;
                return true;
            default:
                return false;

        }
    }

    public List<Observer> getObservers() {
        return observers;
    }

    public List<String> getTweets() {
        return tweets;
    }
}
