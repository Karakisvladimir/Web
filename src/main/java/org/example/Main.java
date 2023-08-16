package org.example;

import java.io.IOException;

import static org.example.task1.Comment.getAndSaveCommentsByUser;
import static org.example.task1.WorkInObject.*;

// Press ⇧ twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        try {
          // createUser();
//            updateUser();
//            deleteUser();
//            getAllUsers();
  //          getUserById();
 //           getUserByUsername();
            getAndSaveCommentsByUser();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}


