
package edu.wit.scds.ds.list.app ;

import java.util.List ;
import java.util.Scanner ;
import java.util.ArrayList ;

/**
 * @author Tyger Maguire
 * 
 * @version 1.0.0 2024-04-05 Initial implementation
 */
public class BlackJack
    {

    /**
     * @param args
     */
    public static void main( String[] args )
        {
        List<Player> pl = new ArrayList<Player>() ;
        Deck gameDeck = new Deck() ;
        Player dealer = new Player( "Dealer" ) ;

        Scanner game = new Scanner( System.in ) ;  // Create a Scanner object
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
                playerNumber = game.nextLine().charAt( 0 ) ;

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
            String playerName = game.nextLine() ;

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
                        playerBet = Integer.parseInt( game.nextLine() ) ;

                        }
                    catch ( Exception e )
                        {
                        playerBet = -1 ;

                        }

                    firstTime = false ;

                    }

                pl.get( i ).placeBet( 0, playerBet ) ;

                }

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

                        answer = game.nextLine() ;
                        firstTime = false ;

                        }

                    if ( answer.equals( "y" ) )
                        {
                        pl.get( i ).splitHand() ;
                        pl.get( i ).placeBet( 1, pl.get( i ).getBet( 0 ) ) ;

                        }

                    }

                else if ( ( pl.get( i ).getHand( 0 ).calculateScore() == 9 ||
                            pl.get( i ).getHand( 0 ).calculateScore() == 10 ||
                            pl.get( i ).getHand( 0 ).calculateScore() == 11 ) &&
                          pl.get( i ).getBet( 0 ) <= pl.get( i ).getBalance() )
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

                        firstTime = false ;
                        answer = game.nextLine() ;

                        }

                    if ( answer.equals( "y" ) )
                        {
                        Card fCard = gameDeck.draw() ;
                        fCard.setFaceUp( true ) ;
                        pl.get( i ).doubleDown( 0, fCard ) ;
                        doubled = true ;

                        }

                    }

                for ( int ii = 0 ; ii < pl.get( i ).getHands().size() ; ii++ )
                    {
                    // System.out.println(pl.get( i ).getHands().size() );

                    firstTime = true ;
                    boolean turn = true ;

                    while ( turn )
                        {
                        System.out.println( pl.get( i ).getName() + ": " +
                                            pl.get( i ).getHand( ii ).toString() ) ;

                        if ( firstTime && pl.get( i ).getHand( ii ).calculateScore() == 21 )
                            {

                            turn = false ;

                            }
                        else if ( doubled )
                            {
                            turn = false ;

                            }
                        else if ( pl.get( i ).getHand( ii ).isBusted() )
                            {
                            System.out.println( pl.get( i ).getName() + " has busted" ) ;
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

                                answer = game.nextLine() ;
                                firstTime=false;
                                }

                            if ( answer.equals( "hit" ) )
                                {
                                pl.get( i ).hit( ii, gameDeck.draw() ) ;

                                }
                            else if ( answer.equals( "stand" ) )
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
            while ( dealerTurn)
                {
                System.out.println( "dealer hand: " + dealer.getHand( 0 ).toString() ) ;
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
                    if ( ( pl.get( i ).getHand( ii ).calculateScore() >
                           dealer.getHand( 0 ).calculateScore() ||
                           dealer.getHand( 0 ).calculateScore() > 21 ) &&
                         pl.get( i ).getHand( ii ).calculateScore() <= 21 )
                        {
                        if ( pl.get( i ).getHand( ii ).calculateScore() == 21 )
                            {
                            System.out.println( pl.get( i ).getName() + "has won " +
                                                (int) ( pl.get( i ).getBet( ii ) * 1.5 ) +
                                                " points" ) ;
                            pl.get( i )
                              .increaseBalance( (int) ( pl.get( i ).getBet( ii ) +
                                                        pl.get( i ).getBet( ii ) * 1.5 ) ) ;
                            System.out.println( pl.get( i ).getName() + "has " +
                                                pl.get( i ).getBalance() + " points remainig " ) ;

                            }
                        else
                            {
                            System.out.println( pl.get( i ).getName() + "has won " +
                                                ( pl.get( i ).getBet( ii ) ) + " points" ) ;

                            pl.get( i )
                              .increaseBalance( pl.get( i ).getBet( ii ) +
                                                pl.get( i ).getBet( ii ) ) ;
                            System.out.println( pl.get( i ).getName() + "has " +
                                                pl.get( i ).getBalance() + " points remaining " ) ;

                            }

                        }
                    else if ( ( pl.get( i ).getHand( ii ).calculateScore() ==
                                dealer.getHand( 0 ).calculateScore() &&
                                pl.get( i ).getHand( ii ).calculateScore() <= 21 ) ||
                              ( pl.get( i ).getHand( ii ).calculateScore() > 21 &&
                                dealer.getHand( 0 ).calculateScore() > 21 ) )
                        {
                        System.out.println( pl.get( i ).getName() + "has tied " ) ;
                        pl.get( i ).increaseBalance( ( pl.get( i ).getBet( ii ) ) ) ;
                        System.out.println( pl.get( i ).getName() + "has " +
                                            pl.get( i ).getBalance() + " points remaining " ) ;

                        }

                    else
                        {
                        System.out.println( pl.get( i ).getName() + "has lost " +
                                            pl.get( i ).getBet( ii ) ) ;
                        System.out.println( pl.get( i ).getName() + "has " +
                                            pl.get( i ).getBalance() + " points remaining " ) ;

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

            for ( int i = 0 ; i < pl.size() ; i++ )
                {

                pl.get( i ).resetHand() ;

                }

            gameDeck.addCards( dealer.getHand( 0 ).getCards() ) ;
            dealer.resetHand() ;
            for ( int i = pl.size() - 1 ; i >= 0 ; i-- )
                {
                if ( pl.get( i ).getBalance() <= 0 )
                    {
                    System.out.println( pl.get( i ).getName() +
                                        "has run out of money and will be removed from the game" ) ;
                    pl.remove( i ) ;

                    }

                }

            for ( int i = pl.size() - 1 ; i >= 0 ; i-- )
                {
                System.out.println( pl.get( i ).getName() +
                                    ", would you like to leave the game?" ) ;
                String answer = "T" ;
                firstTime = true ;
                while ( !answer.equals( "y" ) && !answer.equals( "n" ) )
                    {
                    if ( !firstTime )
                        {
                        System.out.println( "Invalid player input" ) ;

                        }

                    answer = game.nextLine() ;

                    }

                if ( answer.equals( "y" ) )
                    {
                    pl.remove( i ) ;

                    }

                }

            String remainingPlayers = "the remaing players are: " ;
            for ( int i = 0 ; i < pl.size() ; i++ )
                {
                remainingPlayers = remainingPlayers + pl.get( i ).getName() ;

                }

            if ( pl.size() == 0 )
                {
                System.out.println( "No more players remain, game has ended" ) ;
                gamePlay = false ;

                }
            else
                {
                System.out.println( remainingPlayers ) ;

                }

            }

        System.out.println( "Thanks for playing" ) ;
        game.close() ;

        }

    }

// end class BlackJack
