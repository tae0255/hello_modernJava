import Chapter07.CountWordsIteratively;
import Chapter07.WordCounter;
import Chapter08.CollectionFactory;
import Chapter08.ListSetProcessor;
import Chapter08.MapProcessor;

import java.util.List;
import java.util.Locale;

public class Main {
    public static void main(String[] args){
        //System.out.println(Runtime.getRuntime().availableProcessors());
        //CountWordsIteratively._test();
        //new WordCounter(0,true)._test();

        CollectionFactory._test();
        ListSetProcessor._test();
        new MapProcessor()._test();

    }

}