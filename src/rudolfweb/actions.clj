(ns rudolfweb.actions
  (:use hiccup.core) 
  (:use hiccup.form-helpers)
  (:use rudolfweb.actions)
  ;Compojure provides an easy to use DSL for route definitions 
  (:use compojure.core) 
  (:require [compojure.route :as route]))

;;Some image urls, just for fun.
(defn img 
  [] 
  (rand-nth ["http://www.coloring-page.com/pages/christmas/rudolf.gif"
             "http://kepler.nasa.gov/files/mws/JohannesTimeline1.png"
             "http://cddis.gsfc.nasa.gov/lw17/images/logo_nasa_sm.gif"]))

;;Helper function : this generates the html.
(defn layout 
  [title & body]
  (html
   [:head [:title title]]
   [:link {:rel "stylesheet"
           :href "article.css"
           :type "text/css"
           :media "all"}]
   [:script {:type "text/javascript"
             :src "jquery1.7.2.js"}] 
   [:script {:type "text/javascript"
             :src "script.js"}] 
   [:body [:h1.header title] body]))

(def routes2 ["home" "blog" "labs"])

;;
(defn static-page 
  []
  (layout 
   "Welcome to RudolF: We believe in the power of agile, domain-driven software.  Not sure what that is ?  Thats okay, you'll know soon enough.  This is the internal hub for our ongoing research.  It's written in Clojure.  We are currently developing a business front end as well, so check back !"
   [:ul 
    ;;Dynamically generate the routes by hydrating a vector 
    (map #(vector :li [:a {:href (str "/" %)} %]) 
         ["home" "blog" "tools"])
    ]
   [:h1 "RudolfLabs.com 5/14/2012"]
   [:img {:src (img)}]))


;;Compujure Routes
(defroutes main-routes
  (GET "/" [] (static-page))
  (GET "/static-page" [] (static-page))
  (route/not-found "<h1>Page not found</h1>"))

