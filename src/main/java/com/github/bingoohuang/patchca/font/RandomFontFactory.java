package com.github.bingoohuang.patchca.font;

import com.google.common.collect.Lists;
import java.awt.Font;
import java.io.InputStream;
import java.util.List;
import java.util.Random;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RandomFontFactory extends RandomSystemFontFactory {
  static List<Font> fonts =
      loadFonts("Tahoma.ttf", "Tahoma Bold.ttf", "Verdana.ttf", "Verdana Bold.ttf");

  @SneakyThrows
  private static List<Font> loadFonts(String... names) {
    List<Font> fonts = Lists.newArrayList();
    ClassLoader cl = RandomFontFactory.class.getClassLoader();
    for (String name : names) {
      String path = "com/github/bingoohuang/patchca/" + name;
      try (InputStream is = cl.getResourceAsStream(path)) {
        Font f = Font.createFont(Font.TRUETYPE_FONT, is);
        fonts.add(f);
      } catch (Exception ex) {
        log.error("load font {} error", name, ex);
      }
    }
    return fonts;
  }

  @Override
  public Font getFont(int index) {
    Random r = new Random();
    Font f = fonts.get(r.nextInt(fonts.size()));

    float size =  minSize;
    if (maxSize - minSize > 0) {
      size += r.nextInt(maxSize - minSize);
    }
    return f.deriveFont(size);
  }
  
}
