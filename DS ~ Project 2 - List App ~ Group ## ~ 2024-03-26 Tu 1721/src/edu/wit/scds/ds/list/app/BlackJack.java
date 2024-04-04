
package edu.wit.scds.ds.list.app ;

import java.util.ArrayList ;

/**
 * @author Tyger Maguire
 * 
 * @version 1.0.0 2024-04-04 Initial implementation
 */

import java.util.List ;
import java.util.Scanner ;

public class BlackJack
    {

    protected List<Player> pl ;
    private Deck gameDeck ;
    private Player dealer ;

    /**
     * @param args
     */
    public void main( String[] args )
        {

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
            String playerName = Game.nextLine() ;

            this.pl.add( new Player( playerName + " " ) ) ;

            }

        boolean gamePlay = true ;

        while ( gamePlay )
            {
            this.gameDeck.shuffle() ;
            for ( int i = 0 ; i < this.pl.size() ; i++ )
                {
                firstTime = true ;
                System.out.println( this.pl.get( i ).getName() + " please make your bet." ) ;
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

                this.pl.get( i ).placeBet( 0, playerBet ) ;

                }

            for ( int i = 0 ; i < this.pl.size() ; i++ )
                {
                this.pl.get( i ).addCard( this.gameDeck.draw() ) ;

                }

            this.dealer.addCard( this.gameDeck.draw() ) ;
            for ( int i = 0 ; i < this.pl.size() ; i++ )
                {
                this.pl.get( i ).addCard( this.gameDeck.draw() ) ;

                }

            Card hiddenCard = ( this.gameDeck.draw() ) ;
            for ( int i = 0 ; i < this.pl.size() ; i++ )
                {
                System.out.println( this.pl.get( i ).getName() + " hand:" ) ;
                this.pl.get( i ).toString() ;

                }

            System.out.println( "Dealer hand: " ) ;
            this.dealer.toString() ;

            }

        for ( int i = 0 ; i < this.pl.size() ; i++ )
            {
            if ( this.pl.get( i ).getHand( 0 ).calculateScore() == 21 )
                {
                System.out.println( "congradualtions " + this.pl.get( i ).getName() +
                                    " on your black jack you have been rewarded with " +
                                    this.pl.get( i ).getBet( 0 ) * 1.5 + " points." ) ;
                this.pl.get( i ).increaseBalance( (int) ( this.pl.get( i ).getBet( 0 ) * 1.5 ) ) ;

                }

            if ( this.pl.get( i ).canSplit() )
                {
                System.out.println( this.pl.get( i ).getName() + ", would you like to split?" ) ;
                String answer = "T" ;
                firstTime = true ;
                while ( answer != "y" || answer != "n" )
                    {
                    if ( !firstTime )
                        {
                        System.out.println( "Invalid player input" ) ;

                        }

                    answer = Game.nextLine() ;

                    }

                if ( answer == "y" )
                    {
                    this.pl.get( i ).splitHand() ;
                    this.pl.get( i ).placeBet( 0, this.pl.get( i ).getBet( 0 ) ) ;

                    }

                }

            if ( this.pl.get( i ).getHand( 0 ).calculateScore() == 9 ||
                 this.pl.get( i ).getHand( 0 ).calculateScore() == 10 ||
                 this.pl.get( i ).getHand( 0 ).calculateScore() == 11 )
                {
                System.out.println( this.pl.get( i ).getName() +
                                    ", would you like to double down?" ) ;
                String answer = "T" ;
                firstTime = true ;
                while ( answer != "y" || answer != "n" )
                    {
                    if ( !firstTime )
                        {
                        System.out.println( "Invalid player input" ) ;

                        }

                    answer = Game.nextLine() ;

                    }

                if ( answer == "y" )
                    {
                    this.pl.get( i ).doubleDown( 0, gameDeck.draw() ) ;
                    ;

                    }

                }

            for ( int i = 0 ; i < this.pl.size() ; i++ )
                {
                boolean turn = true ;
                firstTime=true;
                while ( turn )
                    {
                        if(firstTime&&this.pl.get( i ).getHand( 0 ).calculateScore() == 21) {
                        
                        break;
                        }
                        
                        
                        firstTime=false;
                    }

                }

            }

        }

    }

// end class BlackJack