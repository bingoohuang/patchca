package com.github.bingoohuang.patchca;

import com.github.bingoohuang.patchca.background.SingleColorBackgroundFactory;
import com.github.bingoohuang.patchca.color.SingleColorFactory;
import com.github.bingoohuang.patchca.filter.predefined.CurvesRippleFilterFactory;
import com.github.bingoohuang.patchca.font.RandomFontFactory;
import com.github.bingoohuang.patchca.service.AbstractCaptchaService;
import com.github.bingoohuang.patchca.text.renderer.BestFitTextRenderer;
import com.github.bingoohuang.patchca.utils.encoder.EncoderHelper;
import com.github.bingoohuang.patchca.word.RandomWordFactory;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shuvigoss@gmail.com (ShuWei) 2018/8/27
 * @since 1.0
 */
@Slf4j
public abstract class CaptchaGenerator {
  private static final DefaultCaptchaService SERVICE = new DefaultCaptchaService();

  @SneakyThrows
  public static void main(String[] args) {
    @Cleanup ByteArrayOutputStream b = new ByteArrayOutputStream();
    try {
      String captcha = EncoderHelper.getChallangeAndWriteImage(SERVICE, "png", b);
      System.out.println(captcha);
      System.out.println(b.toByteArray().length);
    } catch (IOException e) {
      log.error("生成验证码异常!", e);
    }
  }

  static class DefaultCaptchaService extends AbstractCaptchaService {
    public DefaultCaptchaService() {
      wordFactory = new DefaultWordFactory();
      fontFactory = new RandomFontFactory(); // 字体
      textRenderer = new BestFitTextRenderer(); // 效果
      backgroundFactory = new SingleColorBackgroundFactory(); // 背景
      colorFactory = new SingleColorFactory(new Color(25, 60, 170)); // 字体颜色
      filterFactory = new CurvesRippleFilterFactory(colorFactory); // 样式(曲线波纹加干扰线)
      width = 150; // 图片长宽
      height = 50;
    }
  }

  private static class DefaultWordFactory extends RandomWordFactory {
    public DefaultWordFactory() {
      characters = "abdekmnwx23456789";
      minLength = 4;
      maxLength = 5;
    }
  }
}
