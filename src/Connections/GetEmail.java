/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connections;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego Jacobs
 */
public class GetEmail {
    private ServerConnection serverConnection;
    private String email;
    private Calendar date;
    private DateFormat dateFormat;
    private String message;
    private String response;
    private DataOutputStream output;
    private DataInputStream in;
    
    public GetEmail(String email){
        this.email = email;
        this.date = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        this.message = new String();
        serverConnection = new ServerConnection("127.0.0.1", 8000);
    }
    
    public Boolean validate(){
        Boolean isValid = false;
        serverConnection.setClient();
        
        OutputStream outToServer = serverConnection.getOutputStream();
        output = new DataOutputStream(outToServer);
        InputStream inFromServer = serverConnection.getInputStream();
        in = new DataInputStream(inFromServer);
        
        message = "HELO";
        System.out.println(message);
        this.writeUTF(message);        
        response = readUTF();
        System.out.println(response);
        if(response.startsWith("200")){
            message = "USER: <" + this.email +">";
            System.out.println(message);
            this.writeUTF(message);        
            response = readUTF();
            System.out.println(response);
            if(response.startsWith("200")){
                message = "DATE: <" + this.dateFormat.format(this.date) +">";
                System.out.println(message);
                this.writeUTF(message);        
                response = readUTF();
                System.out.println(response);
                if(response.startsWith("200")){
                    message = "GET";
                    System.out.println(message);
                    this.writeUTF(message);        
                    response = readUTF();
                    System.out.println(response);
                    isValid = response.startsWith("200");
                }
            }
        }
        message = "QUIT";
        this.writeUTF(message);        
        response = readUTF();
        
        serverConnection.closeClient();
        return isValid;
    }
    
    private void writeUTF(String message){
        try {
            output.writeUTF(message);
        } catch (IOException ex) {
            Logger.getLogger(ValidateUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String readUTF(){
        try {
            return in.readUTF();
        } catch (IOException ex) {
            Logger.getLogger(ValidateUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
