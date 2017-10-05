/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longestwordindict;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author AJONE2
 */
public class LongestWordinDict {
    
    private static final String testStringPrompt = "Enter the string you would like to test against: ";
    private static final String quitString = "<q>";
    private static final String testWordPrompt = "Enter a string to add to the word set (Enter " + quitString + " to end input) :";
    
    private static boolean useSample = true;
    
    private static final String sampleString = "abppplle";
    private static final List<String> sampleWordList = new ArrayList<String>(Arrays.asList("able", "apple", "bale", "pale", "kangaroo"));
    
    
    public static void main(String[] args) {
        
        String longestWord = "";
        
        String testString;
        List<String> testWordList;
        
        if (useSample) {
            testString = sampleString;
            testWordList = sampleWordList;
        }
        else {
            testString = assignStringInput(testStringPrompt);
            testWordList = assignWordInput(testWordPrompt);
        }
        
        HashMap testStringHash = splitStringtoHash(testString);
        
        for (String word : testWordList) {
            
            if (validateWord(word, testStringHash)) {
                System.out.println(word + " passed validation");
                
                if (word.length()>longestWord.length()) {
                    longestWord = word;
                }
                
            }
            
        } // end for 
        
        System.out.println("Longest word is " + longestWord);
        
    } // end main method
    
    public static String assignStringInput(String prompt) {
        System.out.println(prompt);
        
        Scanner sc = new Scanner(System.in);
        String inputString = sc.nextLine();
        
        return inputString;
    }
    
    public static List<String> assignWordInput(String prompt) {
        List<String> inputWordAL = new ArrayList();
        Scanner sc = new Scanner(System.in);        
        
        boolean gettingInput = true;
        
        while (gettingInput) {
            System.out.println(prompt);
            String inString = sc.nextLine();
            
            if (inString.equals(quitString)) {
                gettingInput = false;
            }
            else {
                inputWordAL.add(inString);
                System.out.println("Added " + inString);
            }
            
        } // end while
        
        return inputWordAL;
        
    }
    
    public static HashMap splitStringtoHash(String inString) {
        
        String[] arrInString = inString.split("");
        HashMap<String,Integer> letterMap = new HashMap<>();
        
        for (String letter : arrInString) {
            
            if (!letterMap.containsKey(letter)){
                letterMap.put(letter, 1);
            }
            else {
                letterMap.put(letter, letterMap.get(letter)+1);
            }
            
        }
        
        return letterMap;
        
    }
    
    public static boolean validateWord(String inWord, HashMap inTestHash) {
        
        boolean isValidWord = true;
        HashMap currWordHash = splitStringtoHash(inWord);
       
        Iterator letterIt = currWordHash.keySet().iterator();
        
        while (letterIt.hasNext()) {
            
            String currLetter = letterIt.next().toString();
            
            int wordLetterCount = (int) currWordHash.get(currLetter);
            int testStrLetterCount = (int) inTestHash.get(currLetter);
            
            if (!inTestHash.containsKey(currLetter) || (wordLetterCount > testStrLetterCount) ) {
                
                System.out.println(inWord + " failed validation");
                isValidWord = false;
                
                break;
            }
        }
        return isValidWord;
    }    
}
