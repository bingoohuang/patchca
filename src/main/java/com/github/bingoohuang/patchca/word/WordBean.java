package com.github.bingoohuang.patchca.word;

public class WordBean {

    private String word;
    private String answer;
    private String tips;

    public WordBean(String word, String answer, String tips) {
        this.word = word;
        this.answer = answer;
        this.tips = tips;
    }

    public String getWord() {
        return word;
    }

    public String getAnswer() {
        return answer;
    }

    public String getTips() {
        return tips;
    }

}
