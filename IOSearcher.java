package test;

import java.io.FileReader;

public class IOSearcher {
    static boolean search(String word, String... fileNames) {
        try {
            // iterate over the files
            for (String fileName : fileNames) {
                // open the file for reading
                FileReader fileReader = new FileReader(fileName);
                int data = fileReader.read();
                // iterate over the file
                while (data != -1) {
                    char ch = (char) data;
                    // check if the first character of the word is found
                    if (ch == word.charAt(0)) {
                        boolean found = true;
                        // check if the rest of the word is found
                        for (int i = 1; i < word.length(); i++) {
                            data = fileReader.read();
                            if (data == -1) {
                                return false;
                            }
                            ch = (char) data;
                            if (ch != word.charAt(i)) {
                                found = false;
                                break;
                            }
                        }
                        // if the word is found, return true
                        if (found) {
                            return true;
                        }
                    }
                    // read the next character
                    data = fileReader.read();
                }
                // close the file
                fileReader.close();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }
}
