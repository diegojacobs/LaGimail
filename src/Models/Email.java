/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author Diego Jacobs
 */
public class Email {
    private int EmailId;
    private String From;
    private String To;
    private String Data;
    private Date Date;

    public Email() {
    }
    
    public Email(String From, String To, String Data) {
        this.From = From;
        this.To = To;
        this.Data = Data;
    }
    
    public Email(int EmailId, String From, String To, String Data) {
        this.EmailId = EmailId;
        this.From = From;
        this.To = To;
        this.Data = Data;
    }

    public Email(int EmailId, String From, String To, Date Date, String Data) {
        this.EmailId = EmailId;
        this.From = From;
        this.To = To;
        this.Date = Date;
        this.Data = Data;
    }
    
    public Email(String From, String To, Date Date, String Content) {
        this.EmailId = EmailId;
        this.From = From;
        this.To = To;
        this.Date = Date;
        this.Data = Data;
    }

        
    public int getEmailId() {
        return EmailId;
    }

    public void setEmailId(int EmailId) {
        this.EmailId = EmailId;
    }

    public String getFrom() {
        return From;
    }

    public void setFrom(String From) {
        this.From = From;
    }

    public String getTo() {
        return To;
    }

    public void setTo(String To) {
        this.To = To;
    }

    public String getData() {
        return Data;
    }

    public void setData(String Data) {
        this.Data = Data;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }
    
    public String getParsedData(){
        String text = new String();
        String subject = new String();
        String from = new String();
        String to = new String();
        String cc = new String();
        String content = new String();
        
        String[] array = this.Data.split("\n");
        System.out.println("Data: \n\r" +this.Data);
        System.out.println(array.length);
        for(int i=0; i<array.length; i++){
            String line = array[i];
            System.out.println(line);
            if(line.startsWith("SUBJECT")){
                subject = array[i];
            }            
            else if(line.startsWith("FROM")){
                from = array[i];
            }            
            else if(line.startsWith("TO")){
                to = array[i];
            }            
            else if(line.startsWith("CC")){
                cc = array[i];
            }
            else 
                content = array[i];
        }
        
        if (from.isEmpty())
            text = "FROM: " + this.From + "\n";
        else
            text = from + "\n";
        if (to.isEmpty())
            text += "TO: " + this.To + "\n";
        else
            text += to + "\n";
        if (cc.isEmpty())
            text += "CC: \n";
        else
            text += cc + "\n";
        if (subject.isEmpty())
            text += "SUBJECT: \n";
        else
            text += subject + "\n";
        text += "\nContent: \n";
        text += content;
        
        return text;
    }

    @Override
    public String toString() {
        return "From: " + this.From + " " + this.Date.toString();
    }
}
