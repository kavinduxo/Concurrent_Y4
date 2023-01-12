/** 
 *******************************************************
 ** File:   	Writer_81.java [WAS: Writer_78.java]
 ** Author:	P. Howells
 ** Tutorial:   5: Introduction to Java Threads 
 ** Exercise:   8.1 , Based on Week 07 Tutorial, Ex 7.8
 ** Modified:   13/11/2022
 **
 ** ******************************************************* 
**/


class Writer_82 extends Thread 
{

   private final SharedVariable X ;
   private final SharedVariable Y ;

   private final int count ; 
   private final int lowerLimit ; 


   private final int OneSecond = 1000 ;
   private int   time = 2 ;            // Ex 8.2


   public Writer_82( String name,  SharedVariable sv1, SharedVariable sv2, 
                     int count, int lowerLimit  ) 
   {
      super( name ) ;  

      // Ex 8.1      
      System.out.println( getName() + " Thread State in Constructor is: " + getState​() ) ;

      this.X = sv1 ;     
      this.Y = sv2 ;     

      this.count = count ;     
      this.lowerLimit = lowerLimit ;     
    }


   public void run() 
   {

      System.out.println( getName() + ": STARTED" ) ;

      // Ex 8.1
      System.out.println( getName() + " Thread State in run() is: " + getState​() ) ;

      for ( int i = 0 ; i < count ; i++ ) 
      {
         int newValue = lowerLimit + i ;

         X.assign( newValue ) ;

         System.out.println( getName() + " assigned X: " + newValue) ;

         Y.assign( newValue ) ;

         System.out.println( getName() + " assigned Y: " + newValue) ;

       
         try {
                // sleep( (int) ( Math.random() * OneSecond * time ) ) ;     // Ex8.2
                sleep( (int) ( OneSecond * time ) ) ;                        //
             } 
         catch (InterruptedException e) {}

      }

      System.out.println( getName() + ": TERMINATING" ) ;

   }

} // Writer_82
