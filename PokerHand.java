/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokerhand;
import java.util.*;
import static java.util.Collections.list;
/**
 *
 * @author Sleepynhi
 * 
 * Output poker hand classification of 5 card inputs 
 * 
 */
public class PokerHand {

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {
        //Array to store in userinput
        String[] hand = new String[5];        
        // Scanner to take in user's input. 
        Scanner sc = new Scanner(System.in);
        System.out.println("Type in your valid hand. ex: 10h jh qh kh ah (10h for 10 of hearts) ");
        String card = sc.nextLine();
        //System.out.println(card); check input
        
        //Split string and store into array
         try {
            hand = card.split("\\s");
         } 
         catch (Exception e) {
            System.out.println(e);
         }
         
        //Check for any royal and convert to numeric value
         cardConversion(hand);
         //System.out.println(Arrays.toString(hand));
         
         
         //Check for same suit 
         handValue(hand);
         
         System.exit(0);
      
                
    }
    
    public static String handValue(String[] c){
        
        //Trying to seperate each card to two seperate string. 
        
        String[] handc = c; 
        String numValue = "";
        String suitValue = "";
        
        //Storing Strings into two seperate arrays 
        String[] suitArray = new String[5];
        int[] numArray = new int[5];
 
        
        for (int i = 0; i <= handc.length-1; i++){
            String cardValue = handc[i];
            System.out.print("Card value is: " + cardValue);
            
            //Grab end character and remove it and take remaining character and add it to numValue
            String cardValueTrim = cardValue.substring(cardValue.length()-1);
            String numValueTrim = cardValue.substring(0, cardValue.length()-1);
            System.out.println(" The trimmed string value is: [ " + cardValueTrim + " ]  Remaining string value is " + numValueTrim);
                    
            
            numArray[i] = Integer.parseInt(numValueTrim);
            suitValue +=cardValueTrim + " " ;
            
            suitArray[i] = cardValueTrim;
            
            
            System.out.println("Value of numValue is: "+ numValue + "Value of suitValue is: " + suitValue);
        }
        //Print out int array of NumValueTrim
        System.out.println("Integer array of numValueTrim is: " + Arrays.toString(numArray));
        
        
        List<Integer> checkNum = new ArrayList<>();
        List<Integer> storedNum = new ArrayList<>();
        
        for (int i = 0; i <= numArray.length-1; i++ ){
            int currentInt = numArray[i];
            if (checkNum.contains(currentInt)){
                continue; 
            }
            
            int count = 0;
            
            for (int j = 0; j <= numArray.length-1; j++){
                if (numArray[j] == currentInt){
                    //Goes thru the whole array and increment count value. 
                    count++;
                    
                }
                
            }
            
            storedNum.add(count);
            checkNum.add(currentInt);
        }
        
        
        storedNum.sort(null);
         //checking if sort is successful 
        System.out.print("Arraylist of storedNum: " + storedNum.toString());
        
      
        final String oneOfaKind= "[1, 1, 1, 1, 1]";
        final String twoPair = "[1, 2, 2]";
        final String onePair = "[1, 1, 1, 2]";
        final String threeOfaKind = "[1, 1, 3]";
        final String fourOfaKind = "[1, 4]";
        final String fullHouse = "[2, 3]";
        
        
        //boolean flag = storedNum.toString().equals("[1, 2, 2]");
        //System.out.println("Test for equals: " + flag);
        
        
        //Counting num of suits. If suit's not equal to 5, ignore. 
        System.out.println("suitArray values are: "+Arrays.toString(suitArray));
                
        int suitCount=0;
        boolean sameSuit = false;
        for ( int i = 0 ; i <= suitArray.length-1 ; i++){
            String curSuit = suitArray[0];
            
                if (suitArray[i].equals(curSuit)){
                    suitCount++;
                    if (suitCount == 5){
                    sameSuit = true; 
                    } 
                }
                  
        }
        
        System.out.println("Suit count is: "+suitCount + " and same suit is: "+ sameSuit);
        
       

        // If hand is one of a kind and is the same suit, check for straight or royal flush, else it is a flush
        if (storedNum.toString().equals(oneOfaKind) == true && sameSuit == true){
            System.out.println("Order of flush: " + Arrays.toString(numArray));
            
            Arrays.sort(numArray);
            System.out.println("Sorted flush: " + Arrays.toString(numArray));
            
            if (Arrays.toString(numArray).equals("[10, 11, 12, 13, 14]")){
            System.out.println("You have a royal flush");
            System.exit(0);
            }
            
            // Checking increment of 1 for next element in array
            // Specifically checking for straight flush 
            
            int sqCount = 0; 
            boolean isSQ= false;
            for(int i = 0; i<= numArray.length-2; i ++){
                
                if(numArray[i] + 1 == numArray[i+1] ){
                    sqCount++;
                    if (sqCount == 4){  //number needed to check if all is checked
                    isSQ = true;
                    }
                }
            }
            //System.out.println("sqCount is: " + sqCount + "isSQ value is: " + isSQ); 
            
            
            
            if (isSQ == true){
                System.out.println("You have a straight flush.");
                
            }
            else {
                
                //Output is a flush if isSQ is false && does not equal to a royal flush
                
                System.out.println("You have a flush.");
                
            }
        }
        
        
        else if (storedNum.toString().equals(oneOfaKind)){
            System.out.println("Your hand is high card."); 
        }else if (storedNum.toString().equals(onePair)){
            System.out.println("Your hand is one pair."); 
        }else if (storedNum.toString().equals(twoPair)){
            System.out.println("Your hand is two pair."); 
        }else if (storedNum.toString().equals(threeOfaKind)){
            System.out.println("Your hand is three of a kind."); 
        }else if (storedNum.toString().equals(fourOfaKind)){
            System.out.println("Your hand is four of a kind."); 
        }
        else if (storedNum.toString().equals(oneOfaKind)){
            System.out.println("Your hand is full house."); 
        }
        
        
        
//        
//        switch (storedNum.toString()) {
//            case oneOfaKind:
//                System.out.println("Your hand is high card."); 
//                break; 
//            case onePair:
//                System.out.println("Your hand is one pair.");
//                break;
//            case twoPair: 
//                System.out.println("Your hand is two pair.");
//                break;
//            case threeOfaKind: 
//                System.out.println("Your hand is three of a kind.");  
//                break;
//            case fullHouse: 
//                System.out.println("Your hand is a full house.");
//                break;
//            case fourOfaKind: 
//                System.out.println("Your hand is four of a kind.");
//                break;
//            default: 
//                System.out.println("Hand Unknown");
//            
//        }
//        
        
//        try {
//            
//            suitArray = suitArray.split("\\s");
//        } catch (Exception e ){
//            System.out.println(e);
//        }
        
        
        //Counting occ. of number  ex: 2 , 3, 10, 3, 14
        for (int i = 0; i <= numArray.length-1; i++){
            //numArray[i] use another array to check for dup
            
            for (int j = 0; j >= numArray.length-1; j++){
                //Find a way to skip it's own index. 
                //increment int value if dup. 
                
            }
            
        }
        
        return null;
    }
        
    public static String[] cardConversion(String[] c){
        //Convert royal to numeric values
        System.out.println("Method parameter for card conversion is:"+ Arrays.toString(c));
        String[] newCard = c;      
        char checkc;
        int conNum; 
        for (int i=0; i<=newCard.length-1; i++){
            //Set index into string to check for royal    
            String a = newCard[i];
            System.out.println("current value of i is:" + a);
   
                         
            checkc = a.charAt(0);
               System.out.println("current value of checkc is:" + checkc);

                if('J' == checkc || 'j' == checkc){
                    conNum = 11;

                }else if ('Q' == checkc || 'q' == checkc){
                    conNum = 12;

                }else if ('K' == checkc || 'k' == checkc){
                    conNum = 13;

                }else if ('A' == checkc || 'a' == checkc){
                    conNum = 14;          
                } else if ( '1' == checkc) {
                    //check if number is 10
                    conNum = 10;
                    newCard[i] = "" + conNum + a.charAt(2);
                    continue;
                }
                else{
                    conNum = Character.getNumericValue(checkc);
                }

            System.out.println("Conversion num is: " + conNum);
            newCard[i] = "" + conNum + a.charAt(1);

            System.out.println("Conversion value of checkc is: " +newCard[i]);
        }
        
        System.out.println("Value of converted hand is: " + Arrays.toString(newCard));
        return newCard;
    }
}
    

