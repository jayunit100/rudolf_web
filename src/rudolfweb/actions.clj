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
   "Welcome to RudolF: this is the hub for our activities.  It's written in Clojure."
   [:ul 
    [:li "Clojure  Jetty  (service)"]
    [:li "Ring (abstraction)"]
    [:li "Compojure (forwarding)"] 
    [:li "Hiccup (formatting)."]
    [:li "extend : routes.clj."]]
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
