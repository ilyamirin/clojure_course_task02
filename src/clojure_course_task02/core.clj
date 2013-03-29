(ns clojure-course-task02.core
  (:require [clojure.java.io :as io])
  (:gen-class))

(defn explore-file [file-list]
  (loop [files file-list 
         res []]  
    (let [dirs 
          (mapcat #(.listFiles %) 
               (filter #(.isDirectory %) files))] 
          (if (empty? dirs)
            res
            (recur dirs (into res (map #(.getName %) files)))))))

(defn find-files [file-name path]
  (let [file-name-regex (re-pattern file-name)]
    (remove #(empty? (re-seq file-name-regex %)) 
                 (explore-file [(io/file path)]))))

(defn usage []
  (println "Usage: $ run.sh file_name path"))

(defn -main [file-name path]
  (if (or (nil? file-name)
          (nil? path))
    (usage)
    (do
      (println "Searching for " file-name " in " path "...")
      (dorun (map println (find-files file-name path))))))
