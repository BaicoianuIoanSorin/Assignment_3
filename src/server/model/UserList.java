package server.model;

import java.util.ArrayList;

public class UserList
{
    private ArrayList<User> users;

    public UserList()
    {
        this.users = new ArrayList<>();
    }

    public ArrayList<User> getUsers()
    {
        return users;
    }

    public void addUser(String name)
    {
        boolean ok = true;
        for(int i = 0; i < users.size(); i++)
        {
            if(users.get(i).getName().equals(name))
            {
                ok = false;
            }
        }
        if(ok)
        {
            User user = new User(name);
            users.add(user);
        }
    }

    public void removeUser(String name)
    {
        for(int i = 0; i < users.size(); i++)
        {
            if(users.get(i).getName().equals(name))
            {
                users.remove(i);
            }
        }
    }

    public int size()
    {
        return users.size();
    }

    public User getUser(int number)
    {
        return users.get(number);
    }

}
