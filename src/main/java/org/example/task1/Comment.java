package org.example.task1;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Comment {
    public static void getCommentsForPost(int id) {
        String url = "https://jsonplaceholder.typicode.com/posts/" + id + "/comments";
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String commentsJSON = br.readLine();

            //Create JSONArray from commentsJSON
            JSONArray commentsArray = new JSONArray(commentsJSON);

            // Get user id
            JSONObject commentObj = commentsArray.getJSONObject(0);
            int userId = commentObj.getInt("postId");

            // Create file to which comments will be written
            String filename = "user-" + userId + "-post-" + id + "-comments.json";
            File file = new File(filename);

            // Write comments to file
            try (PrintWriter writer = new PrintWriter(file)) {
                writer.write(commentsJSON);
                System.out.println("Comments have been written to " + filename);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}