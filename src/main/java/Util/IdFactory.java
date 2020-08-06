package Util;

public class IdFactory {
    private static IdFactory idFactory = null;
    private static int questionsCounter = 0;

    private IdFactory() {
    }

    private static IdFactory getInstance() {
        if (idFactory == null ) {
            idFactory = new IdFactory();
        }
        return idFactory;
    }

    public static int getQuestionID() {
        questionsCounter++;
        return IdFactory.getInstance().getQuestionID();
    }
}
