package org.example.task1;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class WorkInObject {

    // метод для створення нового об'єкта
    public static void createUser() throws IOException {
        String baseUrl = "https://jsonplaceholder.typicode.com/users";
        Document document = Jsoup.connect(baseUrl).data("name", "Vania").data("username", "vania")
                .data("email", "john@gmail.com")
                .post();

        System.out.println("New user was created: " + document);
    }

    // метод для оновлення об'єкта
    public static void updateUser() throws IOException {
        String baseUrl = "https://jsonplaceholder.typicode.com/users";
        Document doc = (Document) Jsoup.connect(baseUrl).method(Connection.Method.PUT)
                .data("name", "John Doe")
                .data("username", "johndoe")
                .data("email", "johndoe@example.com")
                .execute();

        // якщо у відповіді на запит отримали такий самий JSON, що був відправлений
        // - значить об'єкт був успішно оновлений
        System.out.println("User was updated: " + doc);
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

        // отримуємо інформацію про всі користувачі
        System.out.println("All users info: " + doc);
    }

    // метод для отримання інформації про користувача за id
    public static void getUserById() throws IOException {
        String baseUrl1 = "https://jsonplaceholder.typicode.com";
        String baseUrl = "https://jsonplaceholder.typicode.com/users/11";
        Document doc1 = Jsoup.connect(baseUrl).ignoreContentType(true).get();
        System.out.println("User info by ID: " + doc1);
    }

    // метод для отримання інформації про користувача за username
    public static void getUserByUsername() throws IOException {
        String baseUrl = "https://jsonplaceholder.typicode.com/users?username=Bret";
        Document doc2 = Jsoup.connect(baseUrl).ignoreContentType(true).get();
        System.out.println("User info by username: " + doc2);
    }

}

