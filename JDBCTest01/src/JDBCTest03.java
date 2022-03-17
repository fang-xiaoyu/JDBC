import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest03 {
    /** 注册驱动的另一种方式 */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement stmt = null;
        // 1. 注册驱动
        /** 第一种写法 */
        //DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        /** 第二种写法 更常用，因为双引号内的字符串可以写到配置文件中 */
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 2. 获取链接
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BJPOWERNODE", "root", "123456");
        System.out.println("数据库连接对象 = " + conn);
    }
}
