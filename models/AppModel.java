package models;

import java.util.*;

public class AppModel{
    
    private ArrayList<String> wordsArrayList;
    private String mWord;

    public AppModel(){
        this.wordsArrayList = initializeWordList();
        this.mWord = generateRandomWord();
    }
    

    public String getDottedWord(){
        StringBuilder dottedStringBuilder = new StringBuilder();
        for(int i = 0; i<this.mWord.length(); i++){
            dottedStringBuilder.append(".");
        }
        return dottedStringBuilder.toString();
    }

    public void randomiseWord(){
        this.mWord = generateRandomWord();
    }

    private ArrayList<String> initializeWordList(){

        ArrayList<String> wordsArrayList = new ArrayList<>();
        //add values to list
        
        wordsArrayList.add("Revolution");wordsArrayList.add("Application");
        wordsArrayList.add("Intelligence");wordsArrayList.add("Communication");
        wordsArrayList.add("Heavily");wordsArrayList.add("Validation");
        wordsArrayList.add("Chemistry");wordsArrayList.add("Beautiful");
        wordsArrayList.add("Programming");wordsArrayList.add("Engineering");
        wordsArrayList.add("Internet");wordsArrayList.add("Language");
        wordsArrayList.add("Physics");wordsArrayList.add("Electronics");
        wordsArrayList.add("Science");wordsArrayList.add("Difficulty");
        wordsArrayList.add("Attempts");wordsArrayList.add("Progress");
        wordsArrayList.add("Industry");wordsArrayList.add("Organization");
        wordsArrayList.add("Artificial");wordsArrayList.add("School");
        wordsArrayList.add("Database");wordsArrayList.add("Network");
        wordsArrayList.add("Graphics");wordsArrayList.add("Attempts");
        wordsArrayList.add("Windows");wordsArrayList.add("Characters");
        wordsArrayList.add("Friendly");wordsArrayList.add("Screenshot");
        wordsArrayList.add("Statistics");wordsArrayList.add("Learning");
        wordsArrayList.add("Baseball");
    
        
        return wordsArrayList;
    }

    //generate random word
    private String generateRandomWord(){       
        return this.wordsArrayList.get(new Random().nextInt(this.wordsArrayList.size()));
    }

    public ArrayList<String> getWords(){
        return this.wordsArrayList;
    }

    public String getWord(){
        return this.mWord.toLowerCase();
    }

}
