package DBAccess;

import static org.junit.Assert.*;
import org.junit.Test;
import Domain.DBAccess.SQLServerDBAccess;


public class SQLServerDBAccessTest {

    @Test
    public void getConnection() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        sqlServerDBAccess.getConnection();
    }

    @Test
    public void getAllLeagues() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getAllLeagues();
        assertEquals(3,ret.length);
        assertEquals("Bundes League",ret[0][0]);
    }

    @Test
    public void getAllLeagueSeasons() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getAllLeagueSeasons("Bundes League");
        assertEquals(2,ret.length);
        assertEquals("1999",ret[0][0]);
    }

    @Test
    public void getSeasonPositions() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getSeasonPositions("japanika","1999");
        assertEquals(4,ret.length);
    }

    @Test
    public void getFootballGames() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getFootballGames("japanika","1999");
        assertEquals(2,ret.length);
    }

    @Test
    public void getAllStadiums() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getAllStadiums();
        assertEquals(5,ret.length);
    }

    @Test
    public void getAllTeams() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getAllStadiums();
        assertEquals(5,ret.length);
    }

    @Test
    public void getTeamBudget() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getTeamBudget("hbs");
        assertEquals(3,ret.length);
    }

    @Test
    public void getTeamOwners() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getTeamOwners("hbs");
        assertEquals(3,ret.length);
        ret = sqlServerDBAccess.getTeamOwners("beitar");
        assertEquals(2,ret.length);
    }

    @Test
    public void getTeamPlayers() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getTeamPlayers("hbs");
        assertEquals(5,ret.length);
        ret = sqlServerDBAccess.getTeamPlayers("tel aviv");
        assertEquals(3,ret.length);
    }

    @Test
    public void getOwnersAppointments() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getOwnersAppointments("12");
        assertEquals(2,ret.length);
        ret = sqlServerDBAccess.getOwnersAppointments("9");
        assertEquals(1,ret.length);
    }

    @Test
    public void getTeamCoaches() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getTeamCoaches("hbs");
        assertEquals(2,ret.length);
        ret = sqlServerDBAccess.getTeamCoaches("tel aviv");
        assertEquals(1,ret.length);
    }

    @Test
    public void getTeamsFans() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getTeamsFans("hbs");
        assertEquals(3,ret.length);
    }

    @Test
    public void getTeamJobObservers() {
    }

    @Test
    public void getAllPlayers() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getAllPlayers();
        assertEquals(8,ret.length);
    }

    @Test
    public void getAllCoaches() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getAllCoaches();
        assertEquals(4,ret.length);
    }

    @Test
    public void getAllManagers() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getAllManagers();
        assertEquals(2,ret.length);
    }

    @Test
    public void getManagersPermissions() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getManagersPermissions("25");
        assertEquals(2,ret.length);
        ret = sqlServerDBAccess.getManagersPermissions("26");
        assertEquals(1,ret.length);
    }

    @Test
    public void getAllMainReferees() {
    }

    @Test
    public void getAllLinesReferees() {
    }

    @Test
    public void getAllVarReferees() {
    }

    @Test
    public void getAllMembers() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getAllMembers();
        assertEquals(36,ret.length);
    }

    @Test
    public void getAllOwners() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getAllOwners();
        assertEquals(7,ret.length);
    }

    @Test
    public void getAllTickets() {
        SQLServerDBAccess sqlServerDBAccess = new Domain.DBAccess.SQLServerDBAccess("jdbc:sqlserver://localhost:1433;databaseName=FootballTest;user=sa;password=Warning11");
        String[][] ret = sqlServerDBAccess.getAllTickets();
        assertEquals(3,ret.length);
    }

    @Test
    public void getGamesFouls() {
    }

    @Test
    public void getGameGoals() {
    }

    @Test
    public void getGameInjurys() {
    }

    @Test
    public void getGameoffsides() {
    }

    @Test
    public void getGameRedCardEvents() {
    }

    @Test
    public void getGameYellowCardEvents() {
    }

    @Test
    public void getGameStartEvent() {
    }

    @Test
    public void getGameSubstitutionEvent() {
    }

    @Test
    public void getGamerelocationEvent() {
    }

    @Test
    public void getGameEndEvent() {
    }

    @Test
    public void insertLeagueToDB() {
    }

    @Test
    public void insertAssociationMemberToDB() {
    }

    @Test
    public void insertSeasonToDB() {
    }

    @Test
    public void insertGameToDB() {
    }

    @Test
    public void insertLeaguePositionToDB() {
    }

    @Test
    public void insertMainRefereeToLeagueToDB() {
    }

    @Test
    public void insertLineRefereeToLeagueToDB() {
    }

    @Test
    public void insertVarRefereeToLeagueToDB() {
    }

    @Test
    public void insertFanToTeamToDB() {
    }

    @Test
    public void insertBudgetTransactionToDB() {
    }

    @Test
    public void insertTeamOwnerToTeamToDB() {
    }

    @Test
    public void insertAppointmentToOwnerToDB() {
    }

    @Test
    public void insertPlayerToTeamToDB() {
    }

    @Test
    public void insertCoachToTeamToDB() {
    }

    @Test
    public void insertManagerToTeamToDB() {
    }

    @Test
    public void insertPlayerTweetToDB() {
    }

    @Test
    public void insertPlayerObserverToDB() {
    }

    @Test
    public void insertManagerPermissionToDB() {
    }

    @Test
    public void insertTeamToDB() {
    }

    @Test
    public void insertTeamManagerToDB() {
    }

    @Test
    public void insertPlayerToDB() {
    }

    @Test
    public void insertTicketToDB() {
    }

    @Test
    public void insertStadiumToDB() {
    }

    @Test
    public void insertCoachToDB() {
    }

    @Test
    public void insertCoachTweetToDB() {
    }

    @Test
    public void insertMemberToDB() {
    }

    @Test
    public void insertMemberTicketToDB() {
    }

    @Test
    public void insertMemberSearchToDB() {
    }

    @Test
    public void insertMemberTeamToDB() {
    }

    @Test
    public void insertMemberPlayerFollowedToDB() {
    }

    @Test
    public void insertMemberCoachFollowedToDB() {
    }

    @Test
    public void insertMainRefereeToDB() {
    }

    @Test
    public void insertLineRefereeToDB() {
    }

    @Test
    public void insertVarRefereeToDB() {
    }

    @Test
    public void insertTeamOwnerToDB() {
    }

    @Test
    public void insertFoulEventToDB() {
    }

    @Test
    public void insertGoalEventToDB() {
    }

    @Test
    public void insertInjuryEventToDB() {
    }

    @Test
    public void insertOffsideEventToDB() {
    }

    @Test
    public void insertRedCardEventToDB() {
    }

    @Test
    public void insertYellowCardEventToDB() {
    }

    @Test
    public void insertSubstituteEventToDB() {
    }

    @Test
    public void insertGameDelayedEventToDB() {
    }

    @Test
    public void insertGameRelocationEventToDB() {
    }

    @Test
    public void insertGameEndEventToDB() {
    }

    @Test
    public void insertGameStartEventToDB() {
    }
}
