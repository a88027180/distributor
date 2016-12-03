/**
 * 
 */
package zn.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;



import zn.until.NoteResult;

/**
 * @author hq
 *
 */
@Service("pictureService")
public class PictureServiceImpl implements PictureService {



	/* (non-Javadoc)
	 * @see zn.service.PictureService#findAllPictures()
	 */
	@Override
	public NoteResult findAllPictures() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see zn.service.PictureService#deletePictures(java.lang.String)
	 */
	@Override
	public NoteResult deletePictures(String pictureId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see zn.service.PictureService#findUserPicture()
	 */
	@Override
	public NoteResult findUserPicture() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see zn.service.PictureService#uploadPictures(org.springframework.web.multipart.MultipartFile, java.lang.String, java.lang.String)
	 */
	@Override
	public NoteResult uploadPictures(MultipartFile file, String pathUrl, String fileName) {
		NoteResult note=new NoteResult(); 
	 
	         int lastlen= fileName.lastIndexOf(".");
	        String extensionName =fileName.substring(lastlen+1, fileName.length());  
	         Map<String,String> map = new HashMap<String,String>();
	         map.put("jpg", "jpg");
	         map.put("jpeg", "jpeg");
	         map.put("bmp", "bmp");
	         map.put("gif", "gif");
	    if (null == fileName || 0 == fileName.length()) {
	    	note.setStatus(1);
	        note.setMsg("必须输入文件");
	        note.setData("");
	 	// 文件后缀判断
	 	} else if (!map.containsKey(extensionName)) {
	 		note.setStatus(2);
	 		note.setMsg("输入文件格式不正确");
	 		note.setData("");
	 	// 文件读入
	 	} else{
	 	long time=new Date().getTime();
	 	File upLoadFile = new File(pathUrl+File.separator+time+"."+extensionName);
	 	try {
			file.transferTo(upLoadFile);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 	note.setStatus(0);
	 		note.setMsg("操作成功");
	 		note.setData("");
	 	}
	 
			return note;
	}

	
	
	
	
	
	
}
