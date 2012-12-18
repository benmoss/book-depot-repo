(ns books.views.welcome
  (:require [books.views.common :as common])
  (:use [noir.core :only [defpage]]
        books.models.db
        noir.fetch.remotes
        hiccup.form))

(defpage "/" []
         (common/layout
           [:h1 "Current Books"]
           [:form {:class "search"}
            (text-field "search")
            [:button "Search"]]
           [:ul
            (for [book (db-read-all)]
              [:li (:title book)])]
           [:div
            [:h2 "Create a new book"]
            (label {} "title" "Title: ")
            (text-field {:class "title"} "title")
            [:br]
            (label {} "review" "Review: ")
            (text-area {:class "review"} "review")
            [:br]
            [:button {:class "submit"} "Submit"]]))

(defremote add-book-to-db [book]
           (println (str "Adding to the DB: " book))
           (add-book book))

(defremote search-db [keyword]
           (println (str "Searching for: " keyword))
           (search keyword))
