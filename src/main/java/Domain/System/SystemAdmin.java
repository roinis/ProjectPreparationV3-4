package Domain.System;
import Domain.Events.*;
import Domain.User.*;
import Domain.Jobs.*;
import Domain.Game.*;
import Domain.Association.*;

import java.util.Scanner;

public class SystemAdmin extends Member {

    public SystemAdmin(String user_name, String user_password, String user_id, String full_name){
        super(user_name, user_password, user_id, full_name);
        AlphaSystem system = AlphaSystem.getSystem();
        system.AddAdmin(this);
    }

    public void CloseTeam(Team TeamToClose) throws Exception {
        TeamToClose.setStatus(Team.Status.close);
    }

    public void RemoveMember(Member member) {
        AlphaSystem system = AlphaSystem.getSystem();
        system.RemoveMember(member);
    }

    public void GetNextTicket(){
        Scanner sc = new Scanner(System.in);
        AlphaSystem system = AlphaSystem.getSystem();
        Ticket TicketToAnswer = system.GetNextUnansweredTicket();
        TicketToAnswer.getComplaint();
        String Answer = sc.next();
        TicketToAnswer.AnswerTicket(Answer);
    }

    public EventLog GetLog(){
        AlphaSystem system = AlphaSystem.getSystem();
        return system.getLog();
    }



}
