(ns bas.prevoznici
  (:refer-clojure :exclude [get])
  (:require [clojure.java.jdbc :as j]
            [clojure.java.jdbc.sql :as s]))

(def mysql-db {:subprotocol "mysql"
               :subname "//localhost:3306/bas"
               :user "root"
               :pass " "})

(defn all []
  (j/query mysql-db
       ["SELECT * FROM prevoznici p INNER JOIN mesto m ON (p.sediste_prevoznika = m.id)"]))

(defn get [id]
  (first (j/query mysql-db
                  (s/select * :prevoznici (s/where {:id id})))))

(defn create [params]
  (j/insert! mysql-db :prevoznici params))

(defn update [id params]
  (j/update! mysql-db :prevoznici params (s/where {:id id})))

(defn delete [id]
  (j/delete! mysql-db :prevoznici (s/where {:id id})))
