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
