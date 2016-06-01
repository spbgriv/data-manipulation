package griv.service;

import java.util.Random;

public class RandomResourceStringKeywordService implements KeywordService {

    private static final String[] KEYWORDS_RESOURCES = {
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine",
            "ten"
    };

    private final Random random = new Random();


    public String resolveKeywords(Object site) {
        return KEYWORDS_RESOURCES[random.nextInt(KEYWORDS_RESOURCES.length)] + ", " + KEYWORDS_RESOURCES[random.nextInt(KEYWORDS_RESOURCES.length)];
    }

}
