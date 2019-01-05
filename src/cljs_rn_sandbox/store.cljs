(ns cljs-rn-sandbox.store
  (:require [reagent.core :as r]
            [cljs-rn-sandbox.constants :as constants]))

(def app-state (r/atom {:counter 0
                        :active-operation constants/increment
                        :active-tab constants/counter}))

(def counter-cursor (r/cursor app-state [:counter]))
(def operation-cursor (r/cursor app-state [:active-operation]))
(def active-tab-cursor (r/cursor app-state [:active-tab]))
