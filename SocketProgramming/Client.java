package SocketProgramming;

import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket s=new Socket("localhost",3333);  
        DataInputStream din=new DataInputStream(s.getInputStream());  
        DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  
        
        System.out.println("\u001B[32m" + "Server started + established connection correctly!" + "\u001B[31m");

        String str = br.readLine();
        while(!str.equalsIgnoreCase("HELO")){
            System.out.println("Unknown command; " + str);
            str = br.readLine();
        }
        
        dout.writeUTF("HELO");
        dout.flush();

        //HELO was sent. We must await a reply.
        str = din.readUTF();
        if(str.equalsIgnoreCase("G'DAY")){
            System.out.println("BYE");
            dout.writeUTF("BYE");
        }

        str = din.readUTF();
        if(str.equalsIgnoreCase("BYE")){
            System.out.println("Connection Closed.." + "\u001B[0m");
        }

        
        dout.close();  
        s.close();  
    }
}
