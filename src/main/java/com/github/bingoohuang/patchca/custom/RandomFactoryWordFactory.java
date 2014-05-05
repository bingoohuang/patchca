package com.github.bingoohuang.patchca.custom;

import com.github.bingoohuang.patchca.random.RandUtils;
import com.github.bingoohuang.patchca.word.WordBean;
import com.github.bingoohuang.patchca.word.WordFactory;

import java.util.List;

public class RandomFactoryWordFactory implements WordFactory {
    private List<WordFactory> factories;
    private static ThreadLocal<WordFactory> wordFactory = new ThreadLocal<WordFactory>();

    public RandomFactoryWordFactory(List<WordFactory> factories) {
        this.factories = factories;
    }

    @Override
    public WordBean getNextWord() {
        WordFactory value = factories.get(RandUtils.randInt(factories.size()));
        wordFactory.set(value);
        return value.getNextWord();
    }

    @Override
    public String[] getSupportedFontFamilies() {
        WordFactory wf = wordFactory.get();
        return wf.getSupportedFontFamilies();
    }


}
