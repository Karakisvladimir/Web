package org.example;

import java.io.IOException;

import static org.example.task1.Comment.getCommentsForPost;
import static org.example.task1.OpenTask.printOpenTasksForUser;
import static org.example.task1.WorkInObject.*;

public class Main {
    public static void main(String[] args) {
        try {

            createUser();
            updateUser();
            deleteUser();
            getAllUsers();
            getUserById("5");
            getUserByUsername();
            getCommentsForPost(10);
            printOpenTasksForUser(10);


        } catch (IOException e) {
            throw new RuntimeException(e);

        }

    }

}


