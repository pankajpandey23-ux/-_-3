package onlinetest;

import java.util.*;

public class OnlineExam {
  private static final Map<Integer, String> exams = new HashMap<>();
  private static final Map<Integer, List<Question>> questions = new HashMap<>();
  private static final Map<Integer, Map<Integer, String>> answers = new HashMap<>();

  public static void main(String[] args) {
    // Initialize exam data
    exams.put(1, "Java Fundamentals");
    exams.put(2, "Java Advanced");
    List<Question> javaFundamentalsQuestions = new ArrayList<>();
    javaFundamentalsQuestions.add(new Question("What is the output of the following code?\nint x = 5;\nSystem.out.println(x++);\nSystem.out.println(x);", 
      new String[]{"5, 6", "6, 6", "5, 5", "6, 5"}, "5, 6"));
    javaFundamentalsQuestions.add(new Question("Which of the following is NOT a primitive data type in Java?", 
      new String[]{"int", "float", "boolean", "string"}, "string"));
    javaFundamentalsQuestions.add(new Question("Which keyword is used to create a new object in Java?", 
      new String[]{"object", "new", "create", "instance"}, "new"));
    questions.put(1, javaFundamentalsQuestions);
    List<Question> javaAdvancedQuestions = new ArrayList<>();
    javaAdvancedQuestions.add(new Question("What is the output of the following code?\nString s1 = \"hello\";\nString s2 = \"hello\";\nSystem.out.println(s1 == s2);", 
      new String[]{"true", "false"}, "true"));
    javaAdvancedQuestions.add(new Question("Which of the following is a design pattern in Java?", 
      new String[]{"Adapter", "Iterator", "Observer", "All of the above"}, "All of the above"));
    javaAdvancedQuestions.add(new Question("Which keyword is used to prevent a method from being overridden in Java?", 
      new String[]{"final", "abstract", "static", "private"}, "final"));
    questions.put(2, javaAdvancedQuestions);

    // Start exam
    Scanner scanner = new Scanner(System.in);
    boolean loggedIn = false;
    String username = null;
    while (!loggedIn) {
      System.out.print("Enter username: ");
      username = scanner.next();
      System.out.print("Enter password: ");
      String password = scanner.next();
      if (isValidUser(username, password)) {
        loggedIn = true;
        System.out.println("Login successful. Welcome, " + username + ".");
      } else {
        System.out.println("Invalid username or password. Please try again.");
      }
    }
    updateProfile(scanner, username);
    startExam(scanner, username);
    logout();
  }

  private static boolean isValidUser(String username, String password) {
    // Dummy user validation logic
    return true;
  }

  private static void updateProfile(Scanner scanner, String username) {
    // Dummy profile update logic
    System.out.println("Welcome, " + username + ". Please update your profile.");
    System.out.print("Enter your full name: ");
    String fullName = scanner.nextLine();
    System.out.print("Enter your email address: ");
    String email = scanner.nextLine();
    System.out.print("Enter a new password: ");
    String password = scanner.next();
    System.out.println("Profile updated successfully.");
  }

  private static void startExam(Scanner scanner, String username) {
    // Get exam ID
    System.out.println("Choose an exam:");
    for (int examId : exams.keySet()) {
      System.out.println(examId+" - " + exams.get(examId));
    }
    int examId = scanner.nextInt();
    if (!exams.containsKey(examId)) {
    System.out.println("Invalid exam ID.");
    return;
    }

    // Display exam instructions and start timer
    System.out.println("Instructions:");
    System.out.println("- This exam consists of " + questions.get(examId).size() + " multiple choice questions.");
    System.out.println("- You have 1 minute to complete the exam.");
    System.out.println("- Once you start the exam, the timer will start and you cannot pause or reset the exam.");
    System.out.println("Are you ready to start the exam? (y/n)");
    String ready = scanner.next();
    if (!ready.equalsIgnoreCase("y")) {
    return;
    }
    Timer timer = new Timer();
    timer.schedule(new TimerTask() {
    int remainingTime = 1 * 60;
    public void run() {
    if (remainingTime > 0) {
    remainingTime--;
    } else {
    timer.cancel();
    submitAnswers(username, examId);
    logout();
    }
    }
    }, 0, 1000);

    // Display questions and get answers
    System.out.println("Exam: " + exams.get(examId));
    List<Question> examQuestions = questions.get(examId);
    Map<Integer, String> userAnswers = new HashMap<>();
    for (int i = 0; i < examQuestions.size(); i++) {
    Question question = examQuestions.get(i);
    System.out.println("Question " + (i + 1) + ": " + question.getText());
    String[] options = question.getOptions();
    for (int j = 0; j < options.length; j++) {
    System.out.println((j + 1) + ". " + options[j]);
    }
    System.out.print("Your answer (enter option number): ");
    int answer = scanner.nextInt();
    userAnswers.put(i + 1, options[answer - 1]);
    }
    timer.cancel();
    answers.put(examId, userAnswers);
    submitAnswers(username, examId);
    }

    private static void submitAnswers(String username, int examId) {
    // Calculate score and display results
    Map<Integer, String> userAnswers = answers.get(examId);
    List<Question> examQuestions = questions.get(examId);
    int correctAnswers = 0;
    for (int i = 0; i < examQuestions.size(); i++) {
    if (examQuestions.get(i).getAnswer().equals(userAnswers.get(i + 1))) {
    correctAnswers++;
    }
    }
    System.out.println("Results:");
    System.out.println("- Total questions: " + examQuestions.size());
    System.out.println("- Correct answers: " + correctAnswers);
    System.out.println("- Incorrect answers: " + (examQuestions.size() - correctAnswers));
    }

    private static void logout() {
    System.out.println("Session closed... Goodbye.");
    System.exit(0);
    }

    private static class Question {
    private final String text;
    private final String[] options;
    private final String answer;

    public Question(String text, String[] options, String answer) {
    this.text = text;
    this.options = options;
    this.answer = answer;
    }

    public String getText() {
    return text;
    }

    public String[] getOptions() {
    return options;
    }

    public String getAnswer() {
    return answer;
    }
    }
    }
