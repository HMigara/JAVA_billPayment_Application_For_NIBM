package coursemanagmentsystem;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Haritha
 */
public class SingupPage extends javax.swing.JFrame {

    /**
     * Creates new form SingupPage
     */
    String fname,lname,uname,pass,conpass,con_no;
        Date dob;
        int id;
    public SingupPage() {
        initComponents();
    }
    
    void clearfilds(){
        txtconpasswoed.setText("");
        txtfirstname.setText("");
        txtlastname.setText("");
        txtusername.setText("");
        txtdob.setCalendar(null);
        txtcontactno.setText("");
        txtpassword.setText("");
        
    }
    
        public int createId(){
            ResultSet rs=null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/feesmanagmentdb","nibm","root");
                String sql ="select max(ID) from tbluser";
                Statement st =con.createStatement();
                rs=st.executeQuery(sql);
                
                while(rs.next()){
                    
                    id=rs.getInt(1);
                            id++;
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            return id;
        }
        boolean Validation()
        {
        //
        
        
        fname=txtfirstname.getText();
        lname=txtlastname.getText();
        uname=txtusername.getText();
        pass=txtpassword.getText();
        conpass=txtconpasswoed.getText();
        con_no=txtcontactno.getText();
        dob=txtdob.getDate();
        
        if (fname.equals(""))
        {
            JOptionPane.showMessageDialog(this,"Enter first name");
            return false;
        }
        
          if (lname.equals(""))
        {
            JOptionPane.showMessageDialog(this,"Enter last name");
            return false;
        }
          
            if (uname.equals(""))
        {
            JOptionPane.showMessageDialog(this,"Enter User name");
            return false;
        }
              if (pass.equals(""))
        {
            JOptionPane.showMessageDialog(this,"Enter Password");
            return false;
        }
              
                if (conpass.equals(""))
        {
            JOptionPane.showMessageDialog(this,"Enter Confirm password");
            return false;
        }
                
                  if (con_no.equals(""))
        {
            JOptionPane.showMessageDialog(this,"Enter contact number");
            return false;
        }
                  
                    if (dob == null)
        {
            JOptionPane.showMessageDialog(this,"Enter date of birth");
            return false;
        }
                    
                    return true;
        }
        
        
        public void checkpassword()
        {  
            pass=txtpassword.getText();
            if(pass.length()<8)
                    {
                    lblpassword.setText("Password must be 8 digit");
                    }
                    else{
                         lblpassword.setText("");
                    }
        }
        
         public void checkpasswordCon()
        {  
            pass=txtconpasswoed.getText();
            if(pass.length()<8)
                    {
                    lblconpassword.setText("Password must be 8 digit");
                    }
                    else
                    {
                         lblconpassword.setText("");
                    }
        }
         
         public void checkcontactnum()
         {
         con_no=txtcontactno.getText();
         if(con_no.length()==10)
         {
         lblcontactno.setText("");
         }
         else
         {
         lblcontactno.setText("number should be 10 numbers");
         }
         }
         
         public void insertdata()
         {
             SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            String Bday =date.format(dob);
             try {
                 
                 Class.forName("com.mysql.cj.jdbc.Driver");
                 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/feesmanagmentdb","nibm","root");
                 String sql="insert into tbluser values(?,?,?,?,?,?,?)";
                 PreparedStatement stm =con.prepareStatement(sql);
                 stm.setInt(1,createId());
                 stm.setString(2,fname );
                 stm.setString(3,lname );
                 stm.setString(4,uname );
                 stm.setString(5,pass );
                 stm.setString(6,Bday );
                 stm.setString(7,con_no );
                 int x =stm.executeUpdate();
                 if (x>0){
                     JOptionPane.showMessageDialog(this,"record insert succsess");
                     clearfilds();
                 }
                 con.close();
                 
                                      
             } catch (Exception ex) {
                 ex.printStackTrace();
             }
             
             
         }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtfirstname = new javax.swing.JTextField();
        txtlastname = new javax.swing.JTextField();
        txtusername = new javax.swing.JTextField();
        txtcontactno = new javax.swing.JTextField();
        txtpassword = new javax.swing.JPasswordField();
        txtconpasswoed = new javax.swing.JPasswordField();
        lblpassword = new javax.swing.JLabel();
        lblconpassword = new javax.swing.JLabel();
        lblcontactno = new javax.swing.JLabel();
        btnsignup = new javax.swing.JButton();
        btnlogin = new javax.swing.JButton();
        txtdob = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 102));

        jPanel2.setBackground(new java.awt.Color(0, 51, 153));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("First name :");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Last name :");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Password :");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Confirm Password :");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Birthday :");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Contact number :");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("User name :");

        txtfirstname.setBackground(new java.awt.Color(255, 255, 255));
        txtfirstname.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtfirstname.setForeground(new java.awt.Color(0, 0, 0));

        txtlastname.setBackground(new java.awt.Color(255, 255, 255));
        txtlastname.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtlastname.setForeground(new java.awt.Color(0, 0, 0));

        txtusername.setBackground(new java.awt.Color(255, 255, 255));
        txtusername.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtusername.setForeground(new java.awt.Color(0, 0, 0));

        txtcontactno.setBackground(new java.awt.Color(255, 255, 255));
        txtcontactno.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtcontactno.setForeground(new java.awt.Color(0, 0, 0));
        txtcontactno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcontactnoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcontactnoKeyReleased(evt);
            }
        });

        txtpassword.setBackground(new java.awt.Color(255, 255, 255));
        txtpassword.setForeground(new java.awt.Color(0, 0, 0));
        txtpassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtpasswordKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtpasswordKeyReleased(evt);
            }
        });

        txtconpasswoed.setBackground(new java.awt.Color(255, 255, 255));
        txtconpasswoed.setForeground(new java.awt.Color(0, 0, 0));
        txtconpasswoed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtconpasswoedActionPerformed(evt);
            }
        });
        txtconpasswoed.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtconpasswoedKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtconpasswoedKeyReleased(evt);
            }
        });

        lblpassword.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblpassword.setForeground(new java.awt.Color(204, 0, 0));

        lblconpassword.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblconpassword.setForeground(new java.awt.Color(204, 0, 0));

        lblcontactno.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblcontactno.setForeground(new java.awt.Color(204, 0, 0));

        btnsignup.setBackground(new java.awt.Color(0, 204, 255));
        btnsignup.setForeground(new java.awt.Color(0, 0, 0));
        btnsignup.setText("SignUp");
        btnsignup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsignupActionPerformed(evt);
            }
        });

        btnlogin.setBackground(new java.awt.Color(0, 204, 255));
        btnlogin.setForeground(new java.awt.Color(0, 0, 0));
        btnlogin.setText("Login");
        btnlogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnloginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtfirstname, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                    .addComponent(txtlastname)
                    .addComponent(txtusername)
                    .addComponent(txtcontactno)
                    .addComponent(txtpassword)
                    .addComponent(txtconpasswoed, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtdob, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnsignup)
                        .addGap(18, 18, 18)
                        .addComponent(btnlogin))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblconpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblcontactno, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtfirstname, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtlastname, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtusername, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtpassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblpassword))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtconpasswoed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblconpassword)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtdob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtcontactno, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblcontactno))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsignup)
                    .addComponent(btnlogin))
                .addContainerGap(83, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 44)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("SignUp");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(203, 203, 203)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtconpasswoedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtconpasswoedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtconpasswoedActionPerformed

    private void btnsignupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsignupActionPerformed
           if (Validation()== true){
           insertdata();// TODO add your handling code here:
   } 
    }//GEN-LAST:event_btnsignupActionPerformed

    private void txtpasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpasswordKeyPressed
      // TODO add your handling code here:
    }//GEN-LAST:event_txtpasswordKeyPressed

    private void txtpasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpasswordKeyReleased
checkpassword();        // TODO add your handling code here:
    }//GEN-LAST:event_txtpasswordKeyReleased

    private void txtconpasswoedKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtconpasswoedKeyPressed
checkpasswordCon();         // TODO add your handling code here:
    }//GEN-LAST:event_txtconpasswoedKeyPressed

    private void txtcontactnoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcontactnoKeyPressed
 checkcontactnum();        // TODO add your handling code here:
    }//GEN-LAST:event_txtcontactnoKeyPressed

    private void txtcontactnoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcontactnoKeyReleased
 checkcontactnum();        // TODO add your handling code here:
    }//GEN-LAST:event_txtcontactnoKeyReleased

    private void txtconpasswoedKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtconpasswoedKeyReleased
      checkpasswordCon();   // TODO add your handling code here:
    }//GEN-LAST:event_txtconpasswoedKeyReleased

    private void btnloginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnloginActionPerformed
    Loginpage1 login = new Loginpage1();
    login.show();
    this.dispose();// TODO add your handling code here:
    }//GEN-LAST:event_btnloginActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SingupPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SingupPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SingupPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SingupPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SingupPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnlogin;
    private javax.swing.JButton btnsignup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblconpassword;
    private javax.swing.JLabel lblcontactno;
    private javax.swing.JLabel lblpassword;
    private javax.swing.JPasswordField txtconpasswoed;
    private javax.swing.JTextField txtcontactno;
    private com.toedter.calendar.JDateChooser txtdob;
    private javax.swing.JTextField txtfirstname;
    private javax.swing.JTextField txtlastname;
    private javax.swing.JPasswordField txtpassword;
    private javax.swing.JTextField txtusername;
    // End of variables declaration//GEN-END:variables
}
