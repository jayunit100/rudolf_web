(ns rudolfweb.main-heroku
  (:use ring.adapter.jetty)
  (:use rudolfweb.routes)
  (:use ring.middleware.file)
  (:require [compojure.handler :as handler]))
; Ring provides that adapter to Jetty and the underlying HTTP requests and responses.
; Difference to main-local.clj is that automatic reloading and browser stacktrace is not available under Heroku.

(def app-heroku
  (-> #'main-routes       ; use the routes defined in main-routes 
   (handler/api)          ; neccessary to access the form data in the paramter style way in the route definitions
   (wrap-file "public"))) ; serve static files from directory 'public'

(defn -main []
  (let [_ (print "port is set ?" (System/getenv "PORT"))
        port (Integer/parseInt (System/getenv "PORT"))] ; Watch out ! PORT is a string ! 
    (run-jetty app-heroku {:port port})))               ; run on Heroku!
