
(ns cirru-edn.comp.container
  (:require [hsl.core :refer [hsl]]
            [respo-ui.core :as ui]
            [respo.core :refer [defcomp defeffect >> <> div button textarea span input]]
            [respo.comp.space :refer [=<]]
            [reel.comp.reel :refer [comp-reel]]
            [cirru-edn.config :refer [dev?]]
            [favored-edn.core :refer [write-edn]]
            [cirru-edn.core :refer [write parse]]
            [cirru-edn.nim :as nim]
            [cljs.reader :refer [read-string]]))

(def style-code {:font-family ui/font-code, :white-space :pre, :font-size 13})

(defcomp
 comp-container
 (reel)
 (let [store (:store reel)
       states (:states store)
       cursor []
       state (or (:data states) {:draft ""})]
   (div
    {:style (merge ui/global ui/fullscreen ui/column)}
    (div
     {:style (merge ui/row-center {:padding 8})}
     (button
      {:style ui/button,
       :inner-text "Parse Cirru to EDN",
       :on-click (fn [e d!]
         (try
          (d! :result (write-edn (parse (:draft state)) {:indent 2}))
          (catch js/Error e (d! :error e))))})
     (=< 8 nil)
     (button
      {:style ui/button,
       :inner-text "Write Cirru from EDN",
       :on-click (fn [e d!]
         (try
          (d! :result (write (read-string (:draft state))))
          (catch js/Error e (d! :error e))))}))
    (div {:style ui/center} (if (some? (:error store)) (<> (:error store) {:color :red})))
    (div
     {:style (merge ui/expand ui/row)}
     (textarea
      {:style (merge ui/expand ui/textarea style-code),
       :value (:draft state),
       :on-input (fn [e d!] (d! cursor (assoc state :draft (:value e))) (d! :result "")),
       :placeholder "Data..."})
     (textarea
      {:style (merge ui/expand ui/textarea style-code),
       :value (:result store),
       :placeholder "Compile result.."}))
    (when dev? (comp-reel (>> states :reel) reel {})))))
