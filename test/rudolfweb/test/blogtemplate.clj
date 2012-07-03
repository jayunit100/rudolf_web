(ns rudolfweb.test.blogtemplate
  (:use clojure.test)
  (:require [hiccup.core          :as hc])
  (:require [rudolfweb.blogtemplate :as bt]))


(deftest test-read-articles-file
  (let [af (bt/read-articles-file)]
   (is (= 1 (count af)))
   (is (> (count (af :articles)) 2))))


(deftest read-article
  (is (> (count (bt/read-article "mysql_tutorial")) 50)))

(deftest test-can-find-all-articles
  (let [af (bt/read-articles-file)]
   (is (= (count af) 1))
   (is (> (count (af :articles)) 2))
   (doall
    (for [a (af :articles)]
     (try (bt/read-article a)
          (catch Exception e (is (= 1 2) (str "failed to read article file " a ", exception " e))))))))

(deftest test-can-render-all-articles-without-exceptions
  (let [af (bt/read-articles-file)]
    (doall
      (for [a (af :articles)]
        (try (is (> (count (bt/post a)) 100))
             (catch Exception e (is false (str "failed to render article file " a ", exception " e))))))))



(deftest test-header-and-footer
  (is (.contains (str bt/header) "RudolF"))
  (is (.contains bt/footer "RudolF")))

(deftest test-body-header
  (let [bh (bt/body-header ["article1" "article2"])]
    (is (= 2 (count (get bh 1))))
    (is (.contains (str bh) "article1")) 
    (is (.contains (str bh) "article2"))
    (is (.contains (str bh) "ul"))
    ;;We want to see the :li element twice, once for each article
    ;;We might not need to test the exact html format...
    (is (= 2 
           (count (filter (fn li [t] (.contains (str t) "li")) (nth bh 1)))))
    (is (= :ul (get bh 0))))) ;; uh ... trying to get 0th element from bh



;;A bit of overtesting here.  We might want to limit it to only one blog.
(deftest test-body-header-html
  (let [str_articles_html (hc/html (bt/body-header ((bt/read-articles-file) :articles)))]
    (is (.contains str_articles_html "rudolf_architecture"))
    (is (.contains str_articles_html "mysql_tutorial"))))

(deftest test-make-link
  (is (= 3 (count (bt/make-link "abcd")))))
