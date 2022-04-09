/*
#      #    #######    ########   #######   #          #######   ##      #    #########
#     #        #       #          #         #             #      # #     #    #
#    #         #       #          #         #             #      #  #    #    #
####           #       #####      #######   #             #      #   #   #    #    ####
#    #         #       #                #   #             #      #    #  #    #       #
#     #        #       #                #   #             #      #     # #    #       #
#      #    ########   ########   #######   ########   #######   #      ##    #########
*/


import java.util.InputMismatchException;
import java.util.Scanner;

/*********************************************************************
 * A View class that allows the comunication between the user. It 
 * serves like an I/O system.
 * <p>
 * @version 08/04/2022
 * @TheKiesling
*********************************************************************/
public class View {
    //---------------------------PROPERTIES---------------------------
    private Scanner scan;

    //---------------------------METHODS------------------------------
    /*****************************************************************
     * instance the scanner
     */
    public View(){
        scan = new Scanner(System.in);
    }
    //****************************************************************

    /*****************************************************************
     * catch errors, and shows a message that share that error
     * @param s
     */
    public void error(String s){
        System.out.println(s); 
    }
    //****************************************************************

    /*****************************************************************
     * print a welcome message
     */
    public void welcome(){
        System.out.println("\nHi, Welcome to the Google Translator");
    }
    //****************************************************************

    /*****************************************************************
     * ask the user what action wants to do
     * @return the number of the option's instance
     * @throws InputMismatchException
     * @throws Exception
     */
    public int menu() throws InputMismatchException, Exception{
        int option = -1;
        boolean flag = false;

        try{
            //Option's Menu
            System.out.println("\n\nChoose the option of the action that you want to do");
            System.out.println("\n\n1. Show Dictionary");
            System.out.println("2. Add a word in the Dictionary");
            System.out.println("3. Delete a word in the Dictionary");
            System.out.println("4. Modify a word in the Dictionary");
            System.out.println("5. Translate a file");
            System.out.println("6. Leave\n\n");

            while (!flag){ //Evaluate if the option is allowed
                option = Integer.parseInt(scan.nextLine());
                System.out.println();
                if (option > 0 && option <= 6) //OK option
                    flag = true;
                else{ 
                    System.out.println("ERROR: Choose a correct option"); 
                }
            }
        } catch (InputMismatchException e){ 
            String s = "ERROR: InputMismatch " + option + ": " + e.toString(); 
            throw new InputMismatchException(s);
        } catch (Exception e){ 
            String s = "ERROR: scan.nextInt() " +  option + ": " + e.toString();
            throw new Exception(s);
        }
        return option;
    }
    //****************************************************************

    /*****************************************************************
     * read the translate of a word
     * @return an array with the word in 3 languages
     */
    public String[] newWord(){
        String[] word = new String[3];

        System.out.println("Insert the word in English");
        String wordEnglish = scan.nextLine();
        System.out.println("Insert the word in Spanish");
        String wordSpanish = scan.nextLine();
        System.out.println("Insert the word in French");
        String wordFrench = scan.nextLine();

        word[0] = wordEnglish.toLowerCase().trim(); 
        word[1] = wordSpanish.toLowerCase().trim(); 
        word[2] = wordFrench.toLowerCase().trim();

        System.out.println(); 
        return word;
    }
    //****************************************************************

    /*****************************************************************
     * input of a word that the user wants to delete
     * @return the word to delete
     */
    public String deleteWord(){
        String word = "";
        System.out.println("Insert the word to delete");
        word = scan.nextLine().toLowerCase().trim();
        return word;
    }
    //****************************************************************

    /*****************************************************************
     * print a text
     * @param text
     */
    public void output(String text){
        System.out.println(text);
    }
    //****************************************************************

    /*****************************************************************
     * print a leave message
     */
    public void exit(){
        System.out.println("\nGoodbye :)");
    }
    //****************************************************************
}
