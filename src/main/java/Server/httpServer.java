package Server;
public class httpServer {}
/*
import Domain.Association.AssociationMember;
import Domain.Game.*;
import Domain.Jobs.TeamOwner;
import Domain.System.AlphaSystem;
import Domain.System.Register;
import Domain.User.Member;
import Exceptions.DomainException;
import Server.Config.Configuration;
import Server.Config.ConfigurationManager;
import Server.core.ServerListenerThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class httpServer {
//    private final static Logger LOGGER = LoggerFactory.getLogger(httpServer.class);

    public static void main(String[] args) throws IOException {
        try {
            AssociationMember associationMember=new AssociationMember("amit","123","456","amit");
            Stadium stadium=new Stadium("ter","beer sheve");
            AlphaSystem.getSystem().Register("alona","123","456","alona");
            AlphaSystem.getSystem().Register("zino","123","456","zino");
            Member alona= (Member) AlphaSystem.getSystem().GetSpecificFromDB(2,"alona");
            Member zino= (Member) AlphaSystem.getSystem().GetSpecificFromDB(2,"zino");
            TeamOwner teamOwner1=new TeamOwner(alona);
//            TeamOwner teamOwner2=new TeamOwner(zino);
            Team team1=new Team("beer",teamOwner1,stadium);
//            teamOwner1.addOwner("zino");

            League league1=new League("l1",new SchedulingPolicy(),new ScoringPolicy());
            League league2=new League("l2",new SchedulingPolicy(),new ScoringPolicy());
            // Team team2=new Team("macabi beer sheve",teamOwner2,stadium);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Server Starting...");
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrConfiguration();
        System.out.println("Using Port:"+conf.getPort());
        System.out.println("Using Webroot:"+conf.getWebroot());
        ServerListenerThread serverListenerThread = new ServerListenerThread(conf.getPort(),conf.getWebroot());
        serverListenerThread.start();
    }
}
*/