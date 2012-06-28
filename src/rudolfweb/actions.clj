(ns rudolfweb.actions
  (:require [hiccup.core            :as hc]) 
  (:require [hiccup.form-helpers    :as hph])
  (:require [rudolfweb.tools        :as rts])
  (:require [clojure.data.json      :as json])
  (:require [compojure.core         :as cc]) 
  (:require [rudolfweb.blogtemplate :as rbt])
  (:require [compojure.route        :as route]
            [ring.util.response     :as resp]))


(defn home-layout 
  ""
  [title & body]
  [:html
   [:head [:title title]]
   [:link {:rel "stylesheet"
           :href "style/home.css"
           :type "text/css"
           :media "all"}]
   [:body [:div {:class "main_menu"} [:h1 title]] body]])


(defn rudolf-home
  "Home page"
  []
  (home-layout 
   "RudolF"
   [:div {:class "main_menu"}    
      [:ul 
	      (map #(vector :li [:a {:href (str "/" %)} %])
	         ["home" "blog/" "tools/"])]]))


(defn layout-word-enrichment
  ""
  [url]
  (json/json-str (rts/word-enrichment-url url)))



(cc/defroutes main-routes

  (cc/GET "/" [] 
       (resp/redirect "/home"))

  (cc/GET "/home" [] 
       (hc/html (rudolf-home)))

  (cc/GET "/blog/" []                                   
       (hc/html (rudolfweb.blogtemplate/index)))

  (cc/GET "/blog/:name" {params :params}                       ;; (may be) destructuring: the GET macro binds a map of parameters to params
       (hc/html (rudolfweb.blogtemplate/post (params :name)))) ;; then we pull out the name and pass it to the blog page generator

  (cc/GET "/tools/" [] 
       (resp/redirect "/public/tools/index.html"))
  
	;; word enrichment tool: 
  ;;   input:  url
  ;;   output: word counts as json
  (cc/GET "/tools/:wordenrichment_url" 
          {params :params} 
          {:status 200 
           :headers {"Content-Type" "text/json"} 
           :body  (layout-word-enrichment (params :url))})
      
  (route/not-found 
       "<h1>Page not found</h1>"))
