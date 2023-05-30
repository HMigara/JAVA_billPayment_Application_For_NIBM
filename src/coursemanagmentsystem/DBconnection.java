/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coursemanagmentsystem;

import com.sun.jdi.connect.spi.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Haritha
 */
public  class DBconnection {

    public static Connection getCON()
    {
        Connection con = null;
        try {
             Class.forName("com.mysql.cj.jdbc.Driver");
                 con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/feesmanagmentdb","nibm","root");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
