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
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/*********************************************************************
 * A file reader that analyzes a file and separated their elements
 * and add their in a dinamic array. The array could be called anytime 
 * to obtain the data on the file.
 * <p>
 * @version 05/04/2022
 * @TheKiesling
 ********************************************************************/
public class FileReader {

    //---------------------------PROPERTIES---------------------------
    private File file;
    private Scanner reader;
    private ArrayList<String[]> elements;

    //---------------------------METHODS------------------------------
    /*****************************************************************
     * Constructs a File reader
     * @param fileName
     * @throws FileNotFoundException
     */
    public FileReader(String fileName) throws FileNotFoundException{
        elements = new ArrayList<String[]>();
        file = new File(fileName);
        reader = new Scanner(file);
    }
    //****************************************************************

    /*****************************************************************
     * Read a file that contains data and separates it by a separator.
     * Then add each element of the data to an ArrayList
     * @param separator
     */
    private void read(String separator){
        while(reader.hasNextLine()){
            String[] element = reader.nextLine().split(separator);
            elements.add(element);
        }
        reader.close();
    }
    //****************************************************************

    /*****************************************************************
     * Read the file and returns all the elements it contains
     * @param separator
     * @return all the elements in the file
     */
    public ArrayList<String[]> getElements(String separator){
        read(separator);
        return elements;
    }
    //****************************************************************
}
