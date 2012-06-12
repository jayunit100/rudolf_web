(ns rudolfweb.test.actions
  (:use clojure.test)
  (:use rudolfweb.actions))



(deftest test-layout-word-enrichment
  (let [layout (layout-word-enrichment "http://www.google.com")]
   (is (= 1 (count layout)))
   (is (>= 1 (count (layout :html))))))


(deftest test-layout
  (let [l (layout "abcd" [1 2 3])]
   (is (= 1 (count l)))
   (is (>= 1 (count (l :html))))))


(deftest test-rudolf-home
  (let [rh (rudolf-home)]
   (is (= 1 (count rh)))
   (is (>= 1 (count (rh :html))))))
