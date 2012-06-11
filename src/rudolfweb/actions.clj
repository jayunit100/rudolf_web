(ns rudolfweb.actions
  (:use hiccup.core) 
  (:use hiccup.form-helpers)
  (:use rudolfweb.actions)
  (:use rudolfweb.tools)
  ;Compojure provides an easy to use DSL for route definitions 
  (:use compojure.core) 
  (:use ring.middleware.params)
  (:require rudolfweb.blogtemplate)
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
       (rudolfweb.blogtemplate/index))
  (GET "/blog/:name" {params :params}                ;; (may be) destructuring: the GET macro binds a map of parameters to params
       (rudolfweb.blogtemplate/post (params :name))) ;; then we pull out the name and pass it to the blog page generator
  (GET "/tools/" [] 
       (resp/redirect "/public/tools/index.html"))
  
  ;;word enrichment tool, takes a url as input, outputs word count.
  (GET "/tools/:wordenrichment_url" {params :params}
         (str (rudolfweb.tools/wordenrichment_url (params :url))))

  (route/not-found 
       "<h1>Page not found</h1>"))