package se.jepp.dcbot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Test;

import java.util.List;

public class CoronaDataTest {
    @Test
    public void shouldReturnCoronaData() throws Exception {
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
                System.out.println(data);
        }
    }
    @Test
    public void shouldReturnSameCountry() throws Exception {
        String country = "sweden";
        String cData = "";
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
        System.out.println(cData);
    }
}
