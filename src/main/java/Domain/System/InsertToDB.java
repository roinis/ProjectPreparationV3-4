package Domain.System;

import Domain.*;
import Domain.Association.Budget;
import Domain.DBAccess.*;
import Domain.Events.*;
import Domain.Game.*;
import Domain.Jobs.*;
import Domain.User.Member;
import javafx.util.Pair;

import java.time.LocalDateTime;

public class InsertToDB {
    SQLServerDBAccess DBAccess;
    public InsertToDB(){
        DBAccess=new SQLServerDBAccess();
    }

    public boolean addLeagueToDB(League league){
        String ppw=league.getScoringPolicy().getPointsPerWin()+"";
        String ppl=league.getScoringPolicy().getPointPerLoss()+"";
        String ppd=league.getScoringPolicy().getPointsPerDraw()+"";
        String[] values={league.getName(),league.getSchedulingPolicy().getNumOf2TeamsGames()+"",ppw,ppl,ppd};
        if(!DBAccess.insertToDB(1,values))
            return false;
        return true;
    }

    private boolean addSeasonToDB(Season season,String league) {
        String ppw=season.getScoringPolicy().getPointsPerWin()+"";
        String ppl=season.getScoringPolicy().getPointPerLoss()+"";
        String ppd=season.getScoringPolicy().getPointsPerDraw()+"";
        String[] values={league,season.getYear()+"",season.getSchedulingPolicy().getNumOf2TeamsGames()+"",ppw,ppl,ppd};
        if(!DBAccess.insertToDB(2,values))
            return false;
        return true;
    }

    private boolean addGameToDB(FootballGame game,String SeasonYear,String LeageName) {
        String stadium=game.getStadium().getStadiumName();
        String date=game.getDate().toString();
        String mainRef=game.getMainReferee().getMember().getUser_id();
        String varRef=game.getVarReferee().getMember().getUser_id();
        String line1=game.getLinesManLeft().getMember().getUser_id();
        String line2=game.getLinesManRight().getMember().getUser_id();
        String[] values={LeageName,SeasonYear,game.getHomeTeamName(),game.getAwayTeamName(),date,game.getHomeGoals()+"",game.getAwayGoals()+"",stadium,mainRef,varRef,line1,line2};
        if(!DBAccess.insertToDB(3,values))
            return false;
        return true;
    }

    /*private boolean addGameEventToFB(FootballGame game,Event gameEvent){
        if(gameEvent instanceof FoulEvent){
            String[] values={game.getDate().toString(),game.getHomeTeamName(),game.getAwayTeamName(),"1"};
            if(!DBAccess.insertToDB(8,values))
                return false;
        }
        else if(gameEvent instanceof GoalEvent){
            String[] values={game.getDate().toString(),game.getHomeTeamName(),game.getAwayTeamName(),"1",gameEvent.toString()};
            if(!DBAccess.insertToDB(9,values))
                return false;
        }else if(event instanceof InjuryEvent){
            String[] values={game.toString(),time,team,player};
            if(!DBAccess.insertToDB(10,values))
                return false;
        }else if(event instanceof OffsideEvent){
            String[] values={game.toString(),time,team,player};
            if(!DBAccess.insertToDB(11,values))
                return false;
        }else if(event instanceof RedCardEvent){
            String[] values={game.toString(),time,team,player};
            if(!DBAccess.insertToDB(12,values))
                return false;
        }else if(event instanceof YellowCardEvent){
            String[] values={game.toString(),time,team,player};
            if(!DBAccess.insertToDB(13,values))
                return false;
        }
    }*/

    private boolean addLeaguePositionToDB(LeaguePosition leaguePosition,String year,String leagueName){
        String gamesWon=leaguePosition.getGamesWon()+"";
        String gamesLoss=leaguePosition.getGamesLoss()+"";
        String gamesDraw=leaguePosition.getGamesDraw()+"";
        String goalsScored=leaguePosition.getGoalsScored()+"";
        String goalsRec=leaguePosition.getGoalsReceive()+"";
        String[] values={leagueName,year,leaguePosition.getTeam().getTeamName(),gamesWon,gamesLoss,gamesDraw,goalsScored,goalsRec};
        if(!DBAccess.insertToDB(4,values))
            return false;
        return true;
    }

    private boolean addMainRefereeToLeagueInDB(MainReferee ref,League league) {
        String[] values={ref.getMember().getUser_id(),league.getName()};
        if(!DBAccess.insertToDB(5,values))
            return false;
        return true;
    }

    private boolean addLineRefereeToLeagueInDB(LinesManReferee ref,League league) {
        String[] values={ref.getMember().getUser_id(),league.getName()};
        if(!DBAccess.insertToDB(6,values))
            return false;
        return true;
    }

    private boolean addVarRefereeToLeagueInDB(VarReferee ref,League league) {
        String[] values={ref.getMember().getUser_id(),league.getName()};
        if(!DBAccess.insertToDB(7,values))
            return false;
        return true;
    }


    /*private boolean addGameEventToDB(GameEvent event,FootballGame game) {
        String time=event.getEventGameTime().toString();
        String team=event.getEventTeam().toString();
        String player=event.getEventPlayer().getMember().getUser_id();
        if(event instanceof FoulEvent){
            String[] values={game.,time,team,player,((FoulEvent) event).getFouledPlayer().getMember().getUser_id()};
            if(!DBAccess.insertToDB(8,values))
                return false;
        }
        else if(event instanceof GoalEvent){
            String[] values={game.toString(),time,team,player};
            if(!DBAccess.insertToDB(9,values))
                return false;
        }else if(event instanceof InjuryEvent){
            String[] values={game.toString(),time,team,player};
            if(!DBAccess.insertToDB(10,values))
                return false;
        }else if(event instanceof OffsideEvent){
            String[] values={game.toString(),time,team,player};
            if(!DBAccess.insertToDB(11,values))
                return false;
        }else if(event instanceof RedCardEvent){
            String[] values={game.toString(),time,team,player};
            if(!DBAccess.insertToDB(12,values))
                return false;
        }else if(event instanceof YellowCardEvent){
            String[] values={game.toString(),time,team,player};
            if(!DBAccess.insertToDB(13,values))
                return false;
        }
        return true;
    }*/

    //need extra work for game events
    /*private boolean addGameOtherEventToDB(Event event,FootballGame game) {
        if (event instanceof GameDelayedEvent){

        }else if(event instanceof EndGameEvent){

        }else if(event instanceof GameReLocationEvent){

        }else  if(event instanceof StartGameEvent){

        }
        return true;
    }*/

    public boolean addTeamToDB(Team team){
        String[] values={team.getTeamName(),team.getStatus().ordinal()+"",team.getHomeStadium().getStadiumName()};
        if(!DBAccess.insertToDB(14,values))
            return false;
        return true;
    }

    public boolean addFanToTeamInDB(Team team,Member observer){
        String[] values={team.toString(),(observer).getUser_id()+""};
        if(!DBAccess.insertToDB(22,values))
            return false;
        return true;
    }

    public boolean addBudgetTransactionInDB(Team team, Pair<LocalDateTime,Pair<Double,String>> transaction) {
            String time=transaction.getKey().toString();
            String money=((Pair)transaction.getValue()).getKey()+"";
            String description=((Pair)transaction.getValue()).getValue()+"";
            String[] secValues={team.getTeamName(),time,money,description};
            if(!DBAccess.insertToDB(16,secValues))
                return false;
             return true;
    }

    public boolean addOwnerToTeamInDB(TeamOwner owner, Team team) {
        String[] values={owner.getMember().getUser_id(),team.toString()};
        if(!DBAccess.insertToDB(17,values))
            return false;
        return true;
    }

    public boolean addAppointmentToOwnerInDB(TeamOwner owner,Job appointment){
        String[] secValues={owner.getMember().getUser_id(),appointment.getMember().getUser_id()};
        if(!DBAccess.insertToDB(18,secValues))
            return false;
        return true;
    }

    private boolean addPlayerToTeamInDB(Player player, Team team) {
        String[] values={team.getTeamName(),player.getMember().getUser_id()};
        if(!DBAccess.insertToDB(19,values))
            return false;
        return true;
    }

    private boolean addCoachToTeamInDB(Coach coach, Team team) {
        String[] values={team.getTeamName(),coach.getMember().getUser_id()};
        if(!DBAccess.insertToDB(20,values))
            return false;
        return true;
    }

    private boolean addManagerToTeamInDB(TeamManager coach, Team team) {
        String[] values={team.getTeamName(),coach.getMember().getUser_id()};
        if(!DBAccess.insertToDB(21,values))
            return false;
        return true;
    }

    public boolean addMainRefereeToDB(MainReferee referee){
        String[] values={referee.getMember().getUser_id(),referee.isActiveStatus()+""};
        if(!DBAccess.insertToDB(24,values))
            return false;
        return true;
    }
    public boolean addLineRefereeToDB(MainReferee referee){
        String[] values={referee.getMember().getUser_id(),referee.isActiveStatus()+""};
        if(!DBAccess.insertToDB(25,values)){
            return false;
        }
        return true;
    }

    public boolean addVarRefereeToDB(MainReferee referee){
        String[] values={referee.getMember().getUser_id(),referee.isActiveStatus()+""};
        if(!DBAccess.insertToDB(26,values))
            return false;
        return true;
    }

    public boolean addTeamOwnerToDB(TeamOwner owner){
        String[] values={owner.getMember().getUser_id(),owner.getTeam().getTeamName()};
        if(!DBAccess.insertToDB(32,values))
            return false;
        return true;
    }

    public boolean addPlayerToDB(Player player){
        String[] values={player.getMember().getUser_id()+"",player.getPosition().ordinal()+"",player.getDateOfBirth().toString(),player.getTeam().getTeamName()};
        if(!DBAccess.insertToDB(27,values))
            return false;
        return true;
    }

    public boolean addPlayerTweetToDB(Player player,String tweet){
        String[] secValues={player.getMember().getUser_id(),tweet};
        if(!DBAccess.insertToDB(28,secValues))
            return false;
        return true;
    }

    public boolean addPlayerObserverToDB(Player player,Observer observer){
        String[] secValues={player.getMember().getUser_id(),((Member)observer).getUser_id()+""};
        if(!DBAccess.insertToDB(29,secValues))
            return false;
        return true;
    }
    public boolean addTeamManagerToDB(TeamManager manager){
        String[] values={manager.getMember().getUser_id(),manager.getJobName(),manager.getTeam().getTeamName()};
        if(!DBAccess.insertToDB(30,values))
            return false;
        return true;
    }

    public boolean addManagerPermissionToDB(TeamManager manager,TeamManager.Permissions permission){
        String[] secValues={manager.getMember().getUser_id(),permission.ordinal()+""};
        if(!DBAccess.insertToDB(31,secValues))
            return false;
        return true;
    }


    public boolean addTicketToDB(Ticket ticket){
        String[] values={ticket.getTicketID(),ticket.getWrittenByID(),ticket.getAnswer(),ticket.getComplaint(),ticket.getIfAnswered()+""};
        if(!DBAccess.insertToDB(33,values))
            return false;
        return true;
    }

    public boolean addStadiumToDB(Stadium stadium){
        String[] values={stadium.getStadiumName(),stadium.getCity()};
        if(!DBAccess.insertToDB(34,values))
            return false;
        return true;
    }

    public boolean addCoachToDB(Coach coach){
        String[] values={coach.getMember().getUser_id(),coach.getTeam().getTeamName(),coach.getCertification().ordinal()+"",coach.getJobInTheTeam()};
        if(!DBAccess.insertToDB(35,values))
            return false;
        for (String tweet:coach.getTweets()) {
            String[] secValues={coach.getMember().getUser_id(),tweet};
            if(!DBAccess.insertToDB(28,values))
                return false;
        }
        return true;
    }

    public boolean addMemberToDB(Member member){
        String[] values={member.getUser_id(),member.getFull_name(),member.getUser_name(),member.getUser_password(),member.isBlocked()+""};
        if(!DBAccess.insertToDB(36,values))
            return false;
        for (Ticket ticket:member.getTicketList()) {
            String[] secValues={member.getUser_id(),ticket.getTicketID()};
            if(!DBAccess.insertToDB(37,secValues))
                return false;
        }
        for (String search:member.getSearchHistory()) {
            String[] secValues={member.getUser_id(),search};
            if(!DBAccess.insertToDB(38,secValues))
                return false;
        }
        for (Team team:member.getTeamsFollowed()) {
            String[] secValues={member.getUser_id(),team.getTeamName()};
            if(!DBAccess.insertToDB(39,secValues))
                return false;
        }
        for (Player player:member.getPlayersFollowed()) {
            String[] secValues={member.getUser_id(),player.getMember().getUser_id()};
            if(!DBAccess.insertToDB(40,secValues))
                return false;
        }
        for (Coach coach:member.getCoachesFollowed()) {
            String[] secValues={member.getUser_id(),coach.getMember().getUser_id()};
            if(!DBAccess.insertToDB(41,secValues))
                return false;
        }
        return true;
    }
}
