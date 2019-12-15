package com.atypon.upload.client;

import com.atypon.upload.client.data.AppDataManager;
import com.atypon.upload.client.data.DataManager;
import com.atypon.upload.client.data.file.AppFileHelper;
import com.atypon.upload.client.data.file.FileHelper;
import com.atypon.upload.client.main.AppMainController;
import com.atypon.upload.client.main.MainController;
import com.atypon.upload.client.main.MainView;
import com.google.gson.Gson;

/** Main class that runs the application through the main method. */
public class Main {
  public static void main(String[] args) {
    Gson gson = new Gson();
    FileHelper fileHelper = AppFileHelper.getInstance(gson);
    DataManager dataManager = AppDataManager.getInstance(fileHelper);
    MainController controller = new AppMainController(dataManager);
    MainView mainView = new MainView(controller);
    mainView.start();
  }
}
