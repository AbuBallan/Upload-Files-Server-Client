package com.atypon.upload.server;

import com.atypon.upload.server.core.ServerThread;

public class Main {

  /** Main class that runs the application through the main method. */
  public static void main(String[] args) {
    ServerThread serverThread = new ServerThread();
    serverThread.start();
  }
}
