
(ns cirru-edn.comp.container
  (:require [hsl.core :refer [hsl]]
            [respo-ui.core :as ui]
            [respo.core
             :refer
             [defcomp defeffect cursor-> <> div button textarea span input]]
            [respo.comp.space :refer [=<]]
            [reel.comp.reel :refer [comp-reel]]
            [cirru-edn.config :refer [dev?]]
            [favored-edn.core :refer [write-edn]]
            [cirru-edn.core :refer [write parse]]
            [cljs.reader :refer [read-string]]))

(def style-code {:font-family ui/font-code, :white-space :pre, :font-size 13})

(defcomp
 comp-container
 (reel)
 (let [store (:store reel), states (:states store), state (or (:data states) {:draft ""})]
   (div
    {:style (merge ui/global ui/fullscreen ui/column)}
    (div
     {:style (merge ui/row-parted)}
     (span nil)
     (div
      {:style ui/row-middle}
      (button
       {:style ui/button,
        :inner-text "Parse",
        :on-click (fn [e d! m!] (d! :result (write-edn (parse (:draft state)) {:indent 2})))})
      (button
       {:style ui/button,
        :inner-text "Write",
        :on-click (fn [e d! m!] (d! :result (write (read-string (:draft state)))))})))
    (div
     {:style (merge ui/expand ui/row)}
     (textarea
      {:style (merge ui/expand ui/textarea style-code),
       :value (:draft state),
       :on-input (fn [e d! m!] (m! (assoc state :draft (:value e))))})
     (textarea {:style (merge ui/expand ui/textarea style-code), :value (:result store)}))
    (when dev? (cursor-> :reel comp-reel states reel {})))))
