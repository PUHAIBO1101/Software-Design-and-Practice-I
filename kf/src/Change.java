import javax.swing.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Change {
    Connection con ;
    Statement sql ;
    ResultSet rs ,rs1;
    Change(String phone, int number) {
        transfer(phone, number) ;
    }
    void transfer(String search, int newNum) {		//更新状态
        try {	Class.forName("com.mysql.cj.jdbc.Driver") ;	}
        catch(Exception e) {}
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","puhaibo123") ;
            sql = con.createStatement() ;
            rs = sql.executeQuery("SELECT * FROM bookingmessage WHERE 电话号码='"+search+"'") ;//根据电话号码查找订单
            System.out.println(1);
            rs.next() ;				//跳到下一个才是筛选出的订单
            int befortNum = rs.getInt(2) ;		//原来诊室号
            rs = sql.executeQuery("SELECT * FROM roommessage WHERE 诊室号="+newNum+"") ;
            System.out.println(2);
            rs.next() ;
            System.out.println(2);
            String state = rs.getString(3) ;
            System.out.println(3);
            if( state.equals("空闲") ) {
                System.out.println(3);
                sql.executeUpdate("UPDATE bookingmessage SET 诊室号="+newNum+" WHERE 诊室号='"+befortNum+"'") ;
                System.out.println(4);
                sql.executeUpdate("UPDATE roommessage SET 状态='空闲' WHERE 诊室号="+befortNum+"") ;	//更新原来诊室状态
                System.out.println(5);
                sql.executeUpdate("UPDATE roommessage SET 状态='预定' WHERE 诊室号="+newNum+"") ;	//更新新诊室状态
                System.out.println(6);
                JOptionPane.showMessageDialog(new ChangePanel(), "更换成功！", null, JOptionPane.INFORMATION_MESSAGE);
            }
            else
                JOptionPane.showMessageDialog(new ChangePanel(), "更换失败！", null, JOptionPane.INFORMATION_MESSAGE);
            con.close() ;
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(new ChangePanel(), "更换失败！", null, JOptionPane.INFORMATION_MESSAGE);
        }
    }
}