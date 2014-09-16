package org.akrogen.dynaresume.raphelloworld.model;

public class TableModel {
	
	private String column_0;
	private String column_1;
	private String column_2;
	private String column_3;
	private String column_4;
	private String column_5;
	private String column_6;
	private String column_7;
	private String column_8;
	private String column_9;
	
	public TableModel() {}

	public TableModel(String column_0, String column_1, String column_2, String column_3,
			String column_4, String column_5, String column_6, String column_7,
			String column_8, String column_9) {
		super();
		this.column_0 = column_0;
		this.column_1 = column_1;
		this.column_2 = column_2;
		this.column_3 = column_3;
		this.column_4 = column_4;
		this.column_5 = column_5;
		this.column_6 = column_6;
		this.column_7 = column_7;
		this.column_8 = column_8;
		this.column_9 = column_9;
	}

	public String getColumn_0() {
		return column_0;
	}

	public void setColumn_0(String column_0) {
		this.column_0 = column_0;
	}
	
	public String getColumn_1() {
		return column_1;
	}

	public void setColumn_1(String column_1) {
		this.column_1 = column_1;
	}

	public String getColumn_2() {
		return column_2;
	}

	public void setColumn_2(String column_2) {
		this.column_2 = column_2;
	}

	public String getColumn_3() {
		return column_3;
	}

	public void setColumn_3(String column_3) {
		this.column_3 = column_3;
	}

	public String getColumn_4() {
		return column_4;
	}

	public void setColumn_4(String column_4) {
		this.column_4 = column_4;
	}

	public String getColumn_5() {
		return column_5;
	}

	public void setColumn_5(String column_5) {
		this.column_5 = column_5;
	}

	public String getColumn_6() {
		return column_6;
	}

	public void setColumn_6(String column_6) {
		this.column_6 = column_6;
	}

	public String getColumn_7() {
		return column_7;
	}

	public void setColumn_7(String column_7) {
		this.column_7 = column_7;
	}

	public String getColumn_8() {
		return column_8;
	}

	public void setColumn_8(String column_8) {
		this.column_8 = column_8;
	}

	public String getColumn_9() {
		return column_9;
	}

	public void setColumn_9(String column_9) {
		this.column_9 = column_9;
	}
	
	public String getColumnByIndex(int colIndex) {
		if (colIndex == 0) {
			return column_0;
		} else if (colIndex == 1) {
			return column_1;
		} else if (colIndex == 2) {
			return column_2;
		} else if (colIndex == 3) {
			return column_3;
		} else if (colIndex == 4) {
			return column_4;
		} else if (colIndex == 5) {
			return column_5;
		} else if (colIndex == 6) {
			return column_6;
		} else if (colIndex == 7) {
			return column_7;
		} else if (colIndex == 8) {
			return column_8;
		} else if (colIndex == 9) {
			return column_9;
		} else {
			return null;
		}
	}
}
