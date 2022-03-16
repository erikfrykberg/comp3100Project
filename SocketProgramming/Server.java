import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) throws Exception{  
        //INITIALISE
        ServerSocket ss=new ServerSocket(3333);  
        Socket s=ss.accept();  
        DataInputStream din=new DataInputStream(s.getInputStream());  
        DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
        
        System.out.println("\u001B[32m" + "Server started + established connection correctly!" + "\u001B[31m");

        //LOGIC
        //we must await the first HELO.
        String str = din.readUTF();  
        if(str.equalsIgnoreCase("HELO")){
            System.out.println("G'DAY");
            dout.writeUTF("G'DAY");
            dout.flush();
        } else {
            System.out.println("Unknown command: " + str);
            new Exception();
        }

        str = din.readUTF();
        if(str.equalsIgnoreCase("BYE")) {
            System.out.println("BYE");
            dout.writeUTF("BYE");
            dout.flush();
        } else {
            System.out.println("Unknown command: " + str);
            new Exception();
        }

        System.out.println("Connection closed." + "\u001B[0m");
        //CLOSE.
        din.close();  
        s.close();  
        ss.close();  

    }
}