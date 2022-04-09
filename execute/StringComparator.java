/*
#      #    #######    ########   #######   #          #######   ##      #    #########
#     #        #       #          #         #             #      # #     #    #
#    #         #       #          #         #             #      #  #    #    #
####           #       #####      #######   #             #      #   #   #    #    ####
#    #         #       #                #   #             #      #    #  #    #       #
#     #        #       #                #   #             #      #     # #    #       #
#      #    ########   ########   #######   ########   #######   #      ##    #########
*/

import java.util.Comparator;

/*********************************************************************
 * A comparison function, which imposes a total ordering on Strings. 
 * Comparators can be passed to a sort method (such as Collections.sort 
 * or Arrays.sort) to allow precise control over the sort order.
 * <p>
 * @version 05/04/2022
 * @TheKiesling
*********************************************************************/
public class StringComparator implements Comparator<String>{

    //---------------------------METHODS------------------------------
    /*****************************************************************
     * Compares its two arguments for order. 
     * Returns a negative integer, zero, or a positive integer 
     * as the first argument is less than, equal to, or greater than 
     * the second.
     * @param _string1
     * @param _string2
     * @return a negative integer, zero, or a positive integer 
     */
    @Override
    public int compare(String _string1, String _string2) {
        return _string1.compareTo(_string2);
    }
    //****************************************************************
}