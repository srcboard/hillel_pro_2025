package org.example.lesson9_3;

public abstract class Processor<T,V> {
  private final String id;
  private final String type;
  protected final T data;

  public Processor(String id, String type, T data) {
    this.id = id;
    this.type = type;
    this.data = data;
  }

  public abstract V process();

  public String getId() {
    return id;
  }

  public String getType() {
    return type;
  }

  public T getData() {
    return data;
  }
}
