(ns books.views.common
  (:use [noir.core :only [defpartial]]
        [hiccup.page :only [include-css include-js html5]]))

(defpartial layout [& content]
            (html5
              [:head
               [:title "books"]]
              [:body
               [:div#wrapper
                content]])
            (include-js "/js/main.js"))
