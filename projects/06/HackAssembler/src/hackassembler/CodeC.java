package hackassembler;

import java.util.HashMap;

class CodeC {

	private String codedLine;
	private HashMap<String, String> compTable = new HashMap<>();
	private HashMap<String, String> jumpTable = new HashMap<>();

	public CodeC() {
		populateJumpTable();
		populateCompTable();
	}

	public String codeLine(String line) {
		String comp;
		String jump;
		String dest;
		if (line.contains("=")) {
			if (line.contains(";")) {
				comp = line.split(";")[0].split("=")[1];
				jump = line.split(";")[1];
				dest = line.split("=")[0];
			} else {
				comp = line.split("=")[1];
				jump = null;
				dest = line.split("=")[0];
			}
		} else {
			if (line.contains(";")) {
				comp = line.split(";")[0];
				jump = line.split(";")[1];
				dest = null;
			} else {
				comp = line;
				jump = null;
				dest = null;
			}
		}
		codedLine = "111" + getAbit(comp) + getCompCode(comp) + getDestCode(dest) + getJumpCode(jump);
		return codedLine;
	}

	private void populateCompTable() {
		compTable.put("0", "101010");
		compTable.put("1", "111111");
		compTable.put("-1", "111010");
		compTable.put("D", "001100");
		compTable.put("A", "110000");
		compTable.put("M", "110000");
		compTable.put("!D", "001101");
		compTable.put("!A", "110001");
		compTable.put("!M", "110001");
		compTable.put("-D", "001111");
		compTable.put("-A", "110011");
		compTable.put("-M", "110011");
		compTable.put("D+1", "011111");
		compTable.put("A+1", "110111");
		compTable.put("M+1", "110111");
		compTable.put("D-1", "001110");
		compTable.put("A-1", "110010");
		compTable.put("M-1", "110010");
		compTable.put("D+A", "000010");
		compTable.put("D+M", "000010");
		compTable.put("D-A", "010011");
		compTable.put("D-M", "010011");
		compTable.put("A-D", "000111");
		compTable.put("M-D", "000111");
		compTable.put("D&A", "000000");
		compTable.put("D&M", "000000");
		compTable.put("D|A", "010101");
		compTable.put("D|M", "010101");
	}

	private void populateJumpTable() {
		jumpTable.put(null, "000");
		jumpTable.put("JGT", "001");
		jumpTable.put("JEQ", "010");
		jumpTable.put("JGE", "011");
		jumpTable.put("JLT", "100");
		jumpTable.put("JNE", "101");
		jumpTable.put("JLE", "110");
		jumpTable.put("JMP", "111");
	}

	private boolean isNumber(char c) {
		return Character.isDigit(c);
	}

	private char[] convertToBinary(int i) {
		return Integer.toBinaryString(i).toCharArray();
	}

	private String getDestCode(String dest) {
		char c[] = { '0', '0', '0' };
		if(dest==null || dest.length()==0) return String.valueOf(c);
		char d[] = dest.toCharArray();
		for (char e : d) {
			if (e == 'A')
				c[0] = '1';
			else if (e == 'D')
				c[1] = '1';
			else if (e == 'M')
				c[2] = '1';
		}
		return String.valueOf(c);
	}

	private String getCompCode(String comp) {
		return (String) compTable.get(comp);
	}

	private String getJumpCode(String jump) {
		return (String) jumpTable.get(jump);
	}

	private String getAbit(String comp) {
		return (comp.contains("M") ? "1" : "0");
	}
}
