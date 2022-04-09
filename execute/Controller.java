/*
#      #    #######    ########   #######   #          #######   ##      #    #########
#     #        #       #          #         #             #      # #     #    #
#    #         #       #          #         #             #      #  #    #    #
####           #       #####      #######   #             #      #   #   #    #    ####
#    #         #       #                #   #             #      #    #  #    #       #
#     #        #       #                #   #             #      #     # #    #       #
#      #    ########   ########   #######   ########   #######   #      ##    #########
*/

/*********************************************************************
 * A Controller class that only has 1 method: Main. This class controls 
 * the model (Translator) and relates with the view. So the solution
 * uses the MVC pattern.
 * <p>
 * @version 08/04/2022
 * @TheKiesling
*********************************************************************/
public class Controller {
    
    //---------------------------METHODS------------------------------
    /*****************************************************************
     * Command line interface
     * @param args - The command line parameters.
     */
    public static void main(String[] args){
        View view = new View();
        try{
            view.welcome();
            Translator translator = new Translator();
            translator.readDictionary(); //Read all the words to add
            int option = -1;

            while(option != 6){
                String result = "";
                option = view.menu();

                if (option == 1){ //Show Dictionary
                    result = translator.showDictionary();
                    view.output(result);
                }
                else if (option == 2){ //Add a Word
                    String[] words = view.newWord();
                    translator.addDictionary(words);
                } 
                else if(option == 3){ //Delete a word
                    String word = view.deleteWord();
                    translator.deleteWord(word);
                }
                else if(option == 4){ //Modify a word
                    String[] words = view.newWord();
                    translator.modifyWord(words);
                }
                else if(option == 5){ //Translate a file
                    result = translator.translate();
                    view.output(result);
                }
                else if (option == 6) //Exit
                    view.exit();             
            }

        } catch(Exception e){
            String s = "ERROR: " + e.getMessage();
            view.error(s);
        }
    }
    //****************************************************************
}
