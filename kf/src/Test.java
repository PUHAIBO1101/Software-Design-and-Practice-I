

import com.mysql.cj.x.protobuf.MysqlxCrud;

import javax.swing.*;
import java.sql.*;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 1、注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 2、获取数据库连接对象

        // 3、定义sql
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","puhaibo123") ;
        Statement sql = con.createStatement() ;
        ResultSet rs = sql.executeQuery("SELECT * FROM roommessage WHERE 诊室号="+01);//首先从房间信息表中找符合条件的房间
        			//记录是否预定成功
        while(rs.next()) {
            String type = rs.getString(1) ;
            int number = rs.getInt(2) ;
            String state = rs.getString(3) ;//"UPDATE roomInf SET state='预定' WHERE roomNum="+number
            if(state.equals("空闲")) {				//想预定的房间类型有空房就预定
                sql.executeLargeUpdate("UPDATE roommessage SET 状态='预约' WHERE 诊室号="+01) ;		//更新房间状态
                sql.executeUpdate("INSERT INTO bookingmessage VALUES ('周','儿童',01,'114154','10','11')") ;				//添加预定信息到数据库中


                break ;
            }



    }}}

