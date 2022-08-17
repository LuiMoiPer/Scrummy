package luimoiper.scrummy.utils;

import java.util.Random;

import luimoiper.scrummy.models.ProjectModel;

public class Generator {
    private final static Random random = new Random();

    public static String makeCharSequence() {
        String word = "";
        for (int i = random.nextInt(10) + 1; i > 0; i--) {
            char nextChar = (char) (random.nextInt(26) + 65);
            if (random.nextBoolean()) {
                nextChar += 32;
            }
            word += nextChar;
        }
        return word;
    }

    public static String makeCharSentence() {
        String sentence = "";
        for (int i = random.nextInt(20); i > 0; i--) {
            sentence += makeCharSequence();
            if (i > 1) {
                sentence += " ";
            }
        }
        return sentence + ".";
    }

    public static ProjectModel makeProjectModel() {
        return new ProjectModel(makeCharSequence(), makeCharSentence());
    }

    public static ProjectModel[] makeProjectModelArray(int count) {
        ProjectModel[] projectModels = new ProjectModel[count];
        for (int i = 0; i < projectModels.length; i++) {
            projectModels[i] = makeProjectModel();
        }
        return projectModels;
    }
}
