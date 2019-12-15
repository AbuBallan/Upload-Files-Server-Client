package com.atypon.upload.server.io.socket;

import com.atypon.upload.server.model.FileConfig;
import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * An implementation for SocketReader for file, for more detailed documentation: {@see
 * SocketReader}.
 */
public class FileSocketReader implements SocketReader<FileConfig> {

  private final Socket socket;
  private final Gson gson;
  private final InputStream inputStream;
  private final ObjectInputStream clientData;
  private final BufferedInputStream fileStream;

  /**
   * * Parameterized constructor to initialize the FileSocketReader.
   *
   * @param socket the socket that we will read from
   * @param gson Gson object
   */
  public FileSocketReader(Socket socket, Gson gson) {
    if (socket == null || gson == null) throw new IllegalArgumentException();
    this.socket = socket;
    this.gson = gson;
    try {

      inputStream = socket.getInputStream();
      clientData = new ObjectInputStream(inputStream);
      fileStream = new BufferedInputStream(inputStream);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public FileConfig readConfig() {
    try {
      String request = clientData.readUTF();
      return gson.fromJson(request, FileConfig.class);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public InputStream stream() {
    return fileStream;
  }

  @Override
  public void close() throws IOException {
    inputStream.close();
    clientData.close();
    fileStream.close();
  }
}
