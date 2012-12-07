package com.iit.refrigeratorsimulator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mihai
 */
public class SubmitScore extends javax.swing.JFrame { 
    String username;
    int score;
    /**
     * Creates new form NewJFrame, displays score and username. Prepares score and username to be submitted to server.
     */
    public SubmitScore(String username, int score) {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE); //prevents entire program from closing if this window closes
        
        this.username = username; //sets up public variables
        this.score = score;
        
        jLabel1.setText("Congratulations " + username + " you have survived " + score + " days.");
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Submit Score");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("jLabel1");

        jButton2.setText("Quit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(186, 186, 186)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(177, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//How do you unit test functions that depend on networks/networked data?
        submit(username,score);
        jButton1.setEnabled(false); //Disables submit button once already submitted score
        
       try {//open and render current highscores
       new HighScoreInterface().setVisible(true);  
                   } catch (SQLException ex) {
                       Logger.getLogger(HighScoreInterface.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (ClassNotFoundException ex) {
                       Logger.getLogger(HighScoreInterface.class.getName()).log(Level.SEVERE, null, ex);
                   }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        super.dispose();//Close this window
    }//GEN-LAST:event_jButton2ActionPerformed


     /**
     *  Connects to mysql server and executes a query to add a username/score pair.
     */
    public static void submit(String username, int score){
        
        try {
        Statement stmt; //MYSQL statement
 
        Class.forName("com.mysql.jdbc.Driver");
        String url ="jdbc:mysql://datafridge.ccgxxpmdysxb.us-east-1.rds.amazonaws.com:3306/highscore";//database location
   
        Connection con = // Setting up connection
        DriverManager.getConnection(
        url,"mbadescu", "123123123");
 
        stmt = con.createStatement();

        stmt.executeUpdate(
                "INSERT INTO scores values ('"+ username + "','" + score + "');");  //Submit score

        con.close();    
 
 }catch( Exception e ) {
 e.printStackTrace();
 }//end catch
}
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
