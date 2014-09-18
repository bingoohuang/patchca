package com.github.bingoohuang.patchca;

import com.github.bingoohuang.patchca.color.ColorFactory;
import com.github.bingoohuang.patchca.custom.ChineseIdiomJianpingFactory;
import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.filter.predefined.CurvesRippleFilterFactory;
import com.github.bingoohuang.patchca.filter.predefined.DoubleRippleFilterFactory;
import com.github.bingoohuang.patchca.filter.predefined.RandomFilterFactory;
import com.github.bingoohuang.patchca.filter.predefined.WobbleRippleFilterFactory;
import com.github.bingoohuang.patchca.font.CustomRandomFontFactory;
import com.github.bingoohuang.patchca.font.RandomFontFactory;
import com.github.bingoohuang.patchca.service.Captcha;
import com.github.bingoohuang.patchca.word.RandomWordFactory;

import java.awt.*;
import java.util.Random;

public class PatchcaIdom {
    private static ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
    private static Random random = new Random();

    static {
//        cs.setColorFactory(new SingleColorFactory(new Color(25, 60, 170)));
        cs.setColorFactory(new ColorFactory() {
            @Override
            public Color getColor(int x) {
                int[] c = new int[3];
                int i = random.nextInt(c.length);
                for (int fi = 0; fi < c.length; fi++) {
                    if (fi == i) {
                        c[fi] = random.nextInt(71);
                    } else {
                        c[fi] = random.nextInt(256);
                    }
                }
                return new Color(c[0], c[1], c[2]);
            }
        });

        // 随机字体生成器
//        RandomFontFactory fontFactory = new RandomFontFactory();
        CustomRandomFontFactory fontFactory = new CustomRandomFontFactory();
        fontFactory.setMaxSize(30);
        fontFactory.setMinSize(22);
        cs.setFontFactory(fontFactory);

        ChineseIdiomJianpingFactory wf = new ChineseIdiomJianpingFactory();
        cs.setWordFactory(wf);

        RandomFilterFactory filterFactory = new RandomFilterFactory();
        filterFactory.addFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));
//        filterFactory.addFilterFactory(new MarbleRippleFilterFactory());// 糊
        filterFactory.addFilterFactory(new DoubleRippleFilterFactory());
        filterFactory.addFilterFactory(new WobbleRippleFilterFactory());
//        filterFactory.addFilterFactory(new DiffuseRippleFilterFactory());// 糊
        cs.setFilterFactory(filterFactory);

        // 验证码图片的大小
        cs.setWidth(90);
        cs.setHeight(40);
    }

    public static Captcha next() {
        return cs.getCaptcha();
    }
}
