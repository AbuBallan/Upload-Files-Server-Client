package com.atypon.upload.client.io.file;

import java.io.InputStream;

/**
 * * Socket Writer interface that write stream
 *
 * @param <T> the type of value that SocketWriter write.
 */
public interface StreamReader<T> extends AutoCloseable {

  /**
   * * read stream config
   *
   * @return the config to the stream
   */
  T readConfig();

  /**
   * * read stream from source with config
   *
   * @return input stream
   */
  InputStream readStream();
}
