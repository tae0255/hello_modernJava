package CHAPTER07;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class WordCounter {
    private final int counter;
    private final boolean lastSpace;

    public void _test(){
        final String SENTENCE = "they had their faces twisted toward their haunches\n" +
                "and found it necessary to walk backward,\n" +
                "because they could not see ahead of them.\n" +
                "... and since he wanted so to see ahead,\n" +
                "he looks behind and walks a backward path.";

        Stream<Character> stream = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);
        WordCounter wc = stream.reduce(new WordCounter(0, true),
                                        WordCounter::accumulate,
                                        WordCounter::combine);
        System.out.println("[Stream] Found " + wc.getCounter() + " words");

        Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
        Stream<Character> stream2 = StreamSupport.stream(spliterator, true);
        WordCounter wc2 = stream2.reduce(new WordCounter(0, true),
                WordCounter::accumulate,
                WordCounter::combine);
        System.out.println("[Parallel Stream] Found " + wc2.getCounter() + " words");
    }

    public WordCounter(int counter, boolean lastSpace){
        this.counter = counter;
        this.lastSpace = lastSpace;
    }

    public WordCounter accumulate(Character c){
        if(Character.isWhitespace(c)){
            return lastSpace ?
                    this :
                    new WordCounter(counter, true);
        }else{
            return lastSpace ?
                    new WordCounter(counter+1, false) :
                    this;
        }
    }

    private int getCounter() {
        return counter;
    }

    public WordCounter combine(WordCounter wordCounter){
        return new WordCounter(counter + wordCounter.counter, wordCounter.lastSpace);
    }

    class WordCounterSpliterator implements Spliterator<Character>{
        private final String string;
        private int currentChar = 0;

        public WordCounterSpliterator(String string){
            this.string = string;
        }

        @Override
        public boolean tryAdvance(Consumer<? super Character> action) {
            action.accept(string.charAt(currentChar++));
            return currentChar < string.length();
        }

        @Override
        public Spliterator<Character> trySplit() {
            int currentSize = string.length() - currentChar;

            //파싱할 문자열을 순차처리할만큼 충분히 작아졌음
            if(currentSize<10){
                return null;
            }
            for(int splitPos = currentSize/2+currentChar; splitPos<string.length(); splitPos++){
                if(Character.isWhitespace(string.charAt(splitPos))){
                    Spliterator<Character> spliterator = new WordCounterSpliterator(string.substring(currentChar, splitPos));
                    currentChar = splitPos;
                    return spliterator;
                }
            }

            return null;
        }

        @Override
        public long estimateSize() {
            return string.length() - currentChar;
        }

        @Override
        public int characteristics() {
            return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
        }
    }
}
