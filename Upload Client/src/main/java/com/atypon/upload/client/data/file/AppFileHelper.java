package com.atypon.upload.client.data.file;

import com.atypon.upload.client.data.model.FileConfig;
import com.atypon.upload.client.io.file.FileReader;
import com.atypon.upload.client.io.file.StreamReader;
import com.atypon.upload.client.io.socket.FileSocketWriter;
import com.atypon.upload.client.io.socket.SocketReader;
import com.atypon.upload.client.io.socket.SocketWriter;
import com.atypon.upload.client.io.socket.StringSocketReader;
import com.google.gson.Gson;

import java.io.File;
import java.io.InputStream;
import java.net.Socket;
import java.util.Optional;

import static com.atypon.upload.client.utils.AppConstants.HOST;
import static com.atypon.upload.client.utils.AppConstants.PORT;

/** An implementation for FileHelper, for more detailed documentation: {@see FileHelper}. */
public class AppFileHelper implements FileHelper {

  private static AppFileHelper INSTANCE;

  private final Gson gson;

  private AppFileHelper(Gson gson) {
    if (gson == null) throw new IllegalArgumentException();
    this.gson = gson;
  }

  public static AppFileHelper getInstance(Gson gson) {
    if (INSTANCE == null) {
      INSTANCE = new AppFileHelper(gson);
    }
    return INSTANCE;
  }

  @Override
  public Optional<String> uploadFile(File file) {
    String response = getServerResponse(file);
    if (response.equals("false")) return Optional.empty();
    else return Optional.of(response);
  }

  private String getServerResponse(File file) {
    try (Socket socket = new Socket(HOST, PORT);
        StreamReader<FileConfig> streamReader = new FileReader(file);
        SocketWriter<FileConfig> socketWriter = new FileSocketWriter(socket, gson);
        SocketReader<String> socketReader = new StringSocketReader(socket)) {

      FileConfig fileConfig = streamReader.readConfig();
      InputStream inputStream = streamReader.readStream();
      socketWriter.writeConfig(fileConfig);
      socketWriter.writeStream(inputStream);
      return socketReader.readResponse();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
