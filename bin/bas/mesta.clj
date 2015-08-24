(ns bas.mesta

  (:refer-clojure :exclude [get])
  (:require [clojure.java.jdbc :as j]
            [clojure.java.jdbc.sql :as s]))

(def mysql-db {:subprotocol "mysql"
               :subname "//localhost:3306/bas"
               :user "root"
               :pass " "})

(defn all []
  (j/query mysql-db
       ["SELECT * FROM mesto"]))

(defn create [params]
  (j/insert! mysql-db :mesto params))

(defn get [id]
  (first (j/query mysql-db
    (s/select * :mesto (s/where {:id id})))))

(defn update [id params]
  (j/update! mysql-db :mesto params (s/where {:id id})))

(defn delete [id]
  (j/delete! mysql-db :mesto (s/where {:id id})))
