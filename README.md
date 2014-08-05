EatMe

------------------------------------------------------------------------

.:: INTRODUCTION ::.

This is a simple application that does a search for food with a given API. 

The initial screen is basically an editText where you can type anything you like. The editText will be translated to
the top of the screen and the text typed will be used as a filter to query for foods after 2000ms without typing.

An asyncTask is created and does a query on the webserver or on the local database - depending on the connection status.

A listView is used to present the results. There's a checkbox on the right corner of each row in the listView. You
can select it and use to multi-add items to the database by clicking the button "Save" on the bottom of the screen.
If the item already exists, it will be updated.

You can also single tap a line and the listView item will be rotated and more information about that item will be
displayed on the back face of the list item. (eg. calories, fat...)


.:: ANDROID VERSION ::.

- The application was developed targeting Android 4.0. 


.:: MISSING PARTS ::.

- There's no error treatment for any problem that may happen during the connection with the server.

- There's no unit tests.
      I'd like to have used the Android Instrumentation class for testing.
      If I had more time, I'd have used TDD on the development process. 


.:: FUTURE FEATURES ::.

- Show a progress bar while making the server request. On the current UI, the user has no feedback that 
  something is happening.
- Separate the list_food_item_layout.xml into 3 layouts. One for the front face, another one for the back face, and the   last one that will be the container that includes both.
- Customize the save button appearance.
- Customize launcher icon appearance.


.:: FRAMEWORKS REQUIRED ::.

  NONE. But...
  
  Google Volley API - I've looked into google Volley API and looks quite interesting. If I had more time, I'd definetly   look into that and take my chances :)
  
  And I'd like to have used the Object Relational Mapping Lite (ORM Lite) for persisting Java objects to database.
  
  
