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
 * Representation of a player
 *
 * @author Ibukunoulwa Folajimi    // TODO
 *
 * @version 1.0.0 2024-03-26 Initial implementation
 */
public class Player
    {
    // TODO implement this
    private String name;
    private List<Hand> hands;
    private List<Integer> bets;
    private int balance;
    private boolean splitHand;
    
    /**
     * Constructs a new player with an empty hand and a default bet
     * @param playerName The name of the player
     */
    public Player(String playerName) 
        {
        this.name = playerName;
        this.hands = new ArrayList<>();
        // Initialize with one hand
        this.hands.add(new Hand());
        this.bets = new ArrayList<>();
        // Initialize with a default bet of 0
        this.bets.add(0);
        
        this.splitHand = false;
        this.balance = 2500;
        }
    /**
     * Adds a card to the beginning hand of the player
     * @param card the card to add to hand
     */
    public void addCard(Card card) 
        {
        card.setFaceUp( true );
        this.hands.get(0).addCard(card);
        }
    /**
     * Adds a card to the specified hand of the player
     * @param handIndex the index of the hand to add a card to
     * @param card the card to add
     */
    public void addCard(int handIndex, Card card) 
        {
        validateHandIndex(handIndex);
        card.setFaceUp( true );
        this.hands.get(handIndex).addCard(card);
        }
    /**
     * Allows the player to add a card (hit) for a specified hand
     *
     * @param handIndex The index of the hand that will receive the new card.
     * @param card the card to add to a hand
     * @throws IndexOutOfBoundsException if the specified handIndex does not exist
     */
    public void hit(int handIndex, Card card) 
        {
        validateHandIndex(handIndex);
        addCard(handIndex, card);
        }
    
    /**
     * Places a bet for a specified hand
     * @param handIndex the index of the hand to place a bet on
     * @param amount the amount of the bet
     */
    public void placeBet(int handIndex, int amount) 
        {
        validateHandIndex(handIndex);
        validateBetAmount(amount);
        this.bets.set(handIndex, amount);
        this.balance -= amount;
        }
    
    /**
     * Doubles the bet for a specified hand and adds only one card to it
     * This represents the double down action
     * @param handIndex the index of the hand to double down on
     * @param card the card to add as part of doubling down
     */
    public void doubleDown(int handIndex, Card card) 
        {
        validateHandIndex(handIndex);
        Hand hand = this.hands.get(handIndex);
        hand.addCard(card);
        int currentBet = this.bets.get(handIndex);
        validateBetAmount(currentBet);
        this.bets.set(handIndex, currentBet * 2); // Double the bet
        this.balance -= currentBet;  
        }
    
    
    
    /**
     * Checks if the player's first hand can be split
     * A hand can be split if it consists of exactly two cards of the same rank
     *
     * @return True if the hand can be split, false otherwise
     */
    public boolean canSplit() 
        {
        if (this.hands.size() == 1) 
            { // Only consider splitting the first hand
            List<Card> cards = this.hands.get(0).getCards();
            if (cards.size() == 2 && cards.get(0).getRank().getPoints() == cards.get(1).getRank().getPoints()) 
                {
                return true;
                }
            }
        return false;
        }
    
    /**
     * Splits the first hand of the player into two hands, if possible
     * 
     * @return true if the hand was successfully split; false otherwise
     */
    public boolean splitHand() 
        {
        if (canSplit()) 
            {
            Card cardToMove = this.hands.get(0).disCard();
            if (cardToMove != null)
                {
                Hand newHand = new Hand();
                newHand.addCard( cardToMove );
                this.hands.add( newHand );
                this.bets.add(0);
                setSplitHand(true);
                return true;
                }
            return false;
            }
        return false;
        }
    
    /**
     * Resets the player's hands and prepares for a new round
     * 
     * @return A list of Card objects that were discarded from the player's hands
     */
    public List<Card> resetHand() 
        {
        List<Card> disCardedCards = new ArrayList<>();
        for(Hand hand: this.hands)
            {
            disCardedCards.addAll( hand.clearHand());
            }
        this.hands.clear();
        this.hands.add(new Hand());
        this.bets.clear();
        this.bets.add(0);
        setSplitHand(false );
        return disCardedCards;
        }
    
    /**
     * Checks if any of the player's hands have busted
     *
     * @return true if at least one hand has busted, false otherwise
     */
    public boolean hasBust() 
        {
        for (Hand hand : this.hands) 
            {
            if (hand.isBusted()) 
                {
                return true;
                }
            }
        return false;
        }
    /**
     * Increases the player's balance by a specified amount.
     * Used to add winnings to the player's balance.
     *
     * @param amount the amount to be added to the player's balance. 
     *                  Assumes value should be positive. 
     */
    public void increaseBalance(int amount) 
        {
        this.balance += amount;
        }
    /**
     * Decrease the player's balance by a specified amount.
     * Used to deduct the from the player's balance if they lost the bet.
     *
     * @param amount the amount to be subtracted from the player's balance. 
     *                  Assumes value should be positive. 
     */
    public void decreaseBalance(int amount) 
        {
        this.balance-= amount;
        }
    
    /**
     * Return the current balance of the player
     * 
     * @return the balance of the player
     */
    public int getBalance()
        {
        return this.balance;
        }
    
    /**
     * Get a particular hand of the player
     * 
     * @param handIndex the index of the hand in {@code hands}
     * 
     * @return the particular hand
     */
    public Hand getHand(int handIndex)
        {
        validateHandIndex(handIndex);
        return this.hands.get( handIndex );
        }
    
    /**
     * Get all hands of the player
     * 
     * 
     * @return the list of hands the player has
     */
    public List<Hand> getHands()
        {
        return this.hands;
        }
    
    
    /**
     * Gets a particular hand's bet amount
     * 
     * @param handIndex the index of the hand in {@code hands}
     * 
     * @return the particular hand's bet amount
     */
    public int getBet(int handIndex)
        {
        validateHandIndex(handIndex);
        return this.bets.get( handIndex );
        }
    
    /**
     * Return whether or not the player split their hand
     * 
     * @return true if the player split their hand, false otherwise
     */
    public boolean hasSplitHand()
        {
         return this.splitHand ;

        }
    
    /**
     * Sets the flag indicating whether the player has split their hand.
     * 
     * @param hasSplitHand A boolean indicating the player's current split hand state
     */
    public void setSplitHand(boolean hasSplitHand)
        {
         this.splitHand = hasSplitHand;

        }
    
    
    @Override
    public String toString()
        {
        return this.hands.toString();
        }
    
    /**
     * Get the name of the player
     * 
     * @return name of the player
     */
    public String getName()
        {
            return this.name ;

        }
    /**
     * Validates the specified hand index.
     * 
     * Ensures the hand index is within the bounds of the player's hands list.
     *
     * @param handIndex the index of the hand to validate
     * @throws IndexOutOfBoundsException If the hand index is not valid
     */
    private void validateHandIndex( int handIndex)
        {
        if (handIndex >= this.hands.size() || handIndex < 0)
            {
            throw new IndexOutOfBoundsException("Invalid hand index");
            }
        }
    
    /**
     * Validates the bet amount against the player's current balance.
     * Ensures that the player has enough balance to place the bet.
     *
     * @param amount the bet amount to validate
     * @throws IllegalArgumentException If the bet amount exceeds the player's current balance
     */
    private void validateBetAmount( int amount)
        {
        if (amount > this.balance)
            {
            throw new IllegalArgumentException("Invaild action. Insuffient balance");
            }
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

    }	// end class Player
