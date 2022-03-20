import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * 负责修改被锁住的记录
 */
public class JDBCTest13 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // 1 + 2. 注册驱动 + 获取链接
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            // 3. 获取数据库操作对象
            String sql = "update emp set ename = 'ASD' where job = ?";
            ps = conn.prepareStatement(sql);
            // 4. 执行SQL语句
            ps.setString(1, "manager");
            int count = ps.executeUpdate();
            System.out.print(count != 0?"更新成功":"更新失败");
            // 5. 处理查询结果集
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
            DBUtil.close(conn, ps, null);
        }

    }
}
