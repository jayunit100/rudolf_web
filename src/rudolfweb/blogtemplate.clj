(ns rudolfweb.blogtemplate
  (:use hiccup.core))


(def header
  [:head [:title "auto-title"]])
  

(def footer "")


(defn post
  ""
  [name]              ;; read file based on name
  (html               ;; return as html
   [:html [header
           [:body (slurp (apply str "public/blog/" name ".html"))]
           footer]]))
