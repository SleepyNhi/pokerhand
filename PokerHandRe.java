
package pokerhand.pokerhand;
import java.util.*;
import static java.util.Collections.list;
/**
 *
 * @author Sleepynhi
 */


public class PokerHandRe {
    
    static boolean run = true;
    static boolean dupFlag = false; 
    static boolean legalFlag = false; 
    static boolean suitFlag_flush = true;
    static String[] suits = {"S", "H", "D", "C"};
    static String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    //Array to store in userinput
    private static List<String> hands = new ArrayList<>();
    private static List<Integer> userRanks = new ArrayList<>();
    private static List<Integer> countUserRanks = new ArrayList<>();
    
    
    public static void main(String[] args){
        
        
        
        while (run) {
        // Scanner to take in user's input. 
        Scanner sc = new Scanner(System.in);
        System.out.println("Type in your hand. ex: 10h jh qh kh ah (10h for 10 of hearts). type stop to end. ");
        String card = sc.nextLine().toUpperCase();
            if (card.equals("STOP")){
                run = false;
                System.exit(0);
            }
        //debug("Value of card is: " + card);
        
        //Split string by whitespace and store into arraylist
        hands = Arrays.asList(card.split("\\s"));
        //debug("Value of arraylist is " + hands);
        testLegalCards(hands);
        //check for legal cards
        
        
        
        getHandID();
        //debug("" + userRanks + suitFlag_flush);
        
       
        userRanks.clear();
        countUserRanks.clear();
        dupFlag = false;
        legalFlag = false;
        suitFlag_flush = true;
        
        }
        
    }
    
    public static void getHandID(){
        
        Collections.frequency(userRanks, 3);
        List<Integer> checkedNums = new ArrayList<>();
        if (dupFlag == false && legalFlag == true ){
            
            for (int i = 0; i <= userRanks.size()-1;i++){
                int currentRank = userRanks.get(i);
                if (!checkedNums.contains(currentRank)){
                countUserRanks.add(Collections.frequency(userRanks, currentRank));
                checkedNums.add(currentRank);
                }
                
            }
            //debug(countUserRanks);
            
            countUserRanks.sort(null);
            userRanks.sort(null);
            
            boolean straightFlag = checkStraight(userRanks);
            
            
                    final String oneOfaKind= "[1, 1, 1, 1, 1]";
                    final String twoPair = "[1, 2, 2]";
                    final String onePair = "[1, 1, 1, 2]";
                    final String threeOfaKind = "[1, 1, 3]";
                    final String fourOfaKind = "[1, 4]";
                    final String fullHouse = "[2, 3]";
                    final String royalFlush = "[10, 11, 12, 13, 14]"; 
            
            if (suitFlag_flush == true && userRanks.toString().equals(royalFlush)){
                debug("You have a Royal Flush");
                
            } else if(suitFlag_flush == true && straightFlag == true){
                debug("You have a Straight Flush");
                
            } else if (countUserRanks.toString().equals(fourOfaKind)){
                debug("You have Four of a Kind");
                
            } else if (countUserRanks.toString().equals(fullHouse)){
                debug("You have a Full House");
                
            } else if(suitFlag_flush == true){
                debug("You have a Flush");
               
            } else if (straightFlag == true){
                debug("You have a Straight");
                
            } else if (countUserRanks.toString().equals(threeOfaKind)){
                debug("You have Three of a Kind");
            } else if (countUserRanks.toString().equals(twoPair)){
                debug("Your hand is two pair.");
            } else if (countUserRanks.toString().equals(onePair)){
                debug("Your hand is one pair.");
            } else {
                debug("You have nothing");
            }
                
        }
    }
    

   public static boolean checkStraight(List sortedCountUser){
     
       int count = 0;
       for(int i = 0; i<= sortedCountUser.size()-2; i ++){
            int currNum = (int) sortedCountUser.get(i) + 1;
            int nextNum = (int) sortedCountUser.get(i+1);
            
            //debug("currNum and nextNum: " + currNum + " " + nextNum + "count var: " + count);
            
            if(currNum != nextNum){
              return false;  
            } 
            count++; 
            if (count == 4){
                return true;
            } 
        }
        return false;
        
   } 
    
    public static void testLegalCards(List a){
        //debug("testLegalCards is in progress");
     
        
        List<String> tempHand = new ArrayList<>();
        tempHand = a; 
        //debug("tempHand is: " + tempHand); 
             
        //Checking for duplicate cards. 
        List<String> checkTempHand = new ArrayList<>();
        for (int i = 0; i <= tempHand.size()-1; i++){
//            debug(tempHand.get(i));
//            debug(checkTempHand.toString());
            if (!checkTempHand.contains(tempHand.get(i))){
                
            checkTempHand.add(tempHand.get(i));
            } else {
                dupFlag = true;
                debug("Contains duplicate. Please input valid card value");
                break;
            }
        }
        String flushCheck = null; 
        
        for (int i = 0; i <= tempHand.size()-1; i++){
            String s = tempHand.get(i);         
            String s_suit = s.substring(s.length()-1);
            String s_ranks = s.substring(0, s.length()-1);
            
            
            if (!Arrays.asList(suits).contains(s_suit)){
                debug("Please input valid suits");
                break;
                
                
            } else if (!Arrays.asList(ranks).contains(s_ranks)){
                debug("Please input valid rank");
                break;
            } else {
                legalFlag = true;
            }
            
            
            
            //debug("current suit is "+s_suit);
            
            //Check for flush
            if (flushCheck == null){
                flushCheck = s_suit; 
                
            //debug("Setting flush check var "+flushCheck);
            } else if(!flushCheck.equals(s_suit)){
                
                suitFlag_flush = false;
            }
            
            
            
            
            
            switch (s_ranks){
                case "A" : 
                    userRanks.add(14); break;
                case "K" :
                    userRanks.add(13); break;
                case "Q" :
                    userRanks.add(12); break;
                case "J" : 
                    userRanks.add(11); break;
                default: 
                    userRanks.add(Integer.parseInt(s_ranks));
                    break;
            }
       
        }
            
            
    }   

    
    public static void debug(Object a){
        System.out.println(a);
    }
    
}
