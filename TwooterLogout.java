import twooter.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.Color.*;
import java.util.Scanner;

public class TwooterLogout extends TwooterApp{

    //LOGOUT_PANEL Components
    private static JPanel LOGOUT_PANEL = new JPanel();
    private static JLabel LOGGED_IN_AS_LABEL = new JLabel();
    private static JButton LOGOUT_BUTTON = new JButton("Logout");

    /*Sets accountStatus to logged off*/
    private void SET_LOGGED_OFF_ACCOUNTSTATUS_FUNCTION(){
        try{
            BufferedWriter WRITER = new BufferedWriter(new FileWriter("accountStatus.txt"));
            WRITER.write("false");
            WRITER.close();
        }catch(IOException ex){
            System.out.println("SET_LOGGED_OFF_ACCOUNTSTATUS_FUNCTION");
            System.out.println("Function in TwooterLogout.java");
        }  
    }

    /*Initialize Logout panels*/
    public void INITIALIZE_LOGOUT_PANEL_FUNCTION(){
        int LOGOUT_PANEL_WIDTH = 100;
        int LOGOUT_PANEL_HEIGHT = 50;
        int LOGOUT_PANEL_X_POSITION = (FRAMEWIDTH - 125);
        int LOGOUT_PANEL_Y_POSITION = 0;

        MAINFRAME.add(LOGOUT_PANEL);
        LOGOUT_PANEL.setBounds(LOGOUT_PANEL_X_POSITION, LOGOUT_PANEL_Y_POSITION, LOGOUT_PANEL_WIDTH, LOGOUT_PANEL_HEIGHT);
        LOGOUT_PANEL.setLayout(null);

        LOGOUT_PANEL.add(LOGGED_IN_AS_LABEL);
        LOGOUT_PANEL.add(LOGOUT_BUTTON);

        LOGGED_IN_AS_LABEL.setBounds(5, 0, 100, 25);
        LOGGED_IN_AS_LABEL.setText(USERNAME);
        LOGOUT_BUTTON.setBounds(0, 25, 75, 25);

        //LOGOUT_BUTTON add actionListener
        LOGOUT_BUTTON.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                LOGOUT_BUTTON_ACTIONLISTENER_FUNCTION();
            }
        });
    }

    //LOGOUT_BUTTON actionListener Function
    private void LOGOUT_BUTTON_ACTIONLISTENER_FUNCTION(){
        SET_LOGGED_OFF_ACCOUNTSTATUS_FUNCTION();
        SHOW_OTHER_COMPONENTS_FUNCTION();
    }

    //Remove all LOGOUT_PANEL components
    public void CLEAN_SLATE_LOGOUT_PANEL(){
        MAINFRAME.remove(LOGOUT_PANEL);
        MAINFRAME.revalidate();
        MAINFRAME.repaint();
    }

    //Back to login screen function
    private void SHOW_OTHER_COMPONENTS_FUNCTION(){
        CLEAN_SLATE_LOGOUT_PANEL();
        TWOOTERUSERBAR.CLEAN_SLATE_USERBAR_PANEL();
        TWOOTERLOGIN.INITIALIZE_LOGIN_SCREEN_FUNCTION();

        USERNAME = null;
        PASSWORD = null;
        USERNAME_TOKEN = null;
    }
}