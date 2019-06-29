package com.xinnet.smart.base.util;

import java.util.ArrayList;
import java.util.List;

public class UEnum {
	public static <T extends Enum<T>> T parse(T[] values, int ordinal) {
		for (T value : values) {
			if (value.ordinal() == ordinal) {
				return value;
			}
		}
		return null;
	}

	public static <T extends Enum<T>> T parse(T[] values, String name) {
		if (name == null) {
			return null;
		}
		for (T value : values) {
			if (value.name().equalsIgnoreCase(name)) {
				return value;
			}
		}
		return null;
	}

	public static <T extends Enum<T>> T[] parse(T[] values, String[] names) {
		if (names == null) {
			return null;
		}
		List<T> ret = new ArrayList<T>();
		for (String name : names) {
			ret.add(parse(values, name));
		}
		return ret.toArray(values);
	}
}
