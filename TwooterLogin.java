import twooter.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.Color.*;
import java.util.Scanner;

public class TwooterLogin extends TwooterApp{

    //LOGIN_PANEL Components
    private static JPanel LOGIN_PANEL = new JPanel();
    private static JTextField USERNAME_TEXTFIELD = new JTextField();
    private static JTextField PASSWORD_TEXTFIELD = new JTextField();
    private static JButton LOGIN_BUTTON = new JButton("Login");
    private static JLabel TWOOTER_LABEL = new JLabel("Twooter");

    //Multistate Components - Requires resets
    private static JLabel LOGIN_INCORRECT_LABEL = new JLabel("Login Incorrect");
    private static JButton CREATE_ACCOUNT_BUTTON = new JButton("Create Account");

    //Bools
    private boolean CURRENTLY_LOGGED_ON_BOOL;

    /*Determines whether currently Logged on or off*/
    public boolean RETURNS_CURRENTLY_LOGGED_ON_OR_OFF_FUNCTION(){
        try{
            BufferedReader READER = new BufferedReader(new FileReader("accountStatus.txt"));
            String CURRENT_STATE = READER.readLine();
            String TRUE_STRING = "true";

            if(CURRENT_STATE.equals(TRUE_STRING)){
                CURRENTLY_LOGGED_ON_BOOL = true;
            }
            else{
                CURRENTLY_LOGGED_ON_BOOL = false;
            }
            READER.close();
        }catch(IOException ex){
            System.out.println("RETURNS_CURRENTLY_LOGGED_ON_OR_OFF_FUNCTION IOException Error");
            System.out.println("Function in TwooterLogin.java");
        }
        return CURRENTLY_LOGGED_ON_BOOL;
    }

    /*Sets accountStatus to logged on */
    private void SET_LOGGED_ON_ACCOUNTSTATUS_FUNCTION(){
        try{
            BufferedWriter WRITER = new BufferedWriter(new FileWriter("accountStatus.txt"));
            WRITER.write("true");
            WRITER.close();
        }catch(IOException ex){
            System.out.println("SET_LOGGED_ON_ACCOUNTSTATUS_FUNCTION");
            System.out.println("Function in TwooterLogin.java");
        } 
    }

    /*Initialize Login Screen*/
    public void INITIALIZE_LOGIN_SCREEN_FUNCTION(){
        int LOGIN_PANEL_WIDTH = (FRAMEWIDTH / 3);
        int LOGIN_PANEL_HEIGHT = (FRAMEHEIGHT / 3);
        int LOGIN_PANEL_X_POSITION = LOGIN_PANEL_WIDTH;
        int LOGIN_PANEL_Y_POSITION = LOGIN_PANEL_HEIGHT;

        MAINFRAME.add(LOGIN_PANEL);
        LOGIN_PANEL.setBounds(LOGIN_PANEL_X_POSITION, LOGIN_PANEL_Y_POSITION, LOGIN_PANEL_WIDTH, LOGIN_PANEL_HEIGHT);
        LOGIN_PANEL.setLayout(null);

        LOGIN_PANEL.add(USERNAME_TEXTFIELD);
        LOGIN_PANEL.add(PASSWORD_TEXTFIELD);
        LOGIN_PANEL.add(LOGIN_BUTTON);
        LOGIN_PANEL.add(CREATE_ACCOUNT_BUTTON);
        LOGIN_PANEL.add(TWOOTER_LABEL);

        TWOOTER_LABEL.setBounds(110, -10, 200, 75);
        TWOOTER_LABEL.setFont(TWOOTER_LABEL.getFont().deriveFont(32.0f)); //Set font size float 32
        USERNAME_TEXTFIELD.setBounds(75, 50, 200, 20);
        PASSWORD_TEXTFIELD.setBounds(75, 80, 200, 20);
        LOGIN_BUTTON.setBounds(100, 110, 150, 20);
        CREATE_ACCOUNT_BUTTON.setBounds(100, 140, 150, 20);
        CREATE_ACCOUNT_BUTTON.setEnabled(true);
        LOGIN_INCORRECT_LABEL.setBounds(100, 170, 150, 20);

        //LOGIN_BUTTON add actionListener
        LOGIN_BUTTON.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                LOGIN_BUTTON_ACTIONLISTENER_FUNCTION();
            }
        });

        //CREATE_ACCOUNT_BUTTON add actionListener
        CREATE_ACCOUNT_BUTTON.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                CREATE_ACCOUNT_BUTTON_ACTIONLISTENER_FUNCTION();
            }
        });
    }

    //Remove all LOGIN_PANEL components - ie: clean slate this bitch
    public void CLEAN_SLATE_LOGIN_PANEL(){
        MAINFRAME.remove(LOGIN_PANEL);
        MAINFRAME.revalidate();
        MAINFRAME.repaint();
    }

    //LOGIN_BUTTON actionListener Function
    private void LOGIN_BUTTON_ACTIONLISTENER_FUNCTION(){
        String INPUTTED_USERNAME_STRING = USERNAME_TEXTFIELD.getText();
        String INPUTTED_PASSWORD_STRING = PASSWORD_TEXTFIELD.getText();

        String READER_PASSWORD = RETURNS_PASSWORD_FROM_USERNAME_FUNCTION(INPUTTED_USERNAME_STRING);
        
        //Check inputted username and password is correct or not
        if (INPUTTED_USERNAME_STRING.equals(INPUTTED_USERNAME_STRING) && INPUTTED_PASSWORD_STRING.equals(READER_PASSWORD)){
            LOGIN_PANEL.remove(LOGIN_INCORRECT_LABEL);
            SET_LOGGED_ON_ACCOUNTSTATUS_FUNCTION();
            USERNAME = INPUTTED_USERNAME_STRING;
            SHOW_OTHER_COMPONENTS_FUNCTION();
        }
        else{
            LOGIN_PANEL.add(LOGIN_INCORRECT_LABEL);
        }
    }

    //Returns password from username function
    private String RETURNS_PASSWORD_FROM_USERNAME_FUNCTION(String INPUTTED_USERNAME){
        String READER_USERNAME;
        String READER_PASSWORD = null;

        try{
            BufferedReader READER = new BufferedReader(new FileReader("loginRepository.txt"));
            READER_USERNAME = READER.readLine();
            while(!READER_USERNAME.equals(INPUTTED_USERNAME)){
                if(READER_USERNAME.equals(null)){
                    READER_PASSWORD = null;
                }
                else{
                    READER_USERNAME = READER.readLine();
                }
            }
            READER_PASSWORD = READER.readLine();
            READER.close();

        }catch(IOException ex){
            System.out.println("RETURNS_PASSWORD_FROM_USERNAME_FUNCTION IOException Error");
            System.out.println("Function in TwooterLogin.java");
        }
        return READER_PASSWORD;
    }

    /*CREATE_ACCOUNT_BUTTON actionListener function - appends loginRepository.txt
    Format: username-password, username-password where first line is username
    and password is below it.*/
    private void CREATE_ACCOUNT_BUTTON_ACTIONLISTENER_FUNCTION(){
        String INPUTTED_USERNAME_STRING = USERNAME_TEXTFIELD.getText();
        String INPUTTED_PASSWORD_STRING = PASSWORD_TEXTFIELD.getText(); 

        //First Line = username, Second Line = password
        try{
            //Add to loginRepostitory 
            BufferedWriter WRITER = new BufferedWriter(new FileWriter("loginRepository.txt", true));
            WRITER.newLine();
            WRITER.append(INPUTTED_USERNAME_STRING);
            WRITER.newLine();
            WRITER.append(INPUTTED_PASSWORD_STRING);
            WRITER.close();

            //Add username to usernameList
            BufferedWriter WRITER2 = new BufferedWriter(new FileWriter("usernameList.txt", true));
            WRITER2.newLine();
            WRITER2.append(INPUTTED_USERNAME_STRING);
            WRITER2.close();

            //Disable button
            CREATE_ACCOUNT_BUTTON.setText("Account Created");
            CREATE_ACCOUNT_BUTTON.setEnabled(false);
        }catch(IOException ex){
            System.out.println("CREATE_ACCOUNT_BUTTON_ACTIONLISTENER_FUNCTION IOException Error");
            System.out.println("Function in TwooterLogin.java");
        }
    }

    /*Show Logout panel, userbar and messages*/
    private void SHOW_OTHER_COMPONENTS_FUNCTION(){
        CLEAN_SLATE_LOGIN_PANEL();
        TWOOTERLOGOUT.INITIALIZE_LOGOUT_PANEL_FUNCTION();
        TWOOTERUSERBAR.INITIALIZE_USERBAR_FUNCTION();
    }
}