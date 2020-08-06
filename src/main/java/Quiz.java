import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Quiz {
    private Map<Integer,String> quizSolution;
    private Map<Integer,String> quizQuestion;

    public Quiz() {
        quizSolution = new HashMap<>();
        quizQuestion = new HashMap<>();
        initQuiz();
    }

    private void initQuiz() {
        //300	WORLD CAPITALS	Home to NATO & little green sprouts	Brussels
        quizQuestion.put(1,"Hovedstaden i Østrig?");
        quizSolution.put(1,"Wien");
        quizQuestion.put(2,"Hovedstaden i Burkina Faso?");
        quizSolution.put(2,"Ouagadougou");
        
    }


    public Map<Integer,String> getQuizSolutions() {
        return this.quizSolution;
    }
    public Map<Integer,String> getQuizQuestions() {
        return this.quizQuestion;
    }


}
