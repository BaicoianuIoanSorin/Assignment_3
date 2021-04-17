package client;

import client.mediator.Client;
import javafx.application.Application;
import javafx.stage.Stage;
import client.model.*;
import client.viewmodel.*;
import client.view.*;

import java.io.IOException;

public class MyApplication extends Application
{
  public void start(Stage primaryStage) throws IOException {
    try
    {
      Model model = new ModelManager();
      ViewModelFactory viewModelFactory = new ViewModelFactory(model);
      ViewHandler view = new ViewHandler(viewModelFactory);
      Client client = new Client(model);
      view.start(primaryStage);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}
