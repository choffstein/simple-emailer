(defproject simple-emailer "1.0.0-SNAPSHOT"
  :description "FIXME: write description"
  :dependencies [[org.clojure/clojure "1.3.0-beta1"]
                 [org.apache.commons/commons-email "1.2"]]

  :repositories [["central-proxy" "http://repository.sonatype.org/content/repositories/central/"]]

  :dev-dependencies [[swank-clojure "1.3.0"
                      :exclusions [org.clojure/clojure
                                   org.clojure/clojure-contrib]]
                     [marginalia "0.7.0-SNAPSHOT"]])
