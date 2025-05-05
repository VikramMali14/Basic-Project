import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.ClientInfoStatus;
import java.util.Scanner;

public class Client {

    // Initialize socket and input/output streams
    private Socket s = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;

    // Constructor to put IP address and port
    public Client(String addr, int port,String user_Name) {
        // Establish a connection
        try {
            s = new Socket(addr, port);
            System.out.println("Connected");

            // Takes input from terminal
            in = new DataInputStream(s.getInputStream());
            // Sends output to the socket
            out = new DataOutputStream(s.getOutputStream());
            out.writeUTF(user_Name);
        } catch (UnknownHostException u) {
            System.out.println(u);
            return;
        } catch (IOException i) {
            System.out.println(i);
            return;
        }
         Thread read_Message=new Thread(()->{
             try {
                 while (true) {
                     String mess = in.readUTF();
                     System.out.println(mess);
                 }   }
             catch (Exception e){
                 System.out.println(e);
                              }
         });
        read_Message.start();
        Scanner sc=new Scanner(System.in);
       while (true){
        String a= sc.next();
        try{

        out.writeUTF(a);
       }
    catch (Exception e){
        System.out.println(e);
        }
    }
    }
}