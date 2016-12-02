/**
 * 
 */
package zn.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import org.springframework.stereotype.Service;

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
	 * @see zn.service.PictureService#uploadPictures(java.lang.String, java.lang.Integer)
	 */
	@Override
	public NoteResult uploadPictures(String picture,String pathUrl) {
	long t =   System.currentTimeMillis();    
	File pic=new File(pathUrl);
	FileOutputStream fos;
	try {
		fos = new FileOutputStream(pic);
	
	PrintWriter pw = new PrintWriter(fos,true		);

	pw.println("你好!");		
	
	pw.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return null;
	}
	
	
	
	
	
}
