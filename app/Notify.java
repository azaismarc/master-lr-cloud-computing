import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import models.Rappel;
import play.jobs.Every;
import play.jobs.Job;
 
@Every("10s")
public class Notify extends Job<Rappel> {

    private URL url;

    public Notify() throws MalformedURLException {
        super();
        this.url =  new URL ("https://api.pushover.net/1/messages.json");
    }

    public void doJob() throws IOException {
        System.out.println("Run");
        List<Rappel> rappels = Rappel.findAll();

        for(Rappel rappel : rappels) {
            System.out.println(rappel.doNotify());
            if (rappel.doNotify()) {
                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json; utf-8");
                con.setRequestProperty("Accept", "application/json");
                con.setDoOutput(true);
                String jsonInputString = "{\"token\": \"av2f42j4oj2o63527xkj6n5v2jtfuf\",\"user\": \"u3k5emspp3okesh646pfhozevdt7yp\",\"message\": \""+rappel.message()+"\"}";
                try(OutputStream os = con.getOutputStream()) {
                    byte[] input = jsonInputString.getBytes("utf-8");
                    os.write(input, 0, input.length);			
                }
                try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "utf-8"))) {
                        StringBuilder response = new StringBuilder();
                        String responseLine = null;
                        while ((responseLine = br.readLine()) != null) {
                            response.append(responseLine.trim());
                        }
                        System.out.println(response.toString());
                        rappel.delete();
                }
            }
        }
    }
}