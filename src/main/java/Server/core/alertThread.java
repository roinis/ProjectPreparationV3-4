package Server.core;

import Domain.Events.Event;
import Domain.User.Member;
import http.Request;
import http.Response;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;

public class alertThread extends Thread{
    private Socket socket;
    private Member member;

    public alertThread(Socket socket,Member member) {
        this.socket = socket;
        this.member=member;
    }

    @Override
    public void run() {
        try {
            DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
           LinkedList events = (LinkedList) member.getEventListToSend();
           while (!events.isEmpty()){
               Response response=new Response(200);
               response.addToBody("event",events.removeFirst().toString());
               dOut.write(response.toString().getBytes());
               System.out.println(response);
               dOut.flush();
           }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void start() {
        run();
    }
}
