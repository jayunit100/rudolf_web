(ns rudolfweb.tools
 (:use [clojure.string :only (split)]))


(defn word-enrichment 
  "input:  a string "a b b"
   output: a map : {"a" 1 "b" 2}"
  [str_in] 
  {:pre [(= (type str_in) (type ""))]}
  (let [all (split str_in #"\s+")
        _ (print all)]
    (into {}
          (for [unique_word (set all)]
            [unique_word 
             (count (filter #(= unique_word %) all))]))))


(defn word-enrichment-url 
  "input: a string url "http://www.google.com/a.csv
   output: slurped url word count (see word-enrichment)"
  [str_in]
  {:pre (= "http" (re-find #"http" str_in))} ;;ghetto version of contains?
  (word-enrichment (slurp str_in)))