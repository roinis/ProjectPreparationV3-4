package Domain.Events;
import Domain.Events.*;
import Domain.System.*;
import Domain.Jobs.*;
import Domain.Game.*;
import Domain.Association.*;

import java.sql.Time;
import java.time.LocalDateTime;

public abstract class GameEvent implements Event {
    LocalDateTime eventGameTime;
    Team eventTeam;
    Player eventPlayer;

    public GameEvent(LocalDateTime eventGameTime, Team team, Player player) {
        this.eventGameTime = eventGameTime;
        eventTeam = team;
        eventPlayer = player;
        addEventToLog();
    }

    public void addEventToLog(){
        AlphaSystem.getSystem().getLog().addEvent(this);
    }

    public LocalDateTime getEventGameTime() {
        return eventGameTime;
    }

    public void setEventGameTime(LocalDateTime eventGameTime) {
        this.eventGameTime = eventGameTime;
    }

    public Team getEventTeam() {
        return eventTeam;
    }

    public void setEventTeam(Team eventTeam) {
        this.eventTeam = eventTeam;
    }

    public Player getEventPlayer() {
        return eventPlayer;
    }

    public void setEventPlayer(Player eventPlayer) {
        this.eventPlayer = eventPlayer;
    }
}
