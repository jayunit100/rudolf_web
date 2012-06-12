(ns rudolfweb.test.blogtemplate
  (:use clojure.test)
  (:require [hiccup.core          :as hc])
  (:require [rudolfweb.blogtemplate :as bt]))


(deftest test-read-articles-file
  (let [af (bt/read-articles-file)]
   (is (= 1 (count af)))
   (is (> (count (af :articles)) 1))))


(deftest read-article
  (is (> (count (bt/read-article "git_cheat_sheet")) 50)))


(deftest test-can-find-all-articles
  (for [af (bt/read-articles-file)]
   (is (> (count (bt/read-article af)) 100)))) ;; uh ... trying to check that the file could be read successfully and that an html error response was not being returned


(deftest test-index
  (is (> (count (bt/index)) 100)))


(deftest test-post
  (is (> (count (bt/post "mysql_tutorial")) 100)))


(deftest test-header
  (is (= 6 (count bt/header))))


(deftest test-footer
  (is (= 2 (count bt/footer))))


(deftest test-body-header
  (let [bh (bt/body-header ["abc" "def" "ghi"])]
   (is (= 3 (count (get bh 1))))
   (is (= bh [:ul (list [:li [:a {:href "abc"} "abc"]] 
                        [:li [:a {:href "def"} "def"]] 
                        [:li [:a {:href "ghi"} "ghi"]])])) 
   (is (= :ul (get bh 0))))) ;; uh ... trying to get 0th element from bh


(deftest test-body-header-html
  (is (= (hc/html (bt/body-header ((bt/read-articles-file) :articles))) 
         "<ul><li><a href=\"RudolF_first\">RudolF_first</a></li><li><a href=\"git_cheat_sheet\">git_cheat_sheet</a></li><li><a href=\"mysql_tutorial\">mysql_tutorial</a></li></ul>")))


(deftest test-make-link
  (is (= 3 (count (bt/make-link "abcd")))))

