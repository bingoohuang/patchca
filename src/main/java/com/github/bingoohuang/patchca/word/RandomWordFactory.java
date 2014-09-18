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
package com.github.bingoohuang.patchca.word;

import java.util.Random;

public class RandomWordFactory implements WordFactory {
    protected String characters;
    protected int minLength;
    protected int maxLength;

    public void setCharacters(String characters) {
        this.characters = characters;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public RandomWordFactory() {
        characters = "absdegkmnpwx23456789";
        minLength = 6;
        maxLength = 10;
    }

    @Override
    public WordBean getNextWord() {
        Random rnd = new Random();
        StringBuffer sb = new StringBuffer();
        int l = minLength + (maxLength > minLength ? rnd.nextInt(maxLength - minLength) : 0);
        for (int i = 0; i < l; i++) {
            int j = rnd.nextInt(characters.length());
            sb.append(characters.charAt(j));
        }
        String answer = sb.toString();
        return new WordBean(answer, answer, "请输入图片中的文字");
    }

    private static char ch = '\u2600';

    public String transformString() {
        //  0x2700 to 0x27be, U+2600 - 0x41 ('A')
        // Unicode Dingbats range in 0x2700 to 0x27be - you should get the right glyphs to display.

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            System.err.print(Integer.toString(ch, 16));
            System.err.print("-");
            sb.append(ch);
            ch++;
        }
        System.err.println();
        return sb.toString();
    }

    @Override
    public String[] getSupportedFontFamilies() {
        return null;
    }

}
