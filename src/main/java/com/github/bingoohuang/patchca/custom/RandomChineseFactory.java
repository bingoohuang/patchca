package com.github.bingoohuang.patchca.custom;


import com.github.bingoohuang.patchca.random.RandUtils;
import com.github.bingoohuang.patchca.word.WordBean;
import com.github.bingoohuang.patchca.word.WordFactory;

public class RandomChineseFactory implements WordFactory {
    @Override
    public WordBean getNextWord() {
        String randChinese = RandUtils.randChinese((3 + RandUtils.randInt(2)));

        return new WordBean(randChinese, randChinese, "请输入图片中的文字");
    }

    @Override
    public String[] getSupportedFontFamilies() {
        return null;
    }
}
