(ns rudolfweb.test.tests
  (:use clojure.test)
  (:use rudolfweb.tools)
  (:use clojure.string)
  )

(deftest snippet1
  (= 4 (count (split "ABCAD ABBBBB ABCAD 12" #"\s+"))))

(deftest snippet2
  (= 3 (count (into {} (map vector [:a :b :c] (repeat 0))))))

(deftest test_we
  (= 
    (wordenrichment "a s v a a asdg asdg asdg")
    {"a" 3, "s" 1, "asdg" 3, "v" 1}))

(deftest test_we_url
  (> (count (wordenrichment_url "http://www.google.com")) 10))