import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

/**
 * This program will process text as strings from the web.
 * @author smg
 *
 */
public class WebProcessor {
    /**
     * This code takes the contents of three URL's
     * and turns them into strings. Then it counts
     * the words in each String and outputs it to
     * the user.
     * @param args is a normal argument for the main method
     */
     public static void main(final String[]args) {
         String[] arrayOfURLS = {"http://erdani.com/tdpl/hamlet.txt",
         "https://www.bls.gov/tus/charts/chart9.txt",
         "http://tgftp.nws.noaa.gov/data/raw/fz/fzus53.klot.srf.lot.txt"};
         int countOfWords;
         String storyAsAString;
         final int numberofstories = 3;
         for (int a = 0; a < numberofstories; a++) {
             countOfWords = 0;
             storyAsAString = urlToString(arrayOfURLS[a]);
             String[] storyAsAnArray = storyAsAString.split(" ");
             for (int b = 0; b < storyAsAnArray.length; b++) {
                 storyAsAnArray[b] = wordWithoutExtras(storyAsAnArray[b]);
                 countOfWords++;
             }
             System.out.println("Story number " + (a + 1) + " is "
             + countOfWords + " words in length.");
         }
         String[] hamletArray = urlToString("http://erdani.com/tdpl/hamlet.txt").split(" ");
         System.out.println(countOccurencesOfWord(hamletArray, "prince"));
     }
    /**
     * Retrieve contents from a URL and return them as a string.
     *
     * @param url url to retrieve contents from
     * @return the contents from the url as a string, or an empty string on error
     */
    public static String urlToString(final String url) {
        Scanner urlScanner;
        try {
            urlScanner = new Scanner(new URL(url).openStream(), "UTF-8");
        } catch (IOException e) {
            return "";
        }
        String contents = urlScanner.useDelimiter("\\A").next();
        urlScanner.close();
        return contents;
    }
    /**
     *
     * @param str is the story as an array with each item as one word
     * @param wordToCount is the word that you want to count occurrences of
     * @return returns an integer equal to the amount of occurrences of wordToCount
     */
    public static int countOccurencesOfWord(final String[] str, final String wordToCount) {
        int wordCounter = 0;
        for (int a = 0; a < str.length; a++) {
            if (str[a].toLowerCase().equals(wordToCount)) {
                wordCounter++;
            }
        }
        return wordCounter;
    }

    /**
     *
     * @param input is the string that may have a ",", "!", or "." attached to it
     * @return a string without the extra stuff
     */
    public static String wordWithoutExtras(final String input) {
        String newString = "";
        if (input.indexOf(",") >= 0) {
            newString = input.substring(0, input.indexOf(","));
        } else if (input.indexOf("!") >= 0) {
            newString = input.substring(0, input.indexOf("!"));
        } else if (input.indexOf(".") >= 0) {
            newString = input.substring(0, input.indexOf("."));
        } else {
            newString = input;
        }
        return newString;
    }
}
