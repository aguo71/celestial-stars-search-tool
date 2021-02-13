<!DOCTYPE html>
  <head>

    <meta charset="utf-8">
    <title>${title}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- In real-world webapps, css is usually minified and
         concatenated. Here, separate normalize from our code, and
         avoid minification for clarity. -->
     <link rel="stylesheet" href="css/main.css">
    <style>
        body {
            background-image: url('https://www.google.com/url?sa=i&url=https%3A%2F%2Fnews.softpedia.com%2Fnews%2FHalf-of-the-Universe-s-Stars-Are-Vagabonds-Aimlessly-Floating-Between-Galaxies-464399.shtml&psig=AOvVaw2Ym1jL_UwzjOMZYQBw5Vj9&ust=1613282484480000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCOCH-tOY5u4CFQAAAAAdAAAAABAF');
        }
    </style>
  </head>
  <body>
     ${welcome}
     ${content}
     <!-- Again, we're serving up the unminified source for clarity. -->
     <script src="js/jquery-2.1.1.js"></script>
  </body>
  <!-- See http://html5boilerplate.com/ for a good place to start
       dealing with real world issues like old browsers.  -->
</html>
