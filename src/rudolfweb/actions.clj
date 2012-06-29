(ns rudolfweb.actions
  (:require [hiccup.core            :as hc]) 
  (:require [hiccup.form-helpers    :as hph])
  (:require [rudolfweb.tools        :as rts])
  (:require [clojure.data.json      :as json])
  (:require [compojure.core         :as cc]) 
  (:require [rudolfweb.blogtemplate :as rbt])
  (:require [compojure.route        :as route]
            [ring.util.response     :as resp]))

;;This is made to look the same way facebook jsons look, they 
;;are preformatted html (not plain text).
(defn layout-word-enrichment
  ""
  [url]
  (json/json-str (rts/word-enrichment-url url)))

(cc/defroutes main-routes

  (cc/GET "/" [] 
       (resp/redirect "/index.html"))

  (cc/GET "/home/" [] 
       (resp/redirect "Home.html"))

  (cc/GET "/blog/" []                                   
       (hc/html (rudolfweb.blogtemplate/post "x")))

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
