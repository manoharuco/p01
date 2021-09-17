package views;

import javax.swing.*;

import java.awt.*;
import java.util.*;

public class GamePage{
    
    public JFrame frame;
    private GridLayout grid;

    private GridLayout textFieldGrid;

    //text fields
    public JTextField wordToGuessField, userInputJTextField;
    
    //grid for labels
    private GridLayout labelGridLayout;

    //grid for buttons
    private GridLayout alphabetButtonsLayout;

    //number of lettersList of the alphabet
    private static int alphabetCount = 26;

    private ArrayList<Character> lettersList = new ArrayList<>(alphabetCount);

    //add 26 alphabets and 'new button'
    public ArrayList<JButton> buttons = new ArrayList<>(alphabetCount + 1);
    private final String newButton = "NEW";

    //panel for elements
    private JPanel buttonsPanel, textFieldPanel, healthPanel;

    public Canvas healthCanvas;

    public GamePage(int width, int height) {
        //init JFrame
        frame = new JFrame();

        // init alphabet array list
        initAlphabetArrayList();

        //init buttons
        initButtons();
        
        //init frame with defined dimensions
        initFrame(width, height);
    }

    public JFrame getMainFrame(){
        return this.frame;
    }

    private void initFrame(int width, int height){
        
        frame.setSize(width, height);
        //initialize grid layout
        grid = new GridLayout(4,1);
        frame.setLayout(grid);
        
        alphabetButtonsLayout = new GridLayout(3,9,5,5);
        textFieldGrid = new GridLayout(2,1,5,5);
        labelGridLayout = new GridLayout(2,1,5,5);

        //init panels
        textFieldPanel = new JPanel();
        buttonsPanel = new JPanel();
        healthPanel = new JPanel();

        frame.add(textFieldPanel);
        frame.add(healthPanel);
        frame.add(buttonsPanel);
    
        //init panels
        initTextFieldPanelInterface(textFieldPanel);
        initButtonPanelInterface(buttonsPanel);
        setupHealthPanel(healthPanel);
    }

    private void initButtonPanelInterface(JPanel myPanel){
        myPanel.setLayout(alphabetButtonsLayout);
        //add all buttons
        for(int i = 0; i <= lettersList.size(); i++){
            myPanel.add(buttons.get(i));
        }
    }

    //add text fields to panel
    private void initTextFieldPanelInterface(JPanel textJPanel){

        textFieldPanel.setLayout(textFieldGrid);

        wordToGuessField = new JTextField();
        userInputJTextField = new JTextField();

        userInputJTextField.setFont(userInputJTextField.getFont().deriveFont(Font.BOLD,25f));

        wordToGuessField.setEditable(false);
        userInputJTextField.setEditable(false);

        textJPanel.add(wordToGuessField);
        textJPanel.add(userInputJTextField);
    }

    private void initButtons(){
        for(int i = 0; i < lettersList.size(); i++){
            buttons.add(new JButton(lettersList.get(i).toString()));
        }
        
        JButton newBtn = new JButton(newButton);
        buttons.add(newBtn);
    }

    private void initAlphabetArrayList(){
        for(int i = 65; i < 91; i++){
            lettersList.add((char)i);
        }
        
    }

    private void setupHealthPanel(JPanel panel){
        panel.setLayout(labelGridLayout);

        initHealthCanvas();
        panel.add(healthCanvas);

        //disable alphabet buttons
        int i = 0;
        for(JButton button : buttons){
            if(i != buttons.size() - 1) button.setEnabled(false);
            i++;
        }
    }

    private void initHealthCanvas(){
        healthCanvas = new Canvas(){
            public void paint(Graphics g){
                g.setColor(Color.blue);
                g.drawString("Press <NEW> to Start ", 20, 20);
            }
        };
    }

    public void currentHealth(int playerHealth){
        // clear health panel
        healthPanel.removeAll();
        
        // define canvas
        healthCanvas = new Canvas(){
            public void paint(Graphics g){
                g.setColor(Color.BLUE);
                int fontSize = 15;
                g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
                g.drawString("Health Level", 10, 15);
                int healthBoxWidth = 10;
                for(int i = 0;i < playerHealth;i++){
                    g.fillRect(healthBoxWidth, 40, 35,35);
                    healthBoxWidth = healthBoxWidth + 40;
                }
            }
        };

        // add canvas to health panel
        healthPanel.add(healthCanvas);
        // update user interface
        healthPanel.updateUI();
    }

    public void displayResultsMessage(boolean isWin){
        // remove all elements from health panel
        healthPanel.removeAll();
        // define canvas
        healthCanvas = new Canvas(){
            public void paint(Graphics g){
                String resultsMessage = "";
                String newGameMessage = "Press <NEW> to start new game";

                int fontSize = 20;
                g.setFont(new Font("TimesRoman", Font.BOLD, fontSize));
                // check if player has won
                if(isWin){
                    resultsMessage = "YOU WON!!";
                }else{
                    resultsMessage = "YOU LOST!!";
                }

                g.setColor(Color.red);
                g.drawString(resultsMessage, 10, 30);
                g.setColor(Color.BLUE);
                g.drawString(newGameMessage, 10, 50);
            }
        };
        // add canvas to health panel
        healthPanel.add(healthCanvas);
        // update user interface
        healthPanel.updateUI();
    }

}
