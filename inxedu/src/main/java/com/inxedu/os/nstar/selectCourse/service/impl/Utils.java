package com.inxedu.os.nstar.selectCourse.service.impl;

import java.util.ArrayList;
import java.util.List;

public class Utils {
	/**
	 * 把整数转换成对应课表有没有课
	 *
	 * @param a
	 * @return
	 */
	public static boolean[][] intToArr(int a) {

		boolean[][] kc = new boolean[5][6];
		int col = 0;
		for (int i = 0; i < 30; i++) {
			if (i < 6)
				kc[0][i] = (a ^ i) == 1 ? true : false;
			else {
				if (i % 6 == 0)
					col++;
				kc[col][i % 6] = (a ^ i) == 1 ? true : false;
			}
		}
		return kc;
	}

	/**
	 * 把课表转换成整形数字
	 *
	 * @param bool
	 * @return
	 */
	public static int arrToInt(boolean[][] bool) {
		int a = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 6; j++) {
				if (bool[i][j])
					a += Math.pow(2, 6 * i + j);
			}
		}
		return a;
	}

	/**
	 * 把整形数字代表的课表转换成List
	 */
	public static List<Boolean> intToList(int onLessionTime) {
		List<Boolean> list = new ArrayList<>(30);
		for (int i = 0; i < 30; i++) {
			if ((onLessionTime ^ i) == 1)
				list.add(true);
			else
				list.add(false);
		}
		return list;
	}

	/**
	 * 把List描述的课表转换成用一个整数表述的课表
	 * @param list
	 * @return
	 */
	public static int listToInt(List<Boolean> list){
		int a = 0,i = 0;
		for (Boolean boolean1 : list) {
			if(boolean1)
				a+=Math.pow(2, i);
			i++;
		}

		return a;
	}

}
