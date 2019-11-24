
(ns cirru-edn.core
  (:require [cirru-parser.core :as cirru-parser] [cirru-writer.core :as cirru-writer]))

(defn cirru->edn [xs]
  (cond
    (string? xs)
      (cond
        (= "|" (first xs)) (subs xs 1)
        (= ":" (first xs)) (keyword (subs xs 1))
        (= "true" xs) true
        (= "false" xs) false
        (= "nil" xs) nil
        (re-matches (re-pattern #"-?[\d\.]+") xs) (js/parseFloat xs)
        :else (do (js/console.warn "Unknow data" xs) nil))
    (vector? xs)
      (cond
        (= "[]" (first xs)) (->> xs (rest) (map cirru->edn) (vec))
        (= "list" (first xs)) (->> xs (rest) (map cirru->edn))
        (= "set" (first xs)) (->> xs (rest) (map cirru->edn) (set))
        (= "{}" (first xs))
          (->> xs (rest) (map (fn [[k v]] [(cirru->edn k) (cirru->edn v)])) (into {})))
    :else (do (js/console.warn "Unknown data" xs) nil)))

(defn edn->cirru [data]
  (cond
    (string? data) (str "|" data)
    (number? data) (str data)
    (boolean? data) (str data)
    (keyword? data) (str data)
    (map? data)
      (vec (concat (list "{}") (map (fn [[k v]] [(edn->cirru k) (edn->cirru v)]) data)))
    (vector? data) (vec (concat (list "[]") (map edn->cirru data)))
    (list? data) (vec (concat (list "list") (map edn->cirru data)))
    (set? data) (vec (concat (list "set") (map edn->cirru data)))
    :else (do (js/console.warn "Unknown data" data) [])))

(defn parse [code]
  (let [cirru-tree (cirru-parser/pare code nil)]
    (js/console.log "log" (cirru-parser/pare code nil))
    (if (not= 1 (count cirru-tree))
      (js/console.warn "data should only contain 1 item" (count cirru-tree)))
    (cirru->edn (first cirru-tree))))

(defn write [data] (cirru-writer/write-code [(edn->cirru data)]))
