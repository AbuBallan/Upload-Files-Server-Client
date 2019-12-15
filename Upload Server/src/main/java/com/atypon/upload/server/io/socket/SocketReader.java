package com.atypon.upload.server.io.socket;

import java.io.InputStream;

/**
 * * Socket Reader interface that read Value
 *
 * @param <T> the type of value that SocketReader read.
 */
public interface SocketReader<T> extends AutoCloseable {

  /**
   * * read config from the socket
   *
   * @return the config form socket
   */
  T readConfig();

  /**
   * * read stream from the socket
   *
   * @return the InputStream form socket
   */
  InputStream stream();
}
