
(ns cirru-edn.updater (:require [respo.cursor :refer [mutate]]))

(defn updater [store op op-data op-id op-time]
  (case op
    :states (update store :states (mutate op-data))
    :result (-> store (assoc :result op-data) (assoc :error nil))
    :error (assoc store :error op-data)
    :hydrate-storage op-data
    store))
