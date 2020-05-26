package Server;

import Domain.System.AlphaSystem;
import Domain.System.Register;
import Domain.User.Member;
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
        AlphaSystem.getSystem().Register("test","123","456","gal");
        System.out.println("Server Starting...");
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrConfiguration();
        System.out.println("Using Port:"+conf.getPort());
        System.out.println("Using Webroot:"+conf.getWebroot());
        ServerListenerThread serverListenerThread = new ServerListenerThread(conf.getPort(),conf.getWebroot());
        serverListenerThread.start();
    }
}
