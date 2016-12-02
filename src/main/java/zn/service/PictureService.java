/**
 * 
 */
package zn.service;

import zn.until.NoteResult;

/**
 * @author hq
 *
 */
public interface PictureService {
	public NoteResult uploadPictures(String picture,String pathUrl);
	public NoteResult findAllPictures();
	public NoteResult deletePictures(String pictureId);
	public NoteResult findUserPicture();
}
