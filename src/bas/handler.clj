(ns bas.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.util.response :as resp]
            [ring.middleware.basic-authentication :refer :all]
            [bas.controller :as controller]
            [bas.prevoznici :as prevoznici]
            [bas.linije :as linije]
            [bas.mesta :as mesta]))


(defn authenticated? [name pass]
  (and (= name "user")
       (= pass "pass")))


(defroutes public-routes
  (GET "/linije" [] (controller/linije))
  (route/resources "/linije" ))

;(defn foo
;  "I don't do a whole lot."
;  [x]
;  (println x "Hello, World!"))

(defroutes protected-routes
  (GET "/prevoznici" [] (controller/index))
  (route/resources "/")
  (GET "/linije" [] (controller/linije))
  (route/resources "/")
  (GET "/linije/edit" [] (controller/linijebuttonedit))
  (route/resources "/")
  (GET "/prevoznici/new" [] (controller/new))
  (GET "/prevoznici/:id/delete" [id]
       (do (prevoznici/delete id)
        (resp/redirect "/prevoznici")))
  (POST "/prevoznici/create" [& params]
        (do (prevoznici/create params)
         (resp/redirect "/prevoznici")))
  (POST "/prevoznici/:id/update" [& params]
       (do (prevoznici/update (:id params) params)
         (resp/redirect "/prevoznici")))
  (GET "/prevoznici/:id/edit" [id] (controller/edit id))
  (GET "/mesta/new" [] (controller/newmesta))
  (POST "/mesta/create" [& params]
        (do (mesta/create params)
         (resp/redirect "/mesta/new")))
  (GET "/mesta/:id/edit" [id] (controller/editmesta id))
  (POST "/mesta/:id/update" [& params]
       (do (mesta/update (:id params) params)
         (resp/redirect "/mesta/new")))
  (GET "/mesta/:id/delete" [id]
       (do (mesta/delete id)
        (resp/redirect "/mesta/new")))
  (GET "/linije/new" [] (controller/newlinija))
  (POST "/linije/create" [& params]
        (do (linije/create params)
         (resp/redirect "/linije")))
  (GET "/linije/:id/edit" [id] (controller/editlinije id))
  (POST "/linije/:id/update" [& params]
       (do (linije/update (:id params) params)
         (resp/redirect "/linije/edit")))
  (GET "/linije/:id/delete" [id]
       (do (linije/delete id)
        (resp/redirect "/linije/edit"))))

(defroutes app-routes 
  public-routes 
  (wrap-basic-authentication protected-routes authenticated?)
  (route/not-found "404 Not Found"))

(def app 
  (handler/site app-routes))