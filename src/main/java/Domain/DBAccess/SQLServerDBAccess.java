package Domain.DBAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLServerDBAccess {
    public boolean insertToDB(int choice, String[] values) {
        return false;
    }

    public String[][] getAllLeagues() {
        return null;
    }

    public String[][] getAllLeagueSeasons(String LeagueName) {
        return null;
    }

    public String[][] getSeasonPositions(String LeagueName, String seasonYear) {
        return null;
    }

    public String[][] getFootballGames(String LeagueName, String seasonYear) {
        return null;
    }

    public String[][] getAllStadiums() {
        return null;
    }

    public String[][] getAllTeams() {
        return null;
    }

    public String[][] getTeamBudget(String teamName) {
        return null;
    }

    public String[][] getTeamOwners(String teamName) {
        return null;
    }

    public String[][] getTeamPlayers(String teamName) {
        return null;
    }

    public String[][] getOwnersAppointments(String user_id) {
        return null;
    }

    public String[][] getTeamCoaches(String teamName) {
        return null;
    }

    public String[][] getTeamsFans(String teamName) {
        return null;
    }

    public String[][] getTeamJobObservers(String teamName) {
        return null;
    }

    public String[][] getAllPlayers() {
        return null;
    }

    public String[][] getAllCoaches() {
        return null;
    }

    public String[][] getAllManagers() {
        return null;
    }

    public String[][] getManagersPermissions(String managerValue) {
        return null;
    }

    public String[][] getAllMainReferees() {
        return null;
    }

    public String[][] getAllLinesReferees() {
        return null;
    }

    public String[][] getAllVarReferees(){
        return null;
    }

    public String[][] getAllMembers() {
        return null;
    }

    public String[][] getAllOwners() {
        return null;
    }

    public String[][] getAllTickets() {
        return null;
    }

    public String[][] getGamesFouls(String s, String homeTeamName, String awayTeamName) {
        return null;
    }
}

/*
 * 1 -league
 * 2 -season
 * 3 -game
 * 4 -associationMember
 * 5 -main ref to season
 * 6 -lineRef to season
 * 7 -varRef to season
 * 8 -
 * 9 -GoalEvent
 * 10-InjuryEvent
 * 11-OffsideEvent
 * 12 - redCard
 * 13 -yellowCard
 * 14 -team
 * 15 -budget
 * 16 -budget info
 * 17 -team owners
 * 18 -owners Appoigntments
 * 19 - player to team
 * 20 - coach to team
 * 21 - manager to team
 * 22 - fan(observers) to team
 * 23 - jobs (observers) to tean
 * 24 - mainReferee
 * 25 - var REF
 * 26 - line Ref
 * 27 - player
 * 28 - member tweets
 * 29 - player observers
 * 30 - manager
 * 31 - manager permissions
 * 32 - team Owner
 * 33 - ticket
 * 34 - stadium
 * 35 - coach
 * 36 - member to ticket
 * 37 -member searches
 * 38 -member searches
 * 39 -members team followed
 * 40 -members players followed
 * 41 -members coaches followed
 * */