(ns rudolfweb.actions
  (:use hiccup.core) 
  (:use hiccup.form-helpers))
; Hiccup does the HTML generation part from these strings.


(defn img [] (rand-nth [
                   "http://www.coloring-page.com/pages/christmas/rudolf.gif"
                   "http://kepler.nasa.gov/files/mws/JohannesTimeline1.png"
                   "http://cddis.gsfc.nasa.gov/lw17/images/logo_nasa_sm.gif"]))

(defn layout [title & body]
	  (html
	    [:head [:title title]]
	    [:link {:rel "stylesheet"
                    :href "article.css"
                    :type "text/css"
                    :media "all"}]
            [:body [:h1.header title] body])) 

(defn welcome-page []
	(layout "Welcome to clojure-rudolF"
	  [:br "Clojure Jetty (server)"]
          [:br "Ring (http abstraction layer)"]
          [:br "Compojure (web-forwarding)"] 
          [:br "Hiccup (formatting)."]
	  [:br "See routes.clj for the list of example pages, and expand / edit them."]
	  [:h3 "r u d o l f"]
	  [:img {:src (img)}]))

(defn full-name [first second]
	(layout "Full Name" (str first " " second)))

(defn name-form []
	(layout "Name Form"
	  (form-to [:post "/post-name"]
	  (label :first-name "First Name") (text-field :first-name) [:br] (label :second-name "Second Name") (text-field :second-name) 
	  [:p (submit-button "Submit")])))
	
(defn post-name [first second]
	(full-name first second))
