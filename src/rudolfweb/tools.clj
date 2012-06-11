(ns rudolfweb.tools
 (:use [clojure.string :only (split)]))

;;input :  a string "a b b"
;;output : a map : {"a" 1 "b" 2} 
(defn wordenrichment [str_in] 
  {:pre [(= (type str_in) (type ""))]}
  (let [all (split str_in #"\s+")
        _ (print all)
        ]
        (into {}
              (for [unique_word (set all)]
                [unique_word 
                (count (filter #(= unique_word %) all))]))))

;;input : a string url "http://www.google.com/a.csv
;;output : slurped url word count (see wordenrichment)
(defn wordenrichment_url [str_in]
  {:pre (= "http" (re-find #"http" str_in))} ;;ghetto version of contains?
  (wordenrichment (slurp str_in)))