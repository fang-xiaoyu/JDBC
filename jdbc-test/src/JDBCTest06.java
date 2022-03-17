import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;

/** 实现功能
 *  1. 需求：模拟用户登陆功能的实现
 *  2. 业务描述：程序运行的时候，可以提供一个输入的入口，可以让用户输入用户名和密码
 *      用户输入用户名和密码之后，提交信息，java程序收集到用户信息
 *      java程序连接数据库验证用户名和密码是否合法
 *      合法：显示登陆成功
 *      不合法：显示登陆失败
 *  3. 数据的准备：
 *      在实际开发中，表的设计会使用专业的建模工具: PowerDesign
 *  4. 当前程序存在问题：
 *      输入登录名：fdsa
 *      输入密码：fdsa' or '1'='1
 *      登录成功
 *     这种情况被称为SQL注入。（安全隐患）（黑客常用）
 *     原因：用户输入了含有SQL语句的关键字，并且参与了SQL语句的编译，改变了sql语句的含义
 */

public class JDBCTest06 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ResourceBundle rb = ResourceBundle.getBundle("bjpowernode");
        String className = rb.getString("className");
        String url = rb.getString("url");
        String user = rb.getString("user");
        String password = rb.getString("password");

        Class.forName(className);
        Connection conn = DriverManager.getConnection(url, user, password);
        Statement stmt = conn.createStatement();

        // 初始化一个界面
        Map<String, String> userLoginInfo = initUI();
        // 验证用户名和密码
        boolean loginSuccess = validateLogin(userLoginInfo, stmt);
        System.out.println(loginSuccess == true ? "登录成功" : "登录失败");

        stmt.close();
        conn.close();
    }

    private static boolean validateLogin(Map<String, String> userLoginInfo, Statement stmt) throws SQLException {
        String loginName = userLoginInfo.get("loginName");
        String loginPwd = userLoginInfo.get("loginPwd");
        String sql = "select loginName as ln, loginPwd as lp from t_user where loginName = '"+userLoginInfo.get("loginName")+"' and loginPwd = '"+userLoginInfo.get("loginPwd")+"'";
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()){
            rs.close();
            return true;
        }
        rs.close();
        return false;
    }


    private static Map<String, String> initUI() {
        Scanner s = new Scanner(System.in);              // 获取用户输入
        System.out.println("输入登录名：");
        String loginName = s.nextLine();
        System.out.println("输入密码：");
        String loginPwd = s.nextLine();
        Map<String, String> map = new HashMap<>();
        map.put("loginName", loginName);
        map.put("loginPwd", loginPwd );
        return map;
    }
}
