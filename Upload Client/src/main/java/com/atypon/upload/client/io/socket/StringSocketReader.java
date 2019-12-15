package com.atypon.upload.client.io.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * An implementation for SocketReader for file, for more detailed documentation: {@see
 * SocketReader}.
 */
public class StringSocketReader implements SocketReader<String> {

  private final Socket socket;

  private final ObjectInputStream objectInputStream;

  private final InputStream inputStream;

  /**
   * * Parameterized constructor to initialize the StringSocketReader.
   *
   * @param socket the socket that we will read from
   */
  public StringSocketReader(Socket socket) {
    if (socket == null) throw new IllegalArgumentException();
    this.socket = socket;
    try {
      inputStream = socket.getInputStream();
      this.objectInputStream = new ObjectInputStream(inputStream);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public String readResponse() {
    try {
      return objectInputStream.readUTF();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void close() throws Exception {
    objectInputStream.close();
    inputStream.close();
  }
}
