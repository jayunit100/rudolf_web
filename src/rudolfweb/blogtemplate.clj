(ns rudolfweb.blogtemplate
  (:use [hiccup.core          :as hc])
  (:use [hiccup.page-helpers  :as hph])
  (:use [clj-yaml.core        :as yaml]))


(defn make-link
  [name]
  [:a {:href name} name])


(def articles-file
  (yaml/parse-string (slurp "public/blog/articles.yaml")))


(def body-header
  [:ul (for [art-name (articles-file :articles)]
            [:li (make-link art-name)])])
  

(def footer 
  [:sub "This post brought to you by RudolF Inc"])


(def header
  [:head [:title "RudolF blog"]
         [:meta {:name "description" :content "Interesting articles about programming"}]
         [:meta {:name "keywords" :content "RudolF, programming"}]
         [:meta {:name "author" :content "RudolF"}]
         [:link {:rel "stylesheet" :type "text/css" :href "../style/bloghome.css"}]])


(defn read-post
  "given a post name, return the html for a blog post, 
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
          [:body body-header              ;;  the "body-header" should include a list of all posts
                 (read-post name)         ;;  the body consists of the text of the post
                 footer]]))               ;;  plus a generic footer


(defn index
  ""
  []
  (hc/html 
   (hph/doctype :html5)                                           ;; want browsers in strict mode
   [:html header                                                  ;; the header should import stylesheets and scripts, if necessary
          [:body "Welcome to the blog!"
                 body-header]]))