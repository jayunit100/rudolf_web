(ns rudolfweb.routes
  (:use compojure.core) 
  (:use rudolfweb.actions)
  (:require [compojure.route :as route]))
;  Compojure provides an easy to use DSL for route definitions 

(defroutes main-routes
  (GET "/" 
       [] 
       (welcome-page))
  (GET "/name-form"                  ; simple GET request that returns a web page with a form
       [] 
       (name-form))
  (GET "/full-name/:first/:second"   ; GET request with variable URL
       [first second]
       (full-name first second))
  (GET "/full-name"                  ; GET request with request params
       [first second] 
       (full-name first second)) 
  (POST "/post-name"                 ; POST request with form data
        [first-name second-name] 
        (post-name first-name second-name))
  (route/not-found                   ; unknown URL
   "<h1>Page not found</h1>"))
