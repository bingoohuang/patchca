package com.github.bingoohuang.patchca.custom;


import com.github.bingoohuang.patchca.random.RandUtils;
import com.github.bingoohuang.patchca.random.SpellUtils;
import com.github.bingoohuang.patchca.word.WordBean;
import com.github.bingoohuang.patchca.word.WordFactory;

public class RandomChineseJianpinFactory implements WordFactory {
    @Override
    public WordBean getNextWord() {
        String randChinese = RandUtils.randChinese(4);

        return new WordBean("请输入“" + randChinese + "”的简拼", SpellUtils.getFirst(randChinese), "请输入简拼");
    }

    @Override
    public String[] getSupportedFontFamilies() {
        return new String[]{"宋体"};
    }
}
