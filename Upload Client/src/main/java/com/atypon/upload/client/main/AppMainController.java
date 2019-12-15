package com.atypon.upload.client.main;

import com.atypon.upload.client.data.DataManager;

import java.io.File;
import java.util.Optional;

/** An implementation for MainController, for more detailed documentation: {@see MainController}. */
public class AppMainController implements MainController {

  private final DataManager dataManager;

  private MainView mainView;

  /**
   * * constructor to initialize the AppMainController.
   *
   * @param dataManager the instance of DataManager that represent Model on MVC
   */
  public AppMainController(DataManager dataManager) {
    this.dataManager = dataManager;
  }

  @Override
  public void onAttach(MainView mainView) {
    this.mainView = mainView;
    start();
  }

  private void start() {
    mainView.printWelcomeMsg();
    mainView.listenToInput();
  }

  @Override
  public boolean onReceivePath(String localPath) {
    if (localPath.equals("-1")) return false;

    Optional<File> optionalFile = getFileFromPath(localPath);
    optionalFile.ifPresent(this::uploadAndPrintResponse);
    if (!optionalFile.isPresent()) mainView.printIncorrectPath();

    return true;
  }

  private void uploadAndPrintResponse(File file) {
    Optional<String> remotePathOptional = dataManager.uploadFile(file);
    remotePathOptional.ifPresent(mainView::printSuccessResponse);
    if (!remotePathOptional.isPresent()) mainView.printFailedResponse();
  }

  private Optional<File> getFileFromPath(String localPath) {
    if (validateLocalPath(localPath)) {
      File value = new File(localPath);
      return Optional.of(value);
    } else {
      return Optional.empty();
    }
  }

  private boolean validateLocalPath(String localPath) {
    File file = new File(localPath);
    return file.exists() && file.isFile();
  }
}
