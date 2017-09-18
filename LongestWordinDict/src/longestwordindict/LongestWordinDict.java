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
    private static final String testSetPrompt = "Enter a string to add to the word set (Enter " + quitString + " to end input) :";
    
    
    public static void main(String[] args) {
        String longestWord = "";
        
        String S = assignStringInput(testStringPrompt);
        // System.out.println("Entered " + S);
        String[] D = assignDictInput(testSetPrompt);
        // System.out.println(Arrays.toString(D));
        // Put string in hash map with count of character in Value with character as Key
        HashMap testStringHash = splitStringtoHash(S);
        
        // For each word in the test collection, break out into hashmap similar to test string
        for (String word : D) {
            if (validateWord(word, testStringHash)) {
                System.out.println(word + " passed validation");
                // If successful, check if new string is longer than previous longest string
                if (word.length()>longestWord.length()) {
                    // If so, save new word to longest word
                    longestWord = word;
                }
            }
        } // end for (string : word) 
        System.out.println("Longest word is " + longestWord);
    } // end main method
    
    public static String assignStringInput(String prompt) {
        System.out.println(prompt);
        Scanner sc = new Scanner(System.in);
        String testStringIn = sc.nextLine();
        return testStringIn;
    }
    
    public static String[] assignDictInput(String prompt) {
        Scanner sc = new Scanner(System.in);
        boolean gettingInput = true;
        List<String> wordAL = new ArrayList();
        
        while (gettingInput) {
            System.out.println(prompt);
            String inString = sc.nextLine();
            
            if (inString.equals(quitString)) {
                gettingInput = false;
            }
            else {
                wordAL.add(inString);
                System.out.println("Added " + inString);
            }
        } // end while
        String[] returnArray = new String[wordAL.size()];
        return wordAL.toArray(returnArray);
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
            Set letterSet = currWordHash.keySet();
            // System.out.println(letterSet);
            // For each letter in word check if in the test string, if so, is the Value less than or equal to the Value in the test string
            Iterator letterIt = letterSet.iterator();
            while (letterIt.hasNext()) {
                String currLetter = letterIt.next().toString();
                // System.out.println(currLetter);
                Integer wordLetterCount = (Integer) currWordHash.get(currLetter);
                Integer testStrLetterCount = (Integer) inTestHash.get(currLetter);
                if (!inTestHash.containsKey(currLetter) 
                        || (wordLetterCount > testStrLetterCount) ) {
                    // If at any point there's a bad value, exit and go to next word
                    System.out.println(inWord + " failed validation on " + currLetter);
                    isValidWord = false;
                    break;
                }
            }
        return isValidWord;
    }    
}
