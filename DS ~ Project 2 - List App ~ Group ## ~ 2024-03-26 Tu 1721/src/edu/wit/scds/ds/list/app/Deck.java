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


package edu.wit.scds.ds.list.app;

import java.util.Collections ;

/**
 * Representation of a deck of cards
 *
 * @author Kaleb Maloof // TODO
 *
 * @version 1.0.0 2024-03-26 Initial implementation
 */
public class Deck extends Pile
	{
	// TODO implement this
	/**
	 * Instantiate a Deck of Cards
	 */
	public Deck() 
	    {
	    super();
	    for(Rank rank: Rank.values()) 
	        {
	            for(Suit suit: Suit.values()) 
	                {
	                    this.cards.add(new Card(suit,rank));
	                }
	        }
	    
	    }
	
	
	/**
	 * Draws the top card from the deck
	 * 
	 * @return the top card from the deck. If the deck is empty, this method will throw an exception.
	 */
	public Card draw()
    	{
    	return this.disCard();
    	}
	
	/**
	 * Shuffles the cards in the deck
	 */
	public void shuffle()
    	{
    	    Collections.shuffle( this.cards );
    	}

	/**
     * (optional) test driver
     * 
     * @param args
     *     -unused-
     */
	public static void main( String[] args )
		{
        // OPTIONAL for testing and debugging
		
		Deck deck = new Deck();
		for(Card card: deck.getCards())
		    {
		        card.reveal();
		    }
		System.out.println(deck.toString());
		
		deck.shuffle();
		
		System.out.println(deck.toString());
		}	// end main()

	}	// end class Deck
