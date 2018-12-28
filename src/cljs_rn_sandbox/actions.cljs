(ns cljs-rn-sandbox.actions
  (:require [cljs-rn-sandbox.store :as store]))

(defn increment-counter
  []
  (swap! store/counter-cursor inc))
