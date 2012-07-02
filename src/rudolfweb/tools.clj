(ns rudolfweb.tools
  (:require [clojure.string]))

;;calculate word enrichment from a string
(defn word-enrichment 
  "input:  a string 'a b b'
   output: a map : {'a' 1 'b' 2}"
  [str_in] 
  {:pre [(= (type str_in) (type ""))]}
  (let [all (clojure.string/split str_in #"\b+")]
    (reduce 
      #(let [v (%1 %2)] 
         (assoc %1 %2 
            (if v (inc v) 1) )) {} all)))



;;Crawls a url, then calculates word enrichment from it.
(defn word-enrichment-url 
  "input: a string url 'http://www.google.com/a.csv'
   output: slurped url word count (see word-enrichment)"
  [str_in]
  {:pre [ (= (type str_in) (type ""))
	  (.contains str_in "http")]} ;;ghetto version of contains?
  (word-enrichment (slurp str_in)))

