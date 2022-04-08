/*
#      #    #######    ########   #######   #          #######   ##      #    #########
#     #        #       #          #         #             #      # #     #    #
#    #         #       #          #         #             #      #  #    #    #
####           #       #####      #######   #             #      #   #   #    #    ####
#    #         #       #                #   #             #      #    #  #    #       #
#     #        #       #                #   #             #      #     # #    #       #
#      #    ########   ########   #######   ########   #######   #      ##    #########
*/

package Translator;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import BinarySearchTree.*;

public class Translator {

    private FileReader fileReader;
    private StringComparator comparator;
    private BinarySearchTree<String, String> englishSpanish;
    private BinarySearchTree<String, String> spanishEnglish;
    private BinarySearchTree<String, String> frenchSpanish;
    private BinarySearchTree<String, String> spanishFrench;

    public Translator(){
        comparator = new StringComparator();
        englishSpanish = new BinarySearchTree<String, String>(comparator);
        spanishEnglish = new BinarySearchTree<String,String>(comparator);
        frenchSpanish = new BinarySearchTree<String,String>(comparator);
        spanishFrench = new BinarySearchTree<String,String>(comparator);
    }

    public void readDictionary() throws FileNotFoundException{
        ArrayList<String[]> words = new ArrayList<String[]>();
        fileReader = new FileReader("diccionario.txt");
        words = fileReader.getElements("[,]");
        for (String[] word: words)
            addDictionary(word);
    }

    public String translate() throws FileNotFoundException{
        String newSentence = "";
        ArrayList<String[]> sentences = new ArrayList<String[]>();
        fileReader = new FileReader("texto.txt");
        sentences = fileReader.getElements("[ ]");

        for (String[] words: sentences)
            for (String word: words){
                if (englishSpanish.find(word.toLowerCase()) != null)
                    newSentence += englishSpanish.find(word);
                else if(frenchSpanish.find(word.toLowerCase()) != null)
                    newSentence += frenchSpanish.find(word);
                else
                    newSentence += "*" + word + "*";
            }
        
        return newSentence;
    }

    public void addDictionary(String[] words){
        englishSpanish.insert(words[0].toLowerCase(), words[1].toLowerCase());
        spanishEnglish.insert(words[1].toLowerCase(), words[0].toLowerCase());
        frenchSpanish.insert(words[2].toLowerCase(), words[1].toLowerCase());
        spanishFrench.insert(words[1].toLowerCase(), words[2].toLowerCase());
    }

    public String showDictionary(){
        String wordsDictionary = "";
        ArrayList<String> words = spanishEnglish.inOrder();

        for(String word: words){
            wordsDictionary += word + "-";
            wordsDictionary += englishSpanish.find(word) + "-";
            wordsDictionary += spanishFrench.find(englishSpanish.find(word)) + "\n";
        }

        return wordsDictionary;
    }

    private String[] searchWord(String word){
        String wordEnglish = "";
        String wordSpanish = "";
        String wordFrench = "";

        if(spanishEnglish.find(word) != null){ //Word in spanish
            wordEnglish = spanishEnglish.find(word);
            wordSpanish = word;
            wordFrench = spanishFrench.find(word);
        }
        else if (englishSpanish.find(word) != null){ //Word in english
            wordEnglish = word;
            wordSpanish = englishSpanish.find(word);
            wordFrench = spanishFrench.find(englishSpanish.find(word));
        }
        else if (frenchSpanish.find(word) != null){ //Word in french
            wordEnglish = spanishEnglish.find(frenchSpanish.find(word));
            wordSpanish = frenchSpanish.find(word);
            wordFrench = word;
        }

        String[] words = {wordEnglish, wordSpanish, wordFrench};
        return words;
    }

    public void deleteWord(String word){
        String[] words = searchWord(word);
        spanishEnglish.delete(words[1]);
        spanishFrench.delete(words[1]);
        englishSpanish.delete(words[0]);
        frenchSpanish.delete(words[2]);
    }

    public void modifyWord(String[] words){
        for(String word: words)
            deleteWord(word);
        addDictionary(words);
    }
}
