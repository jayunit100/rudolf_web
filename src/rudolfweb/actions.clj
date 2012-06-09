(ns rudolfweb.actions
  (:use hiccup.core) 
  (:use hiccup.form-helpers)
  (:use rudolfweb.actions)
  ;Compojure provides an easy to use DSL for route definitions 
  (:use compojure.core) 
  (:use ring.middleware.params)
  (:require rudolfweb.module-template)
  (:require [compojure.route :as route]
            [ring.util.response :as resp]))


(defn layout 
  "Helper function : this generates the html"
  [title & body]
  (html
   [:head [:title title]]
   [:link {:rel "stylesheet"
           :href "style/home.css"
           :type "text/css"
           :media "all"}]
   [:body [:h1.header title] body]))


(defn rudolf_home
  "Home page"
  []
  (layout 
    "RudolF"
   [:ul 
    ;;Dynamically generate the routes by hydrating a vector 
    (map #(vector :li [:a {:href (str "/" %)} %]) 
         ["home" "blog/" "tools/"])]))


;;Compujure Routes
(defroutes main-routes
  (GET "/" [] 
       (resp/redirect "/home"))
  (GET "/home" [] 
       (rudolf_home))
  (GET "/blog/" []                                   
       (rudolfweb.module-template/index "Welcome to the blog !" "blog"))
  (GET "/blog/:name" {params :params}
       (rudolfweb.module-template/post (params :name)) "blog") 
  (GET "/tools/" []
       (rudolfweb.module-template/index "RudolF tools " "tools"))
  (GET "/tools/:name" {params :params}
       (rudolfweb.module-template/post (params :name) "tools")) ;; then we pull out the name and pass it to the blog page generator
  (route/not-found 
       "<h1>Page not found</h1>"))
