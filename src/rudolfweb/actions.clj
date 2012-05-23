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
   [:script {:type "text/javascript"
             :src "jquery1.7.2.js"}] 
   [:script {:type "text/javascript"
             :src "http://www.cornify.com/js/cornify.js"}]
   [:script {:type "text/javascript"
             :src "script.js"}] 
   [:body [:h1.header title] body])) 

(defn welcome-page 
  []
  (layout 
   "Welcome to RudolF: We believe in the power of agile, domain-driven software.  Not sure what that is ?  Thats okay, you'll know soon enough.  This is the internal hub for our ongoing research.  It's written in Clojure.  We are currently developing a business front end as well, so check back !"
   [:ul 
    [:li "Our buzzwords : Java, Python, Clojure, MySQL, Heroku, Hadoop"]
    [:li "Our domains : Bioinformatics, Social-Networking, and Global Media"]
    [:li "Questions ? Contact jay.vyas@rudolflabs.com" ]
    [:li "This site : Clojure, Jetty, Ring, and Compujure+Hiccup  (service)"]
    [:li "Developers can extend this site here : routes.clj."]]
   [:h3 "Rudolf"]
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
