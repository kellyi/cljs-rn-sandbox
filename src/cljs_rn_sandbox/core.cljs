(ns cljs-rn-sandbox.core
    (:require [reagent.core :as r :refer [atom]]
              [oops.core :refer [ocall]]
              [cljs-rn-sandbox.views :as views]))

(def expo (js/require "expo"))
(defn app-root [] [views/main])

(defn init []
  (ocall expo "registerRootComponent" (r/reactify-component app-root)))
