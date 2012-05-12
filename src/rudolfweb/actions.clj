(ns rudolfweb.actions
  (:use hiccup.core) 
  (:use hiccup.form-helpers))
; Hiccup does the HTML generation part from these strings.

(defn layout [title & body]
	  (html
	    [:head [:title title]]
	    [:body [:h1.header title] body])) 

(defn welcome-page []
	(layout "Welcome to clojure-rudolF"
	  [:p "Clojure Jetty (server), Ring (http abstraction layer), Compojure (web-forwarding) and Hiccup (formatting)."]
	  [:p "See routes.clj for the list of example pages, and expand / edit them . "]
	  [:h3 "g o r u d o l f g o"]
	  [:img {:src "icon.jpg"}]))

(defn full-name [first second]
	(layout "Full Name" (str first " " second)))

(defn name-form []
	(layout "Name Form"
	  (form-to [:post "/post-name"]
	  (label :first-name "First Name") (text-field :first-name) [:br] (label :second-name "Second Name") (text-field :second-name) 
	  [:p (submit-button "Submit")])))
	
(defn post-name [first second]
	(full-name first second))
