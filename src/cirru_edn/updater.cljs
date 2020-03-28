
(ns cirru-edn.updater (:require [respo.cursor :refer [update-states]]))

(defn updater [store op op-data op-id op-time]
  (case op
    :states (update-states store op-data)
    :result (-> store (assoc :result op-data) (assoc :error nil))
    :error (assoc store :error op-data)
    :hydrate-storage op-data
    store))
