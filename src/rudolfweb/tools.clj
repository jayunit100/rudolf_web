(ns rudolfweb.tools
  (:require [clojure.string :as cs]))

;;calculate word enrichment from a string
(defn word-enrichment 
  "input:  a string 'a b b'
   output: a map : {'a' 1 'b' 2}"
  [str_in] 
  {:pre [(= (type str_in) (type ""))]}
  (let [all (cs/split str_in #"\b+")]
    (into {}
          (for [unique_word (set all)]
            [unique_word 
             (count (filter #(= unique_word %) all))]))))

;;Crawls a url, then calculates word enrichment from it.
(defn word-enrichment-url 
  "input: a string url 'http://www.google.com/a.csv'
   output: slurped url word count (see word-enrichment)"
  [str_in]
  {:pre [ (= (type str_in) (type ""))
	  (.contains str_in "http")]} ;;ghetto version of contains?
  (word-enrichment (slurp str_in)))

