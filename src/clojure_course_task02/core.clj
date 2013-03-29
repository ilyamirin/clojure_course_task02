(ns clojure-course-task02.core
  (:require [clojure.java.io :as io])
  (:gen-class))

(defn find-files [file-name path]
  (let [file-name-regex (re-pattern file-name)]
    (remove #(empty? (re-seq file-name-regex %)) 
            (map #(.getName %) 
                 (file-seq
                   (io/file path))))))

(defn usage []
  (println "Usage: $ run.sh file_name path"))

(defn -main [file-name path]
  (if (or (nil? file-name)
          (nil? path))
    (usage)
    (do
      (println "Searching for " file-name " in " path "...")
      (dorun (map println (find-files file-name path))))))
