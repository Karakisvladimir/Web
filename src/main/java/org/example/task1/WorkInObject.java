package org.example.task1;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class WorkInObject {
    // метод для створення нового об'єкта
    public static void createUser() throws IOException {
        String baseUrl = "https://jsonplaceholder.typicode.com/users";
        String requestBody = "{\"name\": \"Victory user\", \"username\": \"victory\",\"email\":\"victory@gmail.com\"}";
        HttpRequest requestPost = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl))
                .header("Content-type", "application/json; charset=UtF-8")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        try {
            String body = HttpClient.newHttpClient().send(requestPost, HttpResponse.BodyHandlers.ofString()).body();
            System.out.println("New user was created: " + body);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //second variant -now work!!!
//        Document document = Jsoup.connect(baseUrl).data("name", "Vania").data("username", "vania")
//                .data("email", "john@gmail.com")
//                .followRedirects(false)
//                .ignoreContentType(true)
//                .post();
//
//        System.out.println("New user was created: " + document);
    }

    // відправка PUT запиту на API
    public static void updateUser() {
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://jsonplaceholder.typicode.com/users"))
                    .header("Content-Type", "application/json")
                    .method("PUT", HttpRequest.BodyPublishers.ofString("{\"name\":\"John Doe\",\"username\":\"johndoe\",\"email\":\"johndoe@example.com\"}")).build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.statusCode());
            System.out.println(response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // метод для видалення об'єкта
    public static void deleteUser() throws IOException {
        String baseUrl = "https://jsonplaceholder.typicode.com/users/2";

        Connection.Response res = Jsoup.connect(baseUrl).ignoreContentType(true).method(Connection.Method.DELETE).execute();

        System.out.println("User was deleted: " + (res.statusCode() == 200));
    }

    // метод для отримання інформації про всіх користувачів
    public static void getAllUsers() throws IOException {
        String baseUrl = "https://jsonplaceholder.typicode.com/users";
        Document doc = Jsoup.connect(baseUrl).ignoreContentType(true).get();
        System.out.println("All users info: " + doc);
    }
    // метод для отримання інформації про користувача за id
    public static String getUserById(String id) throws IOException {
        String baseUrl = "https://jsonplaceholder.typicode.com/users/" + id;
        Document doc1 = Jsoup.connect(baseUrl).ignoreContentType(true).get();
        System.out.println("doc1 = " + doc1);
        return doc1.toString();
    }




    // метод для отримання інформації про користувача за username
    public static void getUserByUsername() throws IOException {
        String baseUrl = "https://jsonplaceholder.typicode.com/users?username=Bret";
        Document doc2 = Jsoup.connect(baseUrl).ignoreContentType(true).get();
        System.out.println("User info by username: " + doc2);
    }

}

