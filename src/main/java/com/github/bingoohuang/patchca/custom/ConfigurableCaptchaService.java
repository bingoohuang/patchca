/*
 * Copyright (c) 2009 Piotr Piastucki
 * 
 * This file is part of Patchca CAPTCHA library.
 * 
 *  Patchca is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  
 *  Patchca is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *  
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Patchca. If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.bingoohuang.patchca.custom;

import com.github.bingoohuang.patchca.background.SingleColorBackgroundFactory;
import com.github.bingoohuang.patchca.color.SingleColorFactory;
import com.github.bingoohuang.patchca.filter.predefined.CurvesRippleFilterFactory;
import com.github.bingoohuang.patchca.font.RandomFontFactory;
import com.github.bingoohuang.patchca.service.AbstractCaptchaService;
import com.github.bingoohuang.patchca.text.renderer.BestFitTextRenderer;
import com.github.bingoohuang.patchca.word.AdaptiveRandomWordFactory;
import com.github.bingoohuang.patchca.word.RandomWordFactory;
import com.github.bingoohuang.patchca.word.WordFactory;

import java.util.ArrayList;

public class ConfigurableCaptchaService extends AbstractCaptchaService {
    private static ArrayList<WordFactory> wordFactories = new ArrayList<WordFactory>();

    static {
        wordFactories.add(new MathExprFactory()); // 三个单数运算
        wordFactories.add(new MathArithmeticFactory()); // 四则运算
        wordFactories.add(new ChineseIdiomFactory()); // 成语
        wordFactories.add(new ChineseIdiomGuessFactory()); // 成语猜字
        wordFactories.add(new EnglishWordFactory()); // 常见2000英语单词
        wordFactories.add(new AdaptiveRandomWordFactory()); // 宽字符只会有一个的随机
        wordFactories.add(new RandomWordFactory()); // 随机
        wordFactories.add(new SymbolDiffFactory()); // 符号找不同
        wordFactories.add(new KnowledgeWordFactory()); // 地理知识
        wordFactories.add(new AdaptiveRandomWordFactory());
    }

    public ConfigurableCaptchaService() {
        backgroundFactory = new SingleColorBackgroundFactory();
        wordFactory = new RandomFactoryWordFactory(wordFactories);
        fontFactory = new RandomFontFactory();
        fontFactory.setWordFactory(wordFactory);
        textRenderer = new BestFitTextRenderer();
        colorFactory = new SingleColorFactory();
        filterFactory = new CurvesRippleFilterFactory(colorFactory);
        textRenderer.setLeftMargin(10);
        textRenderer.setRightMargin(10);
        width = 180;
        height = 70;
    }

}
