package hackassembler;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

class Parser {

	private CodeA codeA;
	private CodeC codeC;

	public Parser() {
		codeA = new CodeA();
		codeC = new CodeC();
	}

	public void readAsmFile(String hackFileName) {
		Path file = Paths.get("C:\\Users\\gabri\\Documents\\Eclipse\\HackAssembler\\bin\\" + hackFileName + ".asm");
		try (InputStream in = Files.newInputStream(file);
				BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				String temp = LineSanitizer.removeWhiteSpace(line);
				String work = LineSanitizer.removeComments(temp);
				intructionRouter(work, hackFileName);
			}
		} catch (IOException x) {
			System.err.println(x);
		}
	}

	public void writeHackFile(String s, String hackFileName) {
		s=s+"\n";
		byte data[] = s.getBytes();
		Path p = Paths.get("C:\\Users\\gabri\\Documents\\Eclipse\\HackAssembler\\bin\\" + hackFileName + ".hack");

		try (OutputStream out = new BufferedOutputStream(
				Files.newOutputStream(p, StandardOpenOption.CREATE, StandardOpenOption.APPEND))) {
			out.write(data, 0, data.length);
		} catch (IOException x) {
			System.err.println(x);
		}
	}

	private void intructionRouter(String line, String hackFileName) {
		if (line.length() > 0) {
			char c = line.toCharArray()[0];
			if (c == '@')
				writeHackFile(codeA.codeLine(line), hackFileName);
			else
				writeHackFile(codeC.codeLine(line), hackFileName);
		}
	}

}
