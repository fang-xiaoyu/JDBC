import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *  JDBC事务机制：
 *      1. JDBC中的事务是自动提交的
 *          每执行一条DML语句，就会自动提交一次
 *          这与实际业务中，事务由多条DML语句完成相违背，这就必须保证这些DML语句必须同时成功 / 失败
 *      2. 先验证JDBC中是否为自动提交机制
 *      3. 三行代码解决问题
 *          conn.setAutoCommit(false);  // 关闭自动提交
 *          conn.commit();   // 手动提交
 *          conn.rollback;   // 手动回滚
 */
public class JDBCTest10 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode", "root", "123456");

            // 在获取链接后，将自动提交机制设置为手动提交机制
            conn.setAutoCommit(false);

            String sql1 = "insert into dept (deptno, dname, loc) values (?, ?, ?)";
            ps = conn.prepareStatement(sql1);

            Scanner s = new Scanner(System.in);
            System.out.print("请输入部门编号：");
            String deptno = s.nextLine();
            System.out.print("请输入部门名称：");
            String dname = s.nextLine();
            System.out.print("请输入部门地址：");
            String loc = s.nextLine();

            ps.setInt(1, Integer.parseInt(deptno));
            ps.setString(2, dname);
            ps.setString(3, loc);

            int count = ps.executeUpdate();
            System.out.print(count==1?"修改成功":"修改失败");

            ps.setString(1, null);
            ps.executeUpdate();

            // 程序能走到这里，说明以上程序都不存在异常，事务结束，手动提交
            conn.commit();
        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();                 // 回滚事务
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }



    }
}
