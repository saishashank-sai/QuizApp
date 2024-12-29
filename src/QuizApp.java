import java.util.*;

public class QuizApp {
    static Scanner scanner = new Scanner(System.in);
    static int score = 0;
    static int questionIndex = 0;

    static class Question {
        String questionText;
        String[] options;
        int correctOption;

        public Question(String questionText, String[] options, int correctOption) {
            this.questionText = questionText;
            this.options = options;
            this.correctOption = correctOption;
        }
    }

    static List<Question> questions = Arrays.asList(
        new Question("What is the capital of France?", new String[]{"1. Berlin", "2. Madrid", "3. Paris", "4. Rome"}, 3),
        new Question("Which planet is known as the Red Planet?", new String[]{"1. Earth", "2. Mars", "3. Venus", "4. Jupiter"}, 2),
        new Question("What is the largest ocean on Earth?", new String[]{"1. Atlantic", "2. Indian", "3. Pacific", "4. Arctic"}, 3)
    );

    public static void main(String[] args) {
        System.out.println("Welcome to the Quiz App!");
        for (Question question : questions) {
            displayQuestion(question);
        }
        displayResults();
    }

    static void displayQuestion(Question question) {
        System.out.println("\nQuestion " + (questionIndex + 1) + ": " + question.questionText);
        for (String option : question.options) {
            System.out.println(option);
        }

        int answer = getAnswerWithTimer(10);
        if (answer == question.correctOption) {
            System.out.println("Correct!");
            score++;
        } else {
            System.out.println("Wrong! The correct answer was: " + question.options[question.correctOption - 1]);
        }
        questionIndex++;
    }

    static int getAnswerWithTimer(int timeLimit) {
        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("\nTime's up!");
                System.exit(0); 
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, timeLimit * 1000); 

        int answer = -1;
        try {
            System.out.print("Enter your choice (1-4): ");
            answer = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Exiting...");
            System.exit(0);
        } finally {
            timer.cancel(); 
        }
        return answer;
    }

    static void displayResults() {
        System.out.println("\nQuiz Completed!");
        System.out.println("Your Score: " + score + " / " + questions.size());
        System.out.println("Summary:");
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println("Q" + (i + 1) + ": " + q.questionText);
            System.out.println("Correct Answer: " + q.options[q.correctOption - 1]);
            System.out.println();
        }
    }
}
