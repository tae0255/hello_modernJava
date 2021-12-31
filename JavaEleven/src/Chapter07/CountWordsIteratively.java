package Chapter07;

public class CountWordsIteratively {

    public static void _test() {
        final String SENTENCE = "they had their faces twisted toward their haunches\n" +
                "and found it necessary to walk backward,\n" +
                "because they could not see ahead of them.\n" +
                "... and since he wanted so to see ahead,\n" +
                "he looks behind and walks a backward path.";
        System.out.println("[Sequential] Fount " + countWordsInteratively(SENTENCE) + " words");


    }

    public static int countWordsInteratively(String s) {
        int counter = 0;
        boolean lastSpace = true;
        for (char c : s.toCharArray()) {
            if (Character.isWhitespace(c)) {
                lastSpace = true;
            } else {
                if (lastSpace) counter++;
                lastSpace = false;
            }
        }
        return counter;
    }

}