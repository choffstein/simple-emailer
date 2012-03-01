(defproject simple-emailer "1.0.0-SNAPSHOT"
  :description "FIXME: write description"
  :dependencies [[org.clojure/clojure "1.3.0-beta1"]
                 [org.apache.commons/commons-email "1.2"]]

  :dev-dependencies [[marginalia "0.7.0-SNAPSHOT"]]

  :plugins [[s3-wagon-private "1.1.1"]]

  :repositories {"nfr-releases" "s3p://newfound-mvn-repo/releases/"
                 "nfr-snapshots" "s3p://newfound-mvn-repo/snapshots/"
                 "central-proxy" "http://repository.sonatype.org/content/repositories/central/"})
