The RudolF blog-algorithms-site-playground! 
     
It uses 
                              
 - Jetty (server), 
 - Ring (Http abstraction layer for simpler sites)
 - Compojure (Http forwarding and routing library) 
 - Hiccup (HTML representation, in Clojure) 

Get Started By running locally
------------------------------
* get dependencies (goes off to maven and grabs all the jars)

        $ lein deps

* start clojure from inside the project

        $ lein repl 

* and from the repl:

        ;;WARNING : not (:use ...) != (use .. ) !!!!
        user=> (:use 'rudolfweb.main-local) 
        user=> (boot) 

* Finally : Open [the page](http://localhost:8080) in your web browser: you should see a welcome message. For other URLs to try take a look at util.clj.

A more "herokuish" way to run locally:
--------------------------------------
* export PORT=8000 (read by Procfile deployer)
* Run the "Procfile" (run the Procile launch command, in your terminal). 

Run as a new app on Heroku
--------------------------
First create a new app at Heroku with the cedar stack: 

    heroku create --stack cedar <my-app-name>
  
To run the webapp with Heroku we need the Procfile file and main-heroku.clj.

[Example](http://blog.heroku.com/archives/2011/7/5/clojure_on_heroku/) of running (easy), note the Procfile is the "driver".


Deployment to rudolfcode.heroku.com
-----------------------------------

First method

- Certify your keys with heroku (email jay to add you to the heroku app authenticated users emails)
- Install the heroku toolbelt. 
- git remote add heroku git@heroku.com:appname.git
--->  git remote add git@heroku.com:rudolfcode.git



Automated method (beta)

- Push to github (this is experimental, and underway, we will see if it works).

Pushing and Pulling 
-------------------
 - this deploys the code, so make sure you run it locally first:

        git push heroku master

 -  do this any time, it just commits the code to git:

        git push origin master


License
-------
This is distributed under the RudolF license, which means that you can use it however you 
want to as long as you say good things about functional & stateless programming.
