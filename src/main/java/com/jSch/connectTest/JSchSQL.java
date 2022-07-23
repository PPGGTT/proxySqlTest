package com.jSch.connectTest;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import sun.security.tools.keytool.Resources;

import java.io.IOException;
import java.util.List;

public class JSchSQL {
//    public static void main(String[] args) throws JSchException {
//    JSch jsch = new JSch();
//    //这里xxx.xxx.xxx.xxx为已加白名单的远程服务器
//    Session sess = jsch.getSession("root", "192.168.10.101", 22);
//        sess.setPassword("622848");
//        sess.setConfig("StrictHostKeyChecking","no");
//        sess.connect();
//    //这里xxx.xxx.xxx.xxx为数据库连接地址
//    //通过这个set方法可以将远程的3306端口指定为本地的3308端口，因为笔者本地3306已使用，故指定为3308端口
//        sess.setPortForwardingL(3308,"192.168.10.101",3306);
//   try{
//        //读取配置文件，需要注意的是，这里的配置文件中数据库地址为localhost，端口为刚刚指定的3308
//       reader = Resources.getResourceAsReader("conf.xml");
//    } catch(IOException e){
//        e.printStackTrace();
//    }
//
//    sqlSessionFactory =new SqlSessionFactoryBuilder().build(reader);
//
//    SqlSession session = sqlSessionFactory.openSession();
//    String statement = "com.saishangmingzhu.getData";//映射sql的标识字符串
//    List ll = session.selectList(statement);
//        System.out.println(ll);
//        session.commit();
//        session.close();
//        sess.disconnect();
//    }
}
