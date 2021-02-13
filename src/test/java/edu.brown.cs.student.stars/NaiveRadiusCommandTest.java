package edu.brown.cs.student.stars;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Class to test NaiveRadiusCommand.
 */
public class NaiveRadiusCommandTest {

  /**
   ** Tests whether run method works appropriately.
   */
  @Test
  public void testRun() {
    List<Double> test1C = new ArrayList<>();
    test1C.add(0.0);
    test1C.add(0.0);
    test1C.add(0.0);
    Star test1 = new Star(0, "test1", test1C);

    List<Double> test2C = new ArrayList<>();
    test2C.add(1.0);
    test2C.add(0.0);
    test2C.add(0.0);
    Star test2 = new Star(1, "test2", test2C);

    List<Double> test3C = new ArrayList<>();
    test3C.add(-2.0);
    test3C.add(0.0);
    test3C.add(0.0);
    Star test3 = new Star(2, "test3", test3C);

    List<Double> test4C = new ArrayList<>();
    test4C.add(0.0);
    test4C.add(4.0);
    test4C.add(3.0);
    Star test4 = new Star(3, "test4", test4C);

    List<Double> test5C = new ArrayList<>();
    test5C.add(-2.0);
    test5C.add(0.0);
    test5C.add(0.0);
    Star test5 = new Star(4, "test5", test5C);

    List<Double> test6C = new ArrayList<>();
    test6C.add(-5.0);
    test6C.add(0.0);
    test6C.add(0.0);
    Star test6 = new Star(5, "test6", test6C);

    List<Star> stars8 = new ArrayList<>();
    stars8.add(test1);
    stars8.add(test2);
    stars8.add(test3);
    stars8.add(test4);
    stars8.add(test5);
    stars8.add(test6);

    NaiveRadiusCommand command = new NaiveRadiusCommand(stars8);
    String[] args = new String[3];
    args[0] = "naive_radius";
    args[1] = "3";
    args[2] = "test1";
    command.run(args);
    List<Star> results = command.getResults();
    assertEquals(results.size(), 4);
    assertFalse(results.contains(test6));
    assertTrue(results.contains(test5));
    assertTrue(results.contains(test3));
    assertTrue(results.contains(test2));
    assertTrue(results.contains(test1));

    command = new NaiveRadiusCommand(stars8);
    args = new String[5];
    args[0] = "naive_radius";
    args[1] = "3";
    args[2] = "0";
    args[3] = "0";
    args[4] = "0";
    command.run(args);
    results = command.getResults();
    assertEquals(results.size(), 4);
    assertFalse(results.contains(test6));
    assertTrue(results.contains(test5));
    assertTrue(results.contains(test3));
    assertTrue(results.contains(test2));
    assertTrue(results.contains(test1));

    command = new NaiveRadiusCommand(stars8);
    args = new String[5];
    args[0] = "naive_radius";
    args[1] = "1";
    args[2] = "0";
    args[3] = "0";
    args[4] = "0";
    command.run(args);
    results = command.getResults();
    assertEquals(results.size(), 2);
    assertTrue(results.contains(test1));
    assertTrue(results.contains(test2));

    command = new NaiveRadiusCommand(stars8);
    args = new String[5];
    args[0] = "naive_radius";
    args[1] = "0";
    args[2] = "0";
    args[3] = "0";
    args[4] = "0";
    command.run(args);
    results = command.getResults();
    assertEquals(results.size(), 1);
    assertTrue(results.contains(test1));

    command = new NaiveRadiusCommand(stars8);
    args = new String[5];
    args[0] = "naive_radius";
    args[1] = "Sol";
    args[2] = "0";
    args[3] = "0";
    args[4] = "0";
    String err = command.run(args);
    results = command.getResults();
    assertEquals("ERROR: Radius must be a double", err);

    command = new NaiveRadiusCommand(stars8);
    args = new String[4];
    args[0] = "naive_radius";
    args[1] = "5";
    args[2] = "0";
    args[3] = "0";
    err = command.run(args);
    results = command.getResults();
    assertEquals("ERROR: incorrect number of arguments for naive_radius method", err);

    command = new NaiveRadiusCommand(stars8);
    args = new String[3];
    args[0] = "naive_radius";
    args[1] = "5";
    args[2] = "\"\"";
    err = command.run(args);
    results = command.getResults();
    assertEquals("ERROR: Star name cannot be empty string", err);

    command = new NaiveRadiusCommand(stars8);
    args = new String[5];
    args[0] = "naive_radius";
    args[1] = "5";
    args[2] = "broken coordinate";
    args[3] = "0";
    args[4] = "0";
    err = command.run(args);
    results = command.getResults();
    assertEquals("ERROR: Input coordinates not numbers", err);

  }
}
