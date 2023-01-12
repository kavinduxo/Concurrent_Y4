/** *********************************************************************
 * File:	Transaction.java    [class]
 * Author:	P. Howells	
 * Contents:	6SENG002W CWK: Banking System
 *		Provides the basic data structure for a bank transaction. That 
 *		is customer id & the amount to either deposit or withdraw
 * Date:	25/10/19
 ************************************************************************ */

class Transaction
{
    private final String CustomerID ;  
    private final int    amount ;

     public Transaction( String CID, int amount )
     {
        this.CustomerID  = CID ;
        this.amount 	 = amount ;
     }


    public String getCID( )    { return CustomerID ; }

    public int    getAmount( ) { return amount ; }


    public String toString( )
    {
        return  new String( "[ " + "Customer: " + CustomerID + ", " 
                                 + "Amount: "   + amount +
                            "]"  
                          ) ;
    }

} // Transaction

