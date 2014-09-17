package org.akrogen.dynaresume.raphelloworld.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TableModel database table.
 * 
 */
@Entity
@NamedQuery(name="TableModel.findAll", query="SELECT t FROM TableModel t")
@Table(name="TableModel")
public class TableModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idDemoTable;

	@Column(name="column_0")
	private String column0;

	@Column(name="column_1")
	private String column1;

	@Column(name="column_2")
	private String column2;

	@Column(name="column_3")
	private String column3;

	@Column(name="column_4")
	private String column4;

	@Column(name="column_5")
	private String column5;

	@Column(name="column_6")
	private String column6;

	@Column(name="column_7")
	private String column7;

	@Column(name="column_8")
	private String column8;

	@Column(name="column_9")
	private String column9;

	public TableModel() {
	}

	public int getIdDemoTable() {
		return this.idDemoTable;
	}

	public void setIdDemoTable(int idDemoTable) {
		this.idDemoTable = idDemoTable;
	}

	public String getColumn0() {
		return this.column0;
	}

	public void setColumn0(String column0) {
		this.column0 = column0;
	}

	public String getColumn1() {
		return this.column1;
	}

	public void setColumn1(String column1) {
		this.column1 = column1;
	}

	public String getColumn2() {
		return this.column2;
	}

	public void setColumn2(String column2) {
		this.column2 = column2;
	}

	public String getColumn3() {
		return this.column3;
	}

	public void setColumn3(String column3) {
		this.column3 = column3;
	}

	public String getColumn4() {
		return this.column4;
	}

	public void setColumn4(String column4) {
		this.column4 = column4;
	}

	public String getColumn5() {
		return this.column5;
	}

	public void setColumn5(String column5) {
		this.column5 = column5;
	}

	public String getColumn6() {
		return this.column6;
	}

	public void setColumn6(String column6) {
		this.column6 = column6;
	}

	public String getColumn7() {
		return this.column7;
	}

	public void setColumn7(String column7) {
		this.column7 = column7;
	}

	public String getColumn8() {
		return this.column8;
	}

	public void setColumn8(String column8) {
		this.column8 = column8;
	}

	public String getColumn9() {
		return this.column9;
	}

	public void setColumn9(String column9) {
		this.column9 = column9;
	}
	
	public String getColumnByIndex(int colIndex) {
		if (colIndex == 0) {
			return column0;
		} else if (colIndex == 1) {
			return column1;
		} else if (colIndex == 2) {
			return column2;
		} else if (colIndex == 3) {
			return column3;
		} else if (colIndex == 4) {
			return column4;
		} else if (colIndex == 5) {
			return column5;
		} else if (colIndex == 6) {
			return column6;
		} else if (colIndex == 7) {
			return column7;
		} else if (colIndex == 8) {
			return column8;
		} else if (colIndex == 9) {
			return column9;
		} else {
			return null;
		}
	}
}