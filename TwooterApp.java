import javax.swing.JFrame;

import org.omg.CORBA.INITIALIZE;

import twooter.*;

public class TwooterApp{

    //TwooterApp
    static TwooterApp TWOOTERAPP = new TwooterApp();
        static TwooterLogin TWOOTERLOGIN = new TwooterLogin();
        static TwooterLogout TWOOTERLOGOUT = new TwooterLogout();
        static TwooterUserbar TWOOTERUSERBAR = new TwooterUserbar();

    //TwooterClient
    static TwooterClient TWOOTERCLIENT = new TwooterClient();
    
    //Username, Password and Token
    static String USERNAME;
    static String PASSWORD;
    static String USERNAME_TOKEN;

    //TwooterApp Swing Components
    static JFrame MAINFRAME = new JFrame("Twooter!");
        static int FRAMEWIDTH = 1200;
        static int FRAMEHEIGHT = 800;
    
    //Initialize Frame and Panel
    private void INITIALIZE_TWOOTERAPP_FUNCTION(){
        MAINFRAME.setSize(FRAMEWIDTH, FRAMEHEIGHT);
        MAINFRAME.setVisible(true);
        MAINFRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MAINFRAME.setLayout(null);
        MAINFRAME.setResizable(false);
    }
    public static void main(String[] args){
        TWOOTERAPP.INITIALIZE_TWOOTERAPP_FUNCTION();
        
        //Logic Script
        Boolean IS_LOGGED_ON = TWOOTERLOGIN.RETURNS_CURRENTLY_LOGGED_ON_OR_OFF_FUNCTION();
        if (!IS_LOGGED_ON){
            TWOOTERLOGIN.INITIALIZE_LOGIN_SCREEN_FUNCTION();
        }
        else if (IS_LOGGED_ON){
            TWOOTERLOGOUT.INITIALIZE_LOGOUT_PANEL_FUNCTION();
            TWOOTERUSERBAR.INITIALIZE_USERBAR_FUNCTION();
        }
    } 
}