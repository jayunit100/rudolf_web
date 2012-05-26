(ns rudolfweb.blogtemplate
  (:use hiccup.core))


(def header
  [:head [:title "auto-title"]])
  

(def footer "")


(defn post
  ""
  [name]              ;; read file based on name
  (html               ;; return as html
    (apply str        ;; put together header, file we just read, and footer
           header
           (slurp (apply str "public/blog/" name ".html"))
           footer)))
