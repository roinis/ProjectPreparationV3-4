package Domain.Events;
import Domain.User.*;
import Domain.System.*;
import Domain.Jobs.*;
import Domain.Game.*;
import Domain.Association.*;
import java.sql.Time;
import java.time.LocalDateTime;

public class FoulEvent extends GameEvent {
    Player fouledPlayer;

    public FoulEvent(LocalDateTime eventGameTime, Team team, Player player, Player fouledPlayer) {
        super(eventGameTime, team, player);
        this.fouledPlayer = fouledPlayer;
    }

    @Override
    public String toString() {
        return "The Player "
                + eventPlayer.getMember().getFull_name()
                + " of team " + eventTeam.getTeamName()
                + " Committed a foul on "
                + fouledPlayer.getMember().getFull_name()
                + " at "
                + eventGameTime + ".";
    }

    public Player getFouledPlayer() {
        return fouledPlayer;
    }

    public void setFouledPlayer(Player fouledPlayer) {
        this.fouledPlayer = fouledPlayer;
    }
}

