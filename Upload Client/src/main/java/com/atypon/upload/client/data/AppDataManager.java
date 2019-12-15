package com.atypon.upload.client.data;

import com.atypon.upload.client.data.file.FileHelper;

import java.io.File;
import java.util.Optional;

/** An implementation for DataManager, for more detailed documentation: {@see DataManager}. */
public class AppDataManager implements DataManager {

  private static AppDataManager INSTANCE;

  private final FileHelper fileHelper;

  private AppDataManager(FileHelper fileHelper) {
    this.fileHelper = fileHelper;
  }

  public static AppDataManager getInstance(FileHelper fileHelper) {
    if (INSTANCE == null) {
      INSTANCE = new AppDataManager(fileHelper);
    }
    return INSTANCE;
  }

  @Override
  public Optional<String> uploadFile(File file) {
    return fileHelper.uploadFile(file);
  }
}
