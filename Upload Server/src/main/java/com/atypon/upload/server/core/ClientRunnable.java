package com.atypon.upload.server.core;

import com.atypon.upload.server.io.file.FileWriter;
import com.atypon.upload.server.io.file.StreamWriter;
import com.atypon.upload.server.io.socket.FileSocketReader;
import com.atypon.upload.server.io.socket.SocketReader;
import com.atypon.upload.server.io.socket.SocketWriter;
import com.atypon.upload.server.io.socket.StringSocketWriter;
import com.atypon.upload.server.model.FileConfig;
import com.google.gson.Gson;

import java.io.InputStream;
import java.net.Socket;
import java.util.Optional;
import java.util.function.Supplier;

/** A class that handles a single connection with a client */
public class ClientRunnable implements Runnable {

  private final Socket socket;
  private final ThreadLocal<Gson> gsonThreadLocal;

  /**
   * Parameterized constructor to initialize the ServerThread.
   *
   * @param socket The server socket.
   * @param gsonInitial The initial value of gsonThreadLoacl
   */
  public ClientRunnable(Socket socket, Supplier<? extends Gson> gsonInitial) {
    if (socket == null || gsonInitial == null) throw new IllegalArgumentException();
    this.gsonThreadLocal = ThreadLocal.withInitial(gsonInitial);
    this.socket = socket;
  }

  @Override
  public void run() {

    Gson gson = gsonThreadLocal.get();

    try (SocketReader<FileConfig> socketReader = new FileSocketReader(socket, gson);
        StreamWriter<FileConfig> streamWriteable = new FileWriter();
        SocketWriter<Optional<String>> socketWritable = new StringSocketWriter(socket)) {
      FileConfig fileConfig = socketReader.readConfig();
      streamWriteable.setConfig(fileConfig);
      InputStream stream = socketReader.stream();
      Optional<String> remotePathOptional = streamWriteable.write(stream);
      socketWritable.write(remotePathOptional);
      if (remotePathOptional.isPresent())
        System.out.printf("%s uploaded successfully\n", remotePathOptional.get());
      else System.out.printf("%s failed\n", fileConfig.getFileName());
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }
}
