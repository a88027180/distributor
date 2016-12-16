package zn.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import zn.entity.User;



public interface UserDao {
	public int checkTel(String telephone);

	public int checkLogin(@Param("telephone")String telephone,@Param("password")String password);

	public int changeUserState(@Param("userId")int userId,@Param("lastLoadTime")String lastLoadTime);
	public   int   selectIdByTel(String telephone);  
	public int addUserAndGetId(User user);
	
	public int  addUserAndOrg(User user);
	
	public    User selectUserById(Integer userId);
	public int  deleteUser(Integer userId);
	
	public int changeUserInfo(@Param("information")String information,@Param("userName")String userName,@Param("userId")int userId);
	
	public List<User> selectUserByOrg(int orgId);
	public List<User> selectAllUser();
	
	
	public int changePassword(@Param("password")String password,@Param("userId")int userId);
	
	public int changePicUrl(@Param("userId")int userId,@Param("userPicUrl")String userPicUrl);
	public String selectUserPic(int userId);
	
	public  int  userAddMon(Map<String,Object>     param);
	public int userDelteMon(int userId);
	public   List<User>   seleteUserByMonId(int monId);
	public int  deleteUserAndOrg(int userId);
	
	public int userAddLimits(@Param("limitsId")int limitsId,@Param("userId")int userId);
	public int userDeleteLimits(int userId);
	public int seleteUserLimitsById(Integer userId);
	
	public List<Map<String,Object>> seleteUserListByMonId(int monId);
	
	
	public List<Integer> selectUserIdList();

	
}
