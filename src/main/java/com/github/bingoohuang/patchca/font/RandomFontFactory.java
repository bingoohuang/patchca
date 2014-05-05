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

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class RandomFontFactory implements FontFactory {

    protected List<String> families;
    protected int minSize;
    protected int maxSize;
    protected boolean randomStyle;
    private WordFactory wordFactory;

    public RandomFontFactory() {
        families = new ArrayList<String>();
        String[] fontNames = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getAvailableFontFamilyNames();
        for (String fontName : fontNames) {
            Font f = new Font(fontName, Font.PLAIN, 12);
            if (f.canDisplay('a')) {
                //... Display only fonts that have the alphabetic characters.
                families.add(fontName);
            }
        }

        minSize = 45;
        maxSize = 45;
    }

    public RandomFontFactory(List<String> families) {
        this();
        this.families = families;
    }

    public RandomFontFactory(String[] families) {
        this();
        this.families = Arrays.asList(families);
    }

    public RandomFontFactory(int size, List<String> families) {
        this(families);
        minSize = maxSize = size;
    }

    public RandomFontFactory(int size, String[] families) {
        this(families);
        minSize = maxSize = size;
    }

    public void setFamilies(List<String> families) {
        this.families = families;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public void setRandomStyle(boolean randomStyle) {
        this.randomStyle = randomStyle;
    }

    @Override
    public Font getFont(int index) {
        boolean bold = RandUtils.randBoolean() && randomStyle;
        int size = minSize;
        if (maxSize - minSize > 0) {
            size += RandUtils.randInt(maxSize - minSize);
        }

        String[] supportedFamilies = wordFactory.getSupportedFontFamilies();
        if (supportedFamilies != null && supportedFamilies.length > 0) 
            return new Font(supportedFamilies[RandUtils.randInt(supportedFamilies.length)],
                bold ? Font.BOLD : Font.PLAIN, size); 

        String family = families.get(RandUtils.randInt(families.size()));

        Font font = new Font(family, bold ? Font.BOLD : Font.PLAIN, size);

        return font;
    }

    @Override
    public void setWordFactory(WordFactory wordFactory) {
        this.wordFactory = wordFactory;
    }

}
