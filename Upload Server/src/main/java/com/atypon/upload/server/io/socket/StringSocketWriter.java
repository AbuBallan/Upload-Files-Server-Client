package com.atypon.upload.server.io.socket;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Optional;

/**
 * An implementation for SocketWriter for String, for more detailed documentation: {@see
 * SocketWriter}.
 */
public class StringSocketWriter implements SocketWriter<Optional<String>> {

  private final Socket socket;

  private final ObjectOutputStream objectOutputStream;
  private final OutputStream outputStream;

  /**
   * * Parameterized constructor to initialize the StringSocketWriter.
   *
   * @param socket the socket that we will write to
   */
  public StringSocketWriter(Socket socket) {
    if (socket == null) throw new IllegalArgumentException();
    this.socket = socket;
    try {
      outputStream = socket.getOutputStream();
      this.objectOutputStream = new ObjectOutputStream(outputStream);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void write(Optional<String> s) {
    String response = s.orElse("false");
    try {
      objectOutputStream.writeUTF(response);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void close() throws Exception {
    objectOutputStream.close();
    outputStream.close();
  }
}
