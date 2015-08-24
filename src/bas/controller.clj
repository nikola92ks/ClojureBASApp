(ns bas.controller
  (:require [clostache.parser :as clostache]
            [bas.prevoznici :as prevoznici]
            [bas.linije :as linije]
            [bas.mesta :as mesta]))

(defn read-template [template-name]
  (slurp (clojure.java.io/resource
           (str "views/prevoznici/" template-name ".mustache"))))

(defn render-template [template-file params]
  (clostache/render (read-template template-file) params))

(defn index []
  (render-template "index" {:prevoznici (prevoznici/all)}))


(defn new []
  (render-template "new" {:mesta (mesta/all)}))

(defn edit [id]
  (render-template "edit" {:prevoznik (prevoznici/get id)
                           :mesta (mesta/all)}))

(defn newmesta []
  (render-template "mesta" {:mesta (mesta/all)}))

(defn editmesta [id]
  (render-template "editmesta" {:mesto (mesta/get id)}))

(defn linije []
  (render-template "linije" {:linije (linije/all)}))

(defn linijebuttonedit []
  (render-template "linijebuttonedit" {:linije (linije/all)}))

(defn newlinija []
  (render-template "linijenew" {:mesta (mesta/all)
                                :prevoznici (prevoznici/all)}))
(defn editlinije [id]
  (render-template "linijeedit" {:linija (linije/get id)
                                 :mesta (mesta/all)
                                 :prevoznik (prevoznici/all)}))
