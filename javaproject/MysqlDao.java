package com.jdbc;

import java.sql.*;

public class MysqlDao {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private Connection connection;
    private String url = null;
    private String user = null;
    private String passwd = null;

    public MysqlDao(String url, String user, String passwd) {
        this.url = url;
        this.user = user;
        this.passwd = passwd;
    }

    public Connection getConnect() {
        if (connection == null) {
            try {
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(url, user, passwd);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public void getClosed() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //preparedstatment sentence
    public boolean change(String sql , Object ... parameters ) throws SQLException{
        PreparedStatement pre = connection.prepareStatement(sql);
        for( int i =0 ; i< parameters.length ; i++){
            System.out.println( parameters[i] );
            pre.setObject( i+1 , parameters[i] );
        }
        return pre.executeUpdate() > 0 ? true : false;
    }
}