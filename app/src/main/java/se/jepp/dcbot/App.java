package se.jepp.dcbot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) throws LoginException, IOException {
        JDABuilder jda = JDABuilder.createDefault("OTQ5Mjk2MTUxNzYzNTA5MjY4.G7sMzG.9ZJYK1bjVQ_75am6mOnz2Q47EkYQSYLEyirlkE");
        jda.setStatus(OnlineStatus.ONLINE);
        jda.setActivity(Activity.listening("!cData"));
        jda.addEventListeners(new CBot());
        jda.build();

        System.out.println("***********************");
        URL url = ClassLoader.getSystemResource("data.json");
        String jsonString = URLReader(url);

        ObjectMapper objectMapper = new ObjectMapper();
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        List<CoronaData> someClassList = objectMapper.readValue(jsonString, typeFactory.constructCollectionType(List.class, CoronaData.class));
        for(CoronaData data : someClassList){
            System.out.println(data);
        }
    }

   /* public static void startBot() throws LoginException {
        JDABuilder jda = JDABuilder.createDefault("OTQ5Mjk2MTUxNzYzNTA5MjY4.YiIS7w.ZzIpNu-tNzJpBHUnOkubP48M05c");
        jda.setStatus(OnlineStatus.ONLINE);
        jda.setActivity(Activity.watching("cData"));
        jda.addEventListeners(new CBot());
        jda.build();
    }*/
    private static String URLReader(URL url)  throws IOException{
        try (InputStream in = url.openStream()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }
}
