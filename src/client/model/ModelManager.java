package client.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ModelManager implements Model
{
    private ArrayList<String> log;
    private ArrayList<String> messages;
    private String name;
    private PropertyChangeSupport propertyChangeSupport;

    public ModelManager()
    {
        this.name = "";
        this.messages = new ArrayList<>();
        this.log = new ArrayList<>();

        this.propertyChangeSupport = new PropertyChangeSupport(this);
    }

    @Override
    public void login(String name) {
        this.name = name;
        propertyChangeSupport.firePropertyChange("addUser", null, name);
        /** ->>> Property Change Support to be added(i think - Ionut) <<<- **/

    }

    @Override public void addLogs(ArrayList<String> logs)
    {
        log = logs;
        propertyChangeSupport.firePropertyChange("DisplayLog",null,log);
    }

    @Override public ArrayList<String> getLogs()
    {
        return log;
    }

    @Override public void addListener(String propertyName,
                                      PropertyChangeListener listener)
    {
        if(propertyName == null){
            propertyChangeSupport.addPropertyChangeListener(listener);
        }
        else {
            propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
        }
    }

    @Override public void removeListener(String propertyName,
                                         PropertyChangeListener listener)
    {
        if(propertyName == null){
            propertyChangeSupport.removePropertyChangeListener(listener);
        }
        else {
            propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
        }
    }
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
