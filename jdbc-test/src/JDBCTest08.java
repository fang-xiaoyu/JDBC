import java.sql.*;
import java.util.Scanner;

/**
 * 演示必须要用Statement的时候
 */
public class JDBCTest08 {
    /**
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode", "root", "123456");

        String sql = "select ename from emp order by ename ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        Scanner s = new Scanner(System.in);
        System.out.print("请输入asc或者desc");
        String n = s.nextLine();

        ps.setString(1, n);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            System.out.println(rs.getString("ename"));
        }
        rs.close();
        ps.close();
        conn.close();
    }*/
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode", "root", "123456");
        Scanner s = new Scanner(System.in);
        System.out.print("请输入asc或者desc");
        String n = s.nextLine();
        Statement stmt = conn.createStatement();
        String sql = "select ename from emp order by ename " + n;


        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            System.out.println(rs.getString("ename"));
        }
        rs.close();
        stmt.close();
        conn.close();
    }
}
