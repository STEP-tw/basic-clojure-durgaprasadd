(ns assignments.lists-test
  (:require [clojure.test :refer :all]
            [assignments.lists :refer :all]))

(deftest lists
  (testing "map"
    (testing "identity with single coll"
      (is (= [1 2 3] (map' identity [1 2 3]))))
    (testing "+ with multiple colls of same size"
      (is (= [2 4 6] (map' + [1 2 3] [1 2 3]))))
    (testing "+ with multiple colls of different size"
      (are [x y] (= x y)
                 [2 4] (map' + [1 2] [1 2 3])
                 [2 4] (map' + [1 2 3] [1 2])))
    ))