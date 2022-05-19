package se.jepp.dcbot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;

public class BotBrain {
    private static final char PREFIX = '!';
    private static final String HELLO_COMMAND = "hello";

    private String message;
    private String user;
    private String[] words;
    public String cData = "";
    public String country;
    private boolean isCommand = false;
    private boolean isEmbed = false;
    private boolean isMe = false;

    public BotBrain(String message, String user){
        if ("Pandemico".equalsIgnoreCase(user)){
            isMe = true;
        }
        this.message = message;
        this.user = user;
        this.words = message.split(" ");
        isCommand = message.trim().startsWith(PREFIX + "");
        this.message = this.message.replaceAll(PREFIX + "", "");

        isEmbed = isCommand && (message.toLowerCase().contains(HELLO_COMMAND));
    }
    public boolean isEmbed() { return isEmbed; }
    public boolean isMe() { return isMe; }

    public String respond() throws IOException {
        if (message.indexOf("cData")>=0) {
            isCommand = true;
            country = words[1];
            OkHttpClient client = new OkHttpClient();

            String url = "https://raw.githubusercontent.com/zmsp/coronavirus-json-api/master/latest.json";
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            String jsonString = response.body().string();

            ObjectMapper objectMapper = new ObjectMapper();
            TypeFactory typeFactory = objectMapper.getTypeFactory();
            List<CoronaData> someClassList = objectMapper.readValue(jsonString, typeFactory.constructCollectionType(List.class, CoronaData.class));

            for (CoronaData data : someClassList) {
                if(data.getCountry().equalsIgnoreCase(country)) {
                    cData += data+"\n";
                }
            }
            if (cData.equalsIgnoreCase(""))
            {
                return "Could not find data for entered country";
            }
            return cData;
        }
        return "No message selected";
    }
    public boolean isCommand() { return isCommand; }
}
