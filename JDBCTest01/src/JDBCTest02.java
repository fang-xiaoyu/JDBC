import java.sql.*;

public class JDBCTest02 {
    /** JDBC完成delete */
    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        // 1. 注册驱动
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        // 2. 获取链接
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BJPOWERNODE", "root", "123456");
        // 3. 获取数据库操作对象
        stmt = conn.createStatement();
        // 4. 执行sql语句
        //String sql = "delete from t_student";
        String sql = "update t_user set username = 'boss' where id = 1";
        int count = stmt.executeUpdate(sql);
        System.out.println( count == 1 ? "删除成功" : "删除失败");

        // 5. 处理查询结果集

        // 6. 释放内存
        if (stmt != null) {
            stmt.close();
        }
        if (conn != null) {
            conn.close();
        }



    }
}
