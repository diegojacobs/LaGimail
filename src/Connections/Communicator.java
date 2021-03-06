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
    private int size = 100000;
    BufferedReader _in;
    PrintWriter _out;

    public Communicator(String type) {
        if(type.equals("SMTP"))
            //serverConnection = new ServerConnection("172.20.0.151", 12000); //Jhony
            //serverConnection = new ServerConnection("172.20.5.104", 12000); //Elder
            //serverConnection = new ServerConnection("172.20.8.224", 12000); //Mohamed
            //serverConnection = new ServerConnection("172.20.0.115", 12000); //Chus
            //serverConnection = new ServerConnection("172.20.15.87", 12000); //Fernando
            serverConnection = new ServerConnection("172.20.3.144", 12000); //Duarte
        
        if(type.equals("WEB"))
            serverConnection = new ServerConnection("172.20.0.151", 8000);
    }
    
    public void initiateCommunication(){
        serverConnection.setClient();
        in = new DataInputStream(serverConnection.getInputStream());
        out = new DataOutputStream((serverConnection.getOutputStream()));
    }
    
    public void closeCommunication(){
        serverConnection.closeClient();
    }
    
    public void sendMessage(String message){
        try {
            //out.writeInt(message.length());
            out.write(message.getBytes());
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(ValidateUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String readResponse(){
        try {
            //size = in.readInt();
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
