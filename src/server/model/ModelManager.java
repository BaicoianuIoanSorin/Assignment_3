package server.model;



import utility.observer.listener.GeneralListener;
import utility.observer.subject.LocalSubject;
import utility.observer.subject.PropertyChangeHandler;

import java.io.*;
import java.util.ArrayList;

public class ModelManager implements Model
{
    private ArrayList<String> log;
    private UserList userList;
    private File file;
    private PrintWriter out;
    private PropertyChangeHandler<ArrayList<String>,String> propertyChangeHandler;

    public ModelManager() throws FileNotFoundException
    {
        this.log = new ArrayList<>();
        this.userList = new UserList();
        this.propertyChangeHandler = new PropertyChangeHandler<>(this);
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
        propertyChangeHandler.firePropertyChange("Added",log,null);
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

    @Override public ArrayList<String> getConnectedUsers()
    {
        ArrayList<String> returnArrayList = new ArrayList<>();
        for(int i=0;i<getConnectedUsersInt();i++){
            returnArrayList.add(getUser(i).getName());
        }
        return returnArrayList;
    }


    @Override public void addUser(String name)
    {
        userList.addUser(name);
    }

    @Override public void removeUser(String name)
    {
        getAllUsers().removeUser(name);
    }

    @Override
    public User getUser(int i) {
        return userList.getUser(i);
    }

    @Override
    public boolean addListener(GeneralListener<ArrayList<String>, String> listener, String... propertyNames) {
        return propertyChangeHandler.addListener(listener,propertyNames);
    }

    @Override
    public boolean removeListener(GeneralListener<ArrayList<String>, String> listener, String... propertyNames) {
        return propertyChangeHandler.removeListener(listener,propertyNames);
    }

//    @Override public void addListener(String propertyName,
//                                      PropertyChangeListener listener)
//    {
//        if(propertyName == null)
//        {
//            propertyChangeSupport.addPropertyChangeListener(listener);
//        }
//        else propertyChangeSupport.addPropertyChangeListener(propertyName,listener);
//    }
//
//    @Override public void removeListener(String propertyName,
//                                         PropertyChangeListener listener)
//    {
//        if(propertyName == null)
//        {
//            propertyChangeSupport.removePropertyChangeListener(listener);
//        }
//        else propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
//    }
}



