(ns cljs-rn-sandbox.views
  (:require [reagent.core :as r]
            [cljs-rn-sandbox.actions :as actions]
            [cljs-rn-sandbox.constants :as constants]
            [cljs-rn-sandbox.store :as store]))

(def ReactNative (js/require "react-native"))
(def expo (js/require "expo"))
(def AtExpo (js/require "@expo/vector-icons"))
(def ionicons (.-Ionicons AtExpo))
(def ic (r/adapt-react-class ionicons))
(def NativeBase (js/require "native-base"))

(def image (r/adapt-react-class (.-Image ReactNative)))
(def switch (r/adapt-react-class (.-Switch ReactNative)))

(def text (r/adapt-react-class (.-Text NativeBase)))
(def view (r/adapt-react-class (.-View NativeBase)))
(def badge (r/adapt-react-class (.-Badge NativeBase)))
(def button (r/adapt-react-class (.-Button NativeBase)))
(def container (r/adapt-react-class (.-Container NativeBase)))
(def header (r/adapt-react-class (.-Header NativeBase)))
(def content (r/adapt-react-class (.-Content NativeBase)))
(def footer (r/adapt-react-class (.-Footer NativeBase)))
(def footer-tab (r/adapt-react-class (.-FooterTab NativeBase)))
(def icon (r/adapt-react-class (.-Icon NativeBase)))

(def main-view-style {:flex 1
                      :align-self "center"
                      :align-items "center"
                      :flex-direction "column"
                      :justify-content "center"})

(def text-style {:font-size 30
                 :font-weight "100"
                 :margin-bottom 20
                 :text-align "center"})

(defn create-counter-screen-action
  []
  (cond
    (= @store/operation-cursor constants/increment) actions/increment-counter
    :else actions/decrement-counter))

(defn counter-screen
  []
  [view {:style main-view-style}
   [image {:source (js/require "./assets/images/cljs.png")
           :style {:width 200
                   :height 200}}]
   [text {:style text-style} (str constants/counter " -> " @store/counter-cursor)]
   [ic {:name "ios-arrow-down" :size 60 :color "green"}]
   [button {:rounded true :dark true :style {:align-self "center" :justify-self "center"}
            :on-press (create-counter-screen-action)}
    [text @store/operation-cursor]]])

(defn create-footer-tab-icon
  []
  (cond
    (= @store/operation-cursor constants/increment) "ios-arrow-round-up"
    :else "ios-arrow-round-down"))

(defn footer-tab-counter-button
  []
  [button {:vertical true :badge true
           :on-press actions/select-counter-tab}
   [badge
    [text @store/counter-cursor]]
   [icon {:name (create-footer-tab-icon)}]
   [text constants/counter]])

(defn settings-screen
  []
  [view {:style main-view-style}
   [text {:style text-style} "Decrement counter"]
   [switch {:on-value-change actions/toggle-operation
            :value (= @store/operation-cursor constants/decrement)}]])

(defn footer-tab-settings-button
  []
  [button {:vertical true
           :on-press actions/select-settings-tab}
   [icon {:name "settings"}]
   [text constants/settings]])

(defn footer-with-tabs
  []
  [footer
   [footer-tab
    [footer-tab-counter-button]
    [footer-tab-settings-button]]])

(defn main
  []
  [container
   (cond
     (= @store/active-tab-cursor constants/counter) [counter-screen]
     :else [settings-screen])
   [footer-with-tabs]])
