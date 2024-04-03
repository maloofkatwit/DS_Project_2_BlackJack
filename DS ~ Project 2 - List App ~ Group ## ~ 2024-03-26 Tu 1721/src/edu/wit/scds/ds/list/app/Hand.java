/* @formatter:off
 *
 * Dave Rosenberg
 * Comp 2000 - Data Structures
 * Lab: List application - card game
 * Spring, 2024
 * 
 * Usage restrictions:
 * 
 * You may use this code for exploration, experimentation, and furthering your
 * learning for this course. You may not use this code for any other
 * assignments, in my course or elsewhere, without explicit permission, in
 * advance, from myself (and the instructor of any other course).
 * 
 * Further, you may not post (including in a public repository such as on github)
 * nor otherwise share this code with anyone other than current students in my 
 * sections of this course. Violation of these usage restrictions will be considered 
 * a violation of the Wentworth Institute of Technology Academic Honesty Policy.
 *
 * Do not remove this notice.
 *
 * @formatter:on
 */


package edu.wit.scds.ds.list.app ;

/**
 * Representation of a hand of cards
 *
 * @author Your Name    // TODO
 *
 * @version 1.0.0 2024-03-26 Initial implementation
 */
public class Hand extends Pile
    {
    // TODO implement this
    /**
     * Constructs an empty hand.
     */
    public Hand() {
        super();
    }
    
    /**
     * Calculates the total score of the hand in Blackjack,
     * where face cards are 10 and Aces can be 1 or 11.
     *
     * @return The total score of the hand.
     */
    public int calculateScore() 
        {
        // TODO
        return 0;
        }
    /**
     * Sets all cards in the collection to be face up
     */
    public void setAllFaceUP()
        {
        //TODO
        }
    
    

    /**
     * Checks if the hand is busted (i.e., total score exceeds 21).
     *
     * @return True if the hand is busted, false otherwise.
     */
    public boolean isBusted() {
        //TODO
        return false;
    }
    
    /**
     * Clear the hand of cards
     * 
     * @return the list of cards cleared from the hand
     */
    public List<Card> clearHand() 
        {
        return new ArrayList<>();
        }

    /**
     * (optional) test driver
     *
     * @param args
     *     -unused-
     */
    public static void main( final String[] args )
        {
        // OPTIONAL for testing and debugging

        }	// end main()

    }	// end class Hand
