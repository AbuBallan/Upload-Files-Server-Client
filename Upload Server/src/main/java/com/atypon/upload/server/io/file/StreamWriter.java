package com.atypon.upload.server.io.file;

import java.io.InputStream;
import java.util.Optional;

/**
 * * Socket Writer interface that write stream
 *
 * @param <T> the type of value that SocketWriter write.
 */
public interface StreamWriter<T> extends AutoCloseable {

  /**
   * * set stream config
   *
   * @param config the config to the stream
   */
  void setConfig(T config);

  /**
   * * to write stream
   *
   * @param in the InputStream to write
   * @return the response
   */
  Optional<String> write(InputStream in);
}
