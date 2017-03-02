/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LaGmail;

import Connections.SendEmail;
import java.util.ArrayList;

/**
 *
 * @author Diego Jacobs
 */
public class NewEmailUI extends javax.swing.JFrame {
    private InboxUI inboxUI;
    private String user;
    private SendEmail sendEmail;
    
    /**
     * Creates new form NewEmail
     * @param user
     */
    public NewEmailUI(String user) {
        initComponents();
        this.user = user;
        
        fromTextField.setText(user);
        fromTextField.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fromTextField = new javax.swing.JTextField();
        toTextField = new javax.swing.JTextField();
        ccTextField = new javax.swing.JTextField();
        bccTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        contentTextArea = new javax.swing.JTextArea();
        sendButton = new javax.swing.JButton();
        discardButton = new javax.swing.JButton();
        subjectTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        fromTextField.setToolTipText("From");

        toTextField.setToolTipText("To");

        ccTextField.setToolTipText("Cc");

        bccTextField.setToolTipText("Bcc");

        contentTextArea.setColumns(20);
        contentTextArea.setRows(5);
        jScrollPane1.setViewportView(contentTextArea);

        sendButton.setBackground(new java.awt.Color(0, 204, 102));
        sendButton.setText("Send");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        discardButton.setBackground(new java.awt.Color(204, 0, 51));
        discardButton.setText("Discard");
        discardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discardButtonActionPerformed(evt);
            }
        });

        subjectTextField.setToolTipText("Subject");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fromTextField)
            .addComponent(toTextField)
            .addComponent(ccTextField)
            .addComponent(bccTextField)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(discardButton)
                .addGap(18, 18, 18)
                .addComponent(sendButton))
            .addGroup(layout.createSequentialGroup()
                .addComponent(subjectTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(fromTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(toTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ccTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bccTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(subjectTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sendButton)
                    .addComponent(discardButton)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void discardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discardButtonActionPerformed
        // TODO add your handling code here:
        inboxUI = new InboxUI(this.user, false);
        this.dispose();
        inboxUI.setVisible(true);
    }//GEN-LAST:event_discardButtonActionPerformed

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        // TODO add your handling code here:
        String from = user;
        String labelTo = toTextField.getText(); 
        String labelCc = ccTextField.getText(); 
        String labelBcc = bccTextField.getText();
        String subject = subjectTextField.getText();
        String content = contentTextArea.getText();
        
        String data = new String();
        data += "SUBJECT: <" + subject + ">\n";
        data += "FROM: <" + from + ">\n";
        
        ArrayList<String> to = new ArrayList<String>();
        
        if(!labelTo.isEmpty()){
            String[] emailsTo = labelTo.split(";");
            for(String email : emailsTo){
                to.add(email);
                data += "TO: <" + email + ">\n";
            }
        }
        
        if(!labelCc.isEmpty()){
            String[] emailsCc = labelCc.split(";");
            for(String email : emailsCc){
                to.add(email);
                data += "CC: <" + email + ">\n";
            }
        }
        
        if(!labelBcc.isEmpty()){
            String[] emailsBcc = labelBcc.split(";");
            for(String email : emailsBcc){
                to.add(email);
                data += "BCC: <" + email + ">\n";
            }
        }
        
        data += content + "\n";
        
        sendEmail = new SendEmail(user, to, data);
        sendEmail.Send();
        //inboxUI = new InboxUI(user, false);
        this.dispose();
        //inboxUI.setVisible(true);
    }//GEN-LAST:event_sendButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bccTextField;
    private javax.swing.JTextField ccTextField;
    private javax.swing.JTextArea contentTextArea;
    private javax.swing.JButton discardButton;
    private javax.swing.JTextField fromTextField;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton sendButton;
    private javax.swing.JTextField subjectTextField;
    private javax.swing.JTextField toTextField;
    // End of variables declaration//GEN-END:variables
}
