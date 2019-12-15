package com.atypon.upload.server.io.file;

import static com.atypon.upload.server.utils.AppConstants.*;
import com.atypon.upload.server.model.FileConfig;

import java.io.*;
import java.util.Optional;

/**
 * An implementation for StreamWriter for File, for more detailed documentation: {@see
 * StreamWriter}.
 */
public class FileWriter implements StreamWriter<FileConfig> {

  private String filePath;
  private FileOutputStream output;
  private BufferedOutputStream out;
  private FileConfig fileConfig;

  /**
   * * set config to the file stream
   *
   * @param fileConfig the file config
   */
  public void setConfig(FileConfig fileConfig) {
    this.fileConfig = fileConfig;
    filePath = String.format("%s/%s", FOLDER, fileConfig.getFileName());
    prepareFile();
    try {
      output = new FileOutputStream(filePath);
      out = new BufferedOutputStream(output);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Optional<String> write(InputStream in) {
    try {
      long fileLength = fileConfig.getFileSize();
      byte[] buffer = new byte[FILE_BUFFER];
      int count;
      long total = 0;
      while (total < fileLength
          && (count = in.read(buffer, 0, (int) Math.min(fileLength - total, buffer.length))) > 0) {
        out.write(buffer, 0, count);
        total += count;
      }
      return Optional.of(filePath);
    } catch (IOException e) {
      return Optional.empty();
    }
  }

  private void prepareFile() {
    if (filePath == null) throw new RuntimeException();
    File file = new File(filePath);
    file.getParentFile().mkdirs();
  }

  @Override
  public void close() throws Exception {
    out.close();
    output.close();
  }
}
