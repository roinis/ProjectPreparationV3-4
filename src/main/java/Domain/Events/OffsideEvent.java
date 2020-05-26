package Domain.Events;
import Domain.User.*;
import Domain.System.*;
import Domain.Jobs.*;
import Domain.Game.*;
import Domain.Association.*;
import java.sql.Time;

public class OffsideEvent extends GameEvent {
    public OffsideEvent(Time eventGameTime, Team team, Player player) {
        super(eventGameTime, team, player);
    }

    @Override
    public String toString() {
        return "The Player "
                + eventPlayer.getMember().getFull_name()
                + "of team " + eventTeam.getTeamName()
                + " Committed an Offside on"
                + " at "
                + eventGameTime + ".";
    }


}