import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

/**
 * PreparedStatement 完成增删改
 */
public class JDBCTest09 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode", "root", "123456");
            //String sql = "insert into dept (deptno, dname, loc) values (?,?,?)";
            //String sql = "update dept set dname = ?, loc = ? where deptno = ?";  // update语句中修改两个字段不能用and连接
            String sql = "delete from dept where deptno = ?";
            ps = conn.prepareStatement(sql);
            Scanner s = new Scanner(System.in);
            System.out.print("请输入部门编号：");
            String deptno = s.nextLine();
            /**
            System.out.print("请输入部门名称：");
            String dname = s.nextLine();
            System.out.print("请输入部门地址：");
            String loc = s.nextLine();

            ps.setString(1, dname);
            ps.setString(2, loc);*/
            ps.setInt(1, Integer.parseInt(deptno));


            int count = ps.executeUpdate();
            System.out.print(count==1?"添加成功":"添加失败");

        } catch (Exception e) {
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
