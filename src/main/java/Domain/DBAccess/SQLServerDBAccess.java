package Domain.DBAccess;

import jdk.nashorn.internal.runtime.ECMAException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SQLServerDBAccess {
    public static Connection getConnection(){
        Connection DBconnection = null;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=Football;user=sa;password=Warning11";
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
                "      ,[RType]\n" +
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
                "      ,[RType]\n" +
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
                "      ,[RType]\n" +
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
                "      ,[EventDescription]\n" +
                "  FROM [Football].[dbo].[GameEndEvent]\n" +
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
                "      ,[EventDescription]\n" +
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
                "      ,[EventDescription]\n" +
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
                "      ,[EventDescription]\n" +
                "  FROM [Football].[dbo].[GameOffsideEvent]\n" +
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
                "      ,[EventDescription]\n" +
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
                "      ,[EventDescription]\n" +
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
                "      ,[EventDescription]\n" +
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
                "      ,[EventDescription]\n" +
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
                "      ,[EventDescription]\n" +
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
                "      ,[EventDescription]\n" +
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

    public boolean insertLeagueToDB(String leagueName,int NumOf2TeamsGames, int pointsPerWin,int pointsPerLoss,int pointsPerDraw){ }

    public boolean insertSeasonToDB(String leagueName,int SeasonYear,int NumOf2TeamsGames, int pointsPerWin,int pointsPerLoss,int pointsPerDraw) { }


    public boolean insertGameToDB(String leagueName,int SeasonYear,String homeTeamName,String awayTeamName,String date,
                int homeGoals,int awayGoals,String stadiumName,String mainRID ,String varRID ,String line1RID ,String line2RID ){ }


    public boolean insertLeaguePositionToDB(String leagueName,int SeasonYear,String teamName,
                                            String gamesWon,String gamesLoss,String gamesDraw,String goalsScored,String goalsRec){ }

    public boolean insertMainRefereeToLeagueToDB(String refereeID,String leagueName){}

    public boolean insertLineRefereeToLeagueToDB(String refereeID,String leagueName){ }

    public boolean insertVarRefereeToLeagueToDB(String refereeID,String leagueName){ }

    public boolean insertFanToTeamToDB(String teamName,String fanID){ }

    public boolean insertBudgetTransactionToDB(String teamName,String date,String money,String description){ }

    public boolean insertTeamOwnerToTeamToDB(String TOID,String teamName){ }

    public boolean insertAppointmentToOwnerToDB(String TOID,String appID){ }

    public boolean insertPlayerToTeamToDB(String teamName,String playerID){ }

    public boolean insertCoachToTeamToDB(String teamName,String coachID){ }

    public boolean insertManagerToTeamToDB(String teamName,String managerID){ }

    public boolean insertPlayerTweetToDB(String playerID,String tweet){ }

    public boolean insertPlayerObserverToDB(String playerID,String observerID){ }

    public boolean insertManagerPermissionToDB(String managerID,String permission){ }

    public boolean insertTeamToDB(String teamName,String teamStatus,String stadiumName){ }

    public boolean insertTeamManagerToDB(String managerID,String jobName,String teamName){ }

    public boolean insertPlayerToDB(String playerID,String playerPosition, String dateOfBirth,String teamName){ }

    public boolean insertTicketToDB(String ticketID,String ticketOID,String answer,String complaint,String isAnswered){ }

    public boolean insertStadiumToDB(String stadiumName,String cityName){ }

    public boolean insertCoachToDB(String coachID,String teamName,String coachCert,String jobInTeam){ }

    public boolean insertCoachTweetToDB(String coachID,String tweet){ }

    public boolean insertMemberToDB(String memberID,String fullName,String userName,String userPassword,String isBlocked){ }

    public boolean insertMemberTicketToDB(String memberID,String ticketID){ }

    public boolean insertMemberSearchToDB(String memberID,String search){ }

    public boolean insertMemberTeamToDB(String memberID,String teamName){ }

    public boolean insertMemberPlayerFollowedToDB(String memberID,String playerID){ }

    public boolean insertMemberCoachFollowedToDB(String memberID,String coachID){ }

    public boolean insertMainRefereeToDB(String memberID,String refereeID){ }

    public boolean insertLineRefereeToDB(referee.getMember().getUser_id(),referee.isActiveStatus()+""){ }
    
    public boolean insertLeagueToDB(String leagueName,int NumOf2TeamsGames, int pointsPerWin,int pointsPerLoss,int pointsPerDraw){ }
    public boolean insertLeagueToDB(String leagueName,int NumOf2TeamsGames, int pointsPerWin,int pointsPerLoss,int pointsPerDraw){ }
    public boolean insertLeagueToDB(String leagueName,int NumOf2TeamsGames, int pointsPerWin,int pointsPerLoss,int pointsPerDraw){ }
    public boolean insertLeagueToDB(String leagueName,int NumOf2TeamsGames, int pointsPerWin,int pointsPerLoss,int pointsPerDraw){ }
    public boolean insertLeagueToDB(String leagueName,int NumOf2TeamsGames, int pointsPerWin,int pointsPerLoss,int pointsPerDraw){ }
    public boolean insertLeagueToDB(String leagueName,int NumOf2TeamsGames, int pointsPerWin,int pointsPerLoss,int pointsPerDraw){ }





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