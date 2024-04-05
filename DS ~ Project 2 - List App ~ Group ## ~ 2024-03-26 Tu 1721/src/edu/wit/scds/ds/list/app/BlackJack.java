
package edu.wit.scds.ds.list.app ;

/**
 * @author Tyger Maguire
 * 
 * @version 1.0.0 2024-04-04 Initial implementation
 */

import java.util.List ;
import java.util.Scanner ;
import java.util.ArrayList ;

public class BlackJack
    {

    /**
     * 
     */

    /**
     * @param args
     */
    public static void main( String[] args )
        {
        List<Player> pl = new ArrayList<Player>() ;
        Deck gameDeck = new Deck() ;
        Player dealer = new Player( "Dealer" ) ;

        Scanner Game = new Scanner( System.in ) ;  // Create a Scanner object
        System.out.println( "How many will be playing today: " ) ;
        char playerNumber = 'N' ;
        boolean firstTime = true ;
        while ( ! ( ( playerNumber >= '0' ) && ( playerNumber <= ( '4' ) ) ) )
            {
            if ( !firstTime )
                {
                System.out.println( "Invalid player input" ) ;

                }

            try
                {
                playerNumber = Game.nextLine().charAt( 0 ) ;

                }
            catch ( Exception e )
                {
                playerNumber = 'n' ;

                }

            firstTime = false ;

            }

        for ( int i = 0 ; i < playerNumber - '0' ; i++ )
            {
            System.out.println( "please input a player name" ) ;
            String playerName = Game.nextLine() ;

            pl.add( new Player( playerName + " " ) ) ;

            }

        boolean gamePlay = true ;
        Card hiddenCard = null ;
        while ( gamePlay )
            {
            gameDeck.shuffle() ;
            for ( int i = 0 ; i < pl.size() ; i++ )
                {
                firstTime = true ;
                System.out.println( pl.get( i ).getName() + " please make your bet." ) ;
                int playerBet = 0 ;
                while ( playerBet < 1 )
                    {
                    if ( !firstTime )
                        {
                        System.out.println( "Invalid player input" ) ;

                        }

                    try
                        {
                        playerBet = Integer.parseInt( Game.nextLine() ) ;

                        }
                    catch ( Exception e )
                        {
                        playerBet = -1 ;

                        }

                    firstTime = false ;

                    }

                pl.get( i ).placeBet( 0, playerBet ) ;

                }

            System.out.println( pl.size() ) ;
            for ( int i = 0 ; i < pl.size() ; i++ )
                {
                pl.get( i ).addCard( 0, gameDeck.draw() ) ;

                }

            dealer.addCard( gameDeck.draw() ) ;
            for ( int i = 0 ; i < pl.size() ; i++ )
                {
                pl.get( i ).addCard( 0, gameDeck.draw() ) ;

                }

            hiddenCard = ( gameDeck.draw() ) ;
            for ( int i = 0 ; i < pl.size() ; i++ )
                {
                System.out.println( pl.get( i ).getName() + " hand: " + pl.get( i ).toString() ) ;

                }

            System.out.println( "Dealer hand: " + dealer.toString() ) ;

            for ( int i = 0 ; i < pl.size() ; i++ )
                {
                boolean doubled = false ;
                
                if ( pl.get( i ).getHand( 0 ).calculateScore() == 21 )
                    {
                    System.out.println( "congradualtions " + pl.get( i ).getName() +
                                        " on your black jack you have been rewarded with " +
                                        pl.get( i ).getBet( 0 ) * 1.5 + " points." ) ;
                    pl.get( i ).increaseBalance( (int) ( pl.get( i ).getBet( 0 ) * 1.5 ) ) ;

                    }

                else if ( pl.get( i ).canSplit() )
                    {
                    System.out.println( pl.get( i ).getName() + ", would you like to split?" ) ;
                    String answer = "T" ;
                    firstTime = true ;
                    while ( !answer.equals( "y" ) && !answer.equals( "n" ) )
                        {
                        if ( !firstTime )
                            {
                            System.out.println( "Invalid player input" ) ;

                            }

                        answer = Game.nextLine() ;
                        firstTime = false ;

                        }

                    if ( answer.equals(  "y") )
                        {
                        pl.get( i ).splitHand() ;
                        pl.get( i ).placeBet( 0, pl.get( i ).getBet( 0 ) ) ;

                        }

                    }

                else if ( pl.get( i ).getHand( 0 ).calculateScore() == 9 ||
                          pl.get( i ).getHand( 0 ).calculateScore() == 10 ||
                          pl.get( i ).getHand( 0 ).calculateScore() == 11 )
                    {
                    System.out.println( pl.get( i ).getName() +
                                        ", would you like to double down?" ) ;
                    String answer = "T" ;
                    firstTime = true ;
                    while ( !answer.equals( "y" ) && !answer.equals( "n" ) )
                        {
                        if ( !firstTime )
                            {
                            System.out.println( "Invalid player input" ) ;

                            }

                        answer = Game.nextLine() ;

                        }

                    if ( answer.equals( "y" ) )
                        {
                        pl.get( i ).doubleDown( 0, gameDeck.draw() ) ;
                        doubled = true ;

                        }

                    }
                for ( int ii = 0 ; ii < pl.get( i ).getHands().size() ; ii++ )
                    {
                    //System.out.println(pl.get( i ).getHands().size() );

                    firstTime = true ;
                    boolean turn = true ;

                    while ( turn )
                        {
                        System.out.println( pl.get( i ).getHand( ii ).toString() ) ;

                        if ( firstTime && pl.get( i ).getHand( i ).calculateScore() == 21 )
                            {

                            turn = false ;

                            }
                        else if ( doubled == true )
                            {
                            turn = false ;

                            }
                        else if ( pl.get( i ).getHand( ii ).isBusted() )
                            {
                            Syst
                            turn = false ;

                            }
                        else
                            {
                            System.out.println( pl.get( i ).getName() +
                                                ", would you like to hit or stand?" ) ;
                            String answer = "T" ;
                            firstTime = true ;
                            while ( !answer.equals( "hit" ) && !answer.equals( "stand" ) )
                                {
                                if ( !firstTime )
                                    {
                                    System.out.println( "Invalid player input" ) ;

                                    }

                                answer = Game.nextLine() ;

                                }

                            if ( answer.equals ("hit") )
                                {
                                pl.get( i ).hit( ii, gameDeck.draw() ) ;

                                }
                            else if ( answer.equals ("stand") )
                                {
                                turn = false ;

                                }

                            }

                        firstTime = false ;

                        }

                    }

                }

            boolean dealerTurn = true ;
            dealer.addCard( hiddenCard ) ;
            while ( dealerTurn == true )
                {
                System.out.println( dealer.getHand( 0 ).toString() ) ;
                if ( dealer.getHand( 0 ).calculateScore() < 17 )
                    {
                    dealer.hit( 0, gameDeck.draw() ) ;

                    }
                else
                    {
                    dealerTurn = false ;

                    }

                }

            for ( int i = 0 ; i < pl.size() ; i++ )
                {
                for ( int ii = 0 ; ii < pl.get( i ).getHands().size() ; ii++ )
                    {
                    if ( pl.get( i ).getHand( ii ).calculateScore() >
                         dealer.getHand( 0 ).calculateScore() )
                        {
                        if ( pl.get( i ).getHand( ii ).calculateScore() == 21 )
                            {
                            pl.get( i )
                              .increaseBalance( (int) ( pl.get( i ).getBet( ii ) +
                                                        pl.get( i ).getBet( ii ) * 1.5 ) ) ;

                            }
                        else
                            {
                            pl.get( i )
                              .increaseBalance( (int) ( pl.get( i ).getBet( ii ) +
                                                        pl.get( i ).getBet( ii ) ) ) ;

                            }

                        }

                    }

                }

            for ( int i = 0 ; i < pl.size() ; i++ )
                {
                for ( int ii = 0 ; ii < pl.get( i ).getHands().size() ; ii++ )
                    {
                    gameDeck.addCards( pl.get( i ).getHand( ii ).getCards() ) ;

                    }

                }

            gameDeck.addCards( dealer.getHand( 0 ).getCards() ) ;

            for ( int i = pl.size() - 1 ; i >= 0 ; i-- )
                {
                if ( pl.get( i ).getBalance() <= 0 )
                    {
                    pl.remove( i ) ;

                    }

                }

            }

        }

    }

// end class BlackJack