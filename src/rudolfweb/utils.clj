(ns rudolfweb.utils)


(defn traverse-xmap
  "Iterate the map of xml node is recursive function. DOM parsing approach."
  [xmap]
  (if (empty? (:content xmap))
    (print (str "[" (:tag xmap) " " (:attrs xmap)))
    (do
      (print (str "[" (:tag xmap) " " (:attrs xmap)))
      (loop [cvec (:content xmap)]      
        (if (empty? cvec)
          (print (str "]"))
          (recur (do (if (map? (first cvec))
                       (traverse-xmap (first cvec))
                       (print (str "\"" (first cvec) "\"")))
                     (subvec cvec 1))))))))

(defn parse-file
  "Parse XML file containing HTML tag and print the hiccup framework
   html tag represemtation
   :Cons If nested tags are more then it may throw outofmemory error."
  [^String fname] ;; file name
  (traverse-xmap (clojure.xml/parse fname)))