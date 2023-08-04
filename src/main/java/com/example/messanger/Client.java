package com.example.messanger;

import javafx.scene.layout.VBox;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {

  private ServerSocket serverSocket;
  private Socket socket;
  private BufferedReader bufferedReader;
  private BufferedWriter bufferedWriter;

  public Client(Socket socket) {
    try {
      this.socket = socket;
      this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Error Creating Server...");
      closeEverything(socket, bufferedReader, bufferedWriter);
    }
  }

  public void sendMessageToClient(String sendMessageToClient) {
    try {
      bufferedWriter.write(sendMessageToClient);
      bufferedWriter.newLine();
      bufferedWriter.flush();
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Error Sending message to client.");
      closeEverything(socket, bufferedReader, bufferedWriter);
    }
  }

  public void receiveMessageFromClient(VBox vBox) {
    new Thread(new Runnable() {
      @Override
      public void run() {
        while (socket.isConnected()) {
          try {
            String messageToSend = bufferedReader.readLine();
            HelloController.addLabel(messageToSend, vBox);
          } catch (IOException e) {
            e.printStackTrace();
            closeEverything(socket, bufferedReader, bufferedWriter);
            System.out.println("Error receiving Message From The Client");
            break;
          }
        }
      }
    }).start();
  }

  public void closeEverything
          (Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
    try {
      if(bufferedReader != null) {
        bufferedReader.close();
      }
      if(bufferedWriter != null) {
        bufferedWriter.close();
      }
      if(socket != null) {
        socket.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


}
