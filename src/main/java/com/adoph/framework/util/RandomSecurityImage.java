package com.adoph.framework.util;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;

/**
 * 验证码图片生成工具类
 *
 * @author Adoph
 * @version v1.0
 * @since 2017/9/18
 */
public class RandomSecurityImage {

    private static Random random = new Random();
    private static Font font = new Font("Fixedsys", Font.BOLD, 18);

    /**
     * 根据指定的字符和大小生成随机验证码图片
     *
     * @param code   需要绘制到图片上的字符数组
     * @param width  图片宽度
     * @param height 图片高度
     * @param line   干扰线数量
     * @return 图片的输入流
     */
    public static ByteArrayInputStream getImage(char[] code, int width, int height, int line) {
        // BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        g.setColor(getRandColor(110, 133));
        // 绘制干扰线
        for (int i = 0; i < line; i++) {
            drawLine(g, width, height);
        }
        // 绘制随机字符
        for (int i = 0; i < code.length; i++) {
            drawChar(g, width / code.length * i, random.nextInt(height / 3)
                    + height / 3, code[i]);
        }
        g.dispose();
        return convertImageToStream(image);
    }

    /**
     * 将BufferedImage转换成ByteArrayInputStream
     *
     * @param image 图片
     * @return ByteArrayInputStream 流
     */
    private static ByteArrayInputStream convertImageToStream(BufferedImage image) {
        ByteArrayInputStream inputStream = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "JPEG", bos);
            byte[] bts = bos.toByteArray();
            inputStream = new ByteArrayInputStream(bts);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    /*
     * 获得颜色
     */
    private static Color getRandColor(int fc, int bc) {
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc - 16);
        int g = fc + random.nextInt(bc - fc - 14);
        int b = fc + random.nextInt(bc - fc - 18);
        return new Color(r, g, b);
    }

    /*
     * 绘制字符
     */
    private static void drawChar(Graphics g, int x, int y, char code) {
        g.setFont(font);
        g.setColor(getRandColor(10, 200));
        g.translate(random.nextInt(3), random.nextInt(3));
        g.drawString(code + "", x, y);
    }

    /*
     * 绘制干扰线
     */
    private static void drawLine(Graphics g, int width, int height) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(width / 2);
        int yl = random.nextInt(height / 2);
        g.drawLine(x, y, x + xl, y + yl);
    }

    public static void main(String[] args) throws Exception {
        char[] charCode = RandomSecurityCode.getSecurityCode(4, RandomSecurityCode.SecurityCodeLevel.Hard, true);
        ByteArrayInputStream imageStream = RandomSecurityImage.getImage(charCode, 130, 34, 25);
        File file  = new File("D:\\study\\framework\\target\\classes\\code.jpeg");
        if(!file.exists()) {
            file.createNewFile();
        }
        OutputStream os = new FileOutputStream(file);
        IOUtils.copy(imageStream, os);
        os.write(imageStream.read());
        os.close();
        imageStream.close();
        System.out.println(charCode);
    }

}
