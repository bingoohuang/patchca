package com.github.bingoohuang.patchca.custom;

import com.github.bingoohuang.patchca.random.RandUtils;
import com.github.bingoohuang.patchca.random.SpellUtils;
import com.github.bingoohuang.patchca.random.StrUtils;
import com.github.bingoohuang.patchca.word.WordBean;
import com.github.bingoohuang.patchca.word.WordFactory;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;

import java.awt.*;

public class ChineseIdiomJianpingFactory implements WordFactory {
    protected static String[] idioms;

    static {
        String str = StrUtils.loadClasspathResourceToString("/com/github/bingoohuang/patchca/chineseidioms.txt");
        Iterable<String> split = Splitter.onPattern("\\s+").omitEmptyStrings().split(str);
        idioms = Iterables.toArray(split, String.class);

    }

    public WordBean getNextWord() {
        int nextInt = RandUtils.randInt(idioms.length);
        String answer = idioms[nextInt];

        return new WordBean("请输入“" + answer + "”的简拼", SpellUtils.getFirst(answer), "请输入简拼");
    }

    @Override
    public String[] getSupportedFontFamilies() {
        return null;
    }

}
