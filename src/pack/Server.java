import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;

public class Server {
    //De poort voor client-server communicatie
    private static final int port = 4444;

    public static void main(String[] args) throws ParseException {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            //Accepteer sockets op de gedefinieerde poort
            Socket clientSocket = serverSocket.accept();
            //OutStream om naar de client te kunnen schrijven
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            //InStream om data van de client te ontvangen
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            //De regel data die de client stuurt naar de server
            String inputLine;
            //Maak een connectie met mongodb
            MongoClient mongoClient = new MongoClient();
            //Kies database "test"
            MongoDatabase db = mongoClient.getDatabase("test");

            while ((inputLine = in.readLine()) != null) {
                //Splits de argumenten bij spatiebalk
                String[] argumenten = inputLine.split("\\s+");

                if (argumenten[0].equals("create")) {
                    //Document toevoegen
                    db.getCollection("accounts").insertOne(new Document().append("naam", argumenten[1]));
                } else if(argumenten[0].equals("read")) {
                    //Alle documenten uitlezen (standaard)
                } else if(argumenten[0].equals("update")) {
                    //Verander een naam
                    db.getCollection("accounts").updateOne(new Document("naam", argumenten[1]), new Document("$set", new Document("naam", argumenten[2])));
                } else if(argumenten[0].equals("delete")) {
                    //Verwijder een document op basis van de naam die is meegegeven
                    db.getCollection("accounts").deleteOne(new Document("naam", argumenten[1]));
                }

                //Itereer door alle documenten en geef alle namen weer in de 'accounts' collectie
                FindIterable<Document> iterable = db.getCollection("accounts").find();

                final String[] namen = {""};

                iterable.forEach(new Block<Document>() {
                    @Override
                    public void apply(Document document) {
                        namen[0] += document.get("naam") + " ";
                    }
                });

                //Stuur deze namen naar de client
                out.println(namen[0]);
            }
            serverSocket.close();
            mongoClient.close();
        } catch (IOException e) {
            System.out.println("Exceptie opgetreden toen poort "
                    + port + " werd geprobeerd te bereiken");
            System.out.println(e.getMessage());
        }
    }

}