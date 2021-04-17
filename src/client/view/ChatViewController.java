package client.view;

import client.viewmodel.ChatViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

import java.io.IOException;

public class ChatViewController {
    @FXML
    private ListView<String> messages;
    @FXML
    private TextField newMessage;
    @FXML
    private Label activeUsers;
    private ViewHandler viewHandler;
    private Region root;
    private ChatViewModel viewModel;

    public ChatViewController()
    {

    }

    public void init(ViewHandler viewHandler, ChatViewModel viewModel, Region root)
    {
        this.viewModel = viewModel;
        this.viewHandler = viewHandler;
        this.root = root;
        activeUsers.textProperty().bind(viewModel.getActiveUsersProperty());
        messages.setItems(viewModel.getListOfMessages());
        newMessage.textProperty().bindBidirectional(viewModel.getNewMessageProperty());
    }

    public Region getRoot()
    {
        return root;
    }

    public void reset()
    {
        viewModel.reset();
    }

    @FXML private void onSend() throws IOException
    {
        viewModel.sendMessage();
        newMessage.setText("");
    }
}
