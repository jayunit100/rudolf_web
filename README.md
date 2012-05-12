The RudolF blog-algorithms-site-playground ! 
Uses 

Jetty (server), 
Ring (Http abstraction layer for simpler sites)
Compojure (Http forwarding and routing library) 
Hiccup (HTML representation, in Clojure) 

Get Started By running locally
------------------------------
* lein deps (This goes off to maven and grabs all the jars). 
* lein repl (We all know what this does) 
* > (use 'rudolfweb.main-local) 
* > (boot) 
* Finally : Open http://localhost:8080 in your web browser: you should see a welcome message. For other URLs to try take a look into routes.clj.

A more "herokuish" way to run locally:
--------------------------------------
* export PORT=8000 (read by Procfile deployer)
* Run the "Procfile" (run the Procile launch command, in your terminal). 

Run on Heroku
-------------
First create a new app at Heroku with the cedar stack: heroku create --stack cedar <my-app-name>

To run the webapp with Heroku we need the Procfile file and main-heroku.clj.

Example of running (easy), note the Procfile is the "driver" : 
http://blog.heroku.com/archives/2011/7/5/clojure_on_heroku/

Pushing and Pulling 
-------------------
git push heroku master (this deploys the code, so make sure you run it locally first).
git push origin master (do this any time, it just commits the code to git).


License
-------
This is distributed under the RudolF license, which means that you can use it however you 
want to as long as you say good things about functional & stateless programming.
