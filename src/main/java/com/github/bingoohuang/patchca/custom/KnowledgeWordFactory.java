package com.github.bingoohuang.patchca.custom;

import com.github.bingoohuang.patchca.random.RandUtils;
import com.github.bingoohuang.patchca.word.WordBean;
import com.github.bingoohuang.patchca.word.WordFactory;

public class KnowledgeWordFactory implements WordFactory {
    private static WordBean[] wordBeans = new WordBean[]{
            new WordBean("故宫在？市", "北京", "你知道吗?"),
            new WordBean("伦敦在？国", "英", "你知道吗?"),
            new WordBean("伦敦在？州", "欧", "你知道吗?"),
            new WordBean("茅台产自？省", "贵州", "你知道吗?"),
            new WordBean("三峡在？市", "重庆", "你知道吗?"),
            new WordBean("昆明在？省", "云南", "你知道吗?"),
            new WordBean("庐山在？省", "江西", "你知道吗?"),
            new WordBean("黄山在？省", "安徽", "你知道吗?"),
            new WordBean("泰山在？省", "山东", "你知道吗?"),
            new WordBean("太湖在？省", "江苏", "你知道吗?")
    };

    @Override
    public WordBean getNextWord() {
        return wordBeans[RandUtils.randInt(wordBeans.length)];
    }

    @Override
    public String[] getSupportedFontFamilies() {
        return new String[]{"宋体"};
    }

}
