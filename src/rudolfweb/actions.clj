(ns rudolfweb.actions
  (:use hiccup.core) 
  (:use hiccup.form-helpers)
  (:use rudolfweb.actions)
  ;Compojure provides an easy to use DSL for route definitions 
  (:use compojure.core) 
  (:require [compojure.route :as route]
            [ring.util.response :as resp]
            ))

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

;;Home page : 
(defn rudolf_home
  []
  (layout 
    "..Welcome to RudolF.."
   [:ul 
    ;;Dynamically generate the routes by hydrating a vector 
    (map #(vector :li [:a {:href (str "/" %)} %]) 
         ["home" "blog" "tools"])]
   ))

;;Compujure Routes
(defroutes main-routes
  (GET "/" [] (resp/redirect "/home"))
  (GET "/home" [] (rudolf_home))
  (GET "/blog" [] (resp/redirect "/public/blog/index.html"))
  (GET "/tools" [] (resp/redirect "/public/tools/index.html"))
  (route/not-found "<h1>Page not found</h1>"))