import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class display1 {			//显示诊室、病人信息类
    Patient customers ;
    Vector<Room> rooms ;
    Patient c;
    void setCustomer(String phone) {		//从数据库导入病人信息

        try {	Class.forName("jdbc:com.mysql.jdbc.Driver") ;	}
        catch(Exception ee) {}
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","puhaibo123") ;
            Statement sql = con.createStatement() ;
            ResultSet rs = sql.executeQuery("SELECT * FROM bookingmessage where 电话号码='"+phone+"'") ;
            while(rs.next()) {
                String name = rs.getString(1) ;
                String roomNum = rs.getString(2) ;
                String phone1 = rs.getString(3) ;
                String checkInTime = rs.getString(4) ;
                String leaveTime = rs.getString(5) ;

                c = new Patient( name,roomNum,phone1,checkInTime,leaveTime) ;

            }
            con.close() ;
        }
        catch(Exception ee) {}

    }
    Patient get(){
        return c;
    }
    void setRoom() {			//从数据库导入诊室信息
        rooms = new Vector<Room>() ;
        try {	Class.forName("jdbc:com.mysql.jdbc.Driver") ;	}
        catch(Exception ee) {}
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","puhaibo123") ;
            Statement sql = con.createStatement() ;
            ResultSet rs = sql.executeQuery("SELECT * FROM roommessage ") ;
            while(rs.next()) {
                String type = rs.getString(1) ;
                int roomNum = rs.getInt(2) ;
                String state = rs.getString(3) ;
                Room r = new Room(type, roomNum, state) ;
                rooms.add(r) ;
            }
            con.close() ;
        }
        catch(Exception ee) {}
    }
    int getRoomLength() {				//获得房间数量
        return rooms.size() ;
    }

    Room getRoom(int i) {				//获得房间信息
        return rooms.get(i) ;
    }

}