package com.atypon.upload.server.io.socket;

/**
 * * Socket Writer interface that write Value
 *
 * @param <T> the type of value that SocketWriter write.
 */
public interface SocketWriter<T> extends AutoCloseable {

  /**
   * * write value to the socket
   *
   * @param t the output to the socket
   */
  void write(T t);
}
