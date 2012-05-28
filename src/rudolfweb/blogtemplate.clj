(ns rudolfweb.blogtemplate
  (:use [hiccup.core :as hc])
  (:use [hiccup.page-helpers :as hph]))


(def body-header     ;; for now, the header will be simple
  [:ul [:li "this will eventually be a list of posts"]])
  

(def footer 
  [:sub "This post brought to you by RudolF Inc"]) ;; the footer will also be simple for now


(def header
  [:head [:title "RudolF blog"]
         [:meta {:name "description" :content "Interesting articles about programming"}]
         [:meta {:name "keywords" :content "RudolF, programming"}]
         [:meta {:name "author" :content "RudolF"}]
         [:link {:rel "stylesheet" :type "text/css" :href "../style/bloghome.css"}]])


(defn post
  "given a post name, return the html for a blog post, 
   or throw an error if the post doesn't exist
   (NOTE:  error-handling is currently unimplemented)"
  [name]                                                          ;; name of post
  (hc/html                                                        ;; return as html
   (hph/doctype :html5)                                           ;; want browsers in strict mode
   [:html header                                                  ;; the header should import stylesheets and scripts, if necessary
          [:body body-header                                      ;; the "body-header" should include a list of all posts
                 (slurp (apply str "public/blog/" name ".html"))  ;; the body consists of the text of the post
                 footer]]))                                       ;; plus a generic footer


(defn index
  ""
  []
  (hc/html 
   (hph/doctype :html5)                                           ;; want browsers in strict mode
   [:html header                                                  ;; the header should import stylesheets and scripts, if necessary
          [:body "Welcome to the blog!"
                 body-header]]))