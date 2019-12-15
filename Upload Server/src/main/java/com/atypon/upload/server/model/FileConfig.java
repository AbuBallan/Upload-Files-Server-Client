package com.atypon.upload.server.model;

import java.util.Objects;

/** * File Config that contains fileName (required), fileSize (required) */
public class FileConfig {

  private final String fileName;

  private final long fileSize;

  /**
   * * constructor to initialize the FileConfig.
   *
   * @param fileName the file name
   * @param fileSize the file size
   */
  private FileConfig(String fileName, long fileSize) {
    this.fileName = fileName;
    this.fileSize = fileSize;
  }

  public static FileConfig of(String fileName, long fileSize){
    return new FileConfig(fileName, fileSize);
  }

  public String getFileName() {
    return fileName;
  }

  public long getFileSize() {
    return fileSize;
  }

  @Override
  public String toString() {
    return "FileConfig{" + "fileName='" + fileName + '\'' + ", fileSize=" + fileSize + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    FileConfig that = (FileConfig) o;
    return getFileSize() == that.getFileSize() && getFileName().equals(that.getFileName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getFileName(), getFileSize());
  }
}
