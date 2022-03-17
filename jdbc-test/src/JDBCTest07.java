import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * 1. 解决SQL注入问题
 *      只要用户提供的信息不参与SQL语句的编译过程，问题就解决了，使用java.sql.PreparedStatement
 *      PreparedStatement接口继承了 java.sql.Statement，是一种预编译的数据库操作对象
 *      原理：预先对SQL语句进行编译，然后再给SQL语句传值
 *          因此就要先写SQL语句，再建立PreparedStatement，其中一个 ? 代表一个占位符，占位符不要用 ''括起来
 * 2. 解决SQL注入的关键是使用户输入的信息不参与SQL语句编译
 * 3. 对比Statement 和 PreparedStatement
 *      --Statement存在SQL注入问题，PS解决了
 *      --PS更高效，因为当前后两个SQL语句完全一致时，系统会跳过编译直接采用之前的结果
 *          因此PS每次执行的SQL语句都是相同的，而Statement每次用户名和密码都不同，每次都要编译
 *      --PS会在编译阶段做类型的安全检查
 * 4. 什么情况下必须使用Statement？
 *      业务需要支持SQL注入，如需要输入的内容不需要单引号括起来，比如指定升序降序
 */
public class JDBCTest07 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ResourceBundle rb = ResourceBundle.getBundle("bjpowernode");
        String className = rb.getString("className");
        String url = rb.getString("url");
        String user = rb.getString("user");
        String password = rb.getString("password");

        Class.forName(className);
        Connection conn = DriverManager.getConnection(url, user, password);
        // ? : 占位符
        String sql = "select loginName as ln, loginPwd as lp from t_user where loginName = ? and loginPwd = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        // 初始化一个界面
        Map<String, String> userLoginInfo = initUI();
        // 验证用户名和密码
        boolean loginSuccess = validateLogin(userLoginInfo, ps);
        System.out.println(loginSuccess == true ? "登录成功" : "登录失败");

        ps.close();
        conn.close();
    }

    private static boolean validateLogin(Map<String, String> userLoginInfo, PreparedStatement ps) throws SQLException {
        String loginName = userLoginInfo.get("loginName");
        String loginPwd = userLoginInfo.get("loginPwd");
        // 给占位符 ? 传值：第1个 ? 下标是1，第2个 ? 下标是2....在JDBC中，所有下标从1开始
        ps.setString(1, loginName);
        ps.setString(2, loginPwd);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            rs.close();
            return true;
        }
        rs.close();
        return false;
    }


    private static Map<String, String> initUI() {
        Scanner s = new Scanner(System.in);              // 获取用户输入
        System.out.print("输入登录名：");
        String loginName = s.nextLine();
        System.out.print("输入密码：");
        String loginPwd = s.nextLine();
        Map<String, String> map = new HashMap<>();
        map.put("loginName", loginName);
        map.put("loginPwd", loginPwd );
        return map;
    }
}
