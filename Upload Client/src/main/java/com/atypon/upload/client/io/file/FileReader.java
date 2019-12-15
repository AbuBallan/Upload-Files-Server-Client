package com.atypon.upload.client.io.file;

import com.atypon.upload.client.data.model.FileConfig;

import java.io.*;

/**
 * An implementation for StreamReader for File, for more detailed documentation: {@see
 * StreamReader}.
 */
public class FileReader implements StreamReader<FileConfig> {

  private File file;
  private FileInputStream fileInputStream;
  private BufferedInputStream in;

  /**
   * * constructor to initialize the FileReader.
   *
   * @param file the file to read from
   */
  public FileReader(File file) {
    validateFile(file);
    this.file = file;
  }

  private void validateFile(File file) {
    if (!file.exists() || !file.isFile()) {
      FileNotFoundException fileNotFoundException = new FileNotFoundException();
      throw new RuntimeException(fileNotFoundException);
    }
  }

  @Override
  public FileConfig readConfig() {
    String fileName = file.getName();
    long fileSize = file.length();
    FileConfig fileConfig = FileConfig.of(fileName, fileSize);
    return fileConfig;
  }

  @Override
  public InputStream readStream() {
    try {
      fileInputStream = new FileInputStream(file);
      in = new BufferedInputStream(fileInputStream);
      return in;
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void close() throws IOException {
    in.close();
    fileInputStream.close();
  }
}
