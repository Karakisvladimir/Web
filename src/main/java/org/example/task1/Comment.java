package org.example.task1;
import org.jsoup.nodes.Document;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import java.io.FileWriter;
import java.io.IOException;

public class Comment {


    public static void getAndSaveCommentsByUser() throws IOException {
        String baseUrl = "https://jsonplaceholder.typicode.com/users/1/posts";
        Document doc = Jsoup.connect(baseUrl).ignoreContentType(true).get();
        JSONObject lastPostObj = new JSONObject(doc.body().text());
        JSONArray lastPostArray = lastPostObj.getJSONArray("posts");
        int lastPostId = lastPostArray.getJSONObject(lastPostArray.length() - 1).getInt("id");

        // формируем URL для запроса комментариев
        String commentsUrl = "https://jsonplaceholder.typicode.com/posts/" + lastPostId + "/comments";
        Document doc2 = Jsoup.connect(commentsUrl).ignoreContentType(true).get();

        JSONObject commentsObj = new JSONObject(doc2.text());
        JSONArray commentsArray = commentsObj.getJSONArray("comments");

        FileWriter jsonFileWriter = new FileWriter("resources/user-1-post-" + lastPostId + "-comments.json");
        jsonFileWriter.write(commentsArray.toString(2));
        jsonFileWriter.close();

        System.out.println("All comments for last post: " + commentsArray);
    }
}