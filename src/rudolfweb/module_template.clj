(ns rudolfweb.module-template
  (:use [hiccup.core          :as hc])
  (:use [hiccup.page-helpers  :as hph])
  (:use [clj-yaml.core        :as yaml]))


(defn make-link
  [name]
  [:a {:href name} name])


(defn read-articles-file 
  "return articles for blog/tutorial 
   takes 1 parameter: 'blog' or 'tools'
   returns yaml map"
  
  [bt]
   {:pre [(#{"blog" "tools"} bt)] 
    :post [(> (count (:articles %)) 1) ] 
    } ; should be > 1 element in the meta.yaml map
  (try (yaml/parse-string (slurp (str "public/" bt "/meta.yaml")))
       (catch Exception e ("unable to load meta.yaml file"))))

(defn body-header 
  "returns the body header for a blog/tutorial 
   takes 1 parameter, 'blog' or 'tools'"
  [bt]
  {:post [(> (count (:ul %)) 1)]}
  [:ul 
   (map #(vector :li (make-link %)) 
        (:articles (read-articles-file bt)))])


(def footer 
  [:sub "This post brought to you by RudolF (mm division) !"])


(defn header 
  "Header for the page" 
  [module_type]
  [:head [:title (str "RudolF " module_type) ]
         [:meta {:name "description" :content (str "Our " module_type)}]
         [:meta {:name "keywords" :content "RudolF, programming"}]
         [:meta {:name "author" :content "RudolF"}]
         [:link {:rel "stylesheet" :type "text/css" :href "../style/bloghome.css"}]])


(defn read-post
  "given a post name, return the html for a blog post, 
   or an html-ified error message if the file doesn't exist
   looks in either the blogs/tools sections.
   "
  [name bt]
  (try (slurp (apply str "public/" bt "/" name ".html"))
       (catch Exception e (str "<p>error: invalid post name for " bt "/ </p>" ))))


(defn post
  "given a post name, return a complete
   html page"
  [name bt]                               ;;  name of post
  (hc/html                                ;;  return as html
   (hph/doctype :html5)                   ;;  want browsers in strict mode
   [:html (header bt)                     ;;  the header should import stylesheets and scripts, if necessary
          [:body (body-header bt)         ;;  the "body-header" should include a list of all posts
                 (read-post name)         ;;  the body consists of the text of the post
                 footer]]))               ;;  plus a generic footer


(defn index
  "returns an html page with a title (first parameter ;  
   - example : (index \"welcome to the blog !\") :blog|:tools " 
  [str-title moduletype]
  {:pre [()]}
  (hc/html 
   (hph/doctype :html5)                   ;; want browsers in strict mode
   [:html (header moduletype)             ;; the header should import stylesheets and scripts, if necessary
          [:body str-title 
                 (body-header moduletype)]]))
