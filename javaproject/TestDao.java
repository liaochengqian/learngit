package com.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDao {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost/ltd0815";
        String user = "root";
        String passwd = "";
        String table = "staff";

        MysqlDao dao = new MysqlDao(url,user,passwd);
        Connection conn = dao.getConnect();

        //必须将sql语句打印出来，极大的有助于发现错误
        String sql1 = "insert into " + table + " values(?,?,?,?,?,?)";
        System.out.println( "---sql:=" + sql1 );

        dao.change( sql1 , 2016080360,"张88","男","1989/12/11",104.01,"10002" );

        Statement s = conn.createStatement();
        String sql = "select * from staff";
        ResultSet res = s.executeQuery( sql );
        System.out.println("\tno\tname\t\tdept");
        while (res.next()) {
            int no = res.getInt("sno");
            String name = res.getString("sname");
            String dep = res.getString("sdept");
            System.out.printf("%d\t%s\t\t%s\n", no, name, dep);
        }
        dao.getClosed();
    }
}