<#assign content>
<div id = "container">
<#--  displays 4 forms calling corresponding algorithms -->
  <div class = "formStyle"><div class = "formHeader">Naive Neighbors</div>
    <div class = "formDesc">Find nearest neighbors using a naive algorithm.</div><br>
    <div>
    <form method="POST" action="/naive_neighbors">
      <label for="numberNaiveNeighbors">Enter number of neighbors wanted: </label><br>
      <textarea name="numberNaiveNeighbors" id="numberNaiveNeighbors"></textarea><br>
      <label for="searchCriteria1">Enter name of star in quotes <b>or</b> coordinates separated by spaces to search from: <br>
        (format: "Sol" or 0 0 0): </label><br>
      <textarea name="searchCriteria" id="searchCriteria1"></textarea><br>
      <input type="submit">
    </form>
    </div>
  </div>

  <div class = "formStyle"><div class = "formHeader">KDTree Neighbors</div>
    <div class = "formDesc">Find nearest neighbors using an efficient algorithm.</div><br>
    <div>
    <form method="POST" action="/neighbors">
      <label for="numberNeighbors">Enter number of neighbors wanted: </label><br>
      <textarea name="numberNeighbors" id="numberNeighbors"></textarea><br>
      <label for="searchCriteria2">Enter name of star in quotes <b>or</b> coordinates separated by spaces to search from: <br>
        (format: "Sol" or 0 0 0): </label><br>
      <textarea name="searchCriteria" id="searchCriteria2"></textarea><br>
      <input type="submit">
    </form>
    </div>
  </div>

  <div class = "formStyle"><div class = "formHeader">Naive Radius</div>
    <div class = "formDesc">Find neighbors within a given radius using a naive algorithm.</div>
    <div>
      <form method="POST" action="/naive_radius">
      <label for="radiusNaiveDist">Enter radius distance allowed: </label><br>
      <textarea name="radiusNaiveDist" id="radiusNaiveDist"></textarea><br>
        <label for="searchCriteria3">Enter name of star in quotes <b>or</b> coordinates separated by spaces to search from: <br>
        (format: "Sol" or 0 0 0): </label><br>
      <textarea name="searchCriteria" id="searchCriteria3"></textarea><br>
      <input id = "submit2" type="submit">
      </form>
    </div>
  </div>

  <div class = "formStyle"><div class = "formHeader">KDTree Radius</div>
    <div class = "formDesc">Find neighbors within a given radius using an efficient algorithm.</div>
    <div>
      <form method="POST" action="/radius">
      <label for="radiusDist">Enter radius distance allowed: </label><br>
      <textarea name="radiusDist" id="radiusDist"></textarea><br>
      <label for="searchCriteria4">Enter name of star in quotes <b>or</b> coordinates separated by spaces to search from: <br>
        (format: "Sol" or 0 0 0): </label><br>
      <textarea name="searchCriteria" id="searchCriteria4"></textarea><br>
      <input type="submit">
      </form>
    </div>
  </div>
</div>
<div id = "bottom">
<div id = "resultsContainer">
  <h1 class = "welcome" id = "bordered"> Star Search Results (nearest to furthest): </h1>
  <p class = "welcome" >
    <#list results as name>
    <p class = "results">${name}
    </#list>
  </p>
</div>
</div>

</#assign>
<#include "welcome.ftl">
