package com.atypon.upload.client.main;

/**
 * * Main Controller that act as an interface between Model and View components to process all the
 * business logic and incoming requests
 */
public interface MainController {

  /**
   * * on attach main view
   *
   * @param mainView an instance of MainView
   */
  void onAttach(MainView mainView);

  /**
   * * on receive path from mainView
   *
   * @param localPath the localPath that comes form mainView
   * @return true to continue looping and listen for inputs from user otherwise ( false ) to stop
   *     listen for inputs
   */
  boolean onReceivePath(String localPath);
}
