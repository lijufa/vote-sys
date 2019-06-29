package com.xinnet.log.utils;

import java.io.Serializable;

/**
 * 点
 * @author zhb
 * @date 2015年12月23日 下午4:29:27
 */
public class Point implements Serializable {
	private static final long serialVersionUID = 7678827117421683196L;
	private int x;
	private int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
