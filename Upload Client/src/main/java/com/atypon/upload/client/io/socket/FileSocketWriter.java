package com.atypon.upload.client.io.socket;

import com.atypon.upload.client.data.model.FileConfig;
import com.google.gson.Gson;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * An implementation for SocketWriter for String, for more detailed documentation: {@see
 * SocketWriter}.
 */
public class FileSocketWriter implements SocketWriter<FileConfig> {

  private final Socket socket;
  private final Gson gson;
  private final ObjectOutputStream objectOutputStream;
  private final BufferedOutputStream out;

  /**
   * * Parameterized constructor to initialize the FileSocketWriter.
   *
   * @param socket the socket that we will write to
   * @param gson gson object
   */
  public FileSocketWriter(Socket socket, Gson gson) {
    if (socket == null || gson == null) throw new IllegalArgumentException();
    this.socket = socket;
    this.gson = gson;

    try {
      objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
      out = new BufferedOutputStream(socket.getOutputStream());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void writeConfig(FileConfig config) {
    try {
      String request = gson.toJson(config);
      objectOutputStream.writeUTF(request);
      objectOutputStream.flush();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void writeStream(InputStream inputStream) {
    try {
      int data;
      while ((data = inputStream.read()) != -1) {
        out.write(data);
      }
      out.flush();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void close() throws IOException {
    out.close();
    objectOutputStream.close();
  }
}
