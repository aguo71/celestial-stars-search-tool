package edu.brown.cs.student.stars;

/**
 * Interface for classes which perform some behavior upon repl command.
 */
public interface Action {
  /**
   * Behavior/method to run.
   *
   * @param args arguments to method passed from repl command-line
   */
  void run(String[] args);

}
