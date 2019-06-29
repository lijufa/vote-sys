package com.xinnet.smart.base.enums;

public enum DiskTagTypeEnum {
	SSDDISK(0, " SSD硬盘",7), 
	SASDISK(1, "SAS硬盘",8),
	SATADISK(2,"SATA硬盘",9);
	private Integer id;
	private String desc;
	private Integer productType;

	private DiskTagTypeEnum(Integer id, String desc,Integer productType) {
		this.id = id;
		this.desc = desc;
		this.productType = productType;
	}

	public Integer getId() {
		return id;
	}

	public String getDesc() {
		return desc;
	}
	
	

	public Integer getProductType() {
		return productType;
	}

	public static DiskTagTypeEnum parseId(Integer id) {
		if (id != null) {
			DiskTagTypeEnum[] values = DiskTagTypeEnum.values();
			for (DiskTagTypeEnum value : values) {
				if (value.id.intValue() == id.intValue()) {
					return value;
				}
			}
		}
		return null;
	}
}
