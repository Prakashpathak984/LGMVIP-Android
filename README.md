# LGMVIP-Android Task 1

Task 1 : Create a COVID-19 Tracker Android App to See Details of any City
and State in India

Approach
Step 1: Create a new project
Step 2: Now add some files before writing Java and XML code
● Go to Gradle Scripts -> build.gradle (Module: app) section and import the
following dependencies and click the “Sync Now” button to Sync the APP.
● To Learn More About Volley Library Click Here.

implementation ‘com.android.volley:volley:1.1.1’
Step 3: Use JSON Parsing to fetch data from the website
● Click on this URL- https://api.covid19india.org/state_district_wise.json
● It will Show Data of the whole Country and in this, We Are Going to fetch data
from here Only.
Step 4: Design the Layout of the activity_main.xml
1. Go to app -> res -> layout -> activity_main.xml
2. Add A TextView To the layout: To display LGM at the top of the screen.
3. Add A ListView To the Layout: To display the list of cities tracking details on the
screen.

Step 5: Design the Layout for The ListView
1. Create A new Layout by right click on the layout folder inside res Folder
2. Then Click Layout Resource File and Set the name testing.xml
3. Click Finish To save
4. Click testing.xml and Start Design UI Layout for the ListView
5. The Drawable file used in testing.xml is “arrow upward“. Go to drawable ->
New -> Vector Asset and search for “arrow upward” and add it to your file.

Step 6: Create a New JAVA Class to fetch the data we want to fetch from the Website

1. Create a new JAVA class name it as Model.java
2. Use getters and setters functions to create a function for the data you want to
fetch from the website.

Step 7: Create an Adapter Class

● Now create a new JAVA Adapter Class to put the data that have fetched into a
ListView that has created before.

Step 8: Working with MainActivity.java file
● In this file use volley to fetch the data from the provided url.
