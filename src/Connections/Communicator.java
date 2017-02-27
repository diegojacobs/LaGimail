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
    private DataOutputStream output;
    private DataInputStream in;
    BufferedReader _in;
    PrintWriter _out;

    public Communicator() {
        serverConnection = new ServerConnection("127.0.0.1", 8000);
    }
    
    public void initiateCommunication(){
        serverConnection.setClient();
        _out = new java.io.PrintWriter(serverConnection.getOutputStream());
        _in = new java.io.BufferedReader(new java.io.InputStreamReader(serverConnection.getInputStream()));
    }
    
    public void closeCommunication(){
        serverConnection.closeClient();
    }
    
    public String readUTF(String message){
        try {
            _out.println(message);
            _out.flush();
            String response = _in.readLine();
            return response;
        } catch (IOException ex) {
            Logger.getLogger(ValidateUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
