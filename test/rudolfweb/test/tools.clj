(ns rudolfweb.test.tools
  (:use clojure.test)
  (:use rudolfweb.tools)
  (:use clojure.string)
  (:use [clojure.data.json :only (read-json json-str)]))

(deftest snippet1
  (= 4 (count (split "ABCAD ABBBBB ABCAD 12" #"\s+"))))

(deftest snippet2
  (= 3 (count (into {} (map vector [:a :b :c] (repeat 0))))))

(deftest test-we
  (= 
    (wordenrichment "a s v a a asdg asdg asdg")
    {"a" 3, "s" 1, "asdg" 3, "v" 1}))

(deftest test-we-url
  (> (count (wordenrichment_url "https://github.com/clojure/data.json")) 10))

(deftest test-json
  (json-str {:a [1 2 3], :b "Hello"}))