(ns rudolfweb.actions
  (:use [hiccup.core          :as hc]) 
  (:use [hiccup.form-helpers  :as hph])
  (:require rudolfweb.tools)
  (:use [clojure.data.json :only (read-json json-str)])
  (:use [compojure.core    :only (defroutes GET)]) 
  (:use ring.middleware.params)  ;; what does this do?  where is the online documentation?
  (:require rudolfweb.blogtemplate)
  (:require [compojure.route :as route]
            [ring.util.response :as resp]))


(defn layout 
  ""
  [title & body]
  [:html
   [:head [:title title]]
   [:link {:rel "stylesheet"
           :href "style/home.css"
           :type "text/css"
           :media "all"}]
   [:body [:h1.header title] body]])


(defn rudolf-home
  "Home page"
  []
  (layout 
   "RudolF"
   [:ul 
    (map #(vector :li [:a {:href (str "/" %)} %]) 
         ["home" "blog/" "tools/"])]))


(defn layout-word-enrichment
  ""
  [url]
  [:html 
   [:body
    [:p "analysis results:"
        [:pre (json-str (rudolfweb.tools/word-enrichment-url url))]]]])


(defroutes main-routes
  (GET "/" [] 
       (resp/redirect "/home"))
  (GET "/home" [] 
       (hc/html (rudolf-home)))
  (GET "/blog/" []                                   
       (hc/html (rudolfweb.blogtemplate/index)))
  (GET "/blog/:name" {params :params}                          ;; (may be) destructuring: the GET macro binds a map of parameters to params
       (hc/html (rudolfweb.blogtemplate/post (params :name)))) ;; then we pull out the name and pass it to the blog page generator
  (GET "/tools/" [] 
       (resp/redirect "/public/tools/index.html"))
  
  ;;word enrichment tool, takes a url as input, outputs word count as json
  (GET "/tools/:wordenrichment_url" {params :params}
       (hc/html (layout-word-enrichment (params :url))))

  (route/not-found 
       "<h1>Page not found</h1>"))
