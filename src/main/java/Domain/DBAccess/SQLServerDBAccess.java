package Domain.DBAccess;

import jdk.nashorn.internal.runtime.ECMAException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SQLServerDBAccess {
    private String connectionUrl;

    public SQLServerDBAccess(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }

    public  Connection getConnection(){
        Connection DBconnection = null;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=Football;user=sa;password=Warning11";
            DBconnection = DriverManager.getConnection(connectionUrl);
        }catch (Exception e){
            e.printStackTrace();
        }
        return DBconnection;
    }



    public boolean insertToDB(int choice, String[] values) {
        return false;
    }

    public String[][] getAllLeagues() {
        Connection DBconnection = null;
        ResultSet resultSet = null;
        String query = "SELECT [LeagueName]\n" +
                "      ,[NumOfTwoTeamsGames]\n" +
                "      ,[PointsPerWin]\n" +
                "      ,[PointsPerDraw]\n" +
                "      ,[PointsPerLoss]\n" +
                "  FROM [dbo].[League]";
        try{
            DBconnection = getConnection();
            Statement statement = DBconnection.createStatement();
            resultSet = statement.executeQuery(query);


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            };
        }
        return rsToStringArray(resultSet);
    }

    public String[][] getAllLeagueSeasons(String LeagueName) {
        Connection DBconnection = null;
        ResultSet resultSet = null;
        String query = "SELECT [SYear]\n" +
                "      ,[LeagueName]\n" +
                "      ,[NumOfTwoTeamsGames]\n" +
                "      ,[PointsPerWin]\n" +
                "      ,[PointsPerDraw]\n" +
                "      ,[PointsPerLoss]\n" +
                "      FROM [dbo].[Season]" +
                "      WHERE LeagueName = ?";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,LeagueName);
            resultSet = statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            };
        }
        return rsToStringArray(resultSet);
    }

    public String[][] getSeasonPositions(String LeagueName, String seasonYear) {
        Connection DBconnection = null;
        ResultSet resultSet = null;
        String query = "SELECT" +
                "      [TeamName]\n" +
                "      ,[GamesWin]\n" +
                "      ,[GamesLoss]\n" +
                "      ,[GamesDraw]\n" +
                "      ,[GoalsScored]\n" +
                "      ,[GoalsReceived]\n" +
                "      ,[LeagueName]\n" +
                "      ,[SYear]\n" +
                "  FROM [dbo].[LeaguePosition]" +
                "      WHERE LeagueName = ? and SYear = ?";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,LeagueName);
            statement.setString(2,seasonYear);
            resultSet = statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            };
        }
        return rsToStringArray(resultSet);
    }

    public String[][] getFootballGames(String LeagueName, String seasonYear) {
        Connection DBconnection = null;
        ResultSet resultSet = null;
        String query = "SELECT [HomeTeamName]\n" +
                "      ,[AwayTeamName]\n" +
                "      ,[GameDate]\n" +
                "      ,[MainRefereeID]\n" +
                "      ,[LeftRefereeID]\n" +
                "      ,[RightRefereeID]\n" +
                "      ,[VarRefereeID]\n" +
                "      ,[HomeGoals]\n" +
                "      ,[AwayGoals]\n" +
                "      ,[SeasonYear]\n" +
                "      ,[LeagueName]\n" +
                "      ,[StadiumName]\n" +
                "  FROM [dbo].[FootballGame]\n" +
                "  WHERE [LeagueName] = ? and [SeasonYear] = ?";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,LeagueName);
            statement.setString(2,seasonYear);
            resultSet = statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            };
        }
        return rsToStringArray(resultSet);
    }

    public String[][] getAllStadiums() {
        Connection DBconnection = null;
        ResultSet resultSet = null;
        String query = "SELECT [SName]\n" +
                "      ,[City]\n" +
                "  FROM [dbo].[Stadium]";
        try{
            DBconnection = getConnection();
            Statement statement = DBconnection.createStatement();
            resultSet = statement.executeQuery(query);


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            };
        }
        return rsToStringArray(resultSet);
    }

    public String[][] getAllTeams() {
        Connection DBconnection = null;
        ResultSet resultSet = null;
        String query = "SELECT [TeamName]\n" +
                "      ,[StatusID]\n" +
                "      ,[StadiumName]\n" +
                "      ,[Budget]\n" +
                "      ,[MemberID]\n" +
                "  FROM [dbo].[Team]";
        try{
            DBconnection = getConnection();
            Statement statement = DBconnection.createStatement();
            resultSet = statement.executeQuery(query);


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            };
        }
        return rsToStringArray(resultSet);
    }

    public String[][] getTeamBudget(String teamName) {
        Connection DBconnection = null;
        ResultSet resultSet = null;
        String query = "SELECT [TeamName]\n" +
                "      ,[ReportDate]\n" +
                "      ,[TransferAmount]\n" +
                "      ,[TransferCause]\n" +
                "  FROM [Football].[dbo].[BudgetReports]\n" +
                "  WHERE [TeamName] = ?";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,teamName);
            resultSet = statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            };
        }
        return rsToStringArray(resultSet);
    }

    public String[][] getTeamOwners(String teamName) {
        Connection DBconnection = null;
        ResultSet resultSet = null;
        String query = "SELECT [TeamName]\n" +
                "      ,[ReportDate]\n" +
                "      ,[TransferAmount]\n" +
                "      ,[TransferCause]\n" +
                "       FROM [Football].[dbo].[BudgetReports]\n" +
                "      WHERE TeamName = ?";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,teamName);
            resultSet = statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            };
        }
        return rsToStringArray(resultSet);
    }

    public String[][] getTeamPlayers(String teamName) {
        Connection DBconnection = null;
        ResultSet resultSet = null;
        String query = "SELECT [TeamName]\n" +
                "      ,[PlayerID]\n" +
                "       FROM [Football].[dbo].[TeamPlayers]\n" +
                "       WHERE [TeamName] = ?";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,teamName);
            resultSet = statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            };
        }
        return rsToStringArray(resultSet);
    }

    public String[][] getOwnersAppointments(String user_id) {
        Connection DBconnection = null;
        ResultSet resultSet = null;
        String query = "SELECT [TOID]\n" +
                "      ,[AID]\n" +
                "  FROM [Football].[dbo].[TOAppointments]\n" +
                "  WHERE [TOID] = ?";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,user_id);
            resultSet = statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            };
        }
        return rsToStringArray(resultSet);
    }

    public String[][] getTeamCoaches(String teamName) {
        Connection DBconnection = null;
        ResultSet resultSet = null;
        String query = "SELECT [TeamName]\n" +
                "      ,[CoachID]\n" +
                "  FROM [Football].[dbo].[TeamCoaches]\n" +
                "  WHERE [TeamName] = ?";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,teamName);
            resultSet = statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            };
        }
        return rsToStringArray(resultSet);
    }

    public String[][] getTeamsFans(String teamName) {
        return null;
    }

    public String[][] getTeamJobObservers(String teamName) {
        Connection DBconnection = null;
        ResultSet resultSet = null;
        String query = "SELECT [TeamName]\n" +
                "      ,[ObserverID]\n" +
                "  FROM [Football].[dbo].[TeamObservers]\n" +
                "  WHERE [TeamName] = ?";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,teamName);
            resultSet = statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            };
        }
        return rsToStringArray(resultSet);
    }

    public String[][] getAllPlayers() {
        Connection DBconnection = null;
        ResultSet resultSet = null;
        String query = "SELECT [MemberID]\n" +
                "      ,[PosID]\n" +
                "      ,[TeamName]\n" +
                "      ,[BirthDate]\n" +
                "  FROM [Football].[dbo].[Player]";
        try{
            DBconnection = getConnection();
            Statement statement = DBconnection.createStatement();
            resultSet = statement.executeQuery(query);


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            };
        }
        return rsToStringArray(resultSet);
    }

    public String[][] getAllCoaches() {
        Connection DBconnection = null;
        ResultSet resultSet = null;
        String query = "SELECT [MemberID]\n" +
                "      ,[CertID]\n" +
                "      ,[TeamName]\n" +
                "      ,[JoinInTeam]\n" +
                "  FROM [Football].[dbo].[Coach]";
        try{
            DBconnection = getConnection();
            Statement statement = DBconnection.createStatement();
            resultSet = statement.executeQuery(query);


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            };
        }
        return rsToStringArray(resultSet);
    }

    public String[][] getAllManagers() {
        Connection DBconnection = null;
        ResultSet resultSet = null;
        String query = "SELECT [TMID]\n" +
                "      ,[PermissionID]\n" +
                "      ,[TeamName]\n" +
                "  FROM [Football].[dbo].[TeamManager]";
        try{
            DBconnection = getConnection();
            Statement statement = DBconnection.createStatement();
            resultSet = statement.executeQuery(query);


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            };
        }
        return rsToStringArray(resultSet);
    }

    public String[][] getManagersPermissions(String managerValue) {
        Connection DBconnection = null;
        ResultSet resultSet = null;
        String query = "SELECT [TMID]\n" +
                "      ,[PermissionID]\n" +
                "  FROM [Football].[dbo].[TMPermissions]\n" +
                "  WHERE [TMID] = ?";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,managerValue);
            resultSet = statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            };
        }
        return rsToStringArray(resultSet);
    }

    public String[][] getAllMainReferees() {
        Connection DBconnection = null;
        ResultSet resultSet = null;
        String query = "SELECT [MemberID]\n" +
                "      ,[Active]\n" +
                "  FROM [Football].[dbo].[Referee]\n" +
                "  WHERE RType = ?";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,"1");
            resultSet = statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            };
        }
        return rsToStringArray(resultSet);
    }

    public String[][] getAllLinesReferees() {
        Connection DBconnection = null;
        ResultSet resultSet = null;
        String query = "SELECT [MemberID]\n" +
                "      ,[Active]\n" +
                "  FROM [Football].[dbo].[Referee]\n" +
                "  WHERE RType = ?";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,"2");
            resultSet = statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            };
        }
        return rsToStringArray(resultSet);
    }

    public String[][] getAllVarReferees(){
        Connection DBconnection = null;
        ResultSet resultSet = null;
        String query = "SELECT [MemberID]\n" +
                "      ,[Active]\n" +
                "  FROM [Football].[dbo].[Referee]\n" +
                "  WHERE RType = ?";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,"3");
            resultSet = statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            };
        }
        return rsToStringArray(resultSet);
    }

    public String[][] getAllMembers() {
        Connection DBconnection = null;
        ResultSet resultSet = null;
        String query = "SELECT  [ID]\n" +
                "      ,[UserName]\n" +
                "      ,[UserPassword]\n" +
                "      ,[FullName]\n" +
                "      ,[UserOnline]\n" +
                "      ,[Blocked]\n" +
                "  FROM [Football].[dbo].[Member]";
        try{
            DBconnection = getConnection();
            Statement statement = DBconnection.createStatement();
            resultSet = statement.executeQuery(query);


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            };
        }
        return rsToStringArray(resultSet);
    }

    public String[][] getAllOwners() {
        Connection DBconnection = null;
        ResultSet resultSet = null;
        String query = "SELECT [TOID]\n" +
                "      ,[TeamName]\n" +
                "  FROM [Football].[dbo].[TeamOwner]\n" +
                "  WHERE [TOID] = ?";
        try{
            DBconnection = getConnection();
            Statement statement = DBconnection.createStatement();
            resultSet = statement.executeQuery(query);


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            };
        }
        return rsToStringArray(resultSet);
    }

    public String[][] getAllTickets() {
        Connection DBconnection = null;
        ResultSet resultSet = null;
        String query = "SELECT [TOID]\n" +
                "      ,[TeamName]\n" +
                "  FROM [Football].[dbo].[TeamOwner]\n" +
                "  WHERE [TOID] = ?";
        try{
            DBconnection = getConnection();
            Statement statement = DBconnection.createStatement();
            resultSet = statement.executeQuery(query);


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            };
        }
        return rsToStringArray(resultSet);
    }

    public String[][] getGamesFouls(String date, String homeTeamName, String awayTeamName) {
        Connection DBconnection = null;
        ResultSet resultSet = null;
        String query = "SELECT [HomeTeamName]\n" +
                "      ,[AwayTeamName]\n" +
                "      ,[GameDate]\n" +
                "      ,[GameTime]\n" +
                "      ,[TeamName]\n" +
                "      ,[PlayerID]\n" +
                "      ,[FouledPlayerID]\n" +
                "  FROM [Football].[dbo].[GameFoulEvent]\n" +
                "  WHERE [GameDate] = ? and [HomeTeamName] = ? and [AwayTeamName] = ?";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,date);
            statement.setString(2,homeTeamName);
            statement.setString(3,awayTeamName);
            resultSet = statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            };
        }
        return rsToStringArray(resultSet);
    }

    public String[][] getGameGoals(String date, String homeTeamName, String awayTeamName) {
        Connection DBconnection = null;
        ResultSet resultSet = null;
        String query = "SELECT [HomeTeamName]\n" +
                "      ,[AwayTeamName]\n" +
                "      ,[GameDate]\n" +
                "      ,[GameTime]\n" +
                "      ,[TeamName]\n" +
                "      ,[PlayerID]\n" +
                "  FROM [Football].[dbo].[GameGoalEvent]\n" +
                "  WHERE [GameDate] = ? and [HomeTeamName] = ? and [AwayTeamName] = ?";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,date);
            statement.setString(2,homeTeamName);
            statement.setString(3,awayTeamName);
            resultSet = statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            };
        }
        return rsToStringArray(resultSet);
    }

    public String[][] getGameInjurys(String date, String homeTeamName, String awayTeamName) {
        Connection DBconnection = null;
        ResultSet resultSet = null;
        String query = "SELECT [HomeTeamName]\n" +
                "      ,[AwayTeamName]\n" +
                "      ,[GameDate]\n" +
                "      ,[GameTime]\n" +
                "      ,[TeamName]\n" +
                "      ,[PlayerID]\n" +
                "  FROM [Football].[dbo].[GameInjuryEvent]\n" +
                "  WHERE [GameDate] = ? and [HomeTeamName] = ? and [AwayTeamName] = ?";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,date);
            statement.setString(2,homeTeamName);
            statement.setString(3,awayTeamName);
            resultSet = statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            };
        }
        return rsToStringArray(resultSet);
    }

    public String[][] getGameoffsides(String date, String homeTeamName, String awayTeamName) {
        Connection DBconnection = null;
        ResultSet resultSet = null;
        String query = "SELECT [HomeTeamName]\n" +
                "      ,[AwayTeamName]\n" +
                "      ,[GameDate]\n" +
                "      ,[GameTime]\n" +
                "      ,[TeamName]\n" +
                "      ,[PlayerID]\n" +
                "  FROM [Football].[dbo].[GameOffsideEvent]" +
                "  WHERE [GameDate] = ? and [HomeTeamName] = ? and [AwayTeamName] = ?";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,date);
            statement.setString(2,homeTeamName);
            statement.setString(3,awayTeamName);
            resultSet = statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            };
        }
        return rsToStringArray(resultSet);
    }

    public String[][] getGameRedCardEvents(String date, String homeTeamName, String awayTeamName) {
        Connection DBconnection = null;
        ResultSet resultSet = null;
        String query = "SELECT [HomeTeamName]\n" +
                "      ,[AwayTeamName]\n" +
                "      ,[GameDate]\n" +
                "      ,[GameTime]\n" +
                "      ,[TeamName]\n" +
                "      ,[PlayerID]\n" +
                "  FROM [Football].[dbo].[GameRedCardEvent]\n" +
                "  WHERE [GameDate] = ? and [HomeTeamName] = ? and [AwayTeamName] = ?";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,date);
            statement.setString(2,homeTeamName);
            statement.setString(3,awayTeamName);
            resultSet = statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            };
        }
        return rsToStringArray(resultSet);
    }

    public String[][] getGameYellowCardEvents(String date, String homeTeamName, String awayTeamName) {
        Connection DBconnection = null;
        ResultSet resultSet = null;
        String query = "SELECT [HomeTeamName]\n" +
                "      ,[AwayTeamName]\n" +
                "      ,[GameDate]\n" +
                "      ,[GameTime]\n" +
                "      ,[TeamName]\n" +
                "      ,[PlayerID]\n" +
                "  FROM [Football].[dbo].[GameYellowCardEvent]\n" +
                "  WHERE [GameDate] = ? and [HomeTeamName] = ? and [AwayTeamName] = ?";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,date);
            statement.setString(2,homeTeamName);
            statement.setString(3,awayTeamName);
            resultSet = statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            };
        }
        return rsToStringArray(resultSet);
    }

    public String[][] getGameStartEvent(String s, String homeTeamName, String awayTeamName) {
        Connection DBconnection = null;
        ResultSet resultSet = null;
        String query = "SELECT [HomeTeamName]\n" +
                "      ,[AwayTeamName]\n" +
                "      ,[GameDate]\n" +
                "  FROM [Football].[dbo].[GameStartEvent]\n" +
                "  WHERE [GameDate] = ? and [HomeTeamName] = ? and [AwayTeamName] = ?";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,s);
            statement.setString(2,homeTeamName);
            statement.setString(3,awayTeamName);
            resultSet = statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            };
        }
        return rsToStringArray(resultSet);
    }

    public String[][] getGameSubstitutionEvent(String s, String homeTeamName, String awayTeamName) {
        Connection DBconnection = null;
        ResultSet resultSet = null;
        String query = "SELECT [HomeTeamName]\n" +
                "      ,[AwayTeamName]\n" +
                "      ,[GameDate]\n" +
                "      ,[GameTime]\n" +
                "      ,[TeamName]\n" +
                "      ,[OutgoingPlayerID]\n" +
                "      ,[IngoingPlayerID]\n" +
                "  FROM [Football].[dbo].[GameSubtitutionEvent]\n" +
                "  WHERE [GameDate] = ? and [HomeTeamName] = ? and [AwayTeamName] = ?";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,s);
            statement.setString(2,homeTeamName);
            statement.setString(3,awayTeamName);
            resultSet = statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            };
        }
        return rsToStringArray(resultSet);
    }

    public String[][] getGamerelocationEvent(String date, String homeTeamName, String awayTeamName) {
        Connection DBconnection = null;
        ResultSet resultSet = null;
        String query = "SELECT [HomeTeamName]\n" +
                "      ,[AwayTeamName]\n" +
                "      ,[GameDate]\n" +
                "      ,[NewLocation]\n" +
                "      ,[OriginalLocation]\n" +
                "  FROM [Football].[dbo].[GameRelocationEvent]\n" +
                "  WHERE [GameDate] = ? and [HomeTeamName] = ? and [AwayTeamName] = ?";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,date);
            statement.setString(2,homeTeamName);
            statement.setString(3,awayTeamName);
            resultSet = statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            };
        }
        return rsToStringArray(resultSet);
    }

    public String[][] getGameEndEvent(String s, String homeTeamName, String awayTeamName) {
        Connection DBconnection = null;
        ResultSet resultSet = null;
        String query = "SELECT [HomeTeamName]\n" +
                "      ,[AwayTeamName]\n" +
                "      ,[GameDate]\n" +
                "  FROM [Football].[dbo].[GameEndEvent]\n" +
                "  WHERE [GameDate] = ? and [HomeTeamName] = ? and [AwayTeamName] = ?";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,s);
            statement.setString(2,homeTeamName);
            statement.setString(3,awayTeamName);
            resultSet = statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            };
        }
        return rsToStringArray(resultSet);
    }



    public void insertLeagueToDB(String leagueName,int NumOf2TeamsGames, int pointsPerWin,int pointsPerLoss,int pointsPerDraw){
        Connection DBconnection = null;
        String query = "INSERT INTO [dbo].[League]\n" +
                "           ([LeagueName]\n" +
                "           ,[NumOfTwoTeamsGames]\n" +
                "           ,[PointsPerWin]\n" +
                "           ,[PointsPerDraw]\n" +
                "           ,[PointsPerLoss])\n" +
                "     VALUES (?,?,?,?,?)";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,leagueName);
            statement.setString(2,Integer.toString(NumOf2TeamsGames));
            statement.setString(3,Integer.toString(pointsPerWin));
            statement.setString(4,Integer.toString(pointsPerDraw));
            statement.setString(5,Integer.toString(pointsPerLoss));
            statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            };
        }

    }

    public void insertSeasonToDB(String leagueName,int SeasonYear,int NumOf2TeamsGames, int pointsPerWin,int pointsPerLoss,int pointsPerDraw) {
        Connection DBconnection = null;
        String query = "INSERT INTO [dbo].[Season]\n" +
                "           ([SYear]\n" +
                "           ,[LeagueName]\n" +
                "           ,[NumOfTwoTeamsGames]\n" +
                "           ,[PointsPerWin]\n" +
                "           ,[PointsPerDraw]\n" +
                "           ,[PointsPerLoss])\n" +
                "     VALUES (?,?,?,?,?,?)";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,Integer.toString(SeasonYear));
            statement.setString(2,leagueName);
            statement.setString(3,Integer.toString(NumOf2TeamsGames));
            statement.setString(4,Integer.toString(pointsPerWin));
            statement.setString(5,Integer.toString(pointsPerDraw));
            statement.setString(6,Integer.toString(pointsPerLoss));
            statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void insertGameToDB(String leagueName, String SeasonYear, String homeTeamName, String awayTeamName, String date,
                               int homeGoals, int awayGoals, String stadiumName, String mainRID , String varRID , String line1RID , String line2RID ){
        Connection DBconnection = null;
        String query = "INSERT INTO [dbo].[FootballGame]\n" +
                "           ([HomeTeamName]\n" +
                "           ,[AwayTeamName]\n" +
                "           ,[GameDate]\n" +
                "           ,[MainRefereeID]\n" +
                "           ,[LeftRefereeID]\n" +
                "           ,[RightRefereeID]\n" +
                "           ,[VarRefereeID]\n" +
                "           ,[HomeGoals]\n" +
                "           ,[AwayGoals]\n" +
                "           ,[SeasonYear]\n" +
                "           ,[LeagueName])\n" +
                "           ,[StadiumName])\n" +
                "     VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,homeTeamName);
            statement.setString(2,awayTeamName);
            statement.setString(3,date);
            statement.setString(4,mainRID);
            statement.setString(5,line1RID);
            statement.setString(6,line2RID);
            statement.setString(7,varRID);
            statement.setString(8,Integer.toString(homeGoals));
            statement.setString(9,Integer.toString(awayGoals));
            statement.setString(10,SeasonYear);
            statement.setString(11,leagueName);
            statement.setString(12,stadiumName);
            statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void insertLeaguePositionToDB(String leagueName,String SeasonYear,String teamName,
                                            String gamesWon,String gamesLoss,String gamesDraw,String goalsScored,String goalsRec){
        Connection DBconnection = null;
        String query = "INSERT INTO [dbo].[LeaguePosition]\n" +
                "           ([SeasonYear]\n" +
                "           ,[LeagueName]\n" +
                "           ,[TeamName]\n" +
                "           ,[GamesWin]\n" +
                "           ,[GamesLoss]\n" +
                "           ,[GamesDraw]\n" +
                "           ,[GoalsScored]\n" +
                "           ,[GoalsReceived])\n" +
                "     VALUES(?,?,?,?,?,?,?,?)";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,SeasonYear);
            statement.setString(2,leagueName);
            statement.setString(3,teamName);
            statement.setString(4,gamesWon);
            statement.setString(5,gamesLoss);
            statement.setString(6,gamesDraw);
            statement.setString(7,goalsScored);
            statement.setString(8,goalsRec);
            statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void insertMainRefereeToLeagueToDB(String refereeID,String leagueName){
        Connection DBconnection = null;
        String query = "INSERT INTO [dbo].[LeagueMReferee]\n" +
                "           ([LeagueName]\n" +
                "           ,[MainRefereeID])\n" +
                "     VALUES (?,?)";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,leagueName);
            statement.setString(2,refereeID);
            statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void insertLineRefereeToLeagueToDB(String refereeID,String leagueName){
        Connection DBconnection = null;
        String query = "INSERT INTO [dbo].[LeagueLReferee]\n" +
                "           ([LeagueName]\n" +
                "           ,[MainRefereeID])\n" +
                "     VALUES (?,?)";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,leagueName);
            statement.setString(2,refereeID);
            statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void insertVarRefereeToLeagueToDB(String refereeID,String leagueName){
        Connection DBconnection = null;
        String query = "INSERT INTO [dbo].[LeagueVReferee]\n" +
                "           ([LeagueName]\n" +
                "           ,[MainRefereeID])\n" +
                "     VALUES (?,?)";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,leagueName);
            statement.setString(2,refereeID);
            statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void insertFanToTeamToDB(String teamName,String fanID){
        Connection DBconnection = null;
        String query = "INSERT INTO [dbo].[TeamObservers]\n" +
                "           ([TeamName]\n" +
                "           ,[ObserverID])\n" +
                "     VALUES (?,?)";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,teamName);
            statement.setString(2,fanID);
            statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void insertBudgetTransactionToDB(String teamName,String date,String money,String description){
        Connection DBconnection = null;
        String query = "INSERT INTO [dbo].[BudgetReports]\n" +
                "           ([TeamName]\n" +
                "           ,[ReportDate]\n" +
                "           ,[TransferAmount]\n" +
                "           ,[TransferCause])\n" +
                "     VALUES (?,?,?,?)";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,teamName);
            statement.setString(2,date);
            statement.setString(3,money);
            statement.setString(4,description);
            statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void insertTeamOwnerToTeamToDB(String TOID,String teamName){
        Connection DBconnection = null;
        String query = "INSERT INTO [dbo].[TeamTO]\n" +
                "           ([TeamName]\n" +
                "           ,[TOID])\n" +
                "     VALUES (?,?)";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,teamName);
            statement.setString(2,TOID);
            statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void insertAppointmentToOwnerToDB(String TOID,String appID){
        Connection DBconnection = null;
        String query = "INSERT INTO [dbo].[TOAppointments]\n" +
                "           ([TOID]\n" +
                "           ,[AID])\n" +
                "     VALUES (?,?)";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,TOID);
            statement.setString(2,appID);
            statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void insertPlayerToTeamToDB(String teamName,String playerID){
        Connection DBconnection = null;
        String query = "INSERT INTO [dbo].[TeamPlayers]\n" +
                "           ([TeamName]\n" +
                "           ,[PlayerID])\n" +
                "     VALUES (?,?)";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,teamName);
            statement.setString(2,playerID);
            statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void insertCoachToTeamToDB(String teamName,String coachID){
        Connection DBconnection = null;
        String query = "INSERT INTO [dbo].[TeamCoaches]\n" +
                "           ([TeamName]\n" +
                "           ,[CoachID])\n" +
                "     VALUES (?,?)";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,teamName);
            statement.setString(2,coachID);
            statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void insertManagerToTeamToDB(String teamName,String managerID){
        Connection DBconnection = null;
        String query = "INSERT INTO [dbo].[TeamTM]\n" +
                "           ([TeamName]\n" +
                "           ,[TMID])\n" +
                "     VALUES (?,?)";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,teamName);
            statement.setString(2,managerID);
            statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    public void insertPlayerTweetToDB(String playerID,String tweet){
        Connection DBconnection = null;
        String query = "INSERT INTO [dbo].[PlayerTweets]\n" +
                "           ([PlayerID]\n" +
                "           ,[Tweet])\n" +
                "     VALUES (?,?)";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,playerID);
            statement.setString(2,tweet);
            statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    public void insertPlayerObserverToDB(String playerID,String observerID){
        Connection DBconnection = null;
        String query = "INSERT INTO [dbo].[PlayerObservers]\n" +
                "           ([PlayerID]\n" +
                "           ,[ObserverID])\n" +
                "     VALUES (?,?)";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,playerID);
            statement.setString(2,observerID);
            statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void insertManagerPermissionToDB(String managerID,String permission){
        Connection DBconnection = null;
        String query = "INSERT INTO [dbo].[TMPermissions]\n" +
                "           ([TMID]\n" +
                "           ,[PermissionID])\n" +
                "     VALUES (?,?)";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,managerID);
            statement.setString(2,permission);
            statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void insertTeamToDB(String teamName,String teamStatus,String stadiumName){
        Connection DBconnection = null;
        String query = "INSERT INTO [dbo].[Team]\n" +
                "           ([TeamName]\n" +
                "           ,[StatusID]\n" +
                "           ,[StadiumName])\n" +
                "     VALUES (?,?,?)";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,teamName);
            statement.setString(2,teamStatus);
            statement.setString(3,stadiumName);
            statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void insertTeamManagerToDB(String managerID,String jobName,String teamName){
        Connection DBconnection = null;
        String query = "INSERT INTO [dbo].[TeamManager]\n" +
                "           ([TMID]\n" +
                "           ,[JobName]\n" +
                "           ,[TeamName])\n" +
                "     VALUES (?,?,?)";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,managerID);
            statement.setString(2,jobName);
            statement.setString(3,teamName);
            statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void insertPlayerToDB(String playerID,String playerPosition, String dateOfBirth,String teamName){
        Connection DBconnection = null;
        String query = "INSERT INTO [dbo].[Player]\n" +
                "           ([MemberID]\n" +
                "           ,[PosID]\n" +
                "           ,[TeamName]\n" +
                "           ,[BirthDate])\n" +
                "     VALUES (?,?,?,?)";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,playerID);
            statement.setString(2,playerPosition);
            statement.setString(3,teamName);
            statement.setString(4,dateOfBirth);
            statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void insertTicketToDB(String ticketID,String ticketOID,String answer,String complaint,String isAnswered){
        Connection DBconnection = null;
        String query = "INSERT INTO [dbo].[Ticket]\n" +
                "           ([ID]\n" +
                "           ,[Complaint]\n" +
                "           ,[Answer]\n" +
                "           ,[Answered])\n" +
                "     VALUES (?,?,?,?)";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,ticketID);
            statement.setString(2,complaint);
            statement.setString(3,answer);
            statement.setString(4,isAnswered);
            statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void insertStadiumToDB(String stadiumName,String cityName){
        Connection DBconnection = null;
        String query = "INSERT INTO [dbo].[Stadium]\n" +
                "           ([SName]\n" +
                "           ,[City])\n" +
                "     VALUES (?,?)";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,stadiumName);
            statement.setString(2,cityName);
            statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void insertCoachToDB(String coachID,String teamName,String coachCert,String jobInTeam){
        Connection DBconnection = null;
        String query = "INSERT INTO [dbo].[Coach]\n" +
                "           ([MemberID]\n" +
                "           ,[CertID]\n" +
                "           ,[TeamName]\n" +
                "           ,[JoinInTeam])\n" +
                "     VALUES (?,?,?,?)";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,coachID);
            statement.setString(2,coachCert);
            statement.setString(3,teamName);
            statement.setString(4,jobInTeam);
            statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void insertCoachTweetToDB(String coachID,String tweet){
        Connection DBconnection = null;
        String query = "INSERT INTO [dbo].[CoachTweets]\n" +
                "           ([CoachID]\n" +
                "           ,[Tweet])\n" +
                "     VALUES (?,?)";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,coachID);
            statement.setString(2,tweet);
            statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void insertMemberToDB(String memberID,String fullName,String userName,String userPassword,String isBlocked){
        Connection DBconnection = null;
        String query = "INSERT INTO [dbo].[Member]\n" +
                "           ([ID]\n" +
                "           ,[UserName]\n" +
                "           ,[UserPassword]\n" +
                "           ,[FullName]\n" +
                "           ,[UserOnline]\n" +
                "           ,[Blocked])\n" +
                "     VALUES (?,?,?,?,?,?)";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,memberID);
            statement.setString(2,userName);
            statement.setString(3,userPassword);
            statement.setString(4,fullName);
            statement.setString(5,"0");
            statement.setString(6,isBlocked);
            statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void insertMemberTicketToDB(String memberID,String ticketID){
        Connection DBconnection = null;
        String query = "INSERT INTO [dbo].[MemberTickets]\n" +
                "           ([MemberID]\n" +
                "           ,[TicketID])\n" +
                "     VALUES (?,?)";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,memberID);
            statement.setString(2,ticketID);
            statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void insertMemberSearchToDB(String memberID,String search){
        Connection DBconnection = null;
        String query = "INSERT INTO [dbo].[MemberSearch]\n" +
                "           ([MemberID]\n" +
                "           ,[Search])\n" +
                "     VALUES (?,?)";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,memberID);
            statement.setString(2,search);
            statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void insertMemberTeamToDB(String memberID,String teamName){
        Connection DBconnection = null;
        String query = "INSERT INTO [dbo].[MemberTeamFollowed]\n" +
                "           ([MemberID]\n" +
                "           ,[TeamName])\n" +
                "     VALUES (?,?)";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,memberID);
            statement.setString(2,teamName);
            statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void insertMemberPlayerFollowedToDB(String memberID,String playerID){
        Connection DBconnection = null;
        String query = "INSERT INTO [dbo].[MemberPlayerFollowed]\n" +
                "           ([MemberID]\n" +
                "           ,[PlayerID])\n" +
                "     VALUES (?,?)";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,memberID);
            statement.setString(2,playerID);
            statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void insertMemberCoachFollowedToDB(String memberID,String coachID){
        Connection DBconnection = null;
        String query = "INSERT INTO [dbo].[MemberCoachFollowed]\n" +
                "           ([MemberID]\n" +
                "           ,[CoachID])\n" +
                "     VALUES (?,?)";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,memberID);
            statement.setString(2,coachID);
            statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void insertMainRefereeToDB(String refereeID,String isActive){
        Connection DBconnection = null;
        String query = "INSERT INTO [dbo].[Referee]\n" +
                "           ([MemberID]\n" +
                "           ,[RType]\n" +
                "           ,[Active])\n" +
                "     VALUES (?,?,?)";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,refereeID);
            statement.setString(2,"1");
            statement.setString(2,isActive);
            statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void insertLineRefereeToDB(String refereeID,String isActive){
        Connection DBconnection = null;
        String query = "INSERT INTO [dbo].[Referee]\n" +
                "           ([MemberID]\n" +
                "           ,[RType]\n" +
                "           ,[Active])\n" +
                "     VALUES (?,?,?)";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,refereeID);
            statement.setString(2,"2");
            statement.setString(2,isActive);
            statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void insertVarRefereeToDB(String refereeID,String isActive){
        Connection DBconnection = null;
        String query = "INSERT INTO [dbo].[Referee]\n" +
                "           ([MemberID]\n" +
                "           ,[RType]\n" +
                "           ,[Active])\n" +
                "     VALUES (?,?,?)";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,refereeID);
            statement.setString(2,"3");
            statement.setString(2,isActive);
            statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void insertTeamOwnerToDB(String TOID,String teamName){
        Connection DBconnection = null;
        String query = "INSERT INTO [dbo].[TeamTO]\n" +
                "           ([TeamName]\n" +
                "           ,[TOID])\n" +
                "     VALUES (?,?)";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,TOID);
            statement.setString(2,teamName);
            statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void insertFoulEventToDB(String date,String homeTeamName,String awayTeamName,String time,
                                       String teamName,String playerID ,String fouledPlayerID){
        Connection DBconnection = null;
        String query = "INSERT INTO [dbo].[GameFoulEvent]\n" +
                "           ([HomeTeamName]\n" +
                "           ,[AwayTeamName]\n" +
                "           ,[GameDate]\n" +
                "           ,[GameTime]\n" +
                "           ,[TeamName]\n" +
                "           ,[PlayerID]\n" +
                "           ,[FouledPlayerID])\n" +
                "     VALUES (?,?,?)";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,homeTeamName);
            statement.setString(2,awayTeamName);
            statement.setString(3,date);
            statement.setString(4,time);
            statement.setString(5,teamName);
            statement.setString(6,playerID);
            statement.setString(7,fouledPlayerID);
            statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void insertGoalEventToDB(String date,String homeTeamName,String awayTeamName,String time,
                                       String teamName,String playerID){
        Connection DBconnection = null;
        String query = "INSERT INTO [dbo].[GameGoalEvent]\n" +
                "           ([HomeTeamName]\n" +
                "           ,[AwayTeamName]\n" +
                "           ,[GameDate]\n" +
                "           ,[GameTime]\n" +
                "           ,[TeamName]\n" +
                "           ,[PlayerID])\n" +
                "     VALUES (?,?,?,?,?,?)";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,homeTeamName);
            statement.setString(2,awayTeamName);
            statement.setString(3,date);
            statement.setString(4,time);
            statement.setString(5,teamName);
            statement.setString(6,playerID);
            statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void insertInjuryEventToDB(String date,String homeTeamName,String awayTeamName,String time,
                                         String teamName,String playerID){
        Connection DBconnection = null;
        String query = "INSERT INTO [dbo].[GameInjuryEvent]\n" +
                "           ([HomeTeamName]\n" +
                "           ,[AwayTeamName]\n" +
                "           ,[GameDate]\n" +
                "           ,[GameTime]\n" +
                "           ,[TeamName]\n" +
                "           ,[PlayerID])\n" +
                "     VALUES (?,?,?,?,?,?)";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,homeTeamName);
            statement.setString(2,awayTeamName);
            statement.setString(3,date);
            statement.setString(4,time);
            statement.setString(5,teamName);
            statement.setString(6,playerID);
            statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void insertOffsideEventToDB(String date,String homeTeamName,String awayTeamName,String time,
                                          String teamName,String playerID){
        Connection DBconnection = null;
        String query = "INSERT INTO [dbo].[GameOffsideEvent]\n" +
                "           ([HomeTeamName]\n" +
                "           ,[AwayTeamName]\n" +
                "           ,[GameDate]\n" +
                "           ,[GameTime]\n" +
                "           ,[TeamName]\n" +
                "           ,[PlayerID])\n" +
                "     VALUES (?,?,?,?,?,?)";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,homeTeamName);
            statement.setString(2,awayTeamName);
            statement.setString(3,date);
            statement.setString(4,time);
            statement.setString(5,teamName);
            statement.setString(6,playerID);
            statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    };

    public void insertRedCardEventToDB(String date,String homeTeamName,String awayTeamName,String time,
                                          String teamName,String playerID){
        Connection DBconnection = null;
        String query = "INSERT INTO [dbo].[GameRedCardEvent]\n" +
                "           ([HomeTeamName]\n" +
                "           ,[AwayTeamName]\n" +
                "           ,[GameDate]\n" +
                "           ,[GameTime]\n" +
                "           ,[TeamName]\n" +
                "           ,[PlayerID])\n" +
                "     VALUES (?,?,?,?,?,?)";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,homeTeamName);
            statement.setString(2,awayTeamName);
            statement.setString(3,date);
            statement.setString(4,time);
            statement.setString(5,teamName);
            statement.setString(6,playerID);
            statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    };

    public void insertYellowCardEventToDB(String date,String homeTeamName,String awayTeamName,String time,
                                          String teamName,String playerID){
        Connection DBconnection = null;
        String query = "INSERT INTO [dbo].[GameYellowCardEvent]\n" +
                "           ([HomeTeamName]\n" +
                "           ,[AwayTeamName]\n" +
                "           ,[GameDate]\n" +
                "           ,[GameTime]\n" +
                "           ,[TeamName]\n" +
                "           ,[PlayerID])\n" +
                "     VALUES (?,?,?,?,?,?)";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,homeTeamName);
            statement.setString(2,awayTeamName);
            statement.setString(3,date);
            statement.setString(4,time);
            statement.setString(5,teamName);
            statement.setString(6,playerID);
            statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    };

    public void insertSubstituteEventToDB(String date,String homeTeamName,String awayTeamName,
                                             String teamName,String outgoingID,String ingoingID,String time){
        Connection DBconnection = null;
        String query = "INSERT INTO [dbo].[GameSubtitutionEvent]\n" +
                "           ([HomeTeamName]\n" +
                "           ,[AwayTeamName]\n" +
                "           ,[GameDate]\n" +
                "           ,[GameTime]\n" +
                "           ,[TeamName]\n" +
                "           ,[OutgoingPlayerID]\n" +
                "           ,[IngoingPlayerID])\n" +
                "     VALUES (?,?,?,?,?,?,?)";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,homeTeamName);
            statement.setString(2,awayTeamName);
            statement.setString(3,date);
            statement.setString(4,time);
            statement.setString(5,teamName);
            statement.setString(6,outgoingID);
            statement.setString(7,ingoingID);
            statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    };

    public void insertGameDelayedEventToDB(String date,String homeTeamName,String awayTeamName,
                                              String gameDelayedTime, String gameOriginalTime){
        Connection DBconnection = null;
        String query = "INSERT INTO [dbo].[GameDelayedEvent]\n" +
                "           ([GameDate]\n" +
                "           ,[HomeTeamName]\n" +
                "           ,[AwayTeamName]\n" +
                "           ,[GameDelayedTime]\n" +
                "           ,[GameOriginalTime])\n" +
                "     VALUES (?,?,?,?,?)";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,date);
            statement.setString(2,homeTeamName);
            statement.setString(3,awayTeamName);
            statement.setString(4,gameDelayedTime);
            statement.setString(5,gameOriginalTime);
            statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    };


    public void insertGameRelocationEventToDB(String date,String homeTeamName,String awayTeamName,
                                                String newLocation,String originalLocation){
        Connection DBconnection = null;
        String query = "INSERT INTO [dbo].[GameRelocationEvent]\n" +
                "           ([HomeTeamName]\n" +
                "           ,[AwayTeamName]\n" +
                "           ,[GameDate]\n" +
                "           ,[NewLocation]\n" +
                "           ,[OriginalLocation])\n" +
                "     VALUES (?,?,?,?,?)";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,homeTeamName);
            statement.setString(2,awayTeamName);
            statement.setString(3,date);
            statement.setString(4,newLocation);
            statement.setString(5,originalLocation);
            statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    };

    public void insertGameEndEventToDB(String date,String homeTeamName,String awayTeamName){
        Connection DBconnection = null;
        String query = "INSERT INTO [dbo].[GameStartEvent]\n" +
                "           ([HomeTeamName]\n" +
                "           ,[AwayTeamName]\n" +
                "           ,[GameDate])\n" +
                "     VALUES (?,?,?)";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,homeTeamName);
            statement.setString(2,awayTeamName);
            statement.setString(3,date);
            statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    };

    public void insertGameStartEventToDB(String date,String homeTeamName,String awayTeamName){
        Connection DBconnection = null;
        String query = "INSERT INTO [dbo].[GameEndEvent]\n" +
                "           ([HomeTeamName]\n" +
                "           ,[AwayTeamName]\n" +
                "           ,[GameDate])\n" +
                "     VALUES (?,?,?)";
        try{
            DBconnection = getConnection();
            PreparedStatement statement = DBconnection.prepareStatement(query);
            statement.setString(1,homeTeamName);
            statement.setString(2,awayTeamName);
            statement.setString(3,date);
            statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (DBconnection != null)
                    DBconnection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    };


    private String[][] rsToStringArray(ResultSet resultSet){
        List<String[]> listResult = new ArrayList<>();
        List<String> colNames = getRSColNames(resultSet);
        String [] recordResult = null;
        try {
            while (resultSet.next()) {
                recordResult = new String[colNames.size()];
                for (int i = 0;i < colNames.size(); i++) {
                    recordResult[i] = resultSet.getNString(colNames.get(i));
                }
                listResult.add(recordResult);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listResult.toArray(new String[0][]);
    }

    private List<String> getRSColNames(ResultSet resultSet){
        List<String> rsColNames = new ArrayList<>();
        try {
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            for(int i = 1; i < resultSetMetaData.getColumnCount();i++){
                rsColNames.add(resultSetMetaData.getColumnName(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsColNames;
    }

    public void insertStartGameEventToDB(String s, String homeTeamName, String awayTeamName, String time) {

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