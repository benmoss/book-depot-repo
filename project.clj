(defproject books "0.1.0-SNAPSHOT"
            :description "Books- A database-backed Clojurescript app."
            :dependencies [[org.clojure/clojure "1.4.0"]
                           [noir "1.3.0"]
                           [fetch "0.1.0-alpha2"]
                           [enfocus "1.0.0-beta2"]
                           [org.clojure/java.jdbc "0.2.3"]
                           [org.xerial/sqlite-jdbc "3.7.2"]]
            :plugins [[lein-cljsbuild "0.2.8"]]
            :cljsbuild {
                        :builds [{
                                  :source-path "src/cljs"
                                  :compiler {
                                             :output-to "resources/public/js/main.js"
                                             :optimizations :advanced}}]}
            :main books.server)
