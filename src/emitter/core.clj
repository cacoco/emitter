(ns emitter.core
  (:require [clojure.contrib.command-line :as ccl])
  (:gen-class))

(import '(java.util.concurrent Executors))
(def ^:dynamic *pool* (Executors/newFixedThreadPool
              (+ 2 (.availableProcessors (Runtime/getRuntime)))))

(defn dothreads! [f & {thread-count :threads
                       exec-count :times
                       :or {thread-count 1 exec-count 1}}]
  (dotimes [t thread-count]
    (.submit *pool* #(dotimes [_ exec-count] (f)))))

(defn log [data]
  (prn (merge {:ns "emitter"} data)))

(defn rand-id []
  (let [chars (apply vector "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789")
        num-chars (count chars)]
    (apply str
      (take 80 (repeatedly #(get chars (rand-int num-chars)))))))

(defn write [file delay]
    (loop []
      (spit file (str (rand-id) "\n") :append true)
      (Thread/sleep (Long/parseLong delay))
      (recur)))

(defn produce-output [paths delay]
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
    (produce-output files delay))
    (prn "Usage: --files=file1,file2 [--delay=500]")))