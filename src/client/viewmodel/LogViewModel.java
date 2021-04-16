package client.viewmodel;

import client.model.Model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.rmi.RemoteException;


public class LogViewModel {
    private StringProperty userNameProperty;
    private StringProperty errorLabelProperty;
    private Model model;

    public LogViewModel(Model model)
    {
        this.model = model;

        userNameProperty = new SimpleStringProperty("");
        errorLabelProperty = new SimpleStringProperty("");
    }

    public StringProperty getUserNameProperty()
    {
        return userNameProperty;
    }

    public StringProperty getErrorLabelProperty()
    {
        return errorLabelProperty;
    }

    public void reset()
    {
        userNameProperty.set("");
        errorLabelProperty.set("");
    }

    public void enterName() throws RemoteException {
        model.login(userNameProperty.get());
    }
}
