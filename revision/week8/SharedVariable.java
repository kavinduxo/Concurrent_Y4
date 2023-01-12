/** 
 *******************************************************
 ** File:   	SharedVariable.java
 ** Author:	P. Howells
 ** Tutorial    Week 08:  Java Threads States & Life-cycle
 ** Exercise    8.1  Based on Week 07 Tutorial, Ex 7.8
 ** Modified:   13/11/2022
**
 ** ******************************************************* 
**/


class SharedVariable implements Variable 
{
   private int variable = 0 ;

   public SharedVariable( ) 
   {
      variable = 0 ; 
   }


   public SharedVariable( int initialValue ) 
   {
      variable = initialValue ; 
   }


   // assigns it the value
   public void assign( int value ) 
   {            
      variable = value ; 
   }


   // returns its current value
   public  int value( )
   {            
      return ( variable ) ;
   }


   // returns true if 0; false if != 0 
   public boolean isZero( ) 
   {            
      return ( variable == 0 ) ;
   }


   // returns true if 0; false if != 0 
   public boolean isDivisibleBy( int divisor )
   {            
      return ( ( variable % divisor ) == 0 ) ;
   }


} // SharedVariable
