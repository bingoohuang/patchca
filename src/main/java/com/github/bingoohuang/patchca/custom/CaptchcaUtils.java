package com.github.bingoohuang.patchca.custom;

import java.awt.Color;

import com.github.bingoohuang.patchca.color.SingleColorFactory;
import com.github.bingoohuang.patchca.filter.predefined.*;
import com.github.bingoohuang.patchca.random.RandUtils;
import com.github.bingoohuang.patchca.service.Captcha;


public class CaptchcaUtils {
    private static ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
    static {
        cs.setColorFactory(new SingleColorFactory(new Color(25, 60, 170)));
    }

    public static Captcha createCaptchca() {
        switch (RandUtils.randInt(5)) {
        case 0:
            cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));
            break;
        case 1:
            cs.setFilterFactory(new MarbleRippleFilterFactory());
            break;
        case 2:
            cs.setFilterFactory(new DoubleRippleFilterFactory());
            break;
        case 3:
            cs.setFilterFactory(new WobbleRippleFilterFactory());
            break;
        case 4:
            cs.setFilterFactory(new DiffuseRippleFilterFactory());
            break;
        }
        Captcha captcha = cs.getCaptcha();

        return captcha;
    }
}
