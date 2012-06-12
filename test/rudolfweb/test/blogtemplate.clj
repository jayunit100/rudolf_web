(ns rudolfweb.test.blogtemplate
  (:use clojure.test)
  (:use rudolfweb.blogtemplate))


(deftest test-read-articles-file
  (let [af (read-articles-file)]
   (is (= 1 (count af)))
   (is (> 1 (count (af :articles))))))


(deftest read-article
  (is (> 50 (count (read-article "git_cheat_sheet")))))


(deftest test-can-find-all-articles
  (for [af read-articles-file]
   (is (> 100 (count (read-article af)))))) ;; uh ... trying to check that the file could be read successfully and that an html error response was not being returned


(deftest test-index
  (is (> 100 (count (index)))))


(deftest test-post
  (is (> 100 (count (post "mysql_tutorial")))))


(deftest test-header
  (is (= 6 (count header))))


(deftest test-footer
  (is (= 2 (count footer))))


(deftest test-body-header
  (let [bh (body-header ["abc" "def" "ghi"])]
   (is (= 4 (count bh)))
   (is (= :ul (get bh 0))) ;; uh ... trying to get 0th element from bh
   (is (= :li (get (get bh 1) 0)))))


(deftest test-make-link
  (is (= 3 (count (make-link "abcd")))))
