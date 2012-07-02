(ns rudolfweb.tools
  (:require [clojure.string]))

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



;;Crawls a url, then calculates word enrichment from it.
(defn word-enrichment-url 
  "input: a string url 'http://www.google.com/a.csv'
   output: slurped url word count (see word-enrichment)"
  [str_in]
  {:pre [ (= (type str_in) (type ""))
	  (.contains str_in "http")]} ;;ghetto version of contains?
  (word-enrichment (slurp str_in)))

