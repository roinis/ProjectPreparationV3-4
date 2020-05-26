package Service;

import Domain.Association.AssociationMember;
import Domain.Game.*;
import Domain.Jobs.Coach;
import Domain.Jobs.Player;
import Domain.Jobs.Referee;
import Domain.System.*;
import Domain.User.Member;
import Exceptions.DomainException;
import Exceptions.notFoundException;
import http.Parser;
import http.Response;

import java.time.LocalDate;
import java.util.ArrayList;

public class AssociationController {
    public void handle(String path, ArrayList<Parser.StringPair> body, Response response) throws notFoundException, DomainException {
        try {
            String input1, input2, input3, input4, input5;
            switch (path) {
                case "NewLeague":
                    input1 = Parser.getElement("Username", body);
                    input2 = Parser.getElement("LeagueName", body);
                    if (input1 == null || input2 == null)
                        throw new DomainException("invalid input");
                    NewLeague(input1, input2);
                    break;
                case "AddSeasonToLeague":
                    input1 = Parser.getElement("Username", body);
                    input2 = Parser.getElement("LeagueName", body);
                    input3 = Parser.getElement("year", body);
                    if (input1 == null || input2 == null || input3 == null)
                        throw new DomainException("invalid input");
                    AddSeasonToLeague(input1, input2, Integer.parseInt(input3));
                    break;
                default:
                    throw new notFoundException();
            }
        }catch (Exception e){
            if(e.getClass().equals(DomainException.class))
                throw (DomainException)e;
            throw new notFoundException();
        }
    }

    public void NewLeague(String Username, String LeagueName) throws DomainException {
        AssociationMember AssMember = (AssociationMember)AlphaSystem.getSystem().GetSpecificFromDB(8,Username);
        if(AssMember==null){
            throw new DomainException("No such username exists");
        }
        AssMember.NewLeague(LeagueName);
    }

    public void AddSeasonToLeague(String Username, String LeagueName, int year ) throws DomainException {
        AssociationMember AssMember = (AssociationMember)AlphaSystem.getSystem().GetSpecificFromDB(8,Username);
        if(AssMember==null){
            throw new DomainException("No such username exists");
        }
        AssMember.AddSeasonToLeague(LeagueName,year);
    }

    public void AddNewTeam(String Username, String TeamName, Member teamOwner, Stadium HomeStadium ) throws Exception {
        AssociationMember AssMember = (AssociationMember)AlphaSystem.getSystem().GetSpecificFromDB(8,Username);
        if(AssMember==null){
            throw new Exception("No such username exists");
        }
        AssMember.AddNewTeam(TeamName,teamOwner,HomeStadium);
    }


    // to be continued
    public void AddNewRef(String Username,Member member) throws Exception {
        AssociationMember AssMember = (AssociationMember)AlphaSystem.getSystem().GetSpecificFromDB(8,Username);
        if(AssMember==null){
            throw new Exception("No such username exists");
        }
        AssMember.AddNewRef(member);
    }

    public void AddRefToLeague(String Username,Referee RefToAdd, League league) throws Exception {
        AssociationMember AssMember = (AssociationMember)AlphaSystem.getSystem().GetSpecificFromDB(8,Username);
        if(AssMember==null){
            throw new Exception("No such username exists");
        }
        AssMember.AddRefToLeague(RefToAdd, league);
    }

    public boolean AddPlayerJobToMember(String username, Member member, Player.Position pos, LocalDate dateOfBirth) throws Exception {
        AssociationMember AssMember = (AssociationMember)AlphaSystem.getSystem().GetSpecificFromDB(8,username);
        if(AssMember==null){
            throw new Exception("No such username exists");
        }
        return AssMember.AddPlayerJobToMember(member, pos,dateOfBirth );
    }

    public boolean AddCoachJobToMember(String username,Member member, Coach.Certification certification) throws Exception {
        AssociationMember AssMember = (AssociationMember)AlphaSystem.getSystem().GetSpecificFromDB(8,username);
        if(AssMember==null){
            throw new Exception("No such username exists");
        }
        return AssMember.AddCoachJobToMember(member, certification);
    }

    public void ChangeScoringPolicyForLeague(String Username,String LeagueToChange, int pPerWin,int pPerLoss,int pPerDraw) throws Exception {
        AssociationMember AssMember = (AssociationMember)AlphaSystem.getSystem().GetSpecificFromDB(8,Username);
        if(AssMember==null){
            throw new Exception("No such username exists");
        }
        AssMember.ChangeScoringPolicyForLeague(LeagueToChange, pPerWin,pPerLoss, pPerDraw);
    }

    public void ChangeSchedulingPolicyForLeague(String Username, String LeagueToChange, int numOfMatches) throws Exception {
        AssociationMember AssMember = (AssociationMember)AlphaSystem.getSystem().GetSpecificFromDB(8,Username);
        if(AssMember==null){
            throw new Exception("No such username exists");
        }
        AssMember.ChangeSchedulingPolicyForLeague(LeagueToChange, numOfMatches);
    }

    public boolean ChangeScoringPolicyForSeason(String Username, String LeagueToChange,int year, int pPerWin,int pPerLoss,int pPerDraw) throws Exception {
        AssociationMember AssMember = (AssociationMember)AlphaSystem.getSystem().GetSpecificFromDB(8,Username);
        if(AssMember==null){
            throw new Exception("No such username exists");
        }
        return AssMember.ChangeScoringPolicyForSeason(LeagueToChange, year, pPerWin, pPerLoss, pPerDraw);
    }

    public boolean ChangeSchedulingPolicyForSeason(String Username, String LeagueToChange,int year, int numOfMatches) throws Exception {
        AssociationMember AssMember = (AssociationMember)AlphaSystem.getSystem().GetSpecificFromDB(8,Username);
        if(AssMember==null){
            throw new Exception("No such username exists");
        }
        return AssMember.ChangeSchedulingPolicyForSeason(LeagueToChange, year, numOfMatches);
    }

    public boolean AddTeamToSeasonInLeague(String Username, String LeagueName, int seasonYear,Team team ) throws Exception {
        AssociationMember AssMember = (AssociationMember)AlphaSystem.getSystem().GetSpecificFromDB(8,Username);
        if(AssMember==null){
            throw new Exception("No such username exists");
        }
        return AssMember.AddTeamToSeasonInLeague(LeagueName, seasonYear, team);
    }


}
