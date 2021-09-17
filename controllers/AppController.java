package controllers;

import views.GamePage;
import javax.swing.*;

import models.AppModel;

import java.awt.event.*;
import java.util.*;

public class AppController{

    //words object
    private AppModel mWords;

    //View Object
    private GamePage gamePage;

    //random text string
    String mRandomText = "null";
    
    //player health variable
    int playerHealth = 5;

    //Jframe
    public JFrame frame;

    //constructor
    public AppController(int width, int height){
        mWords = new AppModel();
        
        gamePage = new GamePage(width, height);
        this.frame = gamePage.frame;
        
        //set buttons
        initNewButton();
        defineButtonListener();

        //set up text fields
        initTextFields();
    }

    private void initTextFields(){
        this.mRandomText = mWords.getWord();
        gamePage.wordToGuessField.setText(mRandomText);
        gamePage.userInputJTextField.setText(mWords.getDottedWord());
    }

    private void setUpAllButtons(boolean enabled){
        int i = 0;
        for (JButton button : gamePage.buttons) {
            if(i != 26){
                button.setEnabled(true);
            }
            i++;
        }
    }

    private boolean isFailedGame(){
        if(playerHealth <= 0){
            return true;
        }else{
            return false;
        }
    }

    private boolean isWinGame(){
        if(gamePage.userInputJTextField.getText().indexOf(".") != -1){
            return false;
        }else{
            return true;
        }
    }

    //reset health
    private void resetHealth(){
        playerHealth = 5;
    }

    //reduce lives
    private void reduceHealth(){
        playerHealth--;
    }

    private void initNewButton(){
        //define listener for 'New Button'
        gamePage.buttons.get(26).addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            //enable all buttons
            setUpAllButtons(true);
            gamePage.currentHealth(5);
            //change word in textfields
            initTextFields();
            //reset health
            resetHealth();
        }
     });
    }

    private boolean findCharacterInString(char letter){
        char [] allCharacters = mRandomText.toLowerCase().toCharArray();

        ArrayList<Integer> characterPosition = new ArrayList<>(allCharacters.length);

        for(int i = 0; i < allCharacters.length; i++){
            if(allCharacters[i] == letter){
                characterPosition.add(i);
                String value = gamePage.userInputJTextField.getText();
                StringBuilder myBuilder = new StringBuilder(value);
                myBuilder.setCharAt(i, letter);
                gamePage.userInputJTextField.setText(myBuilder.toString());
            } 
        }

        if(characterPosition.size() > 0){
            return true;
        }else{
            return false;
        }
    }

    //define listeners for buttons
    private void defineButtonListener(){
        for (int i = 0; i < 26; i++){
          gamePage.buttons.get(i).addActionListener(new ActionListener(){
              public void actionPerformed(ActionEvent e){
                 JButton clickedButton = (JButton) e.getSource();
                 String text = clickedButton.getText().toLowerCase();

                 clickedButton.setEnabled(false);

                 char clickedLetter = text.charAt(0);
                 boolean isCorrect = findCharacterInString(clickedLetter);
                 if(!isCorrect) reduceHealth();  
                 //update health monitor
                gamePage.currentHealth(playerHealth);
                 if(isFailedGame()){
                    setUpAllButtons(false);
                    //display results
                    gamePage.displayResultsMessage(false);
                    mWords.randomiseWord();
                 } 
                 if(isWinGame()){
                    setUpAllButtons(false);
                    //display results
                    gamePage.displayResultsMessage(true);
                    mWords.randomiseWord();
                 }
              }
          });  
        }
    }
}