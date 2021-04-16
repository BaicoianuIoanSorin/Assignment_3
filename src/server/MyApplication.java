package server;

import javafx.application.Application;
import javafx.stage.Stage;
import server.model.Model;

import java.io.IOException;

public class MyApplication extends Application
{
  private MessageServer messageServer;

  public void start(Stage primaryStage) throws IOException {
    Model model = new ModelManager();
    messageServer = new MessageServer(model);
    Thread thread = new Thread(messageServer);
    thread.start();
  }
  @Override public void stop() throws IOException {
    messageServer.close();
  }
}
