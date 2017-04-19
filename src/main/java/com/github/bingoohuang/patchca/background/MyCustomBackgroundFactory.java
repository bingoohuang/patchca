package com.github.bingoohuang.patchca.background;


import com.github.bingoohuang.patchca.Patchca1;
import com.github.bingoohuang.patchca.service.Captcha;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

// http://www.csdn123.com/html/mycsdn20140110/98/9879765b1488f8fa2c80610aebccc315.html
public class MyCustomBackgroundFactory implements BackgroundFactory {
    private Random random = new Random();

    @Override
    public void fillBackground(BufferedImage image) {
        Graphics graphics = image.getGraphics();

        // 验证码图片的宽高
        int imgWidth = image.getWidth();
        int imgHeight = image.getHeight();

        // 填充为白色背景
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, imgWidth, imgHeight);

        // 画100个噪点(颜色及位置随机)
        for (int i = 0; i < 100; i++) {
            // 随机颜色
            int rInt = random.nextInt(255);
            int gInt = random.nextInt(255);
            int bInt = random.nextInt(255);
            graphics.setColor(new Color(rInt, gInt, bInt));

            // 随机位置
            int xInt = random.nextInt(imgWidth - 3);
            int yInt = random.nextInt(imgHeight - 2);

            // 随机旋转角度
            int sAngleInt = random.nextInt(360);
            int eAngleInt = random.nextInt(360);

            // 随机大小
            int wInt = random.nextInt(6);
            int hInt = random.nextInt(6);

            graphics.fillArc(xInt, yInt, wInt, hInt, sAngleInt, eAngleInt);

            // 画5条干扰线
            if (i % 20 == 0) {
                int xInt2 = random.nextInt(imgWidth);
                int yInt2 = random.nextInt(imgHeight);
                graphics.drawLine(xInt, yInt, xInt2, yInt2);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // 得到验证码对象,有验证码图片和验证码字符串
        Captcha captcha = Patchca1.next();
        // 取得验证码字符串放入Session
        String validationCode = captcha.getChallenge();
        System.out.println(validationCode);

        // 取得验证码图片并输出
        BufferedImage bufferedImage = captcha.getImage();

        ImageIO.write(bufferedImage, "png",
                new FileOutputStream(new File("mycustom.png")));
    }


}
