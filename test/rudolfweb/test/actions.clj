(ns rudolfweb.test.actions
  (:use clojure.test)
  (:require [hiccup.core        :as  hc])
  (:require [rudolfweb.actions  :as  ra])
  (:require [clojure.data.json  :as json]))


;; TODO verify that this is valid JSON
(deftest test-layout-word-enrichment
  (let [layout (ra/layout-word-enrichment "http://www.google.com")]
   (is (map? (json/read-json layout)))
   (is (< 9000 (count layout)))
   (is (.contains layout "google"))))
