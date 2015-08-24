(ns bas.linije
  (:refer-clojure :exclude [get])
  (:require [clojure.java.jdbc :as j]
            [clojure.java.jdbc.sql :as s]))

(def mysql-db {:subprotocol "mysql"
               :subname "//localhost:3306/bas"
               :user "root"
               :pass " "})

(defn all []
  (j/query mysql-db
       ["SELECT vreme, l.id AS id, prevoznik_id, mp.naziv AS polaziste, mo.naziv AS odrediste, datum, naziv_prevoznika FROM linija l INNER JOIN mesto mp ON (l.polaziste = mp.id) INNER JOIN mesto mo ON (l.odrediste = mo.id) INNER JOIN prevoznici p ON (p.id=l.prevoznik_id)"]))

(defn create [params]
  (j/insert! mysql-db :linija params))

(defn get [id]
  (first (j/query mysql-db
    (s/select * :linija (s/where {:id id})))))

(defn update [id params]
  (j/update! mysql-db :linija params (s/where {:id id})))

(defn delete [id]
  (j/delete! mysql-db :linija (s/where {:id id})))