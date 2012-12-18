(ns books.models.db
  (:require [clojure.java.jdbc :as sql]))

(def db
  {:classname "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :subname "db/database.db" })

(defn init-db []
  (try
    (sql/with-connection db
                         (sql/create-table
                           :books
                           [:title "varchar(250)"]
                           [:review "varchar(500)"]))
    (catch Exception ex
      (.getMessage (.getNextException ex)))))

(defn add-book [book]
  (sql/with-connection db
                       (sql/insert-record :books book)))

(defn search [keyword]
  (sql/with-connection db
                       (sql/with-query-results result
                                               ["SELECT * FROM books WHERE title LIKE ?" (str "%" keyword "%")]
                                               (into [] result))))

(defn db-read-all []
  (sql/with-connection db
                       (sql/with-query-results result
                                               ["SELECT * FROM books"]
                                               (into [] result))))
