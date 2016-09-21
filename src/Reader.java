import java.util.HashMap;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


public class Reader {
	
	public static List<String> getLines(String file) {

		BufferedReader br = null;
		List<String> lines = new ArrayList<>();

		try {
			String sCurrentLine, theme;
			br = new BufferedReader(new FileReader(file));

			while ((sCurrentLine = br.readLine()) != null && sCurrentLine.length()>0) {				
				lines.add(sCurrentLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return lines;

	}
	
	public static List<String> getThemes(String file) {

		BufferedReader br = null;
		List<String> themes = new ArrayList<>();
		String theme;

		for (String line : getLines(file)) {
			theme = line.split(";")[2];
			if(!themes.contains(theme)){
				themes.add(theme);
			}
		}
		
		return themes;

	}
	
	public static List<String> getUsers(String file) {

		BufferedReader br = null;
		List<String> users = new ArrayList<>();
		String theme;

		for (String line : getLines(file)) {
			theme = line.split(";")[1];
			if(!users.contains(theme)){
				users.add(theme);
			}
		}
		
		return users;

	}
	
	public static int[][] getMUTmatrix(String file){
		List<String> themes = getThemes(file);
		List<String> users = getUsers(file);
		List<String> lines = getLines(file);
		int[][] mat = new int[users.size()][themes.size()];

		for (int i = 0; i < users.size(); i++) {
			for (int j = 0; j < themes.size(); j++) {
				mat[i][j]=0;
			}
		}
		
		HashMap hmUsers = new HashMap<>();
		int i=0;
		for (String u : users) {
			hmUsers.put(u, i++);
		}
		HashMap hmThemes = new HashMap<>();
		i=0;
		for (String t : themes) {
			hmThemes.put(t, i++);
		}
		
		

		for (String line : lines) {
			String t = line.split(";")[2];
			String u = line.split(";")[1];
			mat[(int) hmUsers.get(u)][(int) hmThemes.get(t)]+=1;
		}
		
		
		
		
		
		
		return mat;
	}

	public static void printThemes(String file) throws FileNotFoundException, UnsupportedEncodingException{
		PrintWriter writer = new PrintWriter("themes.txt", "UTF-8");
		for (String t : getThemes(file)) {
			writer.println(t);

		}
		writer.close();
	}
	public static void printUsers(String file) throws FileNotFoundException, UnsupportedEncodingException{
		PrintWriter writer = new PrintWriter("users.txt", "UTF-8");
		for (String t : getUsers(file)) {
			writer.println(t);

		}
		writer.close();
	}
	
	
	public static void printMatrix(String file) throws FileNotFoundException, UnsupportedEncodingException{
		PrintWriter writer = new PrintWriter("matrix.txt", "UTF-8");
		int[][] mat = getMUTmatrix(file);
		
		for (int i = 0; i < getUsers(file).size(); i++) {
			for (int j = 0; j < getThemes(file).size(); j++) {
				
					writer.print(mat[i][j]+";");
			}
			writer.println(" ");
		}

		writer.close();
	}
	
	
	public static void getThemesMatrix(String file) throws FileNotFoundException, UnsupportedEncodingException{
		PrintWriter writer = new PrintWriter("themesMatrix.txt", "UTF-8");
		List<String> users = getUsers(file);
		List<String> themes = getThemes(file);
		int[][] mat = getMUTmatrix(file);
		
		
		for (int i = 0; i < themes.size(); i++) {
			for (int j = 0; j < themes.size(); j++) {
				int a=0, b=0;
				for (int k = 0; k < users.size(); k++) {
					a+=mat[k][i];
				}
				for (int k = 0; k < users.size(); k++) {
					a+=mat[k][j];
				}
				
				
				writer.print(a+b+";");
			}
			writer.println(" ");

		}
		
		writer.close();
		
		
	}
	
	public static void getUsersMatrix(String file) throws FileNotFoundException, UnsupportedEncodingException{
		PrintWriter writer = new PrintWriter("usersMatrix.txt", "UTF-8");

		List<String> users = getUsers(file);
		List<String> themes = getThemes(file);

		int[][] mat = getMUTmatrix(file);
		
		
		for (int i = 0; i < users.size(); i++) {
			for (int j = 0; j < users.size(); j++) {
				int a=0, b=0;
				for (int k = 0; k < themes.size(); k++) {
					a+=mat[i][k];
				}
				for (int k = 0; k < themes.size(); k++) {
					a+=mat[j][k];
				}
				
				
				writer.print(a+b+";");
			}
			writer.println(" ");

		}
		
		writer.close();
		
		
	}
	
	
	
	
	
	
	
}