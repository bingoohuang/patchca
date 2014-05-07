package com.github.bingoohuang.patchca.random;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class SpellUtils {

    /**
     * 获取全部拼音
     * 
     * @param src
     *            原字符串
     * @return
     */
    public static String getFull(String src) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        StringBuilder pinyin = new StringBuilder();
        try {
            for (char element : src.toCharArray()) {
                // 判断是否为汉字字符
                if (Character.toString(element).matches("[\\u4E00-\\u9FA5]+")) {
                    String[] srcArry = PinyinHelper.toHanyuPinyinStringArray(element, format);
                    pinyin.append(srcArry[0]);
                }
                else {
                    pinyin.append(Character.toString(element));
                }
            }

        }
        catch (BadHanyuPinyinOutputFormatCombination e1) {
           // e1.printStackTrace();
        }

        return pinyin.toString();
    }

    /**
     * 获取首字母拼音
     * 
     * @param str
     *            原字符串
     * @return
     */
    public static String getFirst(String str) {
        StringBuilder pinyin = new StringBuilder();
        for (char word : str.toCharArray()) {
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                pinyin.append(pinyinArray[0].charAt(0));
            }
            else {
                pinyin.append(word);
            }
        }
        return pinyin.toString();
    }
}
