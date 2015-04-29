/**
 * @author mike802
 * @version 1.0 - 9/21/2013
 */


import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

public class Properties{
	
	private String coverPath;
	private String emptyPath;
	private ConcurrentHashMap<String, String> suitPaths = 
			new ConcurrentHashMap<String, String>();
	
	private String logo;
	private String rootDir;
	private String background;
	
	public Properties(String dir){
		rootDir = dir;
		coverPath = rootDir + File.separator + "cards" + File.separator + "cover.png";
		emptyPath = rootDir + File.separator + "cards" + File.separator + "empty.png";
		
		String cards = rootDir + File.separator + "cards" + File.separator;
		suitPaths.put("clubs", cards + "clubs");
		suitPaths.put("diamonds", cards + "diamonds");
		suitPaths.put("hearts", cards + "hearts");
		suitPaths.put("spades", cards + "spades");
		
		logo = rootDir + File.separator + "img" + File.separator + "logo.png";
		background = rootDir + File.separator + "img" + File.separator + "background.png";
	}

	public String suitPath(String suit){
		return suitPaths.get(suit);
	}
	
	public String getCoverPath(){
		return coverPath;
	}
	
	public String getEmptyPath(){
		return emptyPath;
	}
	
	public String getLogo(){
		return logo;
	}
	public String getBackground(){
		return background;
	}
}
