package server.model;

import utility.observer.subject.NamedPropertyChangeSubject;

import java.io.IOException;
import java.util.ArrayList;

public interface Model extends NamedPropertyChangeSubject
{
    UserList getAllUsers();
    void addLog(String log) throws IOException;
    ArrayList<String> getLog();
    int getConnectedUsersInt();
    UserList getConnectedUsers();
    void addUser(String name);
    void removeUser(String name);
}