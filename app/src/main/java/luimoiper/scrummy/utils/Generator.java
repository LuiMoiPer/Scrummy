package luimoiper.scrummy.utils;

import java.util.Date;
import java.util.Random;

import luimoiper.scrummy.models.ProjectModel;
import luimoiper.scrummy.models.SprintModel;
import luimoiper.scrummy.models.TaskModel;

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

    public static Date makeDate() {
        int year = random.nextInt(1020) + 1000;
        int month = random.nextInt(12);
        int day = random.nextInt(28);
        Date date = new Date(year, month, day);
        return date;
    }

    public static ProjectModel makeProjectModel() {
        return new ProjectModel(makeCharSequence(), makeCharSentence());
    }

    public static ProjectModel[] makeProjectModels(int count) {
        ProjectModel[] projectModels = new ProjectModel[count];
        for (int i = 0; i < projectModels.length; i++) {
            projectModels[i] = makeProjectModel();
        }
        return projectModels;
    }

    public static TaskModel makeTaskModel() {
        return new TaskModel(makeCharSequence(), makeCharSentence(), random.nextInt(17));
    }

    public static TaskModel[] makeTaskModels(int count) {
        TaskModel[] taskModels = new TaskModel[count];
        for (int i = 0; i < taskModels.length; i++) {
            taskModels[i] = makeTaskModel();
        }
        return taskModels;
    }

    public static SprintModel makeSprintModel() {
        SprintModel sprintModel = new SprintModel(
                makeCharSequence(),
                makeDate(),
                makeDate(),
                random.nextInt(90) + 10
        );
        return null;
    }
}
