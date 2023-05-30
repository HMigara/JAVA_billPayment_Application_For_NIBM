/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package coursemanagmentsystem;
import com.mysql.cj.jdbc.PreparedStatementWrapper;
import java.sql.ResultSet;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author Haritha
 */
public class EditBillPage extends JFrame {
    /**
     * Creates new form AddFeespage
     */
    public EditBillPage() {
        initComponents();
        
        lblpressenter.setVisible(false);
        FillcourseCombo();
        FillDetails();
        //int billno = getBillNo();
        //txtBillno.setText(Integer.toString(billno));
        
        
    }
Color clrin = new Color(0,0,102);//in color
Color clrout = new Color(51,51,255);//out color



public void selectCash()
{
    lblBankName.setVisible(false);
    lblCardNumber.setVisible(false);
    lblChequeNumber.setVisible(false);
    
    txtBankName.setVisible(false);
    txtCardNumber.setVisible(false);
    txtCheque.setVisible(false);
}

public boolean validation()
{
    if(txtStudentname.getText().equals(""))
    {
        JOptionPane.showMessageDialog(this, "pleace enter student name");
        return false;
    }
    if(txtStudentId.getText().equals(""))
    {
        JOptionPane.showMessageDialog(this, "pleace enter student ID");
        return false;
    }
    if(combCourse.getSelectedItem().toString().equals("select"))
    {
        JOptionPane.showMessageDialog(this, "pleace select the course");
        return false;
    }
    if(txtSemster.getText().equals("")||txtYear.getText().equals(""))
    {
        JOptionPane.showMessageDialog(this, "pleace enter the year and semster");
        return false;
    }
    if(txtDate.getDate()==null)
    {
        JOptionPane.showMessageDialog(this, "pleace select the date");
        return false;
    }
    if(txtDetails.getText().equals(""))
    {
        JOptionPane.showMessageDialog(this, "pleace add payment Details ");
        return false;
    }
    //card
    if(combPayment.getSelectedIndex()==1)
    {
      if(txtCardNumber.getText().equals("") || txtBankName.getText().equals(""))
      {
          JOptionPane.showMessageDialog(this, "pleace enter card number abd bank name ");
        return false;
      }
    }
    if (txtAmount.getText().equals(""))
    {
        JOptionPane.showMessageDialog(this, "pleace enter course amount ");
        return false;
    }
    //cheque
    if(combPayment.getSelectedIndex()==2)
    {
      if(txtCheque.getText().equals("") || txtBankName.getText().equals(""))
      {
          JOptionPane.showMessageDialog(this, "pleace enter card number abd bank name ");
        return false;
      }
    }
    return true;
}
   
public void FillcourseCombo()
{
    try
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
                 Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/feesmanagmentdb","nibm","root");
                PreparedStatement st=connect.prepareStatement("select courseName from tblcourse");
                 ResultSet rs =st.executeQuery();
                
                 while (rs.next())
                 {
                     combCourse.addItem(rs.getString("courseName"));
                 }
                
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
}

public void FillDetails()
{
   try {
             Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/feesmanagmentdb","nibm","root");
        PreparedStatement ps = con.prepareStatement("SELECT * FROM tblfees ORDER BY billNo DESC LIMIT 1");
            ResultSet rs = ps.executeQuery();
            rs.next();
            
            txtBillno.setText(rs.getString("billNo"));
            combPayment.setSelectedItem(rs.getString("paymentMethod"));
            combCourse.setSelectedItem(rs.getString("course"));
            txtBankName.setText(rs.getString("bankName"));
            txtCardNumber.setText(rs.getString("cardNum"));
            txtCheque.setText(rs.getString("chequeNum"));
            txtYear.setText(rs.getString("year"));
            txtSemster.setText(rs.getString("semster"));
            txtDate.setDate(rs.getDate("date"));
            txtStudentId.setText(rs.getString("studentid"));
            txtStudentname.setText(rs.getString("studentname"));
            txtcuorseName.setText(rs.getString("course"));
            txtDetails.setText(rs.getString("paymentDetails"));
            txtTotalWord.setText(rs.getString("totalWord"));
            txtTax.setText(rs.getString("tax"));
            txtRegisterfee.setText(rs.getString("registation"));
            txtAmount.setText(rs.getString("amount"));
            txtTotal.setText(rs.getString("total"));
        
        
        
        } catch (Exception e) {
            e.printStackTrace();
        }
}

public String updateData()
{
    String totalword,studentID,bankname,cardnumber,chequnumber,studentname,paymentMethod,course,year,semster,amount,tax,total,resitationFee,paymentDetails;
    String msg ="";
    Date datee=txtDate.getDate();
    int billno=Integer.parseInt(txtBillno.getText());
    studentname =txtStudentname.getText();
    studentID=txtStudentId.getText();
    bankname=txtBankName.getText(); 
    cardnumber=txtCardNumber.getText();
    chequnumber=txtCheque.getText();
    paymentMethod=combPayment.getSelectedItem().toString();
    course=combCourse.getSelectedItem().toString();
    tax=txtTax.getText();
    total=txtTotal.getText();
    amount=txtAmount.getText();
    resitationFee=txtRegisterfee.getText();
    paymentDetails=txtDetails.getText();
    year=txtYear.getText();
    semster=txtSemster.getText();
    totalword=txtTotalWord.getText();
    
    SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            String Bday =date.format(datee);
    
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/feesmanagmentdb","nibm","root");
        PreparedStatement ps = con.prepareStatement("update tblfees set studentName=?,studentId=?,paymentmethod=?,bankName=?,"
                + "cardNum=?,chequeNum=?,date=?,course=?,year=?,semster=?,amount=?,tax=?,registation=?,total=?,paymentDetails=?,totalWord=?"
                + "where billNo=?");
        //ps.setInt(1, billno);
        ps.setString(1,studentname);
        ps.setString(2, studentID);
        ps.setString(3, paymentMethod);
        ps.setString(4, bankname);
        ps.setString(5, cardnumber);
        ps.setString(6, chequnumber);
        ps.setString(7, Bday);
         ps.setString(8, course);
         ps.setString(9, year);
          ps.setString(10, semster);
           ps.setString(11, amount);
            ps.setString(12, tax);
             ps.setString(13, resitationFee);
              ps.setString(14, total);
               ps.setString(15, paymentDetails);
               ps.setString(16, totalword);
               ps.setInt(17,billno);
               
               
               int rows = ps.executeUpdate();
               if(rows==1)
               {
                   msg = "add";
               }
               else{
                   msg="error";
               }
        
        
    } catch (Exception e) {
        e.printStackTrace();
    }
    
    
    return msg;
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sidepanel = new javax.swing.JPanel();
        panelhome = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        paneladdfees = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        panelserchrecord = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        panelhome4 = new javax.swing.JPanel();
        panelViewRecord = new javax.swing.JLabel();
        panelEditRecord = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        panelViewCourse = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        panelLogout = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        panelViweReport = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        mainpanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        combPayment = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        txtDate = new com.toedter.calendar.JDateChooser();
        lblBankName = new javax.swing.JLabel();
        txtBankName = new javax.swing.JTextField();
        lblChequeNumber = new javax.swing.JLabel();
        txtCheque = new javax.swing.JTextField();
        lblCardNumber = new javax.swing.JLabel();
        txtCardNumber = new javax.swing.JTextField();
        panelChaild = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        combCourse = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtStudentname = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtcuorseName = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDetails = new javax.swing.JTextArea();
        btnPrint = new javax.swing.JButton();
        txtTotalWord = new javax.swing.JTextField();
        txtSemster = new javax.swing.JTextField();
        txtTax = new javax.swing.JTextField();
        txtRegisterfee = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtStudentId = new javax.swing.JTextField();
        txtYear = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txtAmount = new javax.swing.JTextField();
        lblpressenter = new javax.swing.JLabel();
        txtBillno = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sidepanel.setBackground(new java.awt.Color(0, 0, 153));

        panelhome.setBackground(new java.awt.Color(51, 51, 255));
        panelhome.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelhome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelhomeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelhomeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelhomeMouseExited(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/coursemanagmentsystem/icons/icons8-home-50.png"))); // NOI18N
        jLabel1.setText("HOME");

        javax.swing.GroupLayout panelhomeLayout = new javax.swing.GroupLayout(panelhome);
        panelhome.setLayout(panelhomeLayout);
        panelhomeLayout.setHorizontalGroup(
            panelhomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelhomeLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );
        panelhomeLayout.setVerticalGroup(
            panelhomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelhomeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        paneladdfees.setBackground(new java.awt.Color(51, 51, 255));
        paneladdfees.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        paneladdfees.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                paneladdfeesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                paneladdfeesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                paneladdfeesMouseExited(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/coursemanagmentsystem/icons/add.png"))); // NOI18N
        jLabel3.setText("Add Fees");

        javax.swing.GroupLayout paneladdfeesLayout = new javax.swing.GroupLayout(paneladdfees);
        paneladdfees.setLayout(paneladdfeesLayout);
        paneladdfeesLayout.setHorizontalGroup(
            paneladdfeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneladdfeesLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        paneladdfeesLayout.setVerticalGroup(
            paneladdfeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneladdfeesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3))
        );

        panelserchrecord.setBackground(new java.awt.Color(51, 51, 255));
        panelserchrecord.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelserchrecord.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelserchrecordMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelserchrecordMouseExited(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/coursemanagmentsystem/icons/record.png"))); // NOI18N
        jLabel4.setText("Search record");

        javax.swing.GroupLayout panelserchrecordLayout = new javax.swing.GroupLayout(panelserchrecord);
        panelserchrecord.setLayout(panelserchrecordLayout);
        panelserchrecordLayout.setHorizontalGroup(
            panelserchrecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelserchrecordLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );
        panelserchrecordLayout.setVerticalGroup(
            panelserchrecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelserchrecordLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4))
        );

        panelhome4.setBackground(new java.awt.Color(51, 51, 255));
        panelhome4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelhome4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelhome4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelhome4MouseExited(evt);
            }
        });

        panelViewRecord.setBackground(new java.awt.Color(255, 255, 255));
        panelViewRecord.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        panelViewRecord.setForeground(new java.awt.Color(255, 255, 255));
        panelViewRecord.setIcon(new javax.swing.ImageIcon(getClass().getResource("/coursemanagmentsystem/icons/view record.png"))); // NOI18N
        panelViewRecord.setText("View record");

        javax.swing.GroupLayout panelhome4Layout = new javax.swing.GroupLayout(panelhome4);
        panelhome4.setLayout(panelhome4Layout);
        panelhome4Layout.setHorizontalGroup(
            panelhome4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelhome4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(panelViewRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelhome4Layout.setVerticalGroup(
            panelhome4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelhome4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelViewRecord)
                .addContainerGap())
        );

        panelEditRecord.setBackground(new java.awt.Color(51, 51, 255));
        panelEditRecord.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelEditRecord.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelEditRecordMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelEditRecordMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelEditRecordMouseExited(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/coursemanagmentsystem/icons/edit.png"))); // NOI18N
        jLabel6.setText("Edit course");

        javax.swing.GroupLayout panelEditRecordLayout = new javax.swing.GroupLayout(panelEditRecord);
        panelEditRecord.setLayout(panelEditRecordLayout);
        panelEditRecordLayout.setHorizontalGroup(
            panelEditRecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEditRecordLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        panelEditRecordLayout.setVerticalGroup(
            panelEditRecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEditRecordLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelViewCourse.setBackground(new java.awt.Color(51, 51, 255));
        panelViewCourse.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelViewCourse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelViewCourseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelViewCourseMouseExited(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/coursemanagmentsystem/icons/view record.png"))); // NOI18N
        jLabel7.setText("View course");

        javax.swing.GroupLayout panelViewCourseLayout = new javax.swing.GroupLayout(panelViewCourse);
        panelViewCourse.setLayout(panelViewCourseLayout);
        panelViewCourseLayout.setHorizontalGroup(
            panelViewCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelViewCourseLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        panelViewCourseLayout.setVerticalGroup(
            panelViewCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelViewCourseLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7))
        );

        panelLogout.setBackground(new java.awt.Color(51, 51, 255));
        panelLogout.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelLogoutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelLogoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelLogoutMouseExited(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/coursemanagmentsystem/icons/logout.png"))); // NOI18N
        jLabel8.setText("Logout");

        javax.swing.GroupLayout panelLogoutLayout = new javax.swing.GroupLayout(panelLogout);
        panelLogout.setLayout(panelLogoutLayout);
        panelLogoutLayout.setHorizontalGroup(
            panelLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLogoutLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        panelLogoutLayout.setVerticalGroup(
            panelLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLogoutLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelViweReport.setBackground(new java.awt.Color(51, 51, 255));
        panelViweReport.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelViweReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelViweReportMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelViweReportMouseExited(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/coursemanagmentsystem/icons/view record.png"))); // NOI18N
        jLabel9.setText("View report");

        javax.swing.GroupLayout panelViweReportLayout = new javax.swing.GroupLayout(panelViweReport);
        panelViweReport.setLayout(panelViweReportLayout);
        panelViweReportLayout.setHorizontalGroup(
            panelViweReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelViweReportLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        panelViweReportLayout.setVerticalGroup(
            panelViweReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelViweReportLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9))
        );

        javax.swing.GroupLayout sidepanelLayout = new javax.swing.GroupLayout(sidepanel);
        sidepanel.setLayout(sidepanelLayout);
        sidepanelLayout.setHorizontalGroup(
            sidepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidepanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sidepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelViweReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelViewCourse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelEditRecord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelhome4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelserchrecord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(paneladdfees, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelhome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        sidepanelLayout.setVerticalGroup(
            sidepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidepanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelhome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(paneladdfees, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(panelserchrecord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelhome4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelEditRecord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelViewCourse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelViweReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addComponent(panelLogout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(354, Short.MAX_VALUE))
        );

        getContentPane().add(sidepanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, 1040));

        mainpanel.setBackground(new java.awt.Color(0, 51, 153));
        mainpanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Bill no :");
        mainpanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 16, 50, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Payment method :");
        mainpanel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 56, -1, -1));

        combPayment.setBackground(new java.awt.Color(255, 255, 255));
        combPayment.setForeground(new java.awt.Color(0, 0, 0));
        combPayment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash", "Card", "Cheque" }));
        combPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combPaymentActionPerformed(evt);
            }
        });
        mainpanel.add(combPayment, new org.netbeans.lib.awtextra.AbsoluteConstraints(121, 54, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Date :");
        mainpanel.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(628, 16, 50, -1));

        txtDate.setBackground(new java.awt.Color(255, 255, 255));
        mainpanel.add(txtDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(664, 14, 170, -1));

        lblBankName.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblBankName.setForeground(new java.awt.Color(255, 255, 255));
        lblBankName.setText("Bank name :");
        mainpanel.add(lblBankName, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 96, -1, -1));

        txtBankName.setBackground(new java.awt.Color(255, 255, 255));
        txtBankName.setForeground(new java.awt.Color(0, 0, 0));
        mainpanel.add(txtBankName, new org.netbeans.lib.awtextra.AbsoluteConstraints(93, 94, 318, -1));

        lblChequeNumber.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblChequeNumber.setForeground(new java.awt.Color(255, 255, 255));
        lblChequeNumber.setText("Cheque number :");
        mainpanel.add(lblChequeNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, -1, -1));

        txtCheque.setBackground(new java.awt.Color(255, 255, 255));
        txtCheque.setForeground(new java.awt.Color(0, 0, 0));
        mainpanel.add(txtCheque, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 318, -1));

        lblCardNumber.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblCardNumber.setForeground(new java.awt.Color(255, 255, 255));
        lblCardNumber.setText("Card number :");
        mainpanel.add(lblCardNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 124, -1, -1));

        txtCardNumber.setBackground(new java.awt.Color(255, 255, 255));
        txtCardNumber.setForeground(new java.awt.Color(0, 0, 0));
        mainpanel.add(txtCardNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 318, -1));

        panelChaild.setBackground(new java.awt.Color(0, 51, 153));
        panelChaild.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("And Semster :");
        panelChaild.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, -1, -1));

        txtTotal.setEditable(false);
        txtTotal.setBackground(new java.awt.Color(255, 255, 255));
        txtTotal.setForeground(new java.awt.Color(0, 0, 0));
        panelChaild.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 290, 170, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Student ID :");
        panelChaild.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, -1, -1));

        combCourse.setBackground(new java.awt.Color(255, 255, 255));
        combCourse.setForeground(new java.awt.Color(0, 0, 0));
        combCourse.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select" }));
        combCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combCourseActionPerformed(evt);
            }
        });
        panelChaild.add(combCourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 190, -1));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Course :");
        panelChaild.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Amounts");
        panelChaild.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 130, -1, -1));

        jSeparator1.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        panelChaild.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 840, -1));

        jSeparator2.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        panelChaild.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 840, 30));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Payment details :");
        panelChaild.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, -1, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Sr.No");
        panelChaild.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Heads");
        panelChaild.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, -1, -1));

        txtStudentname.setBackground(new java.awt.Color(255, 255, 255));
        txtStudentname.setForeground(new java.awt.Color(0, 0, 0));
        panelChaild.add(txtStudentname, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 318, -1));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Year :");
        panelChaild.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("TAX  5%:");
        panelChaild.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 220, -1, -1));

        jSeparator3.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));
        panelChaild.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 277, 220, 10));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Registation  Fee :");
        panelChaild.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, -1, -1));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Total :");
        panelChaild.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 290, -1, -1));

        txtcuorseName.setBackground(new java.awt.Color(255, 255, 255));
        txtcuorseName.setForeground(new java.awt.Color(0, 0, 0));
        panelChaild.add(txtcuorseName, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 300, -1));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Total in words :");
        panelChaild.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, -1, -1));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setForeground(new java.awt.Color(0, 0, 0));

        txtDetails.setBackground(new java.awt.Color(255, 255, 255));
        txtDetails.setColumns(20);
        txtDetails.setRows(5);
        jScrollPane1.setViewportView(txtDetails);

        panelChaild.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 450, 380, 90));

        btnPrint.setBackground(new java.awt.Color(255, 255, 255));
        btnPrint.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btnPrint.setForeground(new java.awt.Color(0, 0, 0));
        btnPrint.setText("Print");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });
        panelChaild.add(btnPrint, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 490, 100, 40));

        txtTotalWord.setEditable(false);
        txtTotalWord.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalWord.setForeground(new java.awt.Color(0, 0, 0));
        panelChaild.add(txtTotalWord, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 400, 400, -1));

        txtSemster.setBackground(new java.awt.Color(255, 255, 255));
        txtSemster.setForeground(new java.awt.Color(0, 0, 0));
        panelChaild.add(txtSemster, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 90, 130, -1));

        txtTax.setEditable(false);
        txtTax.setBackground(new java.awt.Color(255, 255, 255));
        txtTax.setForeground(new java.awt.Color(0, 0, 0));
        panelChaild.add(txtTax, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 210, 170, -1));

        txtRegisterfee.setEditable(false);
        txtRegisterfee.setBackground(new java.awt.Color(255, 255, 255));
        txtRegisterfee.setForeground(new java.awt.Color(0, 0, 0));
        panelChaild.add(txtRegisterfee, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 240, 170, -1));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Course name :");
        panelChaild.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));

        txtStudentId.setBackground(new java.awt.Color(255, 255, 255));
        txtStudentId.setForeground(new java.awt.Color(0, 0, 0));
        panelChaild.add(txtStudentId, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 20, 170, -1));

        txtYear.setBackground(new java.awt.Color(255, 255, 255));
        txtYear.setForeground(new java.awt.Color(0, 0, 0));
        panelChaild.add(txtYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 130, -1));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Student name:");
        panelChaild.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        txtAmount.setBackground(new java.awt.Color(255, 255, 255));
        txtAmount.setForeground(new java.awt.Color(0, 0, 0));
        txtAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAmountActionPerformed(evt);
            }
        });
        txtAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAmountKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAmountKeyReleased(evt);
            }
        });
        panelChaild.add(txtAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 180, 170, -1));

        lblpressenter.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblpressenter.setForeground(new java.awt.Color(255, 0, 0));
        lblpressenter.setText("Press enter after entering the amount");
        panelChaild.add(lblpressenter, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 260, -1, 20));

        mainpanel.add(panelChaild, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 850, 560));

        txtBillno.setBackground(new java.awt.Color(255, 255, 255));
        txtBillno.setForeground(new java.awt.Color(0, 0, 0));
        mainpanel.add(txtBillno, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 14, 122, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("Re Edit page ");
        mainpanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, 120, 40));

        getContentPane().add(mainpanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 860, 730));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void panelhomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelhomeMouseEntered
        panelhome.setBackground(clrin);
    }//GEN-LAST:event_panelhomeMouseEntered

    private void panelhomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelhomeMouseExited
        panelhome.setBackground(clrout);
    }//GEN-LAST:event_panelhomeMouseExited

    private void paneladdfeesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneladdfeesMouseEntered
        paneladdfees.setBackground(clrin);
    }//GEN-LAST:event_paneladdfeesMouseEntered

    private void paneladdfeesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneladdfeesMouseExited
paneladdfees.setBackground(clrout);        // TODO add your handling code here:
    }//GEN-LAST:event_paneladdfeesMouseExited

    private void panelserchrecordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelserchrecordMouseEntered
panelserchrecord.setBackground(clrin);        // TODO add your handling code here:
    }//GEN-LAST:event_panelserchrecordMouseEntered

    private void panelserchrecordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelserchrecordMouseExited
panelserchrecord.setBackground(clrout);        // TODO add your handling code here:
    }//GEN-LAST:event_panelserchrecordMouseExited

    private void panelhome4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelhome4MouseEntered
panelhome4.setBackground(clrin);        // TODO add your handling code here:
    }//GEN-LAST:event_panelhome4MouseEntered

    private void panelhome4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelhome4MouseExited
        panelhome4.setBackground(clrout);
    }//GEN-LAST:event_panelhome4MouseExited

    private void panelEditRecordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelEditRecordMouseEntered
panelEditRecord.setBackground(clrin);        // TODO add your handling code here:
    }//GEN-LAST:event_panelEditRecordMouseEntered

    private void panelEditRecordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelEditRecordMouseExited
panelEditRecord.setBackground(clrout);        // TODO add your handling code here:
    }//GEN-LAST:event_panelEditRecordMouseExited

    private void panelViewCourseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelViewCourseMouseEntered
panelViewCourse.setBackground(clrin);        // TODO add your handling code here:
    }//GEN-LAST:event_panelViewCourseMouseEntered

    private void panelViewCourseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelViewCourseMouseExited
panelViewCourse.setBackground(clrout);        // TODO add your handling code here:
    }//GEN-LAST:event_panelViewCourseMouseExited

    private void panelViweReportMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelViweReportMouseEntered
panelViweReport.setBackground(clrin);        // TODO add your handling code here:
    }//GEN-LAST:event_panelViweReportMouseEntered

    private void panelViweReportMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelViweReportMouseExited
panelViweReport.setBackground(clrout);        // TODO add your handling code here:
    }//GEN-LAST:event_panelViweReportMouseExited

    private void panelLogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelLogoutMouseEntered
panelLogout.setBackground(clrin);        // TODO add your handling code here:
    }//GEN-LAST:event_panelLogoutMouseEntered

    private void panelLogoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelLogoutMouseExited
panelLogout.setBackground(clrout);        // TODO add your handling code here:
    }//GEN-LAST:event_panelLogoutMouseExited

    private void panelLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelLogoutMouseClicked
        Loginpage1 l1 = new Loginpage1();
        l1.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_panelLogoutMouseClicked

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        if(validation()==true)
        {
             //JOptionPane.showMessageDialog(this, "validation pass");
            String status = updateData();
            if(status.equals("add"))
            {
                JOptionPane.showMessageDialog(this, "record update complete");
                PrintResiptPage print = new PrintResiptPage();
                print.setVisible(true);
                this.dispose();
            }
            else{
                JOptionPane.showMessageDialog(this, "record not updated");
            }
        }
    }//GEN-LAST:event_btnPrintActionPerformed

    private void combPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combPaymentActionPerformed
        //cheque payment
        if(combPayment.getSelectedIndex()==2)
        {
            lblChequeNumber.setVisible(true);
            txtCheque.setVisible(true);
            lblCardNumber.setVisible(false);
            txtCardNumber.setVisible(false);
            lblBankName.setVisible(true);
            txtBankName.setVisible(true);
        }
        //cash payment
        else if (combPayment.getSelectedIndex()==0)
        {
            selectCash();
        }
        //card payment
        else{
            lblCardNumber.setVisible(true);
            txtCardNumber.setVisible(true);
            lblChequeNumber.setVisible(false);
            txtCheque.setVisible(false);
            lblBankName.setVisible(true);
            txtBankName.setVisible(true);
        }
    }//GEN-LAST:event_combPaymentActionPerformed

    private void panelhomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelhomeMouseClicked
        Homepage home = new Homepage();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_panelhomeMouseClicked

    private void txtAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAmountActionPerformed
        Float amount = Float.parseFloat(txtAmount.getText());
        Float tax = (float)(amount*0.05);
        Float regi=(float)2000.0;
        
        float tot= amount+tax+regi;
        txtTax.setText(tax.toString());
        txtRegisterfee.setText(regi.toString());
        txtTotal.setText(Float.toString(tot));
        lblpressenter.setVisible(false);
        
        txtTotalWord.setText(NumberToWordsConverter.convert((int)tot));
    }//GEN-LAST:event_txtAmountActionPerformed

    private void txtAmountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAmountKeyPressed
        lblpressenter.setVisible(true);
    }//GEN-LAST:event_txtAmountKeyPressed

    private void txtAmountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAmountKeyReleased
       
    }//GEN-LAST:event_txtAmountKeyReleased

    private void combCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combCourseActionPerformed
        txtcuorseName.setText(combCourse.getSelectedItem().toString());
    }//GEN-LAST:event_combCourseActionPerformed

    private void panelEditRecordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelEditRecordMouseClicked
        EditCoursePage edit = new EditCoursePage();
        edit.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_panelEditRecordMouseClicked

    private void paneladdfeesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneladdfeesMouseClicked
        AddFeespage add = new AddFeespage();
        add.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_paneladdfeesMouseClicked

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
            java.util.logging.Logger.getLogger(EditBillPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditBillPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditBillPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditBillPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditBillPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPrint;
    private javax.swing.JComboBox<String> combCourse;
    private javax.swing.JComboBox<String> combPayment;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblBankName;
    private javax.swing.JLabel lblCardNumber;
    private javax.swing.JLabel lblChequeNumber;
    private javax.swing.JLabel lblpressenter;
    private javax.swing.JPanel mainpanel;
    private javax.swing.JPanel panelChaild;
    private javax.swing.JPanel panelEditRecord;
    private javax.swing.JPanel panelLogout;
    private javax.swing.JPanel panelViewCourse;
    private javax.swing.JLabel panelViewRecord;
    private javax.swing.JPanel panelViweReport;
    private javax.swing.JPanel paneladdfees;
    private javax.swing.JPanel panelhome;
    private javax.swing.JPanel panelhome4;
    private javax.swing.JPanel panelserchrecord;
    private javax.swing.JPanel sidepanel;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JTextField txtBankName;
    private javax.swing.JTextField txtBillno;
    private javax.swing.JTextField txtCardNumber;
    private javax.swing.JTextField txtCheque;
    private com.toedter.calendar.JDateChooser txtDate;
    private javax.swing.JTextArea txtDetails;
    private javax.swing.JTextField txtRegisterfee;
    private javax.swing.JTextField txtSemster;
    private javax.swing.JTextField txtStudentId;
    private javax.swing.JTextField txtStudentname;
    private javax.swing.JTextField txtTax;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtTotalWord;
    private javax.swing.JTextField txtYear;
    private javax.swing.JTextField txtcuorseName;
    // End of variables declaration//GEN-END:variables

    private String toStrin(Double tot) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
