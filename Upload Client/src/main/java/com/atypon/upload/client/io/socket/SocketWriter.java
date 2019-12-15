package com.atypon.upload.client.io.socket;

import java.io.InputStream;

/**
 * * Socket Writer interface that write Value
 *
 * @param <T> the type of Config that SocketWriter write.
 */
public interface SocketWriter<T> extends AutoCloseable {

  /**
   * * write config to the socket
   *
   * @param config the config to write to the socket
   */
  void writeConfig(T config);

  /**
   * * write stream to the socket
   *
   * @param inputStream the input stream to write to the socket
   */
  void writeStream(InputStream inputStream);
}
