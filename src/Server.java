/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author atamarkin2
 */
public class Server {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static String update(String arg) {
    	String line = "";
    	try {
	        Socket socket = new Socket("codebb.cloudapp.net", Integer.parseInt("17429"));
	        PrintWriter pout = new PrintWriter(socket.getOutputStream());
	        BufferedReader bin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
	        pout.println(Main.USER + " " + Main.PASS);
	        pout.println(arg);
	        pout.println("CLOSE_CONNECTION");
	        pout.flush();
	        
	        line = bin.readLine();
	    	//System.out.println(line);
	    	pout.close();
	        bin.close();    
	        socket.close();
        }
        catch (IOException e) {
        	
        }
        return line;
    }

}
