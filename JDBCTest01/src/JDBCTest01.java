import java.sql.*;

/**
    编程六步
    1. 注册驱动
    2. 获取链接
    3. 获取数据库操作对象
    4. 执行SQL语句
    5. 处理查询结果集
    6. 释放内存
 */
public class JDBCTest01 {
    public static void main(String[] args) {
        Statement stmt = null;
        Connection conn = null;
        try {
            // 1. 注册驱动
            Driver driver = new com.mysql.cj.jdbc.Driver(); // 多态，父类型引用指向子类型对象
            DriverManager.registerDriver(driver);

            // 2. 获取链接
            /**
             * url: 统一资源定位符（网络中某个资源的绝对路径）
             * https://www.baidu.com/
             *   URL包括哪几部分？
             *      通信协议： 通信前就定好的数据传送格式，数据包具体怎么传数据
             *      IP
             *      端口 PORT
             *      资源名
             *   http://182.61.200.7:80/index.html
             *      http://   通信协议
             *      182.61.200.7 服务器IP地址
             *      :80  服务器上软件的端口
             *      index.html  服务器上某个资源名
             *
             *   jdbc:mysql://localhost:3306/test_db
             *      jdbc:mysql   通信协议
             *      localhost 数据库IP地址（本机IP）
             *      :3306  mysql数据库的端口
             *      test_db  具体数据库实例名
             *
             */
            String url = "jdbc:mysql://localhost:3306/BJPOWERNODE";
            String user = "root";
            String password = "123456";
            conn = DriverManager.getConnection(url, user, password);
            // 数据库连接对象 = com.mysql.cj.jdbc.ConnectionImpl@1dd92fe2
            //System.out.println("数据库连接对象 = " + conn);

            // 3. 获取数据库操作对象(Statement专门执行sql语句)
            stmt = conn.createStatement();

            // 4. 执行SQL
            // JDBC中sql语句不用写分号
            String sql = "insert into dept(deptno, dname, loc) values(50, '人事部', '北京')";
            // 专门执行DML语句（insert、delete、update）
            // 返回值是“影响数据库中的记录条数”
            int count = stmt.executeUpdate(sql); // 更新几条返回几
            System.out.println(count == 1 ? "保存成功":"保存失败");   // 以为sql是一条记录，所以count返回应该是1

            // 5. 处理查询结果集

        } catch (SQLException E) {
            E.printStackTrace();
        } finally {
            // 6. 释放资源
            // 为了保证资源一定释放，在finally语句中关闭资源
            // 并且要遵循从小到大（从后开到先开）依次关闭：即先关闭Statement，再关闭Connection
            // 分别对其try...catch...
            // 不能一起try，因为假如stmt出异常，conn就不执行了
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }
}
