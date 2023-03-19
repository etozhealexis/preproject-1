package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl usi = new UserServiceImpl();

//        usi.createUsersTable();
//
//        usi.saveUser("Alex", "Men", (byte) 18);
//
//        usi.removeUserById(3);
//
//        usi.cleanUsersTable();

        usi.cleanUsersTable();
    }
}
