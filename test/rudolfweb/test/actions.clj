(ns rudolfweb.test.actions
  (:use clojure.test)
  (:require [hiccup.core        :as  hc])
  (:require [rudolfweb.actions  :as  ra])
  (:require [clojure.data.json      :as json]))

;;TODO verify that this is valid json
(deftest test-layout-word-enrichment
  (let [layout (ra/layout-word-enrichment "http://www.google.com")]
   (is (map? (json/read-json layout)))
   (is (< 10000 (count layout)) ) ;;Expect more then 10,000 chars.
   (is (.contains layout "google"))
   ))


(deftest test-home-layout
  (let [lay (ra/home-layout "abcd" [1 2 3])]
   (is (= 4 (count lay)))
   (is (= :html (get lay 0)))
   (is (.contains ;;simple test, make sure some of the text is preserved once we run the layout.
         (hc/html 
           (ra/home-layout "Rudolf" [:ul [:li "hi"] [:li "what"]]))
         "what"
         )) 
   (is (= :head (get (get lay 1) 0)))))


(deftest test-rudolf-home
  (let [rh (ra/rudolf-home)]
   (is (= 4 (count rh)))
   (is (>= (count (get rh 1)) 1))))
