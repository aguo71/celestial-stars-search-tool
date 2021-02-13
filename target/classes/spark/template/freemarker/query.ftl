<#assign content>



<div id = "container">

  <p> Find nearest neighbors using a naive algorithm.
  <form method="POST" action="/naive_neighbors">
    <label for="numberNeighbors">Enter number of neighbors wanted: </label><br>
    <textarea name="numberNeighbors" id="numberNeighbors"></textarea><br>
    <label for="searchCriteria">Enter name of star in quotes or coordinate to search from: <br>
      (format: "sol" or 0 0 0): </label><br>
    <textarea name="searchCriteria" id="searchCriteria"></textarea><br>
    <input type="submit">
  </form>
  </p>

  <p> Find nearest neighbors using a naive algorithm.
  <form method="POST" action="/neighbors">
    <label for="numberNeighbors">Enter number of neighbors wanted: </label><br>
    <textarea name="numberNeighbors" id="numberNeighbors"></textarea><br>
    <label for="searchCriteria">Enter name of star in quotes or coordinate to search from: <br>
      (format: "sol" or 0 0 0): </label><br>
    <textarea name="searchCriteria" id="searchCriteria"></textarea><br>
    <input type="submit">
  </form>
  </p>

  <p> Find neighbors within a given radius using a naive algorithm.
  <form method="POST" action="/naive_radius">
    <label for="radiusDist">Enter radius distance allowed: </label><br>
    <textarea name="radiusDist" id="radiusDist"></textarea><br>
    <label for="searchCriteria">Enter name of star in quotes or coordinate to search from: <br>
      (format: "sol" or 0 0 0): </label><br>
    <textarea name="searchCriteria" id="searchCriteria"></textarea><br>
    <input type="submit">
  </form>
  </p>

  <p> Find neighbors within a given radius using an efficient algorithm.
  <form method="POST" action="/radius">
    <label for="radiusDist">Enter radius distance allowed: </label><br>
    <textarea name="radiusDist" id="radiusDist"></textarea><br>
    <label for="searchCriteria">Enter name of star in quotes or coordinate to search from: <br>
      (format: "sol" or 0 0 0): </label><br>
    <textarea name="searchCriteria" id="searchCriteria"></textarea><br>
    <input type="submit">
  </form>
  </p>

</div>
<h1 class = "welcome"> Star Search Results (nearest to furthest): </h1>
<p class = "welcome">
  Star id: Star name
  <#list results as name>
  <p class = "welcome">${name}
</#list>
</p>


</#assign>
<#include "welcome.ftl">
