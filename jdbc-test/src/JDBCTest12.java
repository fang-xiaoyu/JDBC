import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * 开启一个事务，进行查询，使用行级锁/悲观锁，
 */
public class JDBCTest12 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // 1 + 2. 注册驱动 + 获取链接
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            // 3. 获取数据库操作对象
            String sql = "select ename, job from emp where job = ? for update";
            ps = conn.prepareStatement(sql);
            // 4. 执行SQL语句
            Scanner s = new Scanner(System.in);
            ps.setString(1, "manager");
            // 5. 处理查询结果集
            rs = ps.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString("ename") +"  " + rs.getString("job"));
            }
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            // 6. 释放资源
            DBUtil.close(conn, ps, rs);
        }

    }
}
