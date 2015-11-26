import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static org.junit.Assert.*;

public class ClientTest {

    @org.junit.Test
    public void testMain() throws Exception {
        String hostName = "localhost";
        int portNumber = 4444;
        //Maak een socket verbinding aan op een bepaalde host en poort
        Socket echoSocket = new Socket(hostName, portNumber);
        //OutStream om data naar de server te sturen
        PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);

        BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));

        out.println("create test");

        assertEquals("test ", in.readLine());

        out.println("delete test");
    }
}