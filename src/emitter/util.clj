(ns emitter.util)

(def alphanumeric "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789")
(def alphabetic "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz")

(defn randomize [characters length]
  (loop [acc []]
      (if (= (count acc) length) (apply str acc)
        (recur (conj acc (rand-nth characters))))))

(defn random-alphanumeric [length]
  (.toLowerCase (apply str (randomize alphanumeric length))))

(defn random-alphabetic [length]
  (.toLowerCase (apply str (randomize alphabetic length))))

(defn uid []
  (.toLowerCase (apply str (repeatedly 8 #(random-alphanumeric 4)))))