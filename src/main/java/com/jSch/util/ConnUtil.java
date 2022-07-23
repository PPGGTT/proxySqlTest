package com.jSch.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class ConnUtil {
    static ArrayList<Connection> connections = new ArrayList<>();
    static Properties properties = new Properties();
    static int i = 0;
    static {
        InputStream inputStream = ConnUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");

        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Connection createConn(){
        try {
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            Connection connection = dataSource.getConnection();
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;

    }

    public static Connection getConn(){
        Connection connection = createConn();
        connections.add(connection);
        return  connection;
    }

    public static void closeConn() throws SQLException {
        for (Connection connection:connections){
            connection.close();
        }
        connections = new ArrayList<>();
    }

}
