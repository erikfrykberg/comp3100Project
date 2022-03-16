import java.net.*;
import java.io.*;

public class DSClient {
    public static void main(String[] args) throws Exception {
        Socket s=new Socket("localhost",50000);  
        BufferedReader din = new BufferedReader(new InputStreamReader(s.getInputStream())); 
        DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
        
        System.out.println("Server started + established connection correctly!\n");

        String str = "";
        dout.write(("HELO\n").getBytes());
        System.out.println("SENT: HELO");

        dout.flush();

        //EXPECT "OK"
        str = din.readLine();
        System.out.println("RCVD: \'" + str + "\'");

        //SEND AUTH
        dout.write(("AUTH ERIK.FRYKBERG\n").getBytes());
        System.out.println("SENT: AUTH ERIK.FRYKBERG");
        dout.flush();

        //EXPECT SYSTEM LOG INFORMATION
        str = din.readLine();
        System.out.println("RCVD: \'" + str + "\'");

        //SEND REDY
        dout.write(("REDY\n").getBytes());
        System.out.println("SENT: REDY");
        dout.flush();

        //EXPECT SYSTEM LOG INFORMATION
        str = din.readLine();
        System.out.println("RCVD: \'" + str + "\'");

        //SEND QUIT
        dout.write(("QUIT\n").getBytes());
        System.out.println("SENT: QUIT");
        dout.flush();

        str = din.readLine();
        System.out.println("Connection Closed.." + "\u001B[0m");

        dout.close();  
        s.close();  
    }
}
    
