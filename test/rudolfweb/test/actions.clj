(ns rudolfweb.test.actions
  (:use clojure.test)
  (:use rudolfweb.actions))


(deftest test-unimplemented
  (is (= 1 2) "test is not implemented"))
