(ns rudolfweb.blogtemplate
  (:require [hiccup.core          :as hc])
  (:require [hiccup.page-helpers  :as hph])
  (:require [clj-yaml.core        :as yaml]))


(defn make-link
  [name]
  [:a {:href name} name])


(defn read-articles-file
  ""
  []
  (yaml/parse-string (slurp "public/blog/articles.yaml")))


(defn body-header
  ""
  [article-names]
  [:ul (for [art-name article-names]
            [:li (make-link art-name)])])
  

(def footer 
  [:sub "This post brought to you by RudolF Inc"])


(def header
  [:head [:title "RudolF blog"]
         [:meta {:name "description" :content "Interesting articles about programming"}]
         [:meta {:name "keywords" :content "RudolF, programming"}]
         [:meta {:name "author" :content "RudolF"}]
         [:link {:rel "stylesheet" :type "text/css" :href "../style/bloghome.css"}]])


(defn read-article
  "given a article name, return the html for a blog post, 
   or an html-ified error message if the file doesn't exist"
  [name]
  (try (slurp (apply str "public/blog/" name ".html"))
       (catch Exception e "<p>error: invalid blog post name</p>")))


(defn post
  "given a post name, return a complete
   html page"
  [name]                                  ;;  name of post
  (hc/html                                ;;  return as html
   (hph/doctype :html5)                   ;;  want browsers in strict mode
   [:html header                          ;;  the header should import stylesheets and scripts, if necessary
          [:body (body-header ((read-articles-file) :articles))  ;;  the "body-header" should include a list of all posts
                 (read-article name)      ;;  the body consists of the text of the post ... need to check that the yaml file says it's okay
                 footer]]))               ;;  plus a generic footer


(defn index
  ""
  []
  (hc/html 
   (hph/doctype :html5)                   ;; want browsers in strict mode
   [:html header                          ;; the header should import stylesheets and scripts, if necessary
          [:body "Welcome to the blog!"
                 body-header]]))
