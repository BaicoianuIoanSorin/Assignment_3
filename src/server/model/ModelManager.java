package server.model;

import mediator.MessageClientHandler;
import utility.observer.subject.NamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.util.ArrayList;

public class ModelManager implements Model
{
    private ArrayList<String> log;
    private PropertyChangeSupport propertyChangeSupport;
    private UserList userList;
    private ArrayList<MessageClientHandler> OnlineUsers;
    private File file;
    private PrintWriter out;

    public ModelManager() throws FileNotFoundException
    {
        this.log = new ArrayList<>();
        this.userList = new UserList();
        this.propertyChangeSupport = new PropertyChangeSupport(this);
        this.OnlineUsers = new ArrayList<>();
        this.file = new File("ChatLogs.txt");
        out = new PrintWriter(new FileOutputStream(file), true);
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    @Override public UserList getAllUsers()
    {
        return userList;
    }

    @Override public void addLog(String log1) throws IOException
    {
        log.add(log1);
        System.out.println("Added");
        propertyChangeSupport.firePropertyChange("Log",null,log);
        System.out.println("logs");
        out.println(log1);
        out.flush();
        System.out.println("Successfully saved log to file");
    }

    @Override public ArrayList<String> getLog()
    {
        return log;
    }

    @Override public int getConnectedUsersInt()
    {
        return userList.size();
    }

    @Override public UserList getConnectedUsers()
    {
        return userList;
    }


    @Override public void addUser(String name)
    {
        userList.addUser(name);
    }

    @Override public void removeUser(String name)
    {
        getAllUsers().removeUser(name);
    }

    @Override public void addListener(String propertyName,
                                      PropertyChangeListener listener)
    {
        if(propertyName == null)
        {
            propertyChangeSupport.addPropertyChangeListener(listener);
        }
        else propertyChangeSupport.addPropertyChangeListener(propertyName,listener);
    }

    @Override public void removeListener(String propertyName,
                                         PropertyChangeListener listener)
    {
        if(propertyName == null)
        {
            propertyChangeSupport.removePropertyChangeListener(listener);
        }
        else propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
    }
}



