(ns rudolfweb.actions
  (:use hiccup.core) 
  (:use hiccup.form-helpers))
; Hiccup does the HTML generation part from these strings.


(defn img 
  [] 
  (rand-nth ["http://www.coloring-page.com/pages/christmas/rudolf.gif"
             "http://kepler.nasa.gov/files/mws/JohannesTimeline1.png"
             "http://cddis.gsfc.nasa.gov/lw17/images/logo_nasa_sm.gif"]))

(defn layout 
  [title & body]
  (html
   [:head [:title title]]
   [:link {:rel "stylesheet"
           :href "article.css"
           :type "text/css"
           :media "all"}]
   [:body [:h1.header title] body])) 

(defn welcome-page 
  []
  (layout 
   "Welcome to rudolF : This is the hub for our activities.  It is written in clojure."
   [:br "C l o j u r e  J e t t y  (service)"]
   [:br "R i n g (abstraction)"]
   [:br "C o m p o j u r e (forwarding)"] 
   [:br "H i c c u p (formatting)."]
   [:br "e x t e n d : routes.clj."]
   [:h3 "r u d o l f"]
   [:img {:src (img)}]))

(defn full-name 
  [first second]
  (layout "Full Name" (str first " " second)))

(defn name-form 
  []
  (layout "Name Form"
   (form-to 
    [:post "/post-name"]
    (label :first-name "First Name") 
    (text-field :first-name) 
    [:br] 
    (label :second-name "Second Name") 
    (text-field :second-name) 
    [:p (submit-button "Submit")])))
	
(defn post-name 
  [first second]
  (full-name first second))
