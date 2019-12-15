package com.atypon.upload.server.core;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.atypon.upload.server.utils.AppConstants.PORT;

/**
 * ServerThread class which contains the main functionality of the server. The class extends Thread
 * as it runs independently from the interface thread from Main class.
 */
public class ServerThread extends Thread {

  // A boolean to check if the server is running or not.
  private boolean isRunning;

  // A ExecutorService contains ClientRunnable Threads
  private ExecutorService mExecutor;

  /** The method called when the server starts as a separate thread. */
  @Override
  public void run() {
    runServerSocket();
  }

  /** Listens to a connection, once listened, it answers the connection in Client thread. */
  private void runServerSocket() {

    mExecutor = Executors.newCachedThreadPool();

    this.isRunning = true;
    try (ServerSocket serverSocket = new ServerSocket(PORT)) {
      while (isRunning) {
        Socket socket = serverSocket.accept();
        ClientRunnable clientRunnable = new ClientRunnable(socket, Gson::new);
        mExecutor.submit(clientRunnable);
      }
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }

    mExecutor.shutdown();
  }

  /**
   * Stops the server from running by setting the isRunning flag used by the while loop to false and
   * also closing the ServerSocket which interrupts the listening for a new connection thus breaking
   * loop.
   */
  public void stopServer() {
    if (!isRunning) return;
    isRunning = false;
  }
}
