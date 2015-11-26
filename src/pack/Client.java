import java.io.*;
import java.net.*;

/**
 * Maakt een connectie met een server en is een CLI om CRUD operaties op een database uit te voeren
 */
public class Client {
    //Instructie voor de gebruiker
    private static String instructieString =
            "Naam invoeren: 'create <naam>'\n" +
            "Alle namen weergeven: 'read'\n" +
            "Naam aanpassen: 'update <oudenaam> <nieuwenaam>'\n" +
                    "Naam verwijderen: 'delete <naam>'";

    public static void main(String[] args) {
        //Geef de instructies weer
        System.out.println(instructieString);
        String hostName = "localhost";

        try {
            int portNumber = 4444;
            //Maak een socket verbinding aan op een bepaalde host en poort
            Socket echoSocket = new Socket(hostName, portNumber);
            //OutStream om data naar de server te sturen
            PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
            //InStream om data van de server uit te lezen
            BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            //Command-line input
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            //De input van de gebruiker
            String userInput;
            //Als de gebruiker een regel input geeft, stuur dan deze regel naar de server
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);

                //Geef de data die van de server komt weer
                System.out.println(in.readLine() + "\n");
                //Geef iedere keer weer de instructies weer
                System.out.println(instructieString);
            }
            echoSocket.close();
        } catch (IOException e) {
            System.out.println("Connectie met de server mislukt");
            e.printStackTrace();
        }
    }
}