/*
#      #    #######    ########   #######   #          #######   ##      #    #########
#     #        #       #          #         #             #      # #     #    #
#    #         #       #          #         #             #      #  #    #    #
####           #       #####      #######   #             #      #   #   #    #    ####
#    #         #       #                #   #             #      #    #  #    #       #
#     #        #       #                #   #             #      #     # #    #       #
#      #    ########   ########   #######   ########   #######   #      ##    #########
*/

package Association;
import java.util.Map;

/*********************************************************************
 * A class implementing a key-value pair.  This class associates an 
 * immutable key with a mutable value.  Used in many other structures.
 * <p>
 * SOURCE CODE BY: http://www.cs.williams.edu/JavaStructures/doc/structure5/structure5/Association.html 
 * @version 03/04/2022
*********************************************************************/
public class Association<K,V> implements Map.Entry<K,V>
{
    //---------------------------PROPERTIES---------------------------
    protected K theKey; // the key of the key-value pair
    protected V theValue; // the value of the key-value pair

    //---------------------------METHODS------------------------------
    /*****************************************************************
     * Constructs a pair from a key and value.
     * @pre key is non-null
     * @post constructs a key-value pair
     * @param key A non-null object.
     * @param value A (possibly null) object.
     */
    public Association(K key, V value){
        assert (key != null);
        this.theKey = key;
        this.theValue = value;
    }
    //****************************************************************

    /*****************************************************************
     * Constructs a pair from a key; value is null.
     * @pre key is non-null
     * @post constructs a key-value pair; value is null
     * @param key A non-null key value.
     */
    public Association(K key){
        this(key,null);
    }
    //****************************************************************

    /*****************************************************************
     * Standard comparison function.  Comparison based on keys only.
     * @pre other is non-null Association
     * @post returns true iff the keys are equal
     * @param other Another association.
     * @return true iff the keys are equal.
     */
    public boolean equals(Object o){
        Association<K,V> _object1 = (Association<K,V>) o;
        return getKey().equals(_object1.getKey());
    }
    //****************************************************************
    
    /*****************************************************************
     * Standard hashcode function.
     * @post return hash code association with this association
     * @return A hash code for association.
     */
    public int hashCode(){
        return theKey.hashCode();
    }
    //****************************************************************
    
    /*****************************************************************
     * Fetch value from association.  May return null.
     * @post returns value from association
     * @return The value field of the association.
     */
    public V getValue(){
        return this.theValue;
    }
    //****************************************************************

    /*****************************************************************
     * Fetch key from association.  Should not return null.
     * @post returns key from association
     * @return Key of the key-value pair.
     */
    public K getKey(){
        return this.theKey;
    }
    //****************************************************************

    /*****************************************************************
     * Sets the value of the key-value pair.
     * @post sets association's value to value
     * @param value The new value.
     */
    public V setValue(V value){
        this.theValue = value;
        return null;
    }
    //****************************************************************

    /*****************************************************************
     * Standard string representation of an association.
     * @post returns string representation
     * @return String representing key-value pair.
     */
    public String toString(){
        return "-" + getKey() + ": " + getValue() + "\n";
    }
    //****************************************************************
}