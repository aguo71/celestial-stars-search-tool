package tools;

/**
 * Interface for classes which perform some behavior upon repl command.
 */
public interface Action {
  /**
   * Behavior/method to run.
   *
   * @param args arguments to method passed from repl command-line
   * @return string error message
   */
  String run(String[] args);

}
