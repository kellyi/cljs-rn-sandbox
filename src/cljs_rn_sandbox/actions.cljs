(ns cljs-rn-sandbox.actions
  (:require [cljs-rn-sandbox.store :as store]
            [cljs-rn-sandbox.constants :as constants]))

(defn increment-counter
  []
  (swap! store/counter-cursor inc))

(defn decrement-counter
  []
  (swap! store/counter-cursor dec))

(defn select-settings-tab
  []
  (reset! store/active-tab-cursor constants/settings))

(defn select-counter-tab
  []
  (reset! store/active-tab-cursor constants/counter))

(defn toggle-operation
  []
  (let [new-operation
        (cond
          (= @store/operation-cursor constants/increment) constants/decrement
          :else constants/increment)]
    (reset! store/operation-cursor new-operation)))
