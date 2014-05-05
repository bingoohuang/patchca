package com.github.bingoohuang.patchca.custom;

import java.util.ArrayList;

import com.github.bingoohuang.patchca.random.StrUtils;
import com.github.bingoohuang.patchca.random.RandUtils;
import com.github.bingoohuang.patchca.word.WordBean;
import com.github.bingoohuang.patchca.word.WordFactory;

import com.google.common.base.Splitter;

public class SymbolDiffFactory implements WordFactory {
    private static String[] symbols;
    static {
        /*按-分组，同一个组内以,分割*/
        String availableSymbols = "2600-2601-2602-2603-2604-2605,2606-2607,2608-2609-260a,260b-260c,260d-260e,260f-"
                + "2610*-2611-2612-2613-2614*-2615*-2616*-2617-2618*-2619*-261a,261b,261c,261d,261e,261f-"
                + "2620-2621,2622,2623,2624,2625,2626,2627,2628,2629-262a-262b,262c-262d-262e-262f-"
                + "2630,2631,2632,2633,2634,2635,2636,2637-2638-2639,263a,263b-263c-263d,263e-263f,"
                + "2640,2641-2642-2643-2644,2645,2646,2647,2648,2649,264a,264b,264c,264d,264e,264f-"
                + "2650,2651,2652,2653,2654,2655,2656,2657,2658,2659,265a,265b,265c,265d,265e,265f-"
                + "2660,2661,2662,2663,2664,2665,2666,2667-2668,2669,266a,266b,266c,266d,266e-266f-"
                + "2700*-2701,2702,2703,2704-2705*-2706-2707-2708-2709-270a*-270b*-270c,270d-270e,270f,"
                + "2710-2711,2712-2713,2714-2715,2716,2717,2718-2719,271a,271b,271c,271d,271e,271f-"
                + "2720,2721,2722,2723,2724,2725,2726,2727,2728,2729,272a,272b,272c,272d,272e,272f,"
                + "2730,2731,2732,2733,2734,2735,2736,2737,2738,2739,273a,273b,273c,273d,273e,273f,"
                + "2740,2741,2742,2743,2744,2745,2746,2747,2748,2749,274a,274b,274c*,274d*-274e*-274f*-"
                + "2750,2751,2752-2753*-2754*-2755*-2756-2757*-2758,-2759,-275a,275b,275c,275d,275e-275f*-"
                + "2760*-2761,2762,2763-2764,2765-2766,2767-2768*-2769*-276a*-276b*-276c*-276d*-276e*-276f*-"
                + "2770*-2771*-2772*-2773*-2774*-2775*-2776,2777,2778,2779,277a,277b,277c,277d,277e,277f,"
                + "2780,2781,2782,2783,2784,2785,2786,2787,2788,2789,278a,278b,278c,278d,278e,278f,"
                + "2790,2791,2792,2793-2794*-2795*-2796*-2797*-2798,2799,279a,279b,279c,279d,279e,279f,"
                + "27a0,27a1,27a2,27a3,27a4,27a5,27a6,27a7,27a8,27a9,27aa,27ab,27ac,27ad,27ae,27af,"
                + "27b0*,27b1,27b2,27b3,27b4,27b5,27b6,27b7,27b8,27b9,27ba,27bb,27bc,27bd,27be-27bf*-";
        Iterable<String> groups = Splitter.on('-').omitEmptyStrings().trimResults().split(availableSymbols);
        Splitter memberSplitter = Splitter.on(',').omitEmptyStrings().trimResults();
        ArrayList<String> strGroups = new ArrayList<String>();
        for (String group : groups) {
            Iterable<String> members = memberSplitter.split(group);
            StringBuilder sb = new StringBuilder();
            for (String member : members) {
                if (member.endsWith("*")) continue;
                char c = (char) Integer.parseInt(member, 16);
                sb.append(c);
            }
            if (sb.length() > 0) {
                strGroups.add(sb.toString());
            }
        }
        symbols = strGroups.toArray(new String[0]);
    }


    @Override
    public WordBean getNextWord() {
        int randPos1 = RandUtils.randInt(symbols.length);
        int randPos2 = randPos1;
        while (randPos2 == randPos1) {
            randPos2 = RandUtils.randInt(symbols.length);
        }
        String group1 = symbols[randPos1];
        String group2 = symbols[randPos2];
        char ch1 = group1.charAt(RandUtils.randInt(group1.length()));
        char ch2 = group2.charAt(RandUtils.randInt(group2.length()));

        int len = 4 + RandUtils.randInt(2);
        int diffPos = RandUtils.randInt(len);
        String word = StrUtils.repeat("" + ch1, diffPos) + ch2 + StrUtils.repeat("" + ch1, len - diffPos - 1) ;

        return new WordBean(word, "" + (diffPos + 1), "找出符号中不同符号的序号");
    }

    @Override
    public String[] getSupportedFontFamilies() {
        return new String[] { "dialog" };
    }
}
