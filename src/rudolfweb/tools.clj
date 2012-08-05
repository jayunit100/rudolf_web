(ns rudolfweb.tools
  (:require [clojure.string])
  (:use clojure.test
        clojure.java.io
        clojure-csv.core
        clojure.data.json));;should use "only" here! (use '[clojure.data.json :only (read-json json-str)])

(import '(java.io BufferedReader StringReader))


;;calculate word enrichment from a string
(defn word-enrichment 
  "input:  a string 'a b b'
   output: a map : {'a' 1 'b' 2}"
  [str_in] 
  {:pre [(= (type str_in) (type ""))]}
  (let [all (clojure.string/split str_in #"\b+")
        cnt-word (fn cnt-word [w-map word] 
                   (let [v (w-map word)]
                     (assoc w-map word 
                        (if v (inc v) 1))))]
    ;The magic happens here
    (reduce cnt-word {} all)))

;;Crawls a url, expecting CSV, and converts it to json 
(defn csv-to-json
  "input : a url, with csv content
   output : clojure data structure w/ csv contents 
   (no gaurantee on what the data structure is). "
  [str_url]
  {:pre (= (type str_url) (type ""))}
  (json-str (parse-csv (slurp str_url)) ))


;;Crawls a url, then calculates word enrichment from it.
(defn word-enrichment-url 
  "input: a string url 'http://www.google.com/a.csv'
   output: slurped url word count (see word-enrichment)"
  [str_in]
  {:pre [ (= (type str_in) (type ""))
	  (.contains str_in "http")]} ;;ghetto version of contains?
  (word-enrichment (slurp str_in)))

