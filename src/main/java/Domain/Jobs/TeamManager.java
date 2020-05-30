package Domain.Jobs;
import Domain.User.*;
import Domain.System.*;
import Domain.Game.*;
import Exceptions.DomainException;

import java.util.ArrayList;

public class TeamManager extends Job {
    private Team team;
    public enum Permissions{SET_TEAM_STATUS, EDIT_PROPERTIES, ADD_FINANCIAL_REPORT, EDIT_PERSONAL_PAGE}
    private ArrayList<Permissions> permissions;

    public TeamManager(Member member, Team team, ArrayList<Permissions> permissions) {
        super(member);
        this.team = team;
        this.jobName="manager";
        this.permissions=permissions;
        AlphaSystem alphaSystem= AlphaSystem.getSystem();
        alphaSystem.AddtoMemory(5,this);
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void removeAllPermissions(){
        permissions.clear();
    }

    private boolean checkPermissions(Permissions permission){
        if(!permissions.contains(permission)){
            System.out.println("you dont have a permission");
            return false;
        }
        return true;
    }

    public void openTeam() throws DomainException {
        if(checkPermissions(Permissions.SET_TEAM_STATUS)) {
            team.setStatus(Team.Status.open);
        }
    }

    public void closeTeam() throws DomainException {
        if(checkPermissions(Permissions.SET_TEAM_STATUS)) {
            team.setStatus(Team.Status.close);
        }
    }


    public void addWithdraw(Double sum,String description) throws DomainException {
        if(checkPermissions(Permissions.ADD_FINANCIAL_REPORT)) {
            team.addWithdraw(sum, description);
        }

    }

    public void addDeposit(Double sum,String description) throws DomainException {
        if(checkPermissions(Permissions.ADD_FINANCIAL_REPORT)) {
            team.addDeposit(sum, description);
        }
    }

    public void addTweet(String tweet) throws DomainException {
        if(checkPermissions(Permissions.EDIT_PERSONAL_PAGE)) {
            team.addTweet(tweet);
        }
    }

    public void deleteTweet(int index) throws DomainException {
        if(checkPermissions(Permissions.EDIT_PERSONAL_PAGE)) {
            team.deleteTweet(index);
        }
    }

    public void setPermissions(ArrayList<Permissions> permissions) {
        this.permissions = permissions;
    }

    public boolean addNewPlayer(String userName) throws DomainException {
        if(!checkPermissions(Permissions.EDIT_PROPERTIES))
            return false;
        return team.addNewPlayer(userName);
    }

    public boolean removeExistingPlayer(String userName) throws DomainException {
        if(!checkPermissions(Permissions.EDIT_PROPERTIES))
            return false;
        return team.removeExistingPlayer(userName);
    }

    public boolean editExistingPlayerName(String userName,String name) throws DomainException {
        if(!checkPermissions(Permissions.EDIT_PROPERTIES))
            return false;
        return team.editExistingPlayerName( userName, name);
    }

    public boolean editExistingPlayerPosition(String userName,String position) throws DomainException {
        if(!checkPermissions(Permissions.EDIT_PROPERTIES))
            return false;
        return team.editExistingPlayerPosition( userName, position);
    }

    public boolean editExistingPlayerBirthday(String userName,int year,int month,int day) throws DomainException {
        if(!checkPermissions(Permissions.EDIT_PROPERTIES))
            return false;
        return team.editExistingPlayerBirthday( userName, year, month, day);
    }

    public boolean addNewCoach(String userName,String job) throws DomainException {
        if(!checkPermissions(Permissions.EDIT_PROPERTIES))
            return false;
        return team.addNewCoach( userName, job);
    }

    public boolean removeExistingCoach(String userName) throws DomainException {
        if(!checkPermissions(Permissions.EDIT_PROPERTIES))
            return false;
        return team.removeExistingCoach( userName);
    }

    public boolean editExistingCoachName(String user,String newName) throws DomainException {
        if(!checkPermissions(Permissions.EDIT_PROPERTIES))
            return false;
        return team.editExistingCoachName( user, newName);
    }

    public boolean editExistingCoachCertification(String user,int certificationId) throws DomainException {
        if(!checkPermissions(Permissions.EDIT_PROPERTIES))
            return false;
        return team.editExistingCoachCertification( user, certificationId);
    }

    public boolean editExistingCoachJobInTeam(String user,String Job) throws DomainException {
        if(!checkPermissions(Permissions.EDIT_PROPERTIES))
            return false;
        return team.editExistingCoachJobInTeam( user, Job);
    }

    public boolean editExistingManagerName(String user,String newName) throws DomainException {
        if(!checkPermissions(Permissions.EDIT_PROPERTIES))
            return false;
        return team.editExistingManagerName( user, newName);
    }

    public boolean editExistingStadiumName(String newName) throws DomainException {
        if(!checkPermissions(Permissions.EDIT_PROPERTIES))
            return false;
        return team.editExistingStadiumName( newName);
    }

    public boolean setNewStadium(String stadiumName) throws DomainException {
        if(!checkPermissions(Permissions.EDIT_PROPERTIES))
            return false;
        return team.setNewStadium( stadiumName);
    }

    public ArrayList<Permissions> getPermissions() {
        return permissions;
    }

    public void editFullName(String newName){
        getMember().setFull_name(newName);
    }

}
