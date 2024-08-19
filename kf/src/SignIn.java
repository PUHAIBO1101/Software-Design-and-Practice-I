import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SignIn {
    Connection con ;
    Statement sql ;
    ResultSet rs ;
    SignIn(String phone) {
        sign(phone) ;
    }
    void sign(String search) {		//更新预约状态
        try {	Class.forName("com.mysql.cj.jdbc.Driver") ;	}
        catch(Exception e) {}
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","puhaibo123") ;
            sql = con.createStatement() ;
            rs = sql.executeQuery("SELECT * FROM bookingmessage WHERE 电话号码='"+search+"'") ;//根据电话号码查找信息
            rs.next() ;				//跳到下一个才是筛选出的记录
            String roomNum = rs.getString(2) ;		//从数据里获得诊室号
            sql.executeUpdate("UPDATE roommessage SET 状态='预约' WHERE 诊室号='"+roomNum+"'") ;	//更新诊室状态
            JOptionPane.showMessageDialog(new ChangePanel(), "验证成功！", null, JOptionPane.INFORMATION_MESSAGE);
            con.close();
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(new ChangePanel(), "验证失败！", null, JOptionPane.INFORMATION_MESSAGE);
        }
    }
}