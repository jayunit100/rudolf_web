(ns rudolfweb.test.main-local
  (:use clojure.test)
  (:use rudolfweb.main-local))


(deftest test-app-auto-reload
  (print "WARNING : TEST NOT IMPLEMENTED.")
  (is (= 1 1) "unsure how to test this"))

;;TODO : We should write a with-timeout macro and this should die
;;after a few minutes.  CURRENTLY, this hangs instead of failing on a 
;;machine with missing deps.
;;(deftest test-boot
;;  (is (boot)))