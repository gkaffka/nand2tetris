package hackassembler;

public class LineSanitizer {

	public static String removeWhiteSpace(String input){
		return input.replaceAll("\\s+","");
	}
	
	public static String removeComments(String input){
		if(input.indexOf("//")==-1) return input;
		return input.substring(0,input.indexOf("//"));
	}
}
