package com.atypon.upload.client.io.socket;

/**
 * * Socket Reader interface that read Value
 *
 * @param <T> the type of value that SocketReader read.
 */
public interface SocketReader<T> extends AutoCloseable {

  /**
   * * read the response form socket
   *
   * @return the response from socket
   */
  T readResponse();
}
