package com.example;


/**
 * @author bloodkilory
 *         generate on 15/5/28
 */
public class Test {
	public enum RankType {
		// 新增级别需加在最后
		DEFAULT(0), SILVER(1), GOLD(2), PLATINUM(3), RUBY(10), SAPPHIRE(20), EXTREME(30);

		private final int value;

		RankType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public static RankType value(Integer value) {
			RankType[] rankTypes = values();
			for(RankType rank : rankTypes) {
				if(rank.getValue() == value) {
					return rank;
				}
			}
			return RankType.DEFAULT;
		}

		public static RankType valueOf(int ordinal) {
			if (ordinal < 0 || ordinal >= values().length) {
				throw new IndexOutOfBoundsException("Invalid ordinal");
			}
			return values()[ordinal];
		}

	}
	public static void main(String[] args) {
		Test test = new Test();
		int value = 4;

		int rank = 20;

		int ordinal = RankType.value(rank).ordinal();

		RankType rankType = test.calcVipRankType(value, ordinal, "up");
		System.out.println(rankType);
	}

	private RankType calcVipRankType(int level, int rank, String method) {
		int fin = method.equals("up") ? rank + level : rank - level;
		int length = RankType.values().length;
		if(fin >= length) {
			return RankType.valueOf(length - 1);
		} else if(fin <= 0) {
			return RankType.valueOf(0);
		} else {
			return RankType.valueOf(fin);
		}
	}
}
