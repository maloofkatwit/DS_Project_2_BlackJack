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

import java.util.ArrayList ;
import java.util.List ;

/**
 * Representation of a pile of card
 * <p>
 * the bottom card is at position 0
 *
 * @author Kaleb Maloof // DONE
 *
 * @version 1.0.0 2024-03-26 Initial implementation
 */
public class Pile
    {

    // data fields
    /** the list of cards - directly accessible by subclasses */
    protected List<Card> cards ;    // instantiate an ArrayList or LinkedList in the constructor


    // DONE implement this
   
    /**
     * Constructs an empty pile of cards
     */
    public Pile()
        {
        this.cards = new ArrayList<>();
        }
    
    /**
     * Adds a card to the pile
     * 
     * @param aCard the card to be added to the pile
     */
    public void addCard(Card aCard)
        {
        this.cards.add( aCard );
        }
    
    /**
     * Adds a list of cards to the pile
     * 
     * @param listOfCards the cards to be added to the pile
     */
    public void addCards(List<Card> listOfCards)
        {
        for(Card card: listOfCards)
            {
              this.cards.add(card);
            }
        }
    /**
     * Removes and returns the first card from the pile
     * 
     * @return the card that was removed from the pile. If the hand is empty, this method will throw an IndexOutOfBoundsException
     * 
     */
    public Card disCard()
        {
        // remove from front end of the list
        return this.cards.remove( 0 );
        }

    

    /**
     * Returns a list of cards in the pile
     *
     * @return the list of cards in the pile
     */
    public List<Card> getCards() 
        {
        return this.cards;
        }

    @Override
    public String toString()            // debugging aid
        {
        return this.cards.toString() ;

        }	// end toString()


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

    }	// end class Pile
