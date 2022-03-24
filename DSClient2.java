import java.net.*;
import java.io.*;

public class DSClient2 {
    public static void main(String[] args) throws Exception {
        Socket s=new Socket("localhost",50000);  
        BufferedReader din = new BufferedReader(new InputStreamReader(s.getInputStream())); 
        DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
        
        /* COMMAND:
            
                ./ds-server -c '/home/erik/Documents/ds-sim/configs/sample-configs/ds-sample-config01.xml' -v brief -n
            
            or for more info:
                
                ./ds-server -c '/home/erik/Documents/ds-sim/configs/sample-configs/ds-sample-config01.xml' -v all -n
        */


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

        String[] brkn = str.split(" ");
        String cores = brkn[brkn.length - 3];
        String mem = brkn[brkn.length - 2];
        String disk = brkn[brkn.length - 1];
        String id = brkn[2];
        System.out.println("id: " + id + ", cores: " + cores + ", mem: " + mem + ", disk: " + disk);

        //SEND JOB SCHEDULE
        dout.write(("GETS Capable " + cores + " " + mem + " " + disk+ "\n").getBytes());
        dout.flush();

        //READ LINE
        str = din.readLine();
        System.out.println("RCVD: \'" + str + "\'");

        //SEND OK
        dout.write(("OK\n").getBytes());
        dout.flush();

        //READ LINE
        str = din.readLine();
        System.out.println("RCVD: \'" + str + "\'");

        //SEND OK x2
        dout.write(("OK\n").getBytes());
        dout.flush();

        //READ LINE
        str = din.readLine();
        System.out.println("RCVD: \'" + str + "\'");

        String[] brkn2 = str.split(" "); 
        String type = brkn2[0];
        String serverId = brkn2[1];

        //SEND JOB SCHEDULE
        dout.write(("SCHD " + id + " " + type + " " + serverId + "\n").getBytes());
        dout.flush();

        //READ LINE
        str = din.readLine();
        System.out.println("RCVD: \'" + str + "\'");

        //SEND OK
        dout.write(("OK\n").getBytes());
        dout.flush();

        //READ LINE
        str = din.readLine();
        System.out.println("RCVD: \'" + str + "\'");

        //QUIT!
        dout.write(("QUIT\n").getBytes());
        System.out.println("SENT: QUIT");
        dout.flush();

        str = din.readLine();
        System.out.println("Connection Closed.." + "\u001B[0m");

        dout.close();  
        s.close();  
    }
}
    
