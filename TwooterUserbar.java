import twooter.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.Color.*;
import java.util.Scanner;
import java.util.*;
import java.util.Arrays;
import java.util.List;

public class TwooterUserbar extends TwooterApp{

    //Components of TwooterUserbar
    private static JPanel USERBAR_PANEL = new JPanel();
    private static JTextArea USERBAR_TEXTAREA = new JTextArea();
    private static JLabel USERBAR_LABEL = new JLabel("Known Users");
    private static JScrollPane USERBAR_TEXTAREA_SCROLLPANE = new JScrollPane(USERBAR_TEXTAREA);
    
    //Dimensions
    private static int USERBAR_TEXTAREA_HEIGHT = 0;
    private static int USERBAR_PER_USERNAME_HEIGHT = 17;

    //List
    private static List<String> USERNAME_LIST = new ArrayList<String>();

    //Variables
    private static int USERNAME_AMOUNT = 0;

    /*Initialize Userbar*/
    public void INITIALIZE_USERBAR_FUNCTION(){
        int USERBAR_PANEL_WIDTH = (FRAMEWIDTH / 4);
        int USERBAR_PANEL_HEIGHT = (FRAMEWIDTH - 100);
        int USERBAR_PANEL_X_POSITION = (FRAMEWIDTH - 150);
        int USERBAR_PANEL_Y_POSITION = 50;

        MAINFRAME.add(USERBAR_PANEL);
        MAINFRAME.add(USERBAR_TEXTAREA_SCROLLPANE);
        USERBAR_PANEL.setBounds(USERBAR_PANEL_X_POSITION, USERBAR_PANEL_Y_POSITION, USERBAR_PANEL_WIDTH, USERBAR_PANEL_HEIGHT);
        USERBAR_PANEL.setLayout(null);

        USERBAR_PANEL.add(USERBAR_LABEL);
        USERBAR_PANEL.add(USERBAR_TEXTAREA_SCROLLPANE);

        USERBAR_LABEL.setBounds(20, 0, 140, 50);
        USERBAR_LABEL.setFont(USERBAR_LABEL.getFont().deriveFont(14.0f)); //Set font size float 14
        
        DETERMINE_USERBAR_TEXTAREA_HEIGHT_FUNCTION();
        USERBAR_TEXTAREA_SCROLLPANE.setBounds(0, 50, 140, USERBAR_TEXTAREA_HEIGHT);
        USERBAR_TEXTAREA.setEditable(false);

        ADD_USERNAMES_TO_USERBAR_TEXTAREA_FUNCTION();
    }

    //Function to calculate how tall the userbar panel should be
    private void DETERMINE_USERBAR_TEXTAREA_HEIGHT_FUNCTION(){
        int USERNAME_AMOUNT = 0;
        try{
            BufferedReader READER = new BufferedReader(new FileReader("loginRepository.txt"));
            while(READER.readLine() != null){
                USERNAME_AMOUNT++;
            }

            USERBAR_TEXTAREA_HEIGHT = (USERNAME_AMOUNT / 2) * USERBAR_PER_USERNAME_HEIGHT;
            if(USERBAR_TEXTAREA_HEIGHT >= (FRAMEHEIGHT - 200)){
                USERBAR_TEXTAREA_HEIGHT = (FRAMEHEIGHT - 200);
            }
            else{
                USERBAR_TEXTAREA_HEIGHT = (USERNAME_AMOUNT / 2) * USERBAR_PER_USERNAME_HEIGHT;
            }
            READER.close();
        }catch(IOException ex){
            System.out.println("DETERMINE_USERBAR_TEXTAREA_HEIGHT_FUNCTION IOException Error");
            System.out.println("Function in TwooterUserbar.java");
        }
    }

    //Function to add usernames only to userbar
    private void ADD_USERNAMES_TO_USERBAR_TEXTAREA_FUNCTION(){

        //Read and add odd lines to USERNAME_LIST
        try{
            BufferedReader READER = new BufferedReader(new FileReader("usernameList.txt"));
            USERBAR_TEXTAREA.read(READER, null);
            READER.close();
        }catch(IOException ex){
            System.out.println("ADD_USERNAME_TO_USERBAR_TEXTAREA_FUNCTION IOException Error");
            System.out.println("Function in TwooterUserbar.java");
        }
    }
    //Remove all USERBAR components
    public void CLEAN_SLATE_USERBAR_PANEL(){
        MAINFRAME.remove(USERBAR_PANEL);
        MAINFRAME.revalidate();
        MAINFRAME.repaint();
    }
}