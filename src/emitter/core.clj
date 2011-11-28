(ns emitter.core
  (:use [clj-time.core :only [now]]
        [clj-time.format :as format])
  (:require [emitter.util :as util]
            [clojure.contrib.command-line :as ccl]))

(import '(java.util.concurrent Executors))
(def ^:dynamic *pool* (Executors/newFixedThreadPool
              (+ 2 (.availableProcessors (Runtime/getRuntime)))))

(def actions ["POST" "PUT" "DELETE" "GET"])
(def codes [200 400 300 500])
(def log-format (format/formatter "dd/MMM/yyyy:HH:mm:ss Z"))

(defn dothreads! [f & {thread-count :threads
                       exec-count :times
                       :or {thread-count 1 exec-count 1}}]
  (dotimes [t thread-count]
    (.submit *pool* #(dotimes [_ exec-count] (f)))))

(defn log [data]
  (prn (merge {:ns "emitter"} data)))

(defn ip []
  (apply str (interpose "." (repeatedly 4 #(rand-int 256)))))

(defn curr-time []
  (apply str ["[" (unparse log-format (now)) "]"]))

(defn get-line []
  (apply str (ip) "\t- "
    (util/random-alphabetic 5) " "
    (curr-time) " \""
    (rand-nth actions) " /"
    (util/uid) "."
    (util/random-alphabetic 3) " HTTP/1.1 "
    (rand-nth codes) " "
    (rand-int 1024) "\n"))

(defn write [file delay]
  (loop []
    (let [line (get-line)]
      (spit file line :append true))
    (Thread/sleep (Long/parseLong delay))
    (recur)))

(defn emit [paths delay]
  (let [coll (.split paths ",")]
    (loop [i 0]
      (when (< i (count coll))
        (let [file (get coll i)]
          (dothreads! #(write file delay))
          (recur (inc i)))))))

(defn -main [& args]
  (if args
    (ccl/with-command-line args
      "Usage: --files=file1,file2 --delay=500"
      [[files "where to emit"]
       [delay "default delay" "500"]]
      (log {:files files :delay delay})
      (emit files delay))
    (prn "Usage: --files=file1,file2 [--delay=500]")))