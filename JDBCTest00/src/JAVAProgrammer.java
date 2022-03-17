import java.util.ResourceBundle;

public class JAVAProgrammer {
    public static void main(String[] args) throws Exception{
        ResourceBundle rb = ResourceBundle.getBundle("jdbc");  // 读取properties文件
        String className = rb.getString("Stringname");
        Class c = Class.forName(className);               // 反射机制
        JDBC jdbc = (JDBC) c.newInstance();

        jdbc.connected();
    }
}
