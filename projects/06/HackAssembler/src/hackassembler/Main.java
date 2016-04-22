package hackassembler;

public class Main {

	public static void main(String[] args) {

		new Parser().readAsmFile("Add");
		new Parser().readAsmFile("MaxL");
		new Parser().readAsmFile("RectL");


	}

}
