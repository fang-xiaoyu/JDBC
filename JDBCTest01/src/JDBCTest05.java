import java.sql.*;
import java.util.ResourceBundle;

public class JDBCTest05 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException{
        // 使用资源绑定器绑定配置文件
        ResourceBundle rb = ResourceBundle.getBundle("jdbc");
        String driver = rb.getString("driver");
        String url = rb.getString("url");
        String user = rb.getString("user");
        String password = rb.getString("password");

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        // 1. 注册驱动
        Class.forName(driver);
        // 2. 获取链接
        conn = DriverManager.getConnection(url, user, password);
        // 3. 获取数据库操作对象
        stmt = conn.createStatement();
        // 4. 执行sql语句
        //String sql = "delete from t_student";
        String sql = "select * from emp";
        rs = stmt.executeQuery(sql);             // 专门执行DQL语句的方法
        // 5. 处理查询结果集
        // System.out.println(flag1);
        while (rs.next()) {                                // rs.next()返回一个boolean类型的值，返回下一行（记录）有没有值
            int empno = rs.getInt(1);       // rs.getString() 不管数据库中数据类型是什么，都以String形式输出
            String ename = rs.getString(2);       // JDBC中的下标是从1开始，不是从0开始，是查询结果的下标，不是原表emp的下标
            Double sal = rs.getDouble("sal");         // rs.getString() 里也可以直接跟字段名
            System.out.println(empno + ", " + ename + ", " + sal);
        }
        // 6. 释放内存
        if (rs != null) {
            rs.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
}
