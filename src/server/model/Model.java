package server.model;


import utility.observer.subject.LocalSubject;

import java.io.IOException;
import java.util.ArrayList;

public interface Model extends LocalSubject<ArrayList<String>,String>
{
    UserList getAllUsers();
    void addLog(String log) throws IOException;
    ArrayList<String> getLog();
    int getConnectedUsersInt();
    ArrayList<String> getConnectedUsers();
    void addUser(String name);
    void removeUser(String name);
    User getUser(int i);
}