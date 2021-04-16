package client.viewmodel;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import client.model.Model;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ChatViewModel implements LocalListener<ArrayList<String>,String> {
    private Model model;
    private ObservableList<String> messages;
    private StringProperty newMessage;
    private StringProperty userNameInfo;
    private StringProperty activeUsers;
    private PropertyChangeSupport propertyChangeSupport;
    private static ChatViewModel instance;
    private static Object lock = new Object();


    private ChatViewModel(Model model)
    {
        this.model = model;
        activeUsers = new SimpleStringProperty();
        newMessage = new SimpleStringProperty("");
        userNameInfo = new SimpleStringProperty("");
        messages = FXCollections.observableArrayList();
        messages.addAll(model.getLogs());
        propertyChangeSupport = new PropertyChangeSupport(this);
        this.model.addListener(this);
    }

    public static ChatViewModel getInstance(Model model)
    {
        if(instance ==null)
        {
            synchronized (lock)
            {
                if (instance==null)
                {
                    instance = new ChatViewModel(model);
                }
            }
        }
        return instance;
    }
    public StringProperty getNewMessageProperty()
    {
        return newMessage;
    }

    public StringProperty getUserNameInfoProperty()
    {
        return userNameInfo;
    }

    public StringProperty getActiveUsersProperty()
    {
        return activeUsers;
    }

    public ObservableList<String> getListOfMessages()
    {
        return messages;
    }

    public void reset()
    {
        /** ->>> More variables to be added <<<- **/
        messages.clear();
        messages.addAll(model.getLogs());
        newMessage.set("");
        activeUsers.set(null);
    }

    public void sendMessage()
    {
        propertyChangeSupport.firePropertyChange(model.getName(),null,newMessage.get());
    }


    @Override
    public void propertyChange(ObserverEvent<ArrayList<String>, String> event) {
        Platform.runLater(()->{
            messages.clear();
            if(event.getPropertyName().equals("getUserCount"))
            {
                activeUsers.set((String)event.getValue2());
            }
            else if(event.getPropertyName().equals("getUsersNames")){
                messages.add((String) event.getValue2());
            }
            else if(event.getPropertyName().equals("DisplayLog")){

                messages.addAll((ArrayList<String>)event.getValue1());
            }
        });
    }
}