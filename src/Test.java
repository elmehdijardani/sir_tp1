import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;


public class Test {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		
		final String file = "Log-clients-themes.txt";
		// TODO Auto-generated method stub
	

		Reader.printThemes(file);
		Reader.printUsers(file);
		Reader.printMatrix(file);
		Reader.getThemesMatrix(file);
		Reader.getUsersMatrix(file);

		
		System.out.println("nombre de clients :"+Reader.getUsers(file).size());
		System.out.println("nombre de types de produits :"+Reader.getThemes(file).size());


		
		
	}

}
