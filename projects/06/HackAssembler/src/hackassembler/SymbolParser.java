package hackassembler;

import java.util.HashMap;

class SymbolParser {

	private char[] codedLine = new char[16];
	private HashMap<String, Integer> symbolTable = new HashMap<>();

	public SymbolParser() {
		populateSymbolTable();
	}

	public String codeLine(String line) {
		resetLine();

		String value = line.split("@")[1];
		if (isNumber(value))
			addNumberToCodedLine(convertToBinary(Integer.valueOf(value)));
		else {
			Integer i = getSymbolValue(value);
			if (i != null)
				addNumberToCodedLine(convertToBinary(i));
		}

		return String.valueOf(codedLine);
	}

	private boolean isNumber(String line) {
		boolean isNumber = true;
		for (char c : line.toCharArray()) {
			isNumber = isNumber && Character.isDigit(c);
		}
		return isNumber;
	}

	private void populateSymbolTable() {
		symbolTable.put("R0", 0);
		symbolTable.put("R1", 1);
		symbolTable.put("R2", 2);
		symbolTable.put("R3", 3);
		symbolTable.put("R4", 4);
		symbolTable.put("R5", 5);
		symbolTable.put("R6", 6);
		symbolTable.put("R7", 7);
		symbolTable.put("R8", 8);
		symbolTable.put("R9", 9);
		symbolTable.put("R10", 10);
		symbolTable.put("R11", 11);
		symbolTable.put("R12", 12);
		symbolTable.put("R13", 13);
		symbolTable.put("R14", 14);
		symbolTable.put("R15", 15);
		symbolTable.put("SCREEN", 16384);
		symbolTable.put("KBD", 24576);
		symbolTable.put("SP", 0);
		symbolTable.put("LCL", 1);
		symbolTable.put("ARG", 2);
		symbolTable.put("THIS", 3);
		symbolTable.put("THAT", 4);
	}

	private char[] convertToBinary(int i) {
		return new StringBuilder(Integer.toBinaryString(i)).reverse().toString().toCharArray();
	}

	private void addNumberToCodedLine(char[] number) {
		int count = 15;

		for (char c : number) {
			codedLine[count] = c;
			count--;
		}
	}

	private void resetLine() {
		codedLine = new char[16];
		initCodedLine();
	}

	private void initCodedLine() {
		for (int i = 0; i < codedLine.length; i++) {
			codedLine[i] = '0';
		}
	}

	private Integer getSymbolValue(String s) {
		return symbolTable.get(s);
	}

}
