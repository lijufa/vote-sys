package com.xinnet.log.utils;

/**
 * 
 * @author zhb
 * @date 2015年12月24日 上午9:22:15
 */
public final class UMath {
	/**
	 * 计算平面上两点之间的距离
	 * 方法说明:
	 * @author zhb
	 * @date 2015年12月24日 上午9:32:58
	 * @param a
	 * @param b
	 * @return double
	 */
	public static double distanceOfTwoPoint(Point a, Point b) {
		int lengthX, lengthY;
		lengthX = (a.getX() > b.getX()) ? a.getX() - b.getX() : b.getX() - a.getX();
		lengthY = (a.getY() > b.getY()) ? a.getY() - b.getY() : b.getY() - a.getY();
		return Math.sqrt((lengthX * lengthX + lengthY * lengthY));
	}
}
