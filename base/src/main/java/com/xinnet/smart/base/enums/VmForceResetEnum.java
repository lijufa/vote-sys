package com.xinnet.smart.base.enums;

/**
 * 主机强制冻结
 * @author lenovo
 * @date 2016年8月26日 下午4:55:41
 */
public enum VmForceResetEnum {
	/**正常*/
	NORMAL(0, "正常"),
	/**禁用外网*/
	DISABLEINTER(1, "禁用外网"),
	/**禁用内网*/
	DISABLEINTRA(2, "禁用内网"),
	/**禁用网络*/
	DISABLENETWORK(3, "禁用网络"),
	/**启用外网*/
	ENABLEINTER(4, "启用外网"),
	/**启用内网*/
	ENABLEINTRA(5, "启用内网"),
	/**强制关机*/
	DESTROY(6, "强制关机"),
	/**重置VNC密码*/
	RESETVNC(7, "重置VNC密码"),
	/**还原VNC密码*/
	RECOVERVNC(8, "还原VNC密码"),
	/**启用网络*/
	ENABLENETWORK(9, "启用网络"),
	/**强制冻结*/
	FREEZE(9, "强制冻结");//暂时和启用网络值一样,只不过启用网络并不更新数据库的值(只有禁用的情况会更新)
	private final Integer id;
	private final String title;

	VmForceResetEnum(int id, String title) {
		this.id = id;
		this.title = title;
	}

	public final Integer getId() {
		return id;
	}

	public final String getTitle() {
		return title;
	}

	public static final VmForceResetEnum parse(Integer id) {
		if (id != null) {
			VmForceResetEnum[] values = values();
			for (VmForceResetEnum value : values) {
				if (value.id.intValue() == id.intValue()) {
					return value;
				}
			}
		}
		return null;
	}

	/**
	 * 是否重置vnc密码
	 * @author lenovo
	 * @date 2016年9月5日 上午9:25:06
	 * @param forceResetState
	 * @return boolean
	 */
	public static boolean isResetVncPassword(Integer forceResetState) {
		if (forceResetState == null) {
			return false;
		}
		String str = String.valueOf(forceResetState);
		return str.contains(String.valueOf(RESETVNC.getId()));
	}

	/**
	 * 是否正常状态
	 * @author lenovo
	 * @date 2016年9月6日 下午3:33:48
	 * @param forceResetEnums
	 * @return boolean
	 */
	public static boolean isNormal(VmForceResetEnum[] forceResetEnums) {
		if (forceResetEnums == null) {
			return false;
		}
		for (VmForceResetEnum vmForceResetEnum : forceResetEnums) {
			if (vmForceResetEnum.getId().intValue() == NORMAL.getId().intValue()) {
				return true;
			}
		}
		return false;
	}

	public static boolean isFreeze(VmForceResetEnum[] forceResetEnums) {
		if (forceResetEnums == null) {
			return false;
		}
		for (VmForceResetEnum vmForceResetEnum : forceResetEnums) {
			if (vmForceResetEnum.getId().intValue() != NORMAL.getId().intValue()) {
				return true;
			}
		}
		return false;
	}
}
