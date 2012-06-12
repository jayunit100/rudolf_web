(ns rudolfweb.test.main-local
  (:use clojure.test)
  (:use rudolfweb.main-local))


(deftest test-app-auto-reload
  (is (= 1 2) "unsure how to test this"))


(deftest test-boot
  (is (boot)))
