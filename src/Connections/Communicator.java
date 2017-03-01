/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connections;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego Jacobs
 */
public class Communicator {
    private ServerConnection serverConnection;
    private DataOutputStream out;
    private DataInputStream in;
    private int size = 256;
    BufferedReader _in;
    PrintWriter _out;

    public Communicator() {
        serverConnection = new ServerConnection("127.0.0.1", 8000);
    }
    
    public void initiateCommunication(){
        serverConnection.setClient();
        in = new DataInputStream(serverConnection.getInputStream());
        out = new DataOutputStream((serverConnection.getOutputStream()));
    }
    
    public void closeCommunication(){
        serverConnection.closeClient();
    }
    
    public String readUTF(String message){
        try {
            out.write(message.getBytes());
            out.flush();
            byte[] request_bytes = new byte[size];
            in.read(request_bytes);
            String response = new String(request_bytes);
            return response;
        } catch (IOException ex) {
            Logger.getLogger(ValidateUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}