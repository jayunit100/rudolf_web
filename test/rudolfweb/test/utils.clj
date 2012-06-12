(ns rudolfweb.test.utils
  (:use clojure.test)
  (:use rudolfweb.utils))


(deftest test-unimplemented
  (is (= 1 2) "test is not implemented"))
