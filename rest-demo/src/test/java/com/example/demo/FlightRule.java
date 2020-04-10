package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author yangkun generate on 2017/8/30
 * ***
 * *  adt：成人，chd:儿童
 * * change:改签，refund：退票，noshow:未乘机/误机
 * *  beforeTime:起飞前多少小时，-1表示起飞后
 * * charge：收费金额，-1表示不可改签、退票
 * *  changeALLOW:误机情况下是否允许改签
 * * noshowTime:起飞前多少小时算noshow，2表示起飞前2小时为noshow，默认为0
 * *  changeFee:noshow改期费用，-1为不可改期
 * * noshowCharge，changeFee，refundFee的填写。
 * 现在可能遇到两种情况，一种是只有noshowCharge,这种只要填这个字段。
 * 还有一种会有noshow后改期多少钱，noshow后退票多少钱，这个时候填changeFee和refundFee
 * ***
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FlightRule {

    private PersonRule adt;
    private PersonRule chd;

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PersonRule {
        private List<Rule> change;
        private NoShow noShow;
        private List<Rule> refund;

        public NoShow singleNoshow() {
            if(this.noShow == null) {
                return new NoShow();
            }
            return this.noShow;
        }
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Rule {
        private int beforeTime;
        private double charge;
        private String currency;

        public Rule(int beforeTime, double charge) {
            this.beforeTime = beforeTime;
            this.charge = charge;
        }

        public static List<Rule> freeWithOutTime(String currency) {
            List<Rule> rules = new ArrayList<>(2);
            Rule rule1 = new Rule(0, 0, currency);
            Rule rule2 = new Rule(-1, 0, currency);
            rules.add(rule1);
            rules.add(rule2);
            return rules;
        }

        public static List<Rule> cannotBoth(String currency) {
            List<Rule> rules = new ArrayList<>(2);
            Rule rule1 = new Rule(0, -1, currency);
            Rule rule2 = new Rule(-1, -1, currency);
            rules.add(rule1);
            rules.add(rule2);
            return rules;
        }

        public static List<Rule> freeBeforeDep(String currency) {
            List<Rule> rules = new ArrayList<>(2);
            Rule rule1 = new Rule(0, 0, currency);
            rules.add(rule1);
            return rules;
        }

        public static List<Rule> freeAfterDep(String currency) {
            List<Rule> rules = new ArrayList<>(2);
            Rule rule1 = new Rule(-1, 0, currency);
            rules.add(rule1);
            return rules;
        }

        public static List<Rule> feeAfterDep(double fee1, String currency1) {
            List<Rule> rules = new ArrayList<>(2);
            Rule rule1 = new Rule(-1, fee1, currency1);
            rules.add(rule1);
            return rules;
        }

        public static List<Rule> feeWithOutTime(double fee1, double fee2, String currency1, String currency2) {
            List<Rule> rules = new ArrayList<>(2);
            Rule rule1 = new Rule(0, fee1, currency1);
            Rule rule2 = new Rule(-1, fee2, currency2);
            rules.add(rule1);
            rules.add(rule2);
            return rules;
        }

        public static List<Rule> feeBeforeDep(double fee1, String currency1) {
            List<Rule> rules = new ArrayList<>(2);
            Rule rule1 = new Rule(0, fee1, currency1);
            rules.add(rule1);
            return rules;
        }

    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NoShow {
        private boolean changeAllow;
        private double changeFee;
        private boolean refundAllow;
        private double refundFee;
        private int noshowTime;
        private double noshowCharge;
        private String currency;

        public static NoShow fullNoShow(double fee, String currency) {
            NoShow noShow = new NoShow();
            noShow.setChangeFee(fee);
            noShow.setRefundFee(fee);
            noShow.setChangeAllow(true);
            noShow.setRefundAllow(true);
            noShow.setCurrency(currency);
            return noShow;
        }

    }

}
