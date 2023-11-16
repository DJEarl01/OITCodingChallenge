import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class WordDictionary {
    private final ArrayList<String> wordList;

    public WordDictionary(String wordListFileName) {
        wordList = new ArrayList<>();
        readWordsFromFile(wordListFileName);
    }

    private void readWordsFromFile(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            String line;
            do {
                line = reader.readLine();
                if (line != null) {
                    wordList.add(line);
                }
            } while (line != null);
        } catch (IOException e) {
            System.out.println("FileReader got an error: " + e.getMessage());
        }
    }

    public String getRandomWord() {
        int index = (int) (Math.random() * 10);
        return wordList.get(index);
    }

}
