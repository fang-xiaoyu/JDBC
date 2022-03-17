import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/** 将连接数据库的所有文件写入配置文件中 */
public class JDBCTest04 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException{
        // 使用资源绑定器绑定配置文件
        ResourceBundle rb = ResourceBundle.getBundle("jdbc");
        String driver = rb.getString("driver");
        String url = rb.getString("url");
        String user = rb.getString("user");
        String password = rb.getString("password");

        Connection conn = null;
        Statement stmt = null;
        // 1. 注册驱动
        Class.forName(driver);
        // 2. 获取链接
        conn = DriverManager.getConnection(url, user, password);
        // 3. 获取数据库操作对象
        stmt = conn.createStatement();
        // 4. 执行sql语句
        //String sql = "delete from t_student";
        String sql = "update t_user set username = 'bossy' where id = 1";
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
