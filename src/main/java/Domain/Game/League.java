package Domain.Game;
import  Domain.System.*;
import  Domain.Jobs.*;
import Exceptions.DomainException;
import javafx.util.Pair;

import java.util.LinkedList;
import java.util.List;

public class League {
    private String name;
    private LinkedList<Season> seasons;
    private SchedulingPolicy schedulingPolicy;
    private ScoringPolicy scoringPolicy;
    private List<MainReferee> leagueReferees;
    private List<LinesManReferee> leagueLinesmans;
    private List<VarReferee> leagueVarReferees;

    public League(String name, SchedulingPolicy schedulingPolicy, ScoringPolicy scoringPolicy) {
        this.name = name;
        this.seasons=new LinkedList<Season>();
        this.schedulingPolicy=schedulingPolicy;
        this.scoringPolicy=scoringPolicy;
        this.leagueReferees=new LinkedList<MainReferee>();
        this.leagueLinesmans=new LinkedList<LinesManReferee>();
        this.leagueVarReferees=new LinkedList<VarReferee>();
        AlphaSystem alphaSystem= AlphaSystem.getSystem();
        alphaSystem.AddtoMemory(1,this);
    }

    public void addRefereesToSeason(Season season){
        season.scheduleMainReferees(leagueReferees);
        season.scheduleLinesMansReferees(leagueLinesmans);
        season.scheduleVarReferees(leagueVarReferees);
    }

    public String getName(){ return name;}


    public void addMainReferee(MainReferee referee){
        leagueReferees.add(referee);
        AlphaSystem.getSystem().getDB().addMainRefereeToLeagueInDB(referee,this);
    }

    public void addLinesManReferee(LinesManReferee referee){
        leagueLinesmans.add(referee);
        AlphaSystem.getSystem().getDB().addLineRefereeToLeagueInDB(referee,this);
    }

    public void addVarReferee(VarReferee referee){
        leagueVarReferees.add(referee);
        AlphaSystem.getSystem().getDB().addVarRefereeToLeagueInDB(referee,this);
    }

    public boolean removeMainReferee(MainReferee referee){
        for (MainReferee currReferee:leagueReferees) {
            if(referee.getMemberUserName().equals(currReferee.getMemberUserName()))
                return leagueReferees.remove(currReferee);
        }
        return false;
    }

    public boolean removeLinesManReferee(LinesManReferee referee){
        for (LinesManReferee currReferee:leagueLinesmans) {
            if(referee.getMemberUserName().equals(currReferee.getMemberUserName()))
                return leagueLinesmans.remove(currReferee);
        }
        return false;
    }

    public boolean removeVarReferee(VarReferee referee){
        for (VarReferee currReferee:leagueVarReferees) {
            if(referee.getMemberUserName().equals(currReferee.getMemberUserName()))
                return leagueLinesmans.remove(currReferee);
        }
        return false;
    }
    public List<MainReferee> getLeagueReferees(){
        return leagueReferees;
    }

    public List<LinesManReferee> getLeagueLinesmans() {
        return leagueLinesmans;
    }

    public List<VarReferee> getLeagueVarReferees() {
        return leagueVarReferees;
    }

    public boolean addSeason(int year, SchedulingPolicy schedulingPolicy, ScoringPolicy scoringPolicy){
        if(schedulingPolicy==null)
            schedulingPolicy=this.schedulingPolicy;
        if(scoringPolicy==null)
            scoringPolicy=this.scoringPolicy;
        Season newSeason =new Season(year, schedulingPolicy,scoringPolicy);
        for (Season season:seasons) {
            if(season.getYear()==year)
                return false;
        }
        seasons.add(newSeason);
        AlphaSystem.getSystem().getDB().addSeasonToDB(newSeason,name);
        return true;
    }

    public Season getSpecSeason(int year){
        for (Season season:seasons) {
            if(season.getYear()==year)
                return season;
        }
        return null;
    }

    public LinkedList<Pair<String,Integer>> getSeasonRankings(int year){
        Season season=getSpecSeason(year);
        if(season==null)
            return null;
        LinkedList<Pair<String,Integer>> sortedRankingsString=new LinkedList<Pair<String,Integer>>();
        LinkedList<Pair<LeaguePosition, Integer>> sortdRankings = season.getRankings();
        sortdRankings.getFirst().getKey().getTeam();
        for (int i=0;i<sortdRankings.size();i++){
            String teamName=sortdRankings.get(i).getKey().getTeam().getTeamName();
            Integer points=sortdRankings.get(i).getValue();
            sortedRankingsString.add(new Pair<String,Integer>(teamName,points));
        }
        return sortedRankingsString;
    }
    public Season getCurrentSeason(){
        int index = 0;
        int max = 0;
        for(int i = 0;i<seasons.size();i++){
            if(seasons.get(i).getYear()>max){
                max = seasons.get(i).getYear();
                index=i;
            }
        }
        return seasons.get(index);
    }

    public SchedulingPolicy getSchedulingPolicy() {
        return schedulingPolicy;
    }

    public void setSchedulingPolicy(int numOfMatches) {
        this.schedulingPolicy = new SchedulingPolicy(numOfMatches);
    }

    public ScoringPolicy getScoringPolicy() {
        return scoringPolicy;
    }

    public void setScoringPolicy(int pPerWin,int pPerLoss,int pPerDraw) {
        this.scoringPolicy = new ScoringPolicy(pPerWin,pPerDraw,pPerLoss);
    }

    public boolean setSeasonSchedulingPolicy(int year,int numOfMatches) {
        Season season=getSpecSeason(year);
        if(season.setSchedulingPolicy(numOfMatches))
            return true;
        return false;
    }

    public boolean setSeasonScoringPolicy(int year,int pPerWin,int pPerLoss,int pPerDraw) {
        Season season=getSpecSeason(year);
        if(season.setScoringPolicy(pPerWin,pPerDraw,pPerLoss))
            return true;
        return false;
    }

    public void AddRef(Referee refToAdd) {
        if(refToAdd instanceof MainReferee)
            addMainReferee((MainReferee) refToAdd);
        if(refToAdd instanceof LinesManReferee)
            addLinesManReferee((LinesManReferee) refToAdd);
        if(refToAdd instanceof VarReferee)
            addVarReferee((VarReferee) refToAdd);
    }

    public void addSeasonFromDB(Season currSeason) throws DomainException {
        for (Season existingSeason:seasons) {
            if(currSeason.getYear()==existingSeason.getYear())
                throw  new DomainException("reloading from db error. season already exist");
        }
        seasons.add(currSeason);
    }

    public List<Season>  getAllSeasons() {
        return seasons;
    }
}
