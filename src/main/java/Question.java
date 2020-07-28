public class Question {
    //300	WORLD CAPITALS	Home to NATO & little green sprouts	Brussels
    int questionID;
    String category;
    int rating;
    String question;
    String answer;

    public Question(String category, int rating, String question, String answer) {
        this.category = category;
        this.rating = rating;
        this.question = question;
        this.answer = answer;
        this.questionID=Util.IdFactory.getInstance().getQuestionID();
    }

    public int getQuestionID() {
        return questionID;
    }

    public String getCategory() {
        return category;
    }

    public int getRating() {
        return rating;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        String retVal = "";
        retVal = "Question{" +
                "questionID=" + questionID +
                ", category='" + category + '\'' +
                ", rating=" + rating +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
        return retVal;
    }
}
