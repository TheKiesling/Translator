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

/*********************************************************************
 * A class that operates all the actions of the program. It uses BST's
 * package to organizes the words at the dictionary. Also modifies it 
 * when the user wants to change some state of that dictionary
 * <p>
 * @version 08/04/2022
 * @TheKiesling
*********************************************************************/
public class Translator {

    //---------------------------PROPERTIES---------------------------
    private FileReader fileReader;

    //The first language is the key, and the second is the value
    private BinarySearchTree<String, String> englishSpanish;
    private BinarySearchTree<String, String> spanishEnglish;
    private BinarySearchTree<String, String> frenchSpanish;
    private BinarySearchTree<String, String> spanishFrench;

    //---------------------------METHODS------------------------------
    /*****************************************************************
     * Constructs a translator to instances the BST
     */
    public Translator(){
        StringComparator comparator = new StringComparator();
        englishSpanish = new BinarySearchTree<String, String>(comparator);
        spanishEnglish = new BinarySearchTree<String,String>(comparator);
        frenchSpanish = new BinarySearchTree<String,String>(comparator);
        spanishFrench = new BinarySearchTree<String,String>(comparator);
    }
    //****************************************************************

    /*****************************************************************
     * Reads all the words in a file and adds in BST
     * @throws FileNotFoundException
     */
    public void readDictionary() throws FileNotFoundException{
        ArrayList<String[]> words = new ArrayList<String[]>();
        fileReader = new FileReader("diccionario.txt");
        words = fileReader.getElements("[,]");
        for (String[] word: words)
            addDictionary(word);
    }
    //****************************************************************

    /*****************************************************************
     * Translates a file that contains sentences in an indeterminate
     * lenguage. The translation's lenguage to return is spanish
     * @return the translation of each sentence
     * @throws FileNotFoundException
     */
    public String translate() throws FileNotFoundException{
        String newSentence = "";
        ArrayList<String[]> sentences = new ArrayList<String[]>();
        fileReader = new FileReader("texto.txt");
        sentences = fileReader.getElements("[ ]");

        for (String[] sentence: sentences){ //For each sentence
            for (String word: sentence){ //For each word in the sentence
                if (englishSpanish.find(word.toLowerCase()) != null)
                    newSentence += englishSpanish.find(word) + " ";
                else if(frenchSpanish.find(word.toLowerCase()) != null)
                    newSentence += frenchSpanish.find(word) + " ";
                else
                    newSentence += "*" + word + "* "; //Not in the dictionary
            }
            newSentence += "\n";
        }

        return newSentence;
    }
    //****************************************************************

    /*****************************************************************
     * Adds the word and it translation to a specific BST
     * @param words
     */
    public void addDictionary(String[] words){
        englishSpanish.insert(words[0].toLowerCase(), words[1].toLowerCase());
        spanishEnglish.insert(words[1].toLowerCase(), words[0].toLowerCase());
        frenchSpanish.insert(words[2].toLowerCase(), words[1].toLowerCase());
        spanishFrench.insert(words[1].toLowerCase(), words[2].toLowerCase());
    }
    //****************************************************************

    /*****************************************************************
     * Shows all the words in the Dictionary in order (english) 
     * @return all the words in the dictionary
     */
    public String showDictionary(){
        String wordsDictionary = "";
        ArrayList<String> words = englishSpanish.inOrder(); //In order english

        for(String word: words){
            wordsDictionary += spanishEnglish.find(word) + "-";
            wordsDictionary += word + "-";
            wordsDictionary += spanishFrench.find(word) + "\n";
        }

        return wordsDictionary;
    }
    //****************************************************************

    /*****************************************************************
     * Searchs in the Dictionary the word and return that word with
     * it translations.
     * @param word
     * @return the word and it translations
     */
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
    //****************************************************************

    /*****************************************************************
     * Searches the word's translations and delete them into the BST
     * @param word
     */
    public void deleteWord(String word){
        String[] words = searchWord(word);

        spanishEnglish.delete(words[1]);
        spanishFrench.delete(words[1]);
        englishSpanish.delete(words[0]);
        frenchSpanish.delete(words[2]);
    }
    //****************************************************************

    /*****************************************************************
     * Deletes all the words on the BST, and then add thse words
     * (first delete, then add)
     * @param words
     */
    public void modifyWord(String[] words){
        for(String word: words)
            deleteWord(word);
        addDictionary(words);
    }
    //****************************************************************
}
