import twooter.*;
import java.io.*;

public class TwooterAPI extends TwooterApp{

    /*Register USERNAME and saves the returned
      token as USERNAME_TOKEN */
    public void REGISTER_USERNAME_FUNCTION(String REGISTER_USERNAME){
        try{
            String TOKEN = TWOOTERCLIENT.registerName(REGISTER_USERNAME);
            USERNAME = REGISTER_USERNAME;
            USERNAME_TOKEN = TOKEN;
        }catch(IOException ex){
            System.out.println("REGISTER_USERNAME_FUNCTION IOException Error");
            System.out.println("Function in TwooterAPI.java");
        }
    }

    /*Saves USERNAME and its token in currentAccount.txt.
    This will overwrite the previous username and token */
    public void SAVE_CURRENT_USERNAME_AND_TOKEN_FUNCTION(){
        try{
            BufferedWriter WRITER = new BufferedWriter(new FileWriter("currentAccount.txt"));
            WRITER.write(USERNAME);
            WRITER.newLine();
            WRITER.write(USERNAME_TOKEN);
            WRITER.close();
        }catch(IOException ex){
            System.out.println("SAVE_CURRENT_USERNAME_AND_TOKEN_FUNCTION IOException Error");
            System.out.println("Function in TwooterAPI.java");
        }
    }

    /*Sets USERNAME and USERNAME_TOKEN of this program instance from currentAccount.txt*/
    public void SET_USERNAME_AND_TOKEN_FUNCTION(){
        try{
            BufferedReader READER = new BufferedReader(new FileReader("currentAccount.txt"));
            USERNAME = READER.readLine();
            USERNAME_TOKEN = READER.readLine();
            READER.close();
        }catch(IOException ex){
            System.out.println("SET_USERNAME_AND_TOKEN_FUNCTION IOException Error");
            System.out.println("Function in TwooterAPI.java");
        }
    }

    /*Stores USERNAME and its token in accountRepository.txt
    This will append the file.*/
    public void STORE_USERNAME_AND_TOKEN_FUNCTION(){
        try{
            BufferedWriter WRITER = new BufferedWriter(new FileWriter("accountRepository.txt", true));
            WRITER.newLine();
            WRITER.append(USERNAME);
            WRITER.newLine();
            WRITER.append(USERNAME_TOKEN);
            WRITER.close();
        }catch(IOException ex){
            System.out.println("STORE_USERNAME_AND_TOKEN_FUNCTION IOException Error");
            System.out.println("Function in TwooterAPI.java");
        }
    }

    /*Reads and returns USERNAME in currentAccount.txt.
    Does not affect previous username and token */
    public String RETURNS_CURRENT_USERNAME_FUNCTION(){
        String USERNAME_IN_CURRENTACCOUNT = null;
        try{
            BufferedReader READER = new BufferedReader(new FileReader("currentAccount.txt"));
            USERNAME_IN_CURRENTACCOUNT = READER.readLine();
            READER.close();
        }catch(IOException ex){
            System.out.println("RETURNS_CURRENT_USERNAME_FUNCTION IOException Error");
            System.out.println("Function in TwooterAPI.java");
        }
        return USERNAME_IN_CURRENTACCOUNT;
    }

    /*Reads and returns USERNAME_TOKEN in currentAccount.txt.
    Does not affect previous username and token */
    public String RETURNS_CURRENT_USERNAME_TOKEN_FUNCTION(){
        String USERNAME_TOKEN_IN_CURRENTACCOUNT = null;
        try{
            BufferedReader READER = new BufferedReader(new FileReader("currentAccount.txt"));
            READER.readLine(); //Ignores first line
            USERNAME_TOKEN_IN_CURRENTACCOUNT = READER.readLine();
            READER.close();
        }catch(IOException ex){
            System.out.println("RETURNS_CURRENT_USERNAME_TOKEN_FUNCTION IOException Error");
            System.out.println("Function in TwooterAPI.java");
        }
        return USERNAME_TOKEN_IN_CURRENTACCOUNT;
    }

    /*Searches for USERNAME and returns its
    TOKEN from accountRepository.txt */
    public String RETURNS_USERNAME_TOKEN_FROM_USERNAME_FUNCTION(String SEARCH_USERNAME){
        String READER_USERNAME;
        String READER_TOKEN = null;
        try{
            BufferedReader READER = new BufferedReader(new FileReader("accountRepository.txt"));

            //Read first line
            READER_USERNAME = READER.readLine();
            //If not equals, read next line
            while (!READER_USERNAME.equals(SEARCH_USERNAME)){
                //Repeat
                READER_USERNAME = READER.readLine();
            }
            //Loop stops when username equals
            READER_TOKEN = READER.readLine();
            READER.close();

        }catch(IOException ex){
            System.out.println("RETURNS_USERNAME_TOKEN_FROM_USERNAME_FUNCTION IOException Error");
            System.out.println("Function in TwooterAPI.java");
        }
        return READER_TOKEN;
    }

    /*Refreshes USERNAME and TOKEN*/
    public void REFRESH_USERNAME_FUNCTION(String INPUT_NAME){
        String TOKEN = RETURNS_USERNAME_TOKEN_FROM_USERNAME_FUNCTION(INPUT_NAME);

        try{
            TWOOTERCLIENT.refreshName(INPUT_NAME, TOKEN);
        }catch(IOException ex){
            System.out.println("REFRESH_USERNAME_FUNCTION IOException Error");
            System.out.println("Function in TwooterAPI.java");
        }
    }
}

