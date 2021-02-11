import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class HangmanGame {

    public static void main(String[] args) {
        HangmanGame hangmanGame = new HangmanGame();
        hangmanGame.newGame();
        hangmanGame.play();
    }

    public static String[] words = {"SOFIQ", "PLOVDIV", "VARNA", "BURGAS", "PLEVEN",
            "SILISTRA", "LOVECH", "VELIKO TURNOVO", "RUSE", "QMBOL",
            "STARA ZAGORA", "NOVA ZAGORA", "SHUMEN", "PETRICH", "PERNIK",
            "VIDIN", "MONTANA", "VRACA", "HASKOVO", "DOBRICH", "KURDJALI",
            "TURGOVISHTE", "SMOLQN", "SAMOKOV", "BLAGOEVGRAD", "BOTEVGRAD"};

    Random random = new Random();

    int maxErrors = 8;
    int errors;

    String wordToFind;
    char[] wordFound;


    ArrayList<String> letters = new ArrayList<>();

    String nextWordToFind() {

        return words[random.nextInt(words.length)];
    }

    public void newGame() {

        errors = 0;
        letters.clear();
        wordToFind = nextWordToFind();


        wordFound = new char[wordToFind.length()];
        for (int i = 0; i < wordFound.length; i++) {
            wordFound[i] = '_';

        }
    }

    public boolean wordFound() {

        return wordToFind.contentEquals(new String(wordFound));
    }

    private void enter(String newCharEntered) {

        if (!letters.contains(newCharEntered)) {

            if (wordToFind.contains(newCharEntered)) {

                int index = wordToFind.indexOf(newCharEntered);
                while (index >= 0) {
                    wordFound[index] = newCharEntered.charAt(0);
                    index = wordToFind.indexOf(newCharEntered, index + 1);
                }
            } else {

                errors++;
            }

            letters.add(newCharEntered);
        }
    }

    private String wordFoundContent() {

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < wordFound.length; i++) {
            builder.append(wordFound[i]);
            if (i < wordFound.length - 1) {
                builder.append(" ");
            }

        }
        return builder.toString();
    }

    public void play() {

        try (Scanner input = new Scanner(System.in)) {

            while (errors < maxErrors) {
                System.out.println("Enter a letter : ");

                String str = input.next();
                if (str.length() > 1) {
                    str = str.substring(0, 1);
                }

                enter(str);

                System.out.println(wordFoundContent());

                if (wordFound()) {
                    System.out.println("You win !");
                    break;
                } else {

                    System.out.println("Remaining attempts: " + (maxErrors - errors));
                }
            }
            if (errors == maxErrors) {

                System.out.println("You lost!");
                System.out.println("Word to find was: " + wordToFind);

            }

        }
    }
}
