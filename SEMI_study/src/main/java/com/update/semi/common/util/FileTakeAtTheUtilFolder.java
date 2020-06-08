package com.update.semi.common.util;

import java.io.File;
//Util폴더에서 파일명 배열로 가져오기
public class FileTakeAtTheUtilFolder {
	
	   // 해당폴더 파일개수
	   public static int getFileCount(String fileRoute) {
	      int count = 0;
	      File file = new File(fileRoute);
	      for (File f : file.listFiles()) {
	         if (f.isFile()) {
	            count++;
	         }
	      }
	      // System.out.println(count);
	      return count;
	   }
	   
	   // 해당폴더 파일경로+파일명 가져오기
	   public static String[] getFilesName(String fileRoute) {
	      File file = new File(fileRoute);
	      int num = getFileCount(fileRoute);
	      int index = 0;
	      String[] fileNames = new String[num];
	      for(File f : file.listFiles()) {      // listFiles() 해당 경로의 파일들과 폴더의 파일을 배열로 반환한다.
	         if(f.isFile()) {                // 파일이면 true
	            String name = f.getName();      // 파일이나 폴더의 이름을 넘겨준다
	            fileNames[index] = name;
	            index++;
	         }
	      }
	      //System.out.println(Arrays.toString(arrS));
	      return fileNames;
	   }
	
}
