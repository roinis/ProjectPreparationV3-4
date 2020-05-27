package Domain.System;

import Domain.Association.AssociationMember;
import Domain.DBAccess.*;
import Domain.Events.*;
import Domain.Game.*;
import Domain.Jobs.*;
import Domain.User.Member;
import Exceptions.DomainException;
import javafx.util.Pair;

import java.time.LocalDateTime;

public class InsertToDB {
    SQLServerDBAccess DBAccess;
    public InsertToDB(){
        DBAccess=new SQLServerDBAccess();
    }

    private void addLeagueToDB(League league){
        /*
        String ppw=league.getScoringPolicy().getPointsPerWin()+"";
        String ppl=league.getScoringPolicy().getPointPerLoss()+"";
        String ppd=league.getScoringPolicy().getPointsPerDraw()+"";
        String[] values={league.getName(),league.getSchedulingPolicy().getNumOf2TeamsGames()+"",ppw,ppl,ppd};

         */
        DBAccess.insertLeagueToDB(league.getName(),
                league.getSchedulingPolicy().getNumOf2TeamsGames(),
                getScoringPolicy().getPointsPerWin(),
                league.getScoringPolicy().getPointPerLoss(),
                league.getScoringPolicy().getPointsPerDraw());
    }

    private void addAssociationMemberToDB(AssociationMember member){
        String[] values={member.getUser_id()};
        DBAccess.insertToDB(4,values);
    }

    public void addSeasonToDB(Season season,String league) {
        /*
        String ppw=season.getScoringPolicy().getPointsPerWin()+"";
        String ppl=season.getScoringPolicy().getPointPerLoss()+"";
        String ppd=season.getScoringPolicy().getPointsPerDraw()+"";
        String[] values={league,season.getYear()+"",season.getSchedulingPolicy().getNumOf2TeamsGames()+"",ppw,ppl,ppd};
         */
        DBAccess.insertSeasonToDB(league.getName(),
                season.getYear(),
                league.getSchedulingPolicy().getNumOf2TeamsGames(),
                getScoringPolicy().getPointsPerWin(),
                league.getScoringPolicy().getPointPerLoss(),
                league.getScoringPolicy().getPointsPerDraw());
    }

    public void addGameToDB(FootballGame game,String SeasonYear,String LeageName) throws DomainException {
        String stadium=game.getStadium().getStadiumName();
        String date=game.getDate().toString();
        String mainRef=game.getMainReferee().getMember().getUser_id();
        String varRef=game.getVarReferee().getMember().getUser_id();
        String line1=game.getLinesManLeft().getMember().getUser_id();
        String line2=game.getLinesManRight().getMember().getUser_id();
        String[] values={LeageName,SeasonYear,game.getHomeTeamName(),game.getAwayTeamName(),date,game.getHomeGoals()+"",game.getAwayGoals()+"",stadium,mainRef,varRef,line1,line2};

        DBAccess.insertGameToDB(LeageName,SeasonYear,game.getHomeTeamName(),game.getAwayTeamName(),date,
                game.getHomeGoals(),game.getAwayGoals(),stadium,mainRef,varRef,line1,line2);
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

    public void addLeaguePositionToDB(LeaguePosition leaguePosition,String year,String leagueName){
        String gamesWon=leaguePosition.getGamesWon()+"";
        String gamesLoss=leaguePosition.getGamesLoss()+"";
        String gamesDraw=leaguePosition.getGamesDraw()+"";
        String goalsScored=leaguePosition.getGoalsScored()+"";
        String goalsRec=leaguePosition.getGoalsReceive()+"";
        String[] values={leagueName,year,leaguePosition.getTeam().getTeamName(),gamesWon,gamesLoss,gamesDraw,goalsScored,goalsRec};

        DBAccess.insertLeaguePositionToDB(leagueName,year,leaguePosition.getTeam().getTeamName(),
                gamesWon,gamesLoss,gamesDraw,goalsScored,goalsRec);
    }

    public void addMainRefereeToLeagueInDB(MainReferee ref,League league) {
        String[] values={ref.getMember().getUser_id(),league.getName()};
        DBAccess.insertMainRefereeToLeagueToDB(ref.getMember().getUser_id(),league.getName());
    }

    public void addLineRefereeToLeagueInDB(LinesManReferee ref,League league) {
        String[] values={ref.getMember().getUser_id(),league.getName()};
        DBAccess.insertLineRefereeToLeagueToDB(ref.getMember().getUser_id(),league.getName());
    }

    public void addVarRefereeToLeagueInDB(VarReferee ref,League league) {
        String[] values={ref.getMember().getUser_id(),league.getName()};
        DBAccess.insertVarRefereeToLeagueToDB(ref.getMember().getUser_id(),league.getName());
    }

    public void addGameEventToDB(GameEvent event, FootballGame game){
        String time=event.getEventGameTime().toString();

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

    public boolean addFanToTeamInDB(Team team,Member observer){
        String[] values={team.toString(),(observer).getUser_id()+""};
        if(!DBAccess.insertFanToTeamToDB(team.toString(),(observer).getUser_id()))
            return false;
        return true;
    }

    public void addBudgetTransactionInDB(Team team, Pair<LocalDateTime,Pair<Double,String>> transaction) {
            String time=transaction.getKey().toString();
            String money=((Pair)transaction.getValue()).getKey()+"";
            String description=((Pair)transaction.getValue()).getValue()+"";
            String[] secValues={team.getTeamName(),time,money,description};
            DBAccess.insertBudgetTransactionToDB(team.getTeamName(),time,money,description);
    }

    public void addTeamOwnerToTeamInDB(TeamOwner owner, Team team) {
        String[] values={owner.getMember().getUser_id(),team.toString()};
        DBAccess.insertTeamOwnerToTeamToDB(owner.getMember().getUser_id(),team.toString());
    }

    public void addAppointmentToOwnerInDB(TeamOwner owner,Job appointment){
        String[] secValues={owner.getMember().getUser_id(),appointment.getMember().getUser_id()};
        DBAccess.insertAppointmentToOwnerToDB(owner.getMember().getUser_id(),appointment.getMember().getUser_id());
    }

    public void addPlayerToTeamInDB(Player player, Team team) {
        String[] values={team.getTeamName(),player.getMember().getUser_id()};
        DBAccess.insertPlayerToTeamToDB(team.getTeamName(),player.getMember().getUser_id());
    }

    public void addCoachToTeamInDB(Coach coach, Team team) {
        String[] values={team.getTeamName(),coach.getMember().getUser_id()};
        DBAccess.insertCoachToTeamToDB(team.getTeamName(),coach.getMember().getUser_id());
    }

    public void addManagerToTeamInDB(TeamManager coach, Team team) {
        String[] values={team.getTeamName(),coach.getMember().getUser_id()};
        DBAccess.insertManagerToTeamToDB(team.getTeamName(),coach.getMember().getUser_id());
    }

    public void addPlayerTweetToDB(Player player,String tweet){
        String[] secValues={player.getMember().getUser_id(),tweet};
        DBAccess.insertPlayerTweetToDB(player.getMember().getUser_id(),tweet);
    }

    public void addPlayerObserverToDB(Player player,Observer observer){
        String[] secValues={player.getMember().getUser_id(),((Member)observer).getUser_id()+""};
        DBAccess.insertPlayerObserverToDB(player.getMember().getUser_id(),((Member)observer).getUser_id() +"");
    }

    public void addManagerPermissionToDB(TeamManager manager,TeamManager.Permissions permission){
        String[] secValues={manager.getMember().getUser_id(),permission.ordinal()+""};
        DBAccess.insertManagerPermissionToDB(manager.getMember().getUser_id(),permission.ordinal()+"");
    }

    private void addTeamToDB(Team team){
        String[] values={team.getTeamName(),team.getStatus().ordinal()+"",team.getHomeStadium().getStadiumName()};
        DBAccess.insertTeamToDB(team.getTeamName(),team.getStatus().ordinal()+"",team.getHomeStadium().getStadiumName());
    }

    private void addTeamManagerToDB(TeamManager manager){
        String[] values={manager.getMember().getUser_id(),manager.getJobName(),manager.getTeam().getTeamName()};
        DBAccess.insertTeamManagerToDB(manager.getMember().getUser_id(),manager.getJobName(),manager.getTeam().getTeamName());
    }

    private void addPlayerToDB(Player player){
        String[] values={player.getMember().getUser_id()+"",player.getPosition().ordinal()+"",player.getDateOfBirth().toString(),player.getTeam().getTeamName()};
        DBAccess.insertPlayerToDB(player.getMember().getUser_id()+"",player.getPosition().ordinal()+"",
                player.getDateOfBirth().toString(),player.getTeam().getTeamName());
    }

    private void addTicketToDB(Ticket ticket){
        String[] values={ticket.getTicketID(),ticket.getWrittenByID(),ticket.getAnswer(),ticket.getComplaint(),ticket.getIfAnswered()+""};
        DBAccess.insertTicketToDB(ticket.getTicketID(),ticket.getWrittenByID(),ticket.getAnswer()
                ,ticket.getComplaint(),ticket.getIfAnswered()+"");
    }

    private void addStadiumToDB(Stadium stadium){
        String[] values={stadium.getStadiumName(),stadium.getCity()};
        DBAccess.insertStadiumToDB(stadium.getStadiumName(),stadium.getCity());
    }

    private void addCoachToDB(Coach coach){
        String[] values={coach.getMember().getUser_id(),coach.getTeam().getTeamName(),coach.getCertification().ordinal()+"",coach.getJobInTheTeam()};
        DBAccess.insertCoachToDB(coach.getMember().getUser_id(),coach.getTeam().getTeamName(),
                coach.getCertification().ordinal()+"",coach.getJobInTheTeam());
    }

    public void addCoachTweetToDB(Coach coach,String tweet){
        String[] values={coach.getMember().getUser_id(),tweet};
        DBAccess.insertCoachTweetToDB(coach.getMember().getUser_id(),tweet);
    }

    private void addMemberToDB(Member member){
        String[] values={member.getUser_id(),member.getFull_name(),member.getUser_name(),member.getUser_password(),member.isBlocked()+""};
        DBAccess.insertMemberToDB(member.getUser_id(),member.getFull_name(),member.getUser_name()
                ,member.getUser_password(),member.isBlocked()+"");
    }

    public void addMemberTicketToDB(Member member,Ticket ticket){
        String[] secValues={member.getUser_id(),ticket.getTicketID()};
        DBAccess.insertMemberTicketToDB(member.getUser_id(),ticket.getTicketID());
    }

    public void addMemberSearchToDB(Member member,String search){
        String[] secValues={member.getUser_id(),search};
        DBAccess.insertMemberSearchToDB(member.getUser_id(),search);
    }

    public void addMemberTeamFollowed(Member member,Team team){
        String[] secValues={member.getUser_id(),team.getTeamName()};
        DBAccess.insertMemberTeamToDB(member.getUser_id(),team.getTeamName());
    }

    public void addMemberPlayerFollowed(Member member,Player player){
        String[] secValues={member.getUser_id(),player.getMember().getUser_id()};
        DBAccess.insertMemberPlayerFollowedToDB(member.getUser_id(),player.getMember().getUser_id());
    }

    public void addMemberCoachFollowed(Member member,Coach coach){
        String[] secValues={member.getUser_id(),coach.getMember().getUser_id()};
        DBAccess.insertMemberCoachFollowedToDB(member.getUser_id(),coach.getMember().getUser_id());
    }



    private boolean addMainRefereeToDB(MainReferee referee){
        String[] values={referee.getMember().getUser_id(),referee.isActiveStatus()+""};
        if(!DBAccess.insertMainRefereeToDB(referee.getMember().getUser_id(),referee.isActiveStatus()+""))
            return false;
        return true;
    }

    private boolean addLineRefereeToDB(LinesManReferee referee){
        String[] values={referee.getMember().getUser_id(),referee.isActiveStatus()+""};
        if(!DBAccess.insertLineRefereeToDB(referee.getMember().getUser_id(),referee.isActiveStatus()+"")){
            return false;
        }
        return true;
    }

    private boolean addVarRefereeToDB(VarReferee referee){
        String[] values={referee.getMember().getUser_id(),referee.isActiveStatus()+""};
        if(!DBAccess.insertVarRefereeToDB(referee.getMember().getUser_id(),referee.isActiveStatus()+""))
            return false;
        return true;
    }

    private boolean addTeamOwnerToDB(TeamOwner owner){
        String[] values={owner.getMember().getUser_id(),owner.getTeam().getTeamName()};
        if(!DBAccess.insertTeamOwnerToDB(owner.getMember().getUser_id(),owner.getTeam().getTeamName()))
            return false;
        return true;
    }

    public void insert(Object o) {
        if(o instanceof League)
            addLeagueToDB((League)o);
        if(o instanceof Member)
            addMemberToDB((Member)o);
        if(o instanceof AssociationMember)
            addAssociationMemberToDB((AssociationMember) o);
        if(o instanceof Coach)
            addCoachToDB((Coach) o);
        if(o instanceof Team)
            addTeamToDB((Team) o);
        if(o instanceof TeamManager)
            addTeamManagerToDB((TeamManager) o);
        if(o instanceof TeamOwner)
            addTeamOwnerToDB((TeamOwner) o);
        if(o instanceof Player)
            addPlayerToDB((Player) o);
        if(o instanceof MainReferee)
            addMainRefereeToDB((MainReferee) o);
        if(o instanceof LinesManReferee)
            addLineRefereeToDB((LinesManReferee)o);
        if(o instanceof VarReferee)
            addVarRefereeToDB((VarReferee)o);
        if(o instanceof Ticket)
            addTicketToDB((Ticket) o);
        if(o instanceof Stadium)
            addStadiumToDB((Stadium) o);
    }


    public void addFoulEventToDB(FoulEvent event, FootballGame game) {
        String time=event.getEventGameTime().toString();
        String team=event.getEventTeam().getTeamName();
        String player=event.getEventPlayer().getMember().getUser_id();
        String[] values={game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName(),time,team,player,event.getFouledPlayer().getMember().getUser_id()};
        DBAccess.insertFoulEventToDB(game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName(),time,
                team,player,event.getFouledPlayer().getMember().getUser_id());
    }


    public void addGoalEventToDB(GoalEvent event, FootballGame game) {
        String time=event.getEventGameTime().toString();
        String team=event.getEventTeam().getTeamName();
        String player=event.getEventPlayer().getMember().getUser_id();
        String[] values={game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName(),time,team,player};
        DBAccess.insertGoalEventToDB(game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName(),time,team,player);
    }



    public void addInjuryEventToDB(InjuryEvent event, FootballGame game) {
        String time=event.getEventGameTime().toString();
        String team=event.getEventTeam().getTeamName();
        String player=event.getEventPlayer().getMember().getUser_id();
        String[] values={game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName(),time,team,player};
        DBAccess.insertInjuryEventToDB(game.getDate()+"",game.getHomeTeamName(),
                game.getAwayTeamName(),time,team,player);
    }

    public void addOffsideEventToDB(OffsideEvent event, FootballGame game) {
        String time=event.getEventGameTime().toString();
        String team=event.getEventTeam().getTeamName();
        String player=event.getEventPlayer().getMember().getUser_id();
        String[] values={game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName(),time,team,player};
        DBAccess.insertOffsideEventToDB(game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName(),time,team,player);
    }

    public void addRedCardEventToDB(RedCardEvent event, FootballGame game) {
        String time=event.getEventGameTime().toString();
        String team=event.getEventTeam().getTeamName();
        String player=event.getEventPlayer().getMember().getUser_id();
        String[] values={game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName(),time,team,player};
        DBAccess.insertRedCardEventToDB(game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName(),time,team,player);
    }

    public void addYellowCardEventToDB(YellowCardEvent event, FootballGame game) {
        String time=event.getEventGameTime().toString();
        String team=event.getEventTeam().getTeamName();
        String player=event.getEventPlayer().getMember().getUser_id();
        String[] values={game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName(),time,team,player};
        DBAccess.insertYellowCardEventToDB(game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName(),time,team,player);
    }


    public void addStartGameEventToDB(StartGameEvent event, FootballGame game) {
        String time=event.getEventGameTime().toString();
        String[] values={game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName(),time};
        DBAccess.insertStartGameEventToDB(game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName(),time);
    }

    public void addSubstituteEventToDB(SubstitutionEvent event, FootballGame game) {
        String time=event.getEventGameTime().toString();
        String ingoingID=event.getIngoingPlayer().getMember().getUser_id();
        String outgoingID=event.getEventPlayer().getMember().getUser_id();
        String[] values={game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName(),event.getEventTeam().getTeamName(),outgoingID,ingoingID,time};
        DBAccess.insertSubstituteEventToDB(game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName(),
                event.getEventTeam().getTeamName(),outgoingID,ingoingID,time);
    }

    //this dont work in real!
    public void addGameDelayedEventToDB(GameDelayedEvent event, FootballGame game) {
        String[] values={game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName(),event.getGameDelayedTime().toString(),event.getGameOriginalTime().toString()};
        DBAccess.insertGameDelayedEventToDB(game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName(),
                event.getGameDelayedTime().toString(),event.getGameOriginalTime().toString());
    }

    public void addGameRelocationEventToDB(GameReLocationEvent event, FootballGame game) {
        String[] values={game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName(),event.getGameNewLocation().getStadiumName(),event.getGameOriginalLocation().getStadiumName()};
        DBAccess.insertGameRelocationEventToDB(game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName(),
                event.getGameNewLocation().getStadiumName(),event.getGameOriginalLocation().getStadiumName());
    }

    public void addGameEndEvent(EndGameEvent event,FootballGame game){
        String[] values={game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName()};
        DBAccess.insertGameEndEventToDB(game.getDate()+"",game.getHomeTeamName(),game.getAwayTeamName());
    }
}
/*

 */
