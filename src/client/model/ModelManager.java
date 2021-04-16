package client.model;

import client.mediator.Client;
import client.mediator.ClientInterface;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.LocalListener;
import utility.observer.subject.LocalSubject;
import utility.observer.subject.PropertyChangeHandler;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class ModelManager implements Model,LocalListener<ArrayList<String>,String>

{
    private ClientInterface client;
    private ArrayList<String> log;
    private ArrayList<String> messages;
    private String name;
    //private PropertyChangeSupport propertyChangeSupport;
    private PropertyChangeHandler<ArrayList<String>,String> propertyChangeHandler;

    public ModelManager() throws MalformedURLException, NotBoundException, RemoteException {
        this.name = "";
        this.messages = new ArrayList<>();
        this.log = new ArrayList<>();

        propertyChangeHandler = new PropertyChangeHandler<>(this,true);

        this.client = new Client(this);
        propertyChangeHandler.addListener(this);
    }

    @Override
    public void login(String name) throws RemoteException {
        this.name = name;
        client.addUser(name);


    }

    @Override public void addLogs(ArrayList<String> logs)
    {
        log = logs;
        //propertyChangeSupport.firePropertyChange("DisplayLog",null,log);
    }

    @Override public ArrayList<String> getLogs()
    {
        return log;
    }

//    @Override public void addListener(String propertyName,
//                                      PropertyChangeListener listener)
//    {
//        if(propertyName == null){
//            propertyChangeSupport.addPropertyChangeListener(listener);
//        }
//        else {
//            propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
//        }
//    }
//
//    @Override public void removeListener(String propertyName,
//                                         PropertyChangeListener listener)
//    {
//        if(propertyName == null){
//            propertyChangeSupport.removePropertyChangeListener(listener);
//        }
//        else {
//            propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
//        }
//    }
//
//  @Override
//  public ArrayList<String> getMessages(String activeUserName) {
//
//    for(int i = 0; i < userList.size(); i++)
//    {
//      if(userList.getUser(i).equals(activeUserName))
//      {
//        return userList.getUser(i).getMessages();
//      }
//    }
//    return null;
//  }

    @Override
    public String getName()
    {

        return name;
    }

    @Override public void addLog(String log)
    {
        this.log.add(log);
    }

    @Override
    public void propertyChange(ObserverEvent event) {
        propertyChangeHandler.firePropertyChange(event);
    }

    @Override
    public boolean addListener(GeneralListener<ArrayList<String>, String> listener, String... propertyNames) {
        return false;
    }

    @Override
    public boolean removeListener(GeneralListener<ArrayList<String>, String> listener, String... propertyNames) {
        return false;
    }

    //  @Override
//  public void sendMessage(String userName, String message)
//  {
//    for(int i = 0; i < userList.size(); i++)
//    {
//      if(userList.getUser(i).getName().equals(userName))
//      {
//        userList.getUser(i).addMessage(message);
//      }
//    }
//  }
}
