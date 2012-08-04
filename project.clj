(defproject Clojure-Webapp-Example "1.0.0-SNAPSHOT"
  :description "A simple Clojure webapp that shows how to use Jetty, Ring, Compojure and Hiccup to build a web application."
  :dependencies [[org.clojure/clojure "1.2.1"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [ring "1.1.1"]
                 [compojure "0.6.4"]
                 [clj-yaml "0.3.0-SNAPSHOT"]
                 [hiccup "1.0.0"]
                 [org.clojure/data.json "0.1.2"]
                 [clojure-csv/clojure-csv "2.0.0-alpha1"]];;<-- add processing?
  :dev-dependencies
	        [[ring/ring-devel "1.1.0"]])
