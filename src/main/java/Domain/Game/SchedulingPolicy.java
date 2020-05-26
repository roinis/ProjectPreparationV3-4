package Domain.Game;
import  Domain.Events.*;
import  Domain.User.*;
import  Domain.System.*;
import  Domain.Jobs.*;
import  Domain.Association.*;

public class SchedulingPolicy {
    private int numOf2TeamsGames;

    public SchedulingPolicy(int numOf2TeamsGames) {
        this.numOf2TeamsGames = numOf2TeamsGames;
    }
    public SchedulingPolicy(){
        this.numOf2TeamsGames=2;
    }

    public int getNumOf2TeamsGames() {
        return numOf2TeamsGames;
    }
}
