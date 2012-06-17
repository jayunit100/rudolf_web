(ns rudolfweb.test.main-heroku
  (:use clojure.test)
  (:use rudolfweb.main-heroku))


(deftest test-unimplemented
  (print "WARNING TEST NOT IMPLEMENTED") 
  (is (= 1 1) "test is not implemented"))
