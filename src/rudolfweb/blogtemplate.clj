(ns rudolfweb.blogtemplate
  (:require [hiccup.core            :as hc])
  (:require [hiccup.page-helpers    :as hph])
  (:require [clojure.contrib.string :as cs])
  (:require [clj-yaml.core          :as yaml]))


(defn make-link
  [name]
  [:a {:href (str "/blog/" name)} 
      (cs/join " " (map cs/capitalize (cs/split #"_" name)))])


(defn read-articles-file
  ""
  []
  (yaml/parse-string (slurp "public/blog/articles.yaml")))


(defn body-header
  "INPUT : A list of Article names 
   OUTPUT : A map of article names defining an html list <ul>."
  [article-names]
  [:ul (for [art-name article-names]
            [:li (make-link art-name)])])
  

(def footer 
  "This post brought to you by RudolF Inc")


(def header
  [:head [:title "RudolF blog"]
         [:meta {:name "description" :content "Interesting articles about programming"}]
         [:meta {:name "keywords" :content "RudolF, programming"}]
         [:meta {:name "author" :content "RudolF"}]
         [:link {:rel "stylesheet" :type "text/css" :href "/style/bloglayout.css"}]
         [:link {:rel "stylesheet" :type "text/css" :href "/style/blogcontent.css"}]])


(defn read-article
  "given an article name, return the html for a blog post, 
   or an html-ified error message if the file doesn't exist"
  [name]
  (slurp (apply str "public/blog/" name ".html")))


(defn post
  "given a post name, return a complete
   html page"
  [name]                                  ;;  name of post
  (hc/html                                ;;  return as html
   (hph/doctype :html5)                   ;;  want browsers in strict mode
   [:html header                          ;;  the header should import stylesheets and scripts, if necessary
          [:body 
           [:div {:id "banner"}
                 [:div "RudolF blog"]]
           [:div {:id "main"}
             [:div {:id "sidebar"}          ;;  the "body-header" should include a list of all posts
               [:div (body-header ((read-articles-file) :articles))]]
             [:div {:id "content"}          ;;  the body consists of the text of the post
               [:div (try (read-article name) ;;  need to check that the yaml file says it's okay
                         (catch Exception e (str "<p>error: (" name ") is an invalid blog post name</p>")))]]
             [:div {:id "main-footer"}]]
           [:div {:id "footer"}
             [:div footer]]]]))       ;;  plus a generic footer
