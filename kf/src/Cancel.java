import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Cancel {
    Connection con ;
    Statement sql ;
    ResultSet rs ;
    Cancel(String phone) {
        del(phone) ;
    }
    void del(String search) {		//取消预约
        try {	Class.forName("com.mysql.cj.jdbc.Driver") ;	}
        catch(Exception e) {}
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","puhaibo123") ;
            sql = con.createStatement() ;
            rs = sql.executeQuery("SELECT * FROM bookingmessage WHERE 电话号码='"+search+"'") ;//根据电话号码查找订单
            rs.next() ;							//跳到下一个才是筛选出的订单
            int flag = JOptionPane.showConfirmDialog(new ChangePanel(), "确认删除该预约？", null, JOptionPane.YES_NO_CANCEL_OPTION) ;
            if(flag == JOptionPane.YES_OPTION) {
                String roomNum = rs.getString(2) ;		//从订单里获得房间号
                sql.executeUpdate("DELETE FROM bookingmessage WHERE 电话号码='"+search+"'") ;
                sql.executeUpdate("UPDATE roommessage SET 状态='空闲' WHERE 诊室号='"+roomNum+"'") ;	//更新房间状态
                JOptionPane.showMessageDialog(new ChangePanel(), "删除成功！", null, JOptionPane.INFORMATION_MESSAGE);
            }
            con.close() ;
        }
        catch(Exception e) {
            System.out.print(e) ;
            JOptionPane.showMessageDialog(new ChangePanel(), "删除失败！", null, JOptionPane.INFORMATION_MESSAGE);
        }
    }
}