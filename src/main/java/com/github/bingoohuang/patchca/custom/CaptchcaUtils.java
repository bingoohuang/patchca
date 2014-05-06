package com.github.bingoohuang.patchca.custom;

import com.github.bingoohuang.patchca.service.Captcha;


public class CaptchcaUtils {

    public static Captcha createCaptchca() {
        ConfigurableCaptchaService cs = new ConfigurableCaptchaService();

        Captcha captcha = cs.getCaptcha();

        return captcha;
    }
}
