package controller;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

// TODO: Make it an interface and create a custom LLama response.
public class GPTAdventure {
    private static final String API_URL = "https://api.openai.com/v1/completions";
    // TODO: Get api key from env variables or not checked in config file.
    private static final String API_KEY = null;

    /**
     *
     * @param context
     * @return
     * @throws Exception
     */
    public String getDynamicResponse(String context) throws Exception {
        if (API_KEY == null) {
            return "The cake is a lie..";
        }
        String response = getGPTResponse(context);
        return response;
    }

    public static String getGPTResponse(String prompt) throws Exception {
        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
        connection.setDoOutput(true);

        String jsonInputString = "{\"model\": \"text-davinci-003\", \"prompt\": \"" + prompt + "\", \"max_tokens\": 150}";
        try(OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);           
        }

        try(InputStream is = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            return response.toString();
        }
    }
}