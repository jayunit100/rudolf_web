(ns rudolfweb.test.tools
  (:use clojure.test)
  (:use rudolfweb.tools)
  (:require [clojure.string     :as cs])
  (:require [clojure.data.json  :as json]))

(deftest test-csv-to-json 
 (is (.contains (csv-to-json
   "http://csvutil4j.googlecode.com/hg-history/5de0be19c13f593abb445868745c48736ea5f2ef/csvutil4j-csv/src/test/resources/example.csv") "break" )))

(deftest snippet1
  (is (= 4 (count (cs/split "ABCAD ABBBBB ABCAD 12" #"\s+")))))

(deftest snippet2
  (is (= 3 (count 
            (into {} 
             (map vector [:a :b :c] (repeat 0)))))))



(deftest test-word-enrichment
  (is 
   (= 
     (word-enrichment 
       "a s v a a asdg asdg asdg")
       {"" 1, " " 7, "a" 3, "s" 1, "asdg" 3, "v" 1}
     )))


(deftest test-word-enrichment-url
  (is
   (> (count (word-enrichment-url "https://github.com/clojure/data.json")) 10)))


(deftest test-json
  (is (json/json-str {:a [1 2 3], :b "Hello"})))
