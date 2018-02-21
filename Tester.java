/**
 * Created by james on 11/6/2016.
 * CSCD320 midterm assignment
 */
import java.io.*;
import java.util.*;
import java.lang.System;

import Trie.*;

public class Tester {

    private static Hashtable<Integer, ArrayList<String>> hashtable = new Hashtable<>();
    private static TrieTree trie = new TrieTree();
    private static long totalTime;
    private static long trieTime;
    private static long hashTime;

    public static void main(String [] args){

        long startTime;
        long endTime;

        ReadDictionary();


        do
        {
            ArrayList<String> wordCombos = totalWordCombos(menu());
            startTime = System.nanoTime();//used to figure out how long the program run takes

            wordCombos.stream().filter(word -> trie.search(word)).forEach(word ->
            {
                System.out.println("Found in Trie " + word);
            });
            
            endTime = System.nanoTime();
            trieTime = (endTime - startTime);
            
            for(String word : wordCombos) 
            {
                FindRealWords(word, hashtable.get(word.charAt(0) - 'a'));
            }//end for

            System.out.println("Current time complexity from Trie completion is ------------>    " + (trieTime) + " nanoseconds");
            System.out.println("The time complexity to HashTable complexity time is -------->    " + (hashTime) + " nanoseconds");
            System.out.println("The time complexity to combination complexity time is ------>    " + (totalTime) + " nanoseconds");
            System.out.println("The time complexity for total program complexity time is --->    " + (trieTime + hashTime + totalTime) + " nanoseconds\n");

        }while(true);

    }//end of main

    public static String menu()
    {

        Scanner fin = new Scanner(System.in);
        String input;

        do
        {
            System.out.println("Please type in the input using the letters that correspond to the numbers below:\n\n" +
                    "         2={abc} " +
                    "3={def}\n" +
                    "4={ghi} " +
                    " 5={jkl} " +
                    "6={mno}\n" +
                    "7={pqrs} " +
                    "8={tuv} " +
                    "9={wxyz}\n\n");
            System.out.println("Type in exit to end the program");
            System.out.print("Enter the digit input: ");

            input = fin.nextLine();

            if(input.equals("exit"))
            {
                System.out.println("Good-Bye");
                System.exit(0);
            }//end if

        }//end for

        while(!parseInteger(input));

        return input;

    }//end menu

    public static ArrayList<String> totalWordCombos(String digits) 
    {
        int i, j;
        long startTime, endTime;
        ArrayList<String> result = new ArrayList<>();
        ArrayList<String> firstCheck = new ArrayList<>();
        
        
        result.add("");
        startTime = System.nanoTime();

        for (i = 0; i < digits.length(); i++)
        {
            for (String data : result)
            {
                String letters = wordMap.get(digits.charAt(i));
                
                for (j = 0; j < letters.length(); j++)
                {
                    firstCheck.add(data + letters.charAt(j));
                }//end for
                
            }//end for
            
            result = firstCheck;
            firstCheck = new ArrayList<>();
            
        }//end for

        endTime = System.nanoTime();
        totalTime = (endTime - startTime);
        return result;

    }//end totalWordCombos

    static final HashMap<Character, String> wordMap = new HashMap<Character, String>()
    {
        {
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }
    };

    public static void FindRealWords(String word, ArrayList arr){

        long startTime, endTime;
        int i;

        startTime = System.nanoTime();//used to figure out how long it takes to run the program using the hashTable
        for(i = 0; i < arr.size(); i++)
        {
            if(word.equals(arr.get(i)))
            {
                System.out.println("Found in HashTable " + word);
            }//end of if
            
        }//end of for loop
        
        endTime = System.nanoTime();
        hashTime = (endTime - startTime);

    }//end of find combo method

    public static ArrayList readList(Scanner fin, int letterToCompare)
    {
        ArrayList tempList = new ArrayList<String>();
        String temp;
        int compareLetter = letterToCompare + 1;

        while(!(fin.nextLine().charAt(0)==(letterToCompare))) 
        {
            fin.nextLine();
        }//end while
        
        while(fin.hasNextLine())
        {
            temp = fin.nextLine();
            int result = (int)temp.charAt(0);
            
            if(result == compareLetter)
            {
                break;
            }//end if
            
            String [] temporaryList = temp.split(",");
            trie.insert(temporaryList[0]);
            tempList.add(temporaryList[0]);
            
        }//end while

        return tempList;

    }//end readList


    public static void ReadDictionary()//method used to open and read in the dictionary
    {
        try
        {
            Scanner fin;
            fin = new Scanner(new File("dictionary.txt"));

            for(int i = 'a'; i < 123; i++)
            {
                hashtable.put( i - 'a', readList(fin, i ));
            }//end for
            
            fin.close();
        }//end try
        
        catch (Exception word)
        {
            System.out.println("Error when trying to read the dictionary...");
        }//end catch
        
        System.out.println("Successful read of the Dictionary");

    }//end readDictionary

    private static boolean parseInteger(String val)
    {
        try
        {
            Integer.parseInt(val);
            return true;
        }//end try

        catch(NumberFormatException word){
            return false;
        }//end catch
        
    }//end parseInteger

}//end class

