package me.siavash.wotd.exception;


public class InvalidDateException extends IllegalArgumentException {

  public InvalidDateException(){
    super();
  }

  public InvalidDateException(String message){
    super(message);
  }

  public InvalidDateException(String message, Throwable ex){
    super(message, ex);
  }
}
