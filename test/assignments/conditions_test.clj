(ns assignments.conditions-test
  (:require [clojure.test :refer :all]
            [assignments.conditions :refer :all]))

(deftest safe-division
  (testing "non zero denominator"
    (is (= 2 (safe-divide 4 2))))
  (testing "zero denominator"
    (is (nil? (safe-divide 3 0)))))

(deftest informative-division
  (testing "non zero denominator"
    (is (= 2 (informative-divide 4 2))))
  (testing "zero denominator"
    (is (= :infinite (informative-divide 3 0)))))

(deftest harishchandra-test
  (testing "truthy values"
    (are [x y] (= x y)
               true (harishchandra true)
               2 (harishchandra 2)
               0 (harishchandra 0)
               )
    )
  (testing "falsy/nil values"
    (are [x y] (= x y)
               nil (harishchandra false)
               nil (harishchandra nil))
    )
  )

(deftest yudishtira-test
  (testing "truthy values"
    (are [x y] (= x y)
               true (yudishtira true)
               2 (yudishtira 2)
               0 (yudishtira 0)
               )
    )
  (testing "falsy/nil values"
    (are [x y] (= x y)
               :ashwathama (yudishtira false)
               :ashwathama (yudishtira nil))
    )
  )

(deftest duplicate-first-test
  (testing "empty coll"
    (is (nil? (duplicate-first [])))
    )
  (testing "non empty coll"
    (are [x y] (= x y)
               [1 1 2] (duplicate-first [1 2])
               `(1 1 2) (duplicate-first `(1 2)))
    )
  )

(deftest five-point-someone-test
  (testing "y value is 5"
    (are [x y] (= x y)
               :chetan-bhagat (five-point-someone 0 5)
               :chetan-bhagat (five-point-someone 5 5)
               ))
  (testing "x value is 5"
    (are [x y] (= x y)
               :statan-bhagat (five-point-someone 5 0)
               :statan-bhagat (five-point-someone 5 10)
               ))
  (testing "x is greater than y"
    (are [x y] (= x y)
               :greece (five-point-someone 1 0)
               :greece (five-point-someone -1 -2)
               ))

  (testing "default condition"
    (are [x y] (= x y)
               :universe (five-point-someone 0 0)
               :universe (five-point-someone -2 -1)
               ))

  )

(deftest conditions-apply-test
  (testing "single occurance of 1 and 3"
    (are [x y] (= x y)
               :wonder-woman (conditions-apply [1 3])
               :wonder-woman (conditions-apply [1 2 3])
               :wonder-woman (conditions-apply [1 1 3 3])
               :wonder-woman (conditions-apply [1 1 3 3 1])))
  (testing "single occurance of :a :b and :c"
    (are [x y] (= x y)
               :durga (conditions-apply [:a :b :c])
               :durga (conditions-apply [:a :d :e :b :c])
               :durga (conditions-apply [:a :a :b :c :b :c])
               :durga (conditions-apply [:a :a :b :c :b :c :a])))
  (testing "single occurance of [2 3] and [4 5]"
    (are [x y] (= x y)
               :cleopatra (conditions-apply [[2 3] [4 5]])
               :cleopatra (conditions-apply [[2 3] [:a :b] [4 5]])
               :cleopatra (conditions-apply [[2 3] [2 3] [4 5] [4 5]])
               :cleopatra (conditions-apply [[2 3] [2 3] [4 5] [4 5] [2 3]])))
  (testing "default"
    (are [x y] (= x y)
               :tuntun (conditions-apply [1 3 1 3])
               :tuntun (conditions-apply [:a :b :c :a :b :c])
               :tuntun (conditions-apply [1 2])
               :tuntun (conditions-apply [])))
  )

(deftest repeat-and-truncate-test
  (testing "if repeat is true"
    (are [x y] (= x y)
               [1 2 1 2] (repeat-and-truncate [1 2] true false 0)
               [] (repeat-and-truncate [] true false 0)))
  (testing "if truncate is true"
    (are [x y] (= x y)
               [1 2] (repeat-and-truncate [1 2 3] false true 2)
               [] (repeat-and-truncate [] false true 2)))
  (testing "if both repeat and truncate are true"
    (are [x y] (= x y)
               [1 2] (repeat-and-truncate [1 2 3] true true 2)
               [1 2 1] (repeat-and-truncate [1 2] true true 3)))
  (testing "if both repeat and truncate are false"
    (are [x y] (= x y)
               [1 2 3] (repeat-and-truncate [1 2 3] false false 5)
               [] (repeat-and-truncate [] false false 5)))
  )

(deftest order-in-words-test
  (testing "x is greater than y"
    (are [x y] (= x y)
               [:x-greater-than-y] (order-in-words 2 1 2)
               [:x-greater-than-y] (order-in-words -1 -2 -1)))
  (testing "y is greater than z"
    (are [x y] (= x y)
               [:y-greater-than-z] (order-in-words 2 2 1)
               [:y-greater-than-z] (order-in-words -1 -1 -2)))
  (testing "z is greater than x"
    (are [x y] (= x y)
               [:z-greater-than-x] (order-in-words 1 2 2)
               [:z-greater-than-x] (order-in-words -2 -1 -1)))
  (testing "x is greater than y and y is greater than z"
    (are [x y] (= x y)
               [:x-greater-than-y :y-greater-than-z] (order-in-words 3 2 1)
               [:x-greater-than-y :y-greater-than-z] (order-in-words -1 -2 -3)))
  (testing "y is greater than z and z is greater than x"
    (are [x y] (= x y)
               [:y-greater-than-z :z-greater-than-x] (order-in-words 1 3 2)
               [:y-greater-than-z :z-greater-than-x] (order-in-words -3 -1 -2)))
  (testing "x is greater than y and z is greater than x"
    (are [x y] (= x y)
               [:x-greater-than-y :z-greater-than-x] (order-in-words 2 1 3)
               [:x-greater-than-y :z-greater-than-x] (order-in-words -2 -3 -1)))
  (testing "default where x,y and z are equal"
    (is (= [] (order-in-words 2 2 2))))
  )

(deftest zero-aliases-test
  (testing "zero"
    (is (= :zero (zero-aliases 0))))
  (testing "empty list"
    (are [x y] (= x y)
               :empty (zero-aliases [])
               :empty (zero-aliases '())))
  (testing "empty set"
    (is (= :empty-set (zero-aliases #{}))))
  (testing "empty map"
    (is (= :empty-map (zero-aliases {}))))
  (testing "empty string"
    (is (= :empty-string (zero-aliases ""))))
  (testing "non zero or default value"
    (is (= :not-zero (zero-aliases 10))))
  )
