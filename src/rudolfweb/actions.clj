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
      (.replaceAll ;;ghetto replacement of ,
             (json/json-str (rts/word-enrichment-url url)) "," ",\r\n"))

(cc/defroutes main-routes

  (cc/GET "/" [] 
       (resp/redirect "/home"))

  (cc/GET "/home" [] 
       (resp/redirect "/homepage/Home.html"))

  (cc/GET "/blog/" []                                   
       (hc/html (rudolfweb.blogtemplate/index)))

  (cc/GET "/blog/:name" {params :params}                       ;; (may be) destructuring: the GET macro binds a map of parameters to params
       (hc/html (rudolfweb.blogtemplate/post (params :name)))) ;; then we pull out the name and pass it to the blog page generator


  (cc/GET "/tools/" [] 
       (resp/redirect "/public/tools/index.html"))  
		  ;;word enrichment tool, takes a url as input, outputs word count as json
       ;;(cc/GET "/tools/:wordenrichment_url" {params :params}
		   ;;   (layout-word-enrichment (params :url)))
  (cc/GET "/tools/:wordenrichment_url" {params :params} {:status 200 :headers {"Content-Type" "text/plain"} :body  (layout-word-enrichment (params :url))})
      
  (route/not-found 
       "<h1>Page not found</h1>"))
