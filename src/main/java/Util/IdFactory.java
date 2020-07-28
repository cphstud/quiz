package Util;

public class IdFactory {
    private static IdFactory idFactory = null;
    private static int questionsCounter = 0;

    private IdFactory() {
    }

    public static IdFactory getInstance() {
        if (idFactory == null ) {
            idFactory = new IdFactory();
        }
        return idFactory;
    }

    public int getQuestionID() {
        questionsCounter++;
        return idFactory.questionsCounter;
    }
}
