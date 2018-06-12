package com.adoph.framework.test;

import com.adoph.framework.test.vo.UserVo;
import com.adoph.framework.util.SysUtils;
import org.junit.Test;

import java.io.*;
import java.net.URL;

/**
 * 序列化测试
 *
 * @author Adoph
 * @version v1.0
 * @since 2018/6/12
 */
public class SerializeTest {

    @Test
    public void testWrite() {
        UserVo u = new UserVo();
        u.setId(1L);
        u.setSex("男");
        u.setUserName("神马科技");
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(
                    new FileOutputStream(UserVo.class.getResource("").getPath() + "/test"));
            oos.writeObject(u);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testRead() {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(UserVo.class.getResource("").getPath() + "/test"));
            try {
                UserVo o = (UserVo) ois.readObject();
                SysUtils.print(o);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testFilePath() {
        //方式一
        System.out.println("1:" + System.getProperty("user.dir"));
        //方式二
        File directory = new File("");//设定为当前文件夹
        try {
            System.out.println("2:" + directory.getCanonicalPath());//获取标准的路径
            System.out.println("3:" + directory.getAbsolutePath());//获取绝对路径
        } catch (Exception e) {
            e.printStackTrace();
        }
        //方式三
        System.out.println("4:" + SerializeTest.class.getResource("/"));
        System.out.println("5:" + SerializeTest.class.getResource(""));
        //方式4
        System.out.println("6:" + SerializeTest.class.getClassLoader().getResource(""));
        System.out.println("7:" + SerializeTest.class.getClassLoader().getResource("Vehicle.java"));
    }
}
