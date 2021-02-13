package edu.brown.cs.student.stars;


import org.junit.Test;
import tools.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tester class for model/property based testing of commands
 */
public class ModelBasedTest {

  /**
   ** Tests whether naive_neighbors and neighbors return the same output lists with 3 arguments.
   */
  @Test
  public void testNeighbors3Args() {
    int c = 0;
    while(c<500) {
      List<Star> stars = new ArrayList<>();
      // generates random stars dataset
      int length = ThreadLocalRandom.current().nextInt(1, 30);
      for(int x = 0; x < length; x++) {
        int id = ThreadLocalRandom.current().nextInt(0, 100);
        // generates random string: https://www.baeldung.com/java-random-string
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
            .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
            .limit(targetStringLength)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();

        List<Double> coords = new ArrayList<>();
        for(int z = 0; z < 3; z ++) {
          coords.add(ThreadLocalRandom.current().nextDouble(-1000, 1000));
        }
        Star star = new Star(id, generatedString, coords);
        stars.add(star);
      }

      // generates random args
      String[] args = new String[3];
      args[0] = "test";
      args[1] = Integer.toString(ThreadLocalRandom.current().nextInt(0, 30));
      List<Double> target;
      int targ = ThreadLocalRandom.current().nextInt(0, length);
      args[2] = "\"" + stars.get(targ).getName() + "\"";
      target = stars.get(targ).getCoordinates();


      KDTree<Star> tree = new KDTreeConstructor<Star>().buildTree(stars, 0);
      NeighborCommand nonnaive = new NeighborCommand(tree, stars);
      NaiveNeighborCommand naive = new NaiveNeighborCommand(stars);

      nonnaive.run(args);
      naive.run(args);

      List<Star> naiveSol = naive.getResults();
      TreeMap<Double, List<Star>> nonnaiveSol = nonnaive.getResults();
      int counter = 0;
      DistanceCalculator calc = new DistanceCalculator();
      for (Double distance : nonnaiveSol.keySet()) {
        for (Star star: nonnaiveSol.get(distance)) {
          double d1 = calc.getDistance(target, star.getCoordinates());
          double d2 = calc.getDistance(target, naiveSol.get(counter).getCoordinates());
          assertEquals(d1, d2, 0.000);
          assertTrue(stars.contains(naiveSol.get(counter)));
          assertTrue(stars.contains(star));
          counter ++;
        }
      }
      c++;
    }
  }

  /**
   ** Tests whether naive_neighbors and neighbors return the same output lists with 5 arguments.
   */
  @Test
  public void testNeighbors5Args() {
    int c = 0;
    while(c<500) {
      List<Star> stars = new ArrayList<>();
      // generates random stars dataset
      int length = ThreadLocalRandom.current().nextInt(0, 30);
      for(int x = 0; x < length; x++) {
        int id = ThreadLocalRandom.current().nextInt(0, 30);
        // generates random string: https://www.baeldung.com/java-random-string
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        String generatedString = "\"" + random.ints(leftLimit, rightLimit + 1)
            .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
            .limit(targetStringLength)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString() + "\"";

        List<Double> coords = new ArrayList<>();
        for(int z = 0; z < 3; z ++) {
          coords.add(ThreadLocalRandom.current().nextDouble(-1000, 1000));
        }
        Star star = new Star(id, generatedString, coords);
        stars.add(star);
      }

      // generates random args
      String[] args = new String[5];
      args[0] = "test";
      args[1] = Integer.toString(ThreadLocalRandom.current().nextInt(0, 30));
      List<Double> target = new ArrayList<>();
      for(int z = 0; z < 3; z ++) {
        double temp = (ThreadLocalRandom.current().nextDouble(-1000, 1000));
        args[z+2] = Double.toString(temp);
        target.add(temp);
      }
      KDTree<Star> tree;
      if(length == 0) {
        tree = new KDTree<Star>(null, null, null, -1);
        stars.add(new Star(-1, null, new ArrayList<Double>()));
      } else {
        tree = new KDTreeConstructor<Star>().buildTree(stars, 0);
      }
      NeighborCommand nonnaive = new NeighborCommand(tree, stars);
      NaiveNeighborCommand naive = new NaiveNeighborCommand(stars);

      nonnaive.run(args);
      naive.run(args);

      List<Star> naiveSol = naive.getResults();
      TreeMap<Double, List<Star>> nonnaiveSol = nonnaive.getResults();
      int counter = 0;
      DistanceCalculator calc = new DistanceCalculator();
      for (Double distance : nonnaiveSol.keySet()) {
        for (Star star: nonnaiveSol.get(distance)) {
          double d1 = calc.getDistance(target, star.getCoordinates());
          double d2 = calc.getDistance(target, naiveSol.get(counter).getCoordinates());
          assertEquals(d1, d2, 0.000);
          assertTrue(stars.contains(naiveSol.get(counter)));
          assertTrue(stars.contains(star));
          counter ++;
        }
      }
      c++;
    }
  }

  /**
   ** Tests whether naive_radius and radius return the same output lists with 5 arguments.
   */
  @Test
  public void testRadius5Args() {
    int c = 0;
    while(c<500) {
      List<Star> stars = new ArrayList<>();
      // generates random stars dataset
      int length = ThreadLocalRandom.current().nextInt(0, 30);
      for(int x = 0; x < length; x++) {
        int id = ThreadLocalRandom.current().nextInt(0, 30);
        // generates random string: https://www.baeldung.com/java-random-string
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        String generatedString = "\"" + random.ints(leftLimit, rightLimit + 1)
            .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
            .limit(targetStringLength)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString() + "\"";

        List<Double> coords = new ArrayList<>();
        for(int z = 0; z < 3; z ++) {
          coords.add(ThreadLocalRandom.current().nextDouble(-1000, 1000));
        }
        Star star = new Star(id, generatedString, coords);
        stars.add(star);
      }

      // generates random args
      String[] args = new String[5];
      args[0] = "test";
      args[1] = Double.toString(ThreadLocalRandom.current().nextDouble(-100, 100));
      List<Double> target = new ArrayList<>();
      for(int z = 0; z < 3; z ++) {
        double temp = (ThreadLocalRandom.current().nextDouble(-1000, 1000));
        args[z+2] = Double.toString(temp);
        target.add(temp);
      }

      KDTree<Star> tree;
      if(length == 0) {
        tree = new KDTree<Star>(null, null, null, -1);
        stars.add(new Star(-1, null, new ArrayList<Double>()));
      } else {
        tree = new KDTreeConstructor<Star>().buildTree(stars, 0);
      }
      RadiusCommand nonnaive = new RadiusCommand(tree, stars);
      NaiveRadiusCommand naive = new NaiveRadiusCommand(stars);

      nonnaive.run(args);
      naive.run(args);

      // compares results
      List<Star> naiveSol = naive.getResults();
      TreeMap<Double, List<Star>> nonnaiveSol = nonnaive.getResults();
      int counter = 0;
      DistanceCalculator calc = new DistanceCalculator();
      for (Double distance : nonnaiveSol.keySet()) {
        for (Star star: nonnaiveSol.get(distance)) {
          double d1 = calc.getDistance(target, star.getCoordinates());
          double d2 = calc.getDistance(target, naiveSol.get(counter).getCoordinates());
          assertEquals(d1, d2, 0.000);
          assertTrue(stars.contains(naiveSol.get(counter)));
          assertTrue(stars.contains(star));
          counter ++;
        }
      }
      c++;
    }
  }

  /**
   ** Tests whether naive_radius and radius return the same output lists with 3 arguments.
   */
  @Test
  public void testRadius3Args() {
    int c = 0;
    while(c<500) {
      List<Star> stars = new ArrayList<>();
      // generates random stars dataset
      int length = ThreadLocalRandom.current().nextInt(1, 30);
      for(int x = 0; x < length; x++) {
        int id = ThreadLocalRandom.current().nextInt(0, 30);
        // generates random string: https://www.baeldung.com/java-random-string
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        String generatedString = "\"" + random.ints(leftLimit, rightLimit + 1)
            .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
            .limit(targetStringLength)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString() + "\"";

        List<Double> coords = new ArrayList<>();
        for(int z = 0; z < 3; z ++) {
          coords.add(ThreadLocalRandom.current().nextDouble(-1000, 1000));
        }
        Star star = new Star(id, generatedString, coords);
        stars.add(star);
      }

      // generates random args
      String[] args = new String[3];
      args[0] = "test";
      args[1] = Double.toString(ThreadLocalRandom.current().nextDouble(-100, 100));
      List<Double> target;
      int targ = ThreadLocalRandom.current().nextInt(0, length);
      args[2] = "\"" + stars.get(targ).getName() + "\"";
      target = stars.get(targ).getCoordinates();

      KDTree<Star> tree = new KDTreeConstructor<Star>().buildTree(stars, 0);
      RadiusCommand nonnaive = new RadiusCommand(tree, stars);
      NaiveRadiusCommand naive = new NaiveRadiusCommand(stars);

      nonnaive.run(args);
      naive.run(args);

      // compares results
      List<Star> naiveSol = naive.getResults();
      TreeMap<Double, List<Star>> nonnaiveSol = nonnaive.getResults();
      int counter = 0;
      DistanceCalculator calc = new DistanceCalculator();
      for (Double distance : nonnaiveSol.keySet()) {
        for (Star star: nonnaiveSol.get(distance)) {
          double d1 = calc.getDistance(target, star.getCoordinates());
          double d2 = calc.getDistance(target, naiveSol.get(counter).getCoordinates());
          assertEquals(d1, d2, 0.000);
          assertTrue(stars.contains(naiveSol.get(counter)));
          assertTrue(stars.contains(star));
          counter ++;
        }
      }
      c++;
    }
  }

}
