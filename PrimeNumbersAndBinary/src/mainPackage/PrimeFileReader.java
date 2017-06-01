/**
 * 
 */
package mainPackage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * @author William
 *
 */
public class PrimeFileReader {

	/**
	 * @param args
	 * @throws IOException 
	 */
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String filepath = "E:\\Workspace\\PrimeNumberGenerator\\PrimeNumbers.txt"; 
		String[] primeNumbers = ReadFileForPrimes(filepath);
		System.out.println(primeNumbers);

	}
	
	
	@SuppressWarnings("resource")
	public static String[] ReadFileForPrimes (String filePathFromUser) throws IOException{
	File usersFile;
	Stream<String> usersData;
	Object[] userFileData;
	String[] convertedUserFileData;
	
	usersFile = new File(filePathFromUser);
	usersData = Files.lines(Paths.get(usersFile.toURI()));
	userFileData = usersData.toArray();
	convertedUserFileData = new String[userFileData.length];
	int i = 0;
	while(i < userFileData.length){
		convertedUserFileData[i] = userFileData[i].toString();
		i++;
	}
	
	return convertedUserFileData;
	
	}

}
