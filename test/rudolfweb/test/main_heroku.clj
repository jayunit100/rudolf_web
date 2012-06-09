(ns rudolfweb.test.main-heroku
  (:use clojure.test)
  (:use rudolfweb.main-heroku))


(deftest test-unimplemented
  (is (= 1 2) "test is not implemented"))
