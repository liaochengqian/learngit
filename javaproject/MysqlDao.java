public class MysqlDao{
    private static final DRIVER = "com.mysql.jdbc.Driver"
    private static final URL = "jdbc:mysql://localhost:3306/ltd0815";
    private static final USER = "root";
    private static final PASSWD = "";


    public static Connection getConnect(){
        Class.forName( Driver );

    }



}