package com.xinnet.smart.test.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import com.xinnet.smart.base.util.UJson;

@SuppressWarnings("serial")
public class Composite extends HashMap<String, Composite> {
	Composite parent;

	public TreeMap<Integer, List<String>> toTreeMap() {
		Set<java.util.Map.Entry<String, Composite>> entrySet = all().entrySet();
		TreeMap<Integer, List<String>> ret = new TreeMap<Integer, List<String>>();
		for (Map.Entry<String, Composite> entry : entrySet) {
			Integer key = entry.getValue().dependency();
			List<String> list = new ArrayList<String>();
			if (ret.containsKey(key)) {
				list = ret.get(key);
			} else {
				ret.put(key, list);
			}
			list.add(entry.getKey());
		}
		return ret;
	}

	public List<String> asc() {
		List<String> ret = new ArrayList<String>();
		Collection<List<String>> values = toTreeMap().values();
		for (List<String> value : values) {
			ret.addAll(value);
		}
		return ret;
	}

	public List<String> desc() {
		List<String> ret = new ArrayList<String>();
		Collection<List<String>> values = toTreeMap().descendingMap().values();
		for (List<String> value : values) {
			ret.addAll(value);
		}
		return ret;
	}

	/**
	 * 获取顶级对象
	 * @author meitao
	 * @date 2014-8-14 下午8:05:59
	 * @return
	 */
	public Composite top() {
		Composite ret = this;
		while (ret.parent != null) {
			ret = ret.parent;
		}
		return ret;
	}

	/**
	 * 依赖深度
	 * @author meitao
	 * @date 2014-8-14 下午8:07:37
	 * @return
	 */
	public int dependency() {
		int ret = 0;
		for (Composite value : values()) {
			ret = Math.max(value.dependency() + 1, ret);
		}
		return ret;
	}

	/**
	 * 深度
	 * @author meitao
	 * @date 2014-8-14 下午8:07:37
	 * @return
	 */
	public int depth() {
		int ret = 0;
		Composite parent = this;
		while (parent.parent != null) {
			parent = parent.parent;
			ret++;
		}
		return ret;
	}

	/**
	 * 在子节点中查找
	 * @author meitao
	 * @date 2014-8-14 下午8:37:04
	 * @param key
	 * @return
	 */
	public Composite find(String key) {
		if (containsKey(key)) {
			return get(key);
		} else {
			for (Composite value : values()) {
				Composite ret = value.find(key);
				if (ret != null) {
					return ret;
				}
			}
			return null;
		}
	}

	/**
	 * 列出全部节点
	 * @author meitao
	 * @date 2014-8-14 下午9:19:28
	 * @return
	 */
	public Map<String, Composite> all() {
		Map<String, Composite> ret = new HashMap<String, Composite>();
		for (Map.Entry<String, Composite> entry : entrySet()) {
			ret.put(entry.getKey(), entry.getValue());
			ret.putAll(entry.getValue().all());
		}
		return ret;
	}

	/**
	 * 在整棵树上查找并替换，自动去重
	 * @author meitao
	 * @date 2014-8-14 下午8:06:11
	 * @param key
	 * @return
	 */
	@Override
	public Composite put(String key, Composite value) {
		if (value != this) {
			Composite top = top();
			Composite exists = top.find(key);
			if (exists != null) {
				if (exists.depth() <= depth()) {
					exists.parent.remove(key);
					put(key, value);
				}
			} else {
				super.put(key, value);
			}
		}
		return value;
	}

	public Composite getParent() {
		return parent;
	}

	public void setParent(Composite parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		return UJson.toString(this);
	}
}
