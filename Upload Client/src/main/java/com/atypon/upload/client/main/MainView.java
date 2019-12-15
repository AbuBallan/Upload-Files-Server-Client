package com.atypon.upload.client.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** * Main View that prints to console */
public class MainView {

  private MainController controller;

  private InputStreamReader in;
  private BufferedReader bufferedReader;

  /**
   * * Parameterized constructor to initialize the MainView.
   *
   * @param controller instance of MainController
   */
  public MainView(MainController controller) {
    this.controller = controller;
  }

  public void start() {
    controller.onAttach(this);
  }

  public void printWelcomeMsg() {
    System.out.println("================================");
    System.out.println("Welcome to ballan Upload Files System!!!");
    System.out.println("================================");
    System.out.println("(-1) exit");
  }

  public void listenToInput() {
    try {
      in = new InputStreamReader(System.in);
      bufferedReader = new BufferedReader(in);

      String path;
      boolean isRunning = true;
      while (isRunning) {
        System.out.println("Enter loaclPath: ");
        path = bufferedReader.readLine();
        isRunning = controller.onReceivePath(path);
      }

      bufferedReader.close();
      in.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void printIncorrectPath() {
    System.out.println("Incorrect Path!!!");
  }

  public void printSuccessResponse(String remotePath) {
    System.out.printf("Upload Successfully : %s\n", remotePath);
  }

  public void printFailedResponse() {
    System.out.println("Failed!!!");
  }
}
