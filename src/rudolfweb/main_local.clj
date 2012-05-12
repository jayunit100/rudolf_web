(ns rudolfweb.main-local
  (:use ring.adapter.jetty)
  (:use ring.middleware.reload)
  (:use ring.middleware.file)
  (:use ring.middleware.stacktrace)
  (:use rudolfweb.routes)
  (:require [compojure.handler :as handler]))
; Ring provides that adapter to Jetty and the underlying HTTP requests and responses

(def app-auto-reload
	(-> #'main-routes ; use the routes defined in main-routes 
	    (handler/api)  ; neccessary to access the form data in the paramter style way in the route definitions
	    (wrap-file "public") ; serve static files from directory 'public'
	    (wrap-reload '(rudolfweb.main-local rudolfweb.routes rudolfweb.actions)) ; reload automatically
	    (wrap-stacktrace))) ; show stacktrace in browser when exceptions are thrown in the server

(defn boot []
	(run-jetty #'app-auto-reload {:port 8080})) ; start Jetty webserver
