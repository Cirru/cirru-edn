
(ns cirru-edn.nim (:require [cirru-parser.nim :as nim-parser] [cirru-edn.core :as edn]))

(defn parse [code]
  (let [cirru-tree (nim-parser/parse code)]
    (if (not= 1 (count cirru-tree))
      (js/console.warn "data should only contain 1 item" (count cirru-tree)))
    (edn/cirru->edn (first cirru-tree))))
