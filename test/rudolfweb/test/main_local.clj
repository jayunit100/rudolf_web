(ns rudolfweb.test.main-local
  (:use clojure.test)
  (:use rudolfweb.main-local))


(deftest test-unimplemented
  (is (= 1 2) "test is not implemented"))
