package Domain.Jobs;
import Domain.Events.Event;
import Domain.User.*;
import Domain.System.*;
import Domain.Game.*;

import Domain.Events.GameEvent;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Referee extends Job{
    List<FootballGame> GamesToRef;
    List<Season> ActiveSeasons;
    boolean ActiveStatus;
    Member member;
    boolean Var;
    boolean Line;
    boolean Main;



    public Referee(Member member) {
        super(member);
        this.member = member;
        this.jobName="referee";
        GamesToRef = new ArrayList<FootballGame>();
        ActiveSeasons = new ArrayList<Season>();
        ActiveStatus = true;
        AlphaSystem system = AlphaSystem.getSystem();
        system.AddtoMemory(9, this);
        Var = false;
        Line = false;
        Main = false;
    }


    public void editFullName(String newName){
        getMember().setFull_name(newName);
    }

    public void editTraining(String training){
        SetTraining(training);
    }

    private void SetTraining(String training){//1.Main  2.Var  3.Line
            switch (training) {
                case "1":
                    if(Main)
                        Main = false;
                    else
                        Main = true;
                    break;
                case "2":
                    if(Var)
                        Var = false;
                    else
                        Var = true;
                    break;
                case "3":
                    if(Line)
                        Line = false;
                    else
                        Line = true;
                    break;
                default:
                    System.out.println("invalid action");
                    break;
        }
    }

    public boolean CanMainRef(){
        return Main;
    }

    public boolean CanVarRef(){
        return Var;
    }

    public boolean CanLineRef(){
        return Line;
    }

    public List<FootballGame> GetGames(){
        return GamesToRef;
    }

    public void DelistAsRef(){
        ActiveStatus = false;
        //Seasons.removeRef(this);
    }


    public void AddGameToRef(FootballGame ToRef){
        GamesToRef.add(ToRef);
    }

    public void AddSeasonToRef(Season SeasonToRef){
        ActiveSeasons.add(SeasonToRef);
    }

    //כרגע לא עובד
    private void AddEvent(FootballGame Game, GameEvent ChosenEvent){
        //Game.AddEvent();
    }

    private void EditEvents(FootballGame Game, GameEvent ChosenEvent){
        //if game ended in less than 5 hours
        //Game.RemoveEvent();
        //Game.AddEvent();
    }

    public boolean isActiveStatus() {
        return ActiveStatus;
    }

    public void addGoalEvent(int index, Time time, Team team, Player player){
        GamesToRef.get(index).addGoalEvent(time,team,player);
    }

    public void addFoulEvent(int index,Time time, Team team, Player player){
        GamesToRef.get(index).addFoulEvent(time,team,player);
    }

    public void addInjuryEvent(int index,Time time,Team team,Player player){
        GamesToRef.get(index).addInjuryEvent(time,team,player);
    }

    public void addOffsideEvent(int index,Time time,Team team,Player player){
        GamesToRef.get(index).addOffsideEvent(time,team,player);
    }

    public void addRedCardEvent(int index,Time time,Team team,Player player){
        GamesToRef.get(index).addRedCardEvent(time,team,player);
    }

    public void addYellowCardEvent(int index,Time time,Team team,Player player){
        GamesToRef.get(index).addYellowCardEvent(time,team,player);
    }

    public void addSubstitutionEvent(int index,Time time, Team team, Player in, Player out){
        GamesToRef.get(index).addSubstitutionEvent(time,team,in,out);
    }

    public String crateReport(int index){
        List<Event> gameEvents=GamesToRef.get(index).getEvents();
        String report="";
        for(Event e:gameEvents)
            report+=e.toString()+"$";
        return report;
    }
}
