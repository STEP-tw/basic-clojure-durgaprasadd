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
    )

  (testing "filter"
    (testing "even and odd functions with filter"
      (are [x y] (= x y)
                 [0 2 4] (filter' even? (range 5))
                 [1 3] (filter' odd? (range 5))))
    (testing "empty coll"
      (are [x y] (= x y)
                 [] (filter' even? [])
                 [] (filter' even? [1 3])))
    )

  (testing "reduce"
    (testing "with initial value"
      (are [x y] (= x y)
                 5 (reduce' + -1 [1 2 3])
                 5 (reduce' rem 5 [])))
    (testing "without initial value"
      (are [x y] (= x y)
                 1 (reduce' + [1])
                 6 (reduce' + [1 2 3]))))

  (testing "count"
    (testing "empty coll"
      (is (zero? (count' []))))
    (testing "non empty coll"
      (is (= 2 (count' [1 2])))))

  (testing "reverse"
    (testing "non sequential"
      (is (nil? (reverse' 124))))
    (testing "sequential"
      (are [x y] (= x y)
                 [1 2 3] (reverse' [3 2 1])
                 [\a \b] (reverse' "ba"))))

  (testing "every"
    (are [x y] (= x y)
               true (every?' even? [2 4 6])
               false (every?' even? (range 10))
               true (every?' even? [])
               ))
  (testing "some"
    (are [x y] (= x y)
               true (some?' even? (range 10))
               false (some?' even? [1 3 5])
               nil (some?' even? [])
               ))

  (testing "ascending"
    (are [x y] (= x y)
               true (ascending? [])
               true (ascending? [1 2 2 3])
               false (ascending? [1 3 2])
               ))
  )