(ns rudolfweb.tools
 (:use [clojure.string :only (split)]))

;;word enrichment : input a string
;;output : json w/ word enrichments

;;key / value
(defn wordenrichment [str_in] 
  {:pre [(= (type str_in) (type ""))]}
  (let [all (split str_in #"\s+")]
        (into {}
              (for [unique_word (set all)]
                [unique_word 
                (count (filter #(= unique_word %) all))]))))