package com.jSch.connectTest;

import com.jSch.util.ConnUtil;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;


public class JSchConnect {
    private static final Logger logger = LoggerFactory.getLogger(JSchConnect.class);

    public static void main(String[] args) {

        String username = "root";
        String password = "622848";
        String host = "192.168.10.103";
        int port = 22;

        // 创建JSch对象
        JSch jSch = new JSch();
        Session jSchSession = null;

        boolean reulst = false;

        try {

            // 根据主机账号、ip、端口获取一个Session对象
            jSchSession = jSch.getSession(username, host, port);

            // 存放主机密码
            jSchSession.setPassword(password);

            Properties config = new Properties();

            // 去掉首次连接确认
            config.put("StrictHostKeyChecking", "no");

            jSchSession.setConfig(config);

            // 超时连接时间为3秒
            jSchSession.setTimeout(3000);

            // 进行连接
            jSchSession.connect();

            // 获取连接结果
            reulst = jSchSession.isConnected();
            System.out.println("连接是否成功--->"+reulst);
            int port1 = jSchSession.getPort();
            System.out.println(port1);
            jSchSession.setPortForwardingL(3308,"192.168.10.103",3306);

            System.out.println("第一次连接查询****************************");
            long before = System.currentTimeMillis();
            Connection conn = ConnUtil.getConn();
            String sql="select * from countries where region_id=2";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            long after = System.currentTimeMillis();
            long usedTime = after - before;
            System.out.println(usedTime/1000);
            ArrayList<String> strings = new ArrayList<>();
            while (resultSet.next()){
                String column = resultSet.getString(1);
                String string = resultSet.getString(2);
                strings.add(column);
                strings.add(string);
            }
            System.out.println(strings);

            System.out.println("第二次连接查询********************************");
            before = System.currentTimeMillis();
            String sql2="select * from countries where region_id=3";
            PreparedStatement preparedStatement2 = conn.prepareStatement(sql2);
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            after = System.currentTimeMillis();
            usedTime = after - before;
            System.out.println(usedTime/1000);
            ArrayList<String> strings2 = new ArrayList<>();
            while (resultSet2.next()){
                String column = resultSet2.getString(1);
                String string = resultSet2.getString(2);
                strings2.add(column);
                strings2.add(string);
            }
            System.out.println(strings2);


        } catch (JSchException | SQLException e) {
            logger.warn(e.getMessage());
        } finally {
            // 关闭jschSesson流
            if (jSchSession != null && jSchSession.isConnected()) {
                jSchSession.disconnect();
            }
        }



    }
}
