(ns books.cljs.main
  (:require [enfocus.core :as ef]
            [fetch.remotes :as remotes])
  (:require-macros [enfocus.macros :as em]
                   [fetch.macros :as fm]))

(defn get-book-title []
  (em/from (em/select ["#title"]) (em/get-prop :value)))

(defn get-book-review []
  (em/from (em/select ["#review"]) (em/get-prop :value)))

(defn get-book-data []
  {:title (get-book-title)
   :review (get-book-review)})

(defn push-book []
  (fm/remote (add-book-to-db (get-book-data))))

(defn html-title []
  (str "<li>" (get-book-title) "</li>"))

(em/defaction update-books []
    ["ul"] (em/append (html-title)))

(defn get-search-keyword []
  (em/from (em/select ["#search"]) (em/get-prop :value)))
 
(em/defaction update-results [results]
  ["ul"] (em/content
                   (apply str (map (fn [result] (str "<li>" (:title result) "</li>")) results))))

(defn search-books [event]
  (.preventDefault event)
  (fm/remote (search-db (get-search-keyword)) [results]
             (update-results results)
             (.log js/console results)))


(em/defaction clear-form []
              ["#title"] (em/set-prop :value nil)
              ["#review"] (em/set-prop :value nil))

(defn get-book-review []
  (em/from (em/select ["#review"]) (em/get-prop :value)))

(em/defaction setup []
              [".submit"] (em/listen :click #(do (push-book)
                                                 (update-books)
                                                 (clear-form)))
              ["form.search"] (em/listen :submit search-books))

(set! (.-onload js/window) setup)
