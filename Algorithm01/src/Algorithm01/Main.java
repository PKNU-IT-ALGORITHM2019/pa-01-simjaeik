package Algorithm01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static int BUFFER_SIZE = 100000;
	public static data buffer [] = new data[BUFFER_SIZE];

	public static void main(String[] args) {

		try {
			command();
		} catch (FileNotFoundException e) {
			System.out.println("Errors detacted.");
			e.printStackTrace();
		}
	}

	private static void command() throws FileNotFoundException {

		Scanner kb = new Scanner(System.in);

		while(true) {
			System.out.print("$ ");
			String command = kb.next();

			if(command.equals("read")) {
				String read = kb.next();
				handleRead(read);
			}
			else if(command.equals("size")){
				System.out.println("Size is "+ data.size);
			}
			else if(command.equals("find")){
				String find = kb.next();
				handleFind(find, 0, data.size-1);
			}
			else if(command.equals("exit")){
				break;
			}


		}
		kb.close();

	}

	private static void handleFind(String find, int begin, int end) {

		if(begin > end) {
			System.out.println("The "+find+" is can't found.");
			System.out.println(buffer[end].GetFullWord());
			System.out.println("------------------------");
			System.out.println(buffer[begin].GetFullWord());
			return;
		}
		else {
			int middle = (begin+end)/2;
			if(buffer[middle].word.compareToIgnoreCase(find+" ")==0) {
				System.out.println("Found "+ buffer[middle].Samesize + " items");
				for(int i=0; i < buffer[middle].Samesize; i++) 
					System.out.println(buffer[buffer[middle].initNum+i].toString());

				return;
			}
			else if (buffer[middle].word.compareToIgnoreCase(find+" ") > 0)
				handleFind(find, begin, middle-1);
			else 
				handleFind(find, middle+1, end);
		}
	}

	private static void handleRead(String read) throws FileNotFoundException {

		Scanner fileReader = new Scanner(new File(read));

		while (fileReader.hasNextLine()) {
			if (BUFFER_SIZE <= data.size) reallocate();

			data newWord = new data(fileReader.nextLine());

			if(newWord.n!=-1) {
				if ( data.size > 1 && newWord.word.equals(buffer[data.size-2].word)) {
					if(buffer[data.size-2].Samesize == 1) {
						newWord.Samesize++;	
						buffer[data.size-2].Samesize++;
						newWord.initNum = buffer[data.size-2].n ;
					}
					else {
						newWord.Samesize = buffer[data.size-2].Samesize + 1;
						newWord.initNum = buffer[data.size-2].initNum;

						for(int i=0; i < newWord.Samesize-1; i++) {
							buffer[data.size-(2+i)].Samesize = newWord.Samesize;
						}
					}
				}
				buffer[data.size-1] = newWord;
			}
		}	
		System.out.println("The File " + read +" is read.");
		fileReader.close();
	}

	private static void reallocate() {
		data [] tempArray = new data [BUFFER_SIZE*2];
		for (int i=0; i<BUFFER_SIZE;i++)
			tempArray[i]=buffer[i];
		buffer = tempArray;
		BUFFER_SIZE*=2;
	}
}
