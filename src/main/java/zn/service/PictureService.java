/**
 * 
 */
package zn.service;

import org.springframework.web.multipart.MultipartFile;

import zn.until.NoteResult;

/**
 * @author hq
 *
 */
public interface PictureService {
	public NoteResult uploadPictures(MultipartFile file,String pathUrl,String fileName);
	public NoteResult findAllPictures();
	public NoteResult deletePictures(String pictureId);
	public NoteResult findUserPicture();
}
