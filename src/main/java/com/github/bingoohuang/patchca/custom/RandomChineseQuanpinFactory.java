package com.github.bingoohuang.patchca.custom;


import com.github.bingoohuang.patchca.random.RandUtils;
import com.github.bingoohuang.patchca.random.SpellUtils;
import com.github.bingoohuang.patchca.word.WordBean;
import com.github.bingoohuang.patchca.word.WordFactory;

public class RandomChineseQuanpinFactory implements WordFactory {
    @Override
    public WordBean getNextWord() {
        String  randChinese = RandUtils.randChinese(2);

        return new WordBean("请输入“" + randChinese + "”的全拼", SpellUtils.getFull(randChinese), "请输入全拼");
    }

    @Override
    public String[] getSupportedFontFamilies() {
        return new String[]{"宋体"};
    }
}
