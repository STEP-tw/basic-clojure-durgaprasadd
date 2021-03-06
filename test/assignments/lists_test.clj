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

  (testing "transpose"
    (are [x y] (= x y)
               [[1]] (transpose [[1]])
               [[1 3] [2 4]] (transpose [[1 2] [3 4]])
               [[1 4 7] [2 5 8] [3 6 9]] (transpose [[1 2 3] [4 5 6] [7 8 9]])))

  (testing "sum-of-adjacent-digits"
    (are [x y] (= x y)
               [3] (sum-of-adjacent-digits [1 2])
               [3 5] (sum-of-adjacent-digits [1 2 3])))

  (testing "difference"
    (are [x y] (= x y)
               [] (difference [1 2] [1 2])
               [] (difference [] [])
               [3 4] (difference [1 2 5 6] [1 2 3 4])))

  (testing "union"
    (are [x y] (= x y)
               [] (union [] [])
               [1 2] (union [1] [2])
               [1 2 3 4] (union [1 2] [1 2 3 4])))

  (testing "points-around-origin"
    (is (= [[-1 -1] [-1 0] [-1 1] [0 -1] [0 1] [1 -1] [1 0] [1 1]] points-around-origin)))

  (testing "cross-product"
    (are [x y] (= x y)
               [[1 4] [1 3] [1 5] [2 4] [2 3] [2 5] [3 4]] (cross-product [1 2 3] [4 3 5])
               [[1 2]] (cross-product [1] [2])
               [[1 3] [1 4] [2 3] [2 4]] (cross-product [1 2] [3 4])
               [] (cross-product [1] [1])))

  (testing "double-up"
    (are [x y] (= x y)
               [] (double-up [])
               [1 1 2 2 3 3] (double-up [1 2 3])
               ["hi" "hi" "i" "i" "u" "u"] (double-up ["hi" "i" "u"])))

  (testing "third-or-fifth"
    (are [x y] (= x y)
               [] (third-or-fifth [])
               [0 3] (third-or-fifth [0 1 2 3])
               [0 3 5 6] (third-or-fifth [0 1 2 3 4 5 6])))

  (testing "sqr-of-the-first"
    (are [x y] (= x y)
               [1] (sqr-of-the-first [1])
               [16 16 16] (sqr-of-the-first [4 5 6])))

  (testing "russian-dolls"
    (are [x y] (= x y)
               [1 2 3] (russian-dolls [1 2 3] 1)
               [[[1]] [[2]] [[3]]] (russian-dolls [1 2 3] 3)))

  (testing "split-comb"
    (are [x y] (= x y)
               [1 3 2 4] (split-comb [1 2 3 4])
               [1 3 2 4 5] (split-comb [1 2 3 4 5])))

  (testing "muted-thirds"
    (are [x y] (= x y)
               [] (muted-thirds [])
               [1 2] (muted-thirds [1 2])
               [1 2 0] (muted-thirds [1 2 3])
               [1 2 0 4 5 0] (muted-thirds (range 1 7))))

  (testing "palindrome?"
    (are [x y] (= x y)
               true (palindrome? [])
               true (palindrome? [1])
               false (palindrome? [1 2])
               true (palindrome? [1 2 3 2 1])))

  (testing "index-of"
    (are [x y] (= x y)
               -1 (index-of [] 0)
               -1 (index-of [1 2 4] 3)
               0 (index-of [1 2 3] 1)
               3 (index-of [1 2 3 4] 4)))

  (testing "max-three-digit-sequence"
    (are [x y] (= x y)
               [1 2] (max-three-digit-sequence [1 2])
               [1 2 3] (max-three-digit-sequence [1 2 3])
               [2 3 4] (max-three-digit-sequence [1 2 3 4])))

  (testing "distinct"
    (are [x y] (= x y)
               [] (distinct' [])
               [1 2] (distinct' [1 2])
               [1 2] (distinct' [1 2 1 2])
               [1 2] (distinct' [1 1 2 2 1 1])))

  (testing "dedupe"
    (are [x y] (= x y)
               [] (dedupe' [])
               [1 2] (dedupe' [1 2])
               [1 2 1] (dedupe' [1 1 1 1 2 2 1 1 1])))

  (testing "validate-sudoku-grid"
    (are [x y] (= x y)
               true (validate-sudoku-grid
                      [[4 3 5 2 6 9 7 8 1]
                       [6 8 2 5 7 1 4 9 3]
                       [1 9 7 8 3 4 5 6 2]
                       [8 2 6 1 9 5 3 4 7]
                       [3 7 4 6 8 2 9 1 5]
                       [9 5 1 7 4 3 6 2 8]
                       [5 1 9 3 2 6 8 7 4]
                       [2 4 8 9 5 7 1 3 6]
                       [7 6 3 4 1 8 2 5 9]])
               false (validate-sudoku-grid
                       [[1 2 3 4 5 6 7 8 9]
                        [2 3 4 5 6 7 8 9 1]
                        [3 4 5 6 7 8 9 1 2]
                        [4 5 6 7 8 9 1 2 3]
                        [5 6 7 8 9 1 2 3 4]
                        [6 7 8 9 1 2 3 4 5]
                        [7 8 9 1 2 3 4 5 6]
                        [8 9 1 2 3 4 5 6 7]
                        [9 1 2 3 4 5 6 7 8]]
                       )))
  )