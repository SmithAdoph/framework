package com.adoph.framework.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 高斯模糊
 *
 * @author Adoph
 * @version v1.0
 * @date 2017/9/19
 */
public class GaussianBlurUtils {

    private static void readPixel(BufferedImage img, int x, int y, int[] pixels) {
        int xStart = x - 1;
        int yStart = y - 1;
        int current = 0;
        for (int i = xStart; i < 3 + xStart; i++) {
            for (int j = yStart; j < 3 + yStart; j++) {
                int tx = i;
                if (tx < 0) {
                    tx = -tx;
                } else if (tx >= img.getWidth()) {
                    tx = x;
                }
                int ty = j;
                if (ty < 0) {
                    ty = -ty;
                } else if (ty >= img.getHeight()) {
                    ty = y;
                }
                pixels[current++] = img.getRGB(tx, ty);
            }
        }
    }

    private static void fillMatrix(int[][] matrix, int[] values) {
        int filled = 0;
        for (int i = 0; i < matrix.length; i++) {
            int[] x = matrix[i];
            for (int j = 0; j < x.length; j++) {
                x[j] = values[filled++];
            }
        }
    }

    private static int avgMatrix(int[][] matrix) {
        int r = 0;
        int g = 0;
        int b = 0;
        for (int i = 0; i < matrix.length; i++) {
            int[] x = matrix[i];
            for (int j = 0; j < x.length; j++) {
                if (j == 1) {
                    continue;
                }
                Color c = new Color(x[j]);
                r += c.getRed();
                g += c.getGreen();
                b += c.getBlue();
            }
        }
        return new Color(r / 8, g / 8, b / 8).getRGB();
    }

    /**
     * 高斯模糊
     *
     * @param img 图片源
     * @return BufferedImage
     */
    public static void gaussianBlur(BufferedImage img) {
        int height = img.getHeight();
        int width = img.getWidth();
        int[][] matrix = new int[3][3];
        int[] values = new int[9];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                readPixel(img, i, j, values);
                fillMatrix(matrix, values);
                img.setRGB(i, j, avgMatrix(matrix));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedImage img = ImageIO.read(new File("D:\\study\\framework\\target\\classes\\code.jpeg")); //将这个图片拷贝到你项目根目录下
        gaussianBlur(img);
        ImageIO.write(img, "jpeg", new File("D:\\study\\framework\\target\\classes\\code1.jpeg"));
    }

}
