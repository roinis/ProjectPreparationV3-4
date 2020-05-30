package Domain.Game;
import Domain.Events.*;
import Domain.User.*;
import Domain.System.*;
import Domain.Jobs.*;
import Domain.Association.*;


import java.sql.Time;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class FootballGame implements Subject {
    private Season season;
    private LinkedList<Event> events;
    private Stadium stadium;
    private Team home;
    private Team away;
    private LocalDateTime date;
    private MainReferee mainReferee;
    private LinesManReferee linesManLeft;
    private LinesManReferee linesManRight;
    private VarReferee varReferee;
    private int homeGoals;
    private int awayGoals;
    private List<Observer> fanObservers;
    public FootballGame(Season season, Team home, Team away, LocalDateTime date) {
        this.season = season;
        this.home = home;
        this.away = away;
        this.stadium=home.getHomeStadium();
        this.date=date;
        this.events=new LinkedList<>();
        this.homeGoals=0;
        this.awayGoals=0;
        this.fanObservers=new LinkedList<>();
    }

    public String getHomeTeamName(){
        return home.getTeamName().toLowerCase();
    }
    public String getAwayTeamName(){
        return away.getTeamName().toLowerCase();
    }

    public void homeScoreGoal(){
        this.homeGoals++;
    }
    public void awayScoreGoal(){
        this.awayGoals++;
    }

    public void gameHasEnded(){
        if(homeGoals>awayGoals){
            season.addWin(home,homeGoals,awayGoals);
            season.addLoss(away,awayGoals,homeGoals);
        }else  if (awayGoals>homeGoals){
            season.addWin(away,awayGoals,homeGoals);
            season.addLoss(home,homeGoals,awayGoals);
        }else {
            season.addDraw(home,homeGoals,awayGoals);
            season.addDraw(away,awayGoals,homeGoals);
        }
    }
    public boolean addEvent(Event event, Referee currReff, LocalDateTime dateTime){
        if(!currReff.getMemberUserName().equals(mainReferee.getMemberUserName())|| Duration.between(date, dateTime).toHours()>5){
            return false;
        }
        events.add(event);
        return true;
    }


    public Stadium getStadium() {
        return stadium;
    }

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }

    public MainReferee getMainReferee() {
        return mainReferee;
    }

    public void setMainReferee(MainReferee mainReferee) {
        this.mainReferee = mainReferee;
    }

    public LinesManReferee getLinesManLeft() {
        return linesManLeft;
    }

    public void setLinesManLeft(LinesManReferee linesManLeft) {
        this.linesManLeft = linesManLeft;
    }

    public LinesManReferee getLinesManRight() {
        return linesManRight;
    }

    public void setLinesManRight(LinesManReferee linesManRight) {
        this.linesManRight = linesManRight;
    }

    public void addFoulEvent(Time time,Team team,Player player){
        FoulEvent foul=new FoulEvent(time,team,player);
        events.add(foul);
        notifyObserver(foul);
        //AlphaSystem.getSystem().getDB().addFoulEventToDB(foul,this);
    }

    public void addGoalEvent(Time time,Team team,Player player){
        GoalEvent goal=new GoalEvent(time,team,player);
        events.add(goal);
        if (team.equals(home))
            homeScoreGoal();
        else
            awayScoreGoal();
        notifyObserver(goal);
        //AlphaSystem.getSystem().getDB().addGoalEventToDB(goal,this);
    }

    public void addInjuryEvent(Time time,Team team,Player player){
        InjuryEvent injury=new InjuryEvent(time,team,player);
        events.add(injury);
        notifyObserver(injury);
        //AlphaSystem.getSystem().getDB().addInjuryEventToDB(injury,this);
    }

    public void addOffsideEvent(Time time,Team team,Player player){
        OffsideEvent offside=new OffsideEvent(time,team,player);
        events.add(offside);
        notifyObserver(offside);
       // AlphaSystem.getSystem().getDB().addOffsideEventToDB(offside,this);
    }

    public void addRedCardEvent(Time time,Team team,Player player){
        RedCardEvent redCard=new RedCardEvent(time,team,player);
        events.add(redCard);
        notifyObserver(redCard);
        //AlphaSystem.getSystem().getDB().addRedCardEventToDB(redCard,this);
    }

    public void addYellowCardEvent(Time time,Team team,Player player){
        YellowCardEvent yellowCard=new YellowCardEvent(time,team,player);
        events.add(yellowCard);
        notifyObserver(yellowCard);
        //AlphaSystem.getSystem().getDB().addYellowCardEventToDB(yellowCard,this);
    }

    public void addStartEvent(LocalDateTime time){
        StartGameEvent start=new StartGameEvent(time,home,away);
        notifyObserver(null);
        events.add(start);
        //AlphaSystem.getSystem().getDB().addStartGameEventToDB(start,this);
    }

    public void addSubstitutionEvent(Time time,Team team,Player in,Player out){
        SubstitutionEvent substitute=new SubstitutionEvent(time,team,in,out);
        notifyObserver(substitute);
        events.add(substitute);
        //AlphaSystem.getSystem().getDB().addSubstituteEventToDB(substitute,this);
    }

    public void addGameDelayedEvent(LocalDateTime newDate){
        GameDelayedEvent delayed=new GameDelayedEvent(date,newDate,home,away);
        notifyObserver(delayed);
        //AlphaSystem.getSystem().getDB().addGameDelayedEventToDB(delayed,this);

    }

    public void addRelocationGameEvent(Stadium newLocation){
        GameReLocationEvent relocation=new GameReLocationEvent(stadium,newLocation,home,away);
        notifyObserver(relocation);
        events.add(relocation);
        //AlphaSystem.getSystem().getDB().addGameRelocationEventToDB(relocation,this);
    }

    public void addEndGameEvent(LocalDateTime eventGameTime){
        EndGameEvent end=new EndGameEvent(eventGameTime,home,away);
        events.add(end);
        gameHasEnded();
    }


    public int getNumOfEvents() {
        return events.size();
    }





    @Override
    public void register(Observer observer) {
        fanObservers.add(observer);
    }

    @Override
    public void unregister(Observer observer) {
        fanObservers.remove(observer);
    }

    @Override
    public void notifyObserver(Event newEvent) {
        for (Observer observer:fanObservers) {
            observer.update(newEvent);
        }
    }

    public List<Observer> getFanObservers(){
        return fanObservers;
    }

    public VarReferee getVarReferee() {
        return varReferee;
    }

    public void setVarReferee(VarReferee varReferee) {
        this.varReferee = varReferee;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public LinkedList<Event> getEvents() {
        return events;
    }

    public int getHomeGoals() {
        return homeGoals;
    }

    public int getAwayGoals() {
        return awayGoals;
    }

    public void addEventFromDB(Event event) {
        events.add(event);
    }
}
