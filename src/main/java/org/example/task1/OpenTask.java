package org.example.task1;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class OpenTask {
    public static void printOpenTasksForUser(int userId) {
        String apiURL = "https://jsonplaceholder.typicode.com/users/" + userId + "/todos";
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(apiURL).openConnection();
            connection.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            // Перетворюємо рядок в JSON-масив
            JSONArray tasksJSON = new JSONArray(response.toString());

            // Беремо відкриті завдання для поточного користувача
            System.out.println("Open tasks for user " + userId);
            for (int i = 0; i < tasksJSON.length(); i++) {
                JSONObject task = tasksJSON.getJSONObject(i);
                if (!task.getBoolean("completed")) {
                    System.out.println(task.get("title"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}