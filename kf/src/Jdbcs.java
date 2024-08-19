import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javax.swing.JOptionPane;

public class Jdbcs {
    Connection con = null;
    Statement statement = null;
    ResultSet res = null;
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/project";
    String name = "root";
    String passwd = "puhaibo123";

    public Jdbcs() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, name, passwd);
            statement = con.createStatement();

        } catch (ClassNotFoundException e) {
            System.out.println("对不起，找不到这个Driver");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //对用户信息的修改实际上就是对密码的修改
    public boolean update(String username1, String password1, String newpassword) {
        boolean judge = false;
        boolean s = compare(username1, password1);
        if (s) {
            String sql = "update user set password='"+newpassword+"'where username='"+username1+"'";
            try {
                int a = statement.executeUpdate(sql);
                if (a == 1) {
                    JOptionPane.showMessageDialog(null, "密码修改成功！");
                    judge = true;
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "用户不存在！");
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "修改失败");
        }
        return judge;
    }

    //删除用户信息
    public void delete(String username, String password) {
        if (compare(username, password)) {
            JOptionPane.showMessageDialog(null, "已经完成删除");
        }
        String sql = "delete from user where username='"+username+"'";
        try {
            int a = statement.executeUpdate(sql);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "不存在该用户！");
            e.printStackTrace();
        }


    }

    //用户注册功能的实现，添加数据
    public void insert(String username, String password) {
        if (username == null || username.trim().length() <= 0) {
            JOptionPane.showMessageDialog(null, "注册账号为空，请重新输入！");
        }
        String sql = "insert user(username,password)values('"+username+"','"+password+"')";
        try {
            int a = statement.executeUpdate(sql);
            con.close();
            statement.close();
            if (a == 1) {
                JOptionPane.showMessageDialog(null, "注册成功！");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "对不起该用户名已经有了！");
            e.printStackTrace();
        }
    }

    //对比用户名和密码是不匹配
    public boolean compare(String username, String password) {
        boolean m = false;
        String sql = "select password from user where username='"+username+"'";
        try {
            res = statement.executeQuery(sql);
            if (res.next()) {
                String pa = res.getString(1  );
                System.out.println(pa + " " + password);
                if (pa.equals(password)) {
                    m = true;
                } else {
                    JOptionPane.showMessageDialog(null, "密码错误！");
                }
            } else {
                JOptionPane.showMessageDialog(null, "用户名不存在！");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return m;
    }
    public boolean compare1(String username, String password) {
        boolean m = false;
        String sql = "select password from user where username='管理'";
        try {
            res = statement.executeQuery(sql);
            if (res.next()) {
                String pa = res.getString(1  );
                System.out.println(pa + " " + password);
                if (pa.equals(password)) {
                    m = true;
                }

            } else {
                JOptionPane.showMessageDialog(null, "管理员不存在！");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return m;
    }

}
 