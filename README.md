A simple Clojure webapp that shows how to use Jetty, Ring, Compojure and Hiccup to build a web application.

The goal of the example code is to make it easy to start with your first web application with Clojure.

Get Started
-----------
* lein deps
* lein repl
* (use 'rudolfweb.main-local)
* (boot)
* Open http://localhost:8080 in your web browser: you should see a welcome message. For other URLs to try take a look into routes.clj.

Run on Heroku
-------------
First create a new app at Heroku with the cedar stack: heroku create --stack cedar <my-app-name>

To run the webapp with Heroku we need the Procfile file and main-heroku.clj.

Example of running (easy), note the Procfile is the "driver" : 
http://blog.heroku.com/archives/2011/7/5/clojure_on_heroku/

License
-------
This is distributed under the RudolF license, which means that you can use it however you 
want to as long as you say good things about functional & stateless programming.
