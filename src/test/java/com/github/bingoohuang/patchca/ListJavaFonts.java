package com.github.bingoohuang.patchca;

import java.awt.GraphicsEnvironment;

public class ListJavaFonts {
    public static void main(String[] args) {
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String fonts[] = env.getAvailableFontFamilyNames();

        for (int i = 0; i < fonts.length; i++) {
            System.out.println("<" + fonts[i] + ">");
        }
    }

}
