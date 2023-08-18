package org.example.task1;



import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Comment {
    public static void getCommentsForPost(int id) {
        String url = "https://jsonplaceholder.typicode.com/posts/" + id + "/comments";
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type" , "application/json")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            String commentsJSON = null;
            try {
                commentsJSON = HttpClient.newHttpClient()
                        .send(request, HttpResponse.BodyHandlers.ofString()).body();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


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