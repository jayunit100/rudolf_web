(ns rudolfweb.test.main-local
  (:use clojure.test)
  (:use rudolfweb.main-local))


(deftest test-app-auto-reload
  (print "WARNING : UNSURE HOW TO TEST THIS!")
  (is (= 1 1) "unsure how to test this"))


(deftest test-boot
  (is (boot)))
