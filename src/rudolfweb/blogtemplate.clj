(ns rudolfweb.blogtemplate
  (:use hiccup.core))


(def header     ;; for now, the header will be simple
  [:head [:title "auto-title"]])
  

(def footer 
  [:sub "This post brought to you by RudolF Inc"]) ;; the footer will also be simple for now


(defn post
  "given a post name, return the html for a blog post, 
   or throw an error if the post doesn't exist
   (NOTE:  error-handling is currently unimplemented)"
  [name]              ;; read file based on name
  (html               ;; return as html
   [:html header      ;; the header should import stylesheets and scripts, if necessary
          [:body (apply str       ;; the body consists of
                   (slurp (apply str "public/blog/" name ".html"))  ;; the text of the post
                   footer)]]))    ;; plus a generic footer
