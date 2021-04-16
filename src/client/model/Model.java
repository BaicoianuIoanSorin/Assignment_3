package client.model;

import utility.observer.subject.LocalSubject;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Model extends LocalSubject<ArrayList<String>,String>
{
    void login(String name) throws RemoteException;
    void addLogs(ArrayList<String> logs);
    ArrayList<String> getLogs();
    String getName();
    void addLog(String log);
}
