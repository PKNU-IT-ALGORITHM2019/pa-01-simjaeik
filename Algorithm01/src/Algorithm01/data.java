package Algorithm01;

public class data {
	public String line;
	public String fullWord ;		
	public String word;
	public int n ;				// the number of array
	public static int size = 0;
	public int Samesize = 1;
	public int initNum ;

	public data(String line) {
		
		if(line.equals("")) {
			n = -1;
		}
		else{			
			this.line = line;
			fullWord = line.split("\\)")[0];
			word = fullWord.split("\\(")[0];
			n=size;		
			initNum = n ;
			size++;
		}
	}

	public String toString() {
		return line;
	}
	
	public String GetWord() {
		return word;
	}
	
	public String GetFullWord() {
		return fullWord+")";
	}
	
}
