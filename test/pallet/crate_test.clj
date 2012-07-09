(ns pallet.crate-test
  (:require
   [pallet.test-utils :as test-utils])
  (:use
   clojure.test
   pallet.crate
   [pallet.api :only [group-spec]]
   [pallet.test-utils :only [test-session make-node]]))

;; (deftest groups-with-role-test
;;   (let [session (test-session
;;                  {:service-state
;;                   {:group->nodes {(group-spec "group1" :roles :role1) []
;;                                   (group-spec "group2" :roles :role2) []}}})]
;;     (is (= [[:group1] session]
;;              ((groups-with-role :role1) session)))))

(deftest nodes-with-role-test
  (let [n1 (make-node "group1")
        n2 (make-node "group2")
        g1 (group-spec "group1" :roles :role1)
        g2 (group-spec "group2" :roles :role2)
        targets [(assoc g1 :node n1) (assoc g2 :node n2)]
        session (test-session {:service-state targets})]
    (is (= [[(first targets)] session]
           ((nodes-with-role :role1) session)))))
