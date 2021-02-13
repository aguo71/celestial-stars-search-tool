package edu.brown.cs.student.stars;

import com.google.common.collect.ImmutableMap;
import spark.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Handle requests to the front page of our Stars website.
 */
public class RadiusHandler implements TemplateViewRoute {
  StarsUniverse universe;

  /**
   * Constructor for naive_neighbors handler.
   * @param universe universe class containing commands/stars data
   */
  public RadiusHandler(StarsUniverse universe) {
    this.universe = universe;
  }

  @Override
  public ModelAndView handle(Request req, Response res) {
    QueryParamsMap qm = req.queryMap();
    String radiusDist = qm.value("radiusDist");
    String searchCriteria = qm.value("searchCriteria");
    // Parses coordinate/star argument to algorithm
    List<String> matchList = new ArrayList<>();
    Pattern regex = Pattern.compile("[^\\s\"']+|\"[^\"]*\"|'[^']*'");
    Matcher regexMatcher = regex.matcher(searchCriteria);
    while (regexMatcher.find()) {
      matchList.add(regexMatcher.group());
    }
    List<String> radDist = new ArrayList<>();
    Matcher regexMatcher2 = regex.matcher(radiusDist);
    while (regexMatcher2.find()) {
      radDist.add(regexMatcher2.group());
    }
    String[] args;
    if(matchList.size() == 1 && radDist.size() == 1) {
      // case when star name was entered
      args = new String[3];
      args[0] = "radius";
      args[1] = radDist.get(0);
      args[2] = matchList.get(0);
    } else if(matchList.size() == 3 && radDist.size() == 1) {
      // case when coordinates were entered
      args = new String[5];
      args[0] = "radius";
      args[1] = radDist.get(0);
      args[2] = matchList.get(0);
      args[3] = matchList.get(1);
      args[4] = matchList.get(2);
    } else {
      // case when too many arguments are present
      args = new String[matchList.size() + radDist.size() + 1];
      args[0] = "radius";
    }
    // computes algorithm
    String err = universe.guiQRadius(args);
    List<String> resultNames = new ArrayList<>();
    // returns either error message or list of stars
    if(err.equals("")) {
      List<Star> results = universe.getGuiOutput();
      for(Star s: results) {
        if(matchList.size() != 1 ||
            !s.getName().equals(matchList.get(0).replaceAll("\"", ""))) {
          resultNames.add(s.getID() + ": " + s.getName());
        }
      }
    } else {
      resultNames.add(err);
    }

    Map<String, Object> variables = ImmutableMap.of("title",
        "Stars: Universe", "results", resultNames);
    return new ModelAndView(variables, "query.ftl");
  }
}