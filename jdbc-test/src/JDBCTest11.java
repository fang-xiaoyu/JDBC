import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * 两个任务
 *      一：测试DBUtil是否好用
 *      二：模糊查询怎么写？
 */
public class JDBCTest11 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // 1 + 2. 注册驱动 + 获取链接
            conn = DBUtil.getConnection();
            // 3. 获取数据库操作对象
            String sql = "select ename, job from emp where ename like ?";
            ps = conn.prepareStatement(sql);
            // 4. 执行SQL语句
            Scanner s = new Scanner(System.in);
            String ename = s.nextLine();
            ps.setString(1, ename);
            // 5. 处理查询结果集
            rs = ps.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString("ename") +"  " + rs.getString("job"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 6. 释放资源
            DBUtil.close(conn, ps, rs);
        }

    }
}
