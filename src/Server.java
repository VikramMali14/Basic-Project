import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server {

    // Initialize socket and input stream
    private Socket s = null;
    private ServerSocket ss = null;
    static List<ClientHandler> all_clients=new ArrayList<>();
    static Map<String,ClientHandler> user=new HashMap<>();
    // Constructor with port
    public Server(int port) {
        try {
            ss = new ServerSocket(port);
           while(true){
            s=ss.accept();
            ClientHandler a=new ClientHandler(s);
            all_clients.add(a);
               try{
                   DataInputStream in=new DataInputStream(s.getInputStream());
               String me=in.readUTF();
               user.put(me,a);
               }
               catch (IOException e){
                   System.out.println(e);
               }
            a.start();
               System.out.println(user.toString());
        }
        }
        catch (Exception e){
            e.printStackTrace();
        }
}

private class ClientHandler extends Thread{
     Socket Client_Socket;
    private DataInputStream in = null;
    private DataOutputStream out=null;
     public ClientHandler(Socket s){
         Client_Socket=s;
            try{
                in=new DataInputStream(Client_Socket.getInputStream());
                out=new DataOutputStream(Client_Socket.getOutputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
     }
     @Override
     public void run(){
         try {
             while (true){
                 String message=in.readUTF();
                 broadcst(message,this);
             }
         } catch (IOException e) {
             System.out.println(e);

         }
     }
     void broadcst(String msg,ClientHandler s){
         for(ClientHandler a:all_clients){
             if(a!=s){
                 try {
                     a.out.writeUTF(msg);
                 } catch (IOException e) {
                     System.out.println(e);
                 }
             }
         }
     }
}
}