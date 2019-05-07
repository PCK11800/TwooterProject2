import twooter.*;
import java.io.*;

public class MessagesAPI extends TwooterApp{

    //Message ID
    String MESSAGE_ID;

    /*Stores sender's username and message id
    for a specific message*/
    public void STORE_MESSAGE_USERNAME_AND_ID_FUNCTION(){
        try{
            BufferedWriter WRITER = new BufferedWriter(new FileWriter("messageRepository.txt", true));
            WRITER.newLine();
            WRITER.append(USERNAME);
            WRITER.newLine();
            WRITER.append(MESSAGE_ID);
            WRITER.close();
        }catch(IOException ex){
            System.out.println("STORE_MESSAGE_USERNAME_AND_ID_FUNCTION IOException Error");
            System.out.println("Function in MessagesAPI.java");
        }
    }

    /*Post Message and stores its message ID in
    messageRepository.txt */
    public void POST_AND_STORE_MESSAGE_FUNCTION(String INPUT_MESSAGE){
        String PLACEHOLDER_ID;
        try{
            PLACEHOLDER_ID = TWOOTERCLIENT.postMessage(USERNAME_TOKEN, USERNAME, INPUT_MESSAGE);
            MESSAGE_ID = PLACEHOLDER_ID;
            STORE_MESSAGE_USERNAME_AND_ID_FUNCTION();
        }catch(IOException ex){
            System.out.println("POST_AND_STORE_MESSAGE_FUNCTION IOException Error");
            System.out.println("Function in MessagesAPI.java");
        }
    }
}