package com.atypon.upload.client.data.file;

import java.io.File;
import java.util.Optional;

/** * File Helper to simplify work with Files */
public interface FileHelper {

  Optional<String> uploadFile(File file);
}
