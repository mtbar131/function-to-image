(ns com.test
  (:import (javax.swing JFrame))
  (:gen-class))

;; Different functions to generate pixel value
; Each pixel has color value of xor of its x and y co-ordinates
(defn generateXorList [limit]
  (for [x (range limit)
        y (range limit)]
    [x y (bit-xor x y)]
))

; Each pixel has color value of addition of x & y
(defn generateXmodYList [limit]
  (for [x (range limit)
        y (range limit)]
    [x y (mod (+ x y) 256)] ; mod by 256 for rgb limit
))

; Each pixel has color value of bitwise ANDing of x  & y
(defn generateXandYList [limit]
  (for [x (range limit)
        y (range limit)]
    [x y (bit-and x y)] ; mod by 256 for rgb limit
))

(defn makeImage [seq]
  (let [frameSize 250
        frame (doto (JFrame.)
                (.setVisible true)
                (.setSize frameSize frameSize)
                (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE))
        gfx (.getGraphics frame)]    
    (doseq [[x y color] seq]      
      (.setColor gfx (java.awt.Color. color))
      (.fillRect gfx x y 1 1))))

;; Generate the image
(makeImage (generateXorList 250))
(makeImage (generateXmodYList 250))
(makeImage (generateXandYList 250))



