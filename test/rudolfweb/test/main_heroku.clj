(ns rudolfweb.test.main-heroku
  (:use clojure.test)
  (:use rudolfweb.main-heroku))

(deftest test-unimplemented
  (print "Light warning ::: TEST NOT IMPLEMENTED (not sure if its possible to test heroku).") 
  (is (= 1 1) "test is not implemented"))
