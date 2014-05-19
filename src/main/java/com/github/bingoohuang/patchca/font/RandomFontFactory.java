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
package com.github.bingoohuang.patchca.font;

import com.github.bingoohuang.patchca.random.RandUtils;
import com.github.bingoohuang.patchca.word.WordFactory;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class RandomFontFactory implements FontFactory {
    protected List<String> families;
    protected int minSize;
    protected int maxSize;
    private String word;
    private WordFactory wordFactory;

    public RandomFontFactory() {
        families = new ArrayList<String>();
        String[] fontNames = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getAvailableFontFamilyNames();
        for (String fontName : fontNames) {
            if (canDisplay(fontName, 'a')) {
                //... Display only fonts that have the alphabetic characters.
                families.add(fontName);
            }
        }

        minSize = 45;
        maxSize = 45;
    }

    public boolean canDisplay(String fontName, char ch) {
        Font f = new Font(fontName, Font.PLAIN, 12);
        return f.canDisplay(ch);
    }


    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public Font getFont(int index) {
        boolean bold = RandUtils.randBoolean();

        int size = minSize;
        if (maxSize - minSize > 0) {
            size += RandUtils.randInt(maxSize - minSize);
        }

        String[] supportedFamilies = wordFactory.getSupportedFontFamilies();
        if (supportedFamilies != null && supportedFamilies.length > 0)
            return new Font(supportedFamilies[RandUtils.randInt(supportedFamilies.length)],
                    bold ? Font.BOLD : Font.PLAIN, size);

        String family;
        TRY: while(true) {
            family = families.get(RandUtils.randInt(families.size()));
            for (char ch : word.toCharArray())
                if (!canDisplay(family, ch)) continue TRY;

            break;
        }

        return new Font(family, bold ? Font.BOLD : Font.PLAIN, size);
    }

    @Override
    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public void setWordFactory(WordFactory wordFactory) {
        this.wordFactory = wordFactory;
    }

}
