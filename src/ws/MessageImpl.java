package ws;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ws.frame.db.DbUtils;
import com.ws.frame.utils.StrUtils;
import com.ws.web.service.LogsService;

/**
 * IMessage接口的实现类，用来实现接口方法
 * @author YINGFU
 * @version 1.0bate
 */
public class MessageImpl implements IMessage {
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@SuppressWarnings("unused")
	private final int IS_READ = 0, UN_READ = 1 , STATE_OK = 0;

	/**
	 * 药企平台推送数据到本地数据库中
	 * @param message	推送的信息， 如果为“通知公告”及“办事指南”模块时，为药采网的链接地址
	 * @param message2	数组类型的账号信息
	 * @param message3	模块标识， 目前平台推送的为汉字
	 * @param message4	信息标题
	 * @return >0 数据保存成功   <=0 数据保存失败
	 */
	@Override
	public int saveMessage(String message, String[] message2, String message3,
			String message4) {
		String sql = "insert into message(data, usercode, module, title, pushed, time, isDel, imsi) values(?,?,?,?,?,?,?,?)";
		List<Object[]> params = new ArrayList<Object[]>();
		//关于系统日志的相关数据信息
		List<Object[]> logsParams = new ArrayList<Object[]>();
		String pushTime = sdf.format(new Date());
		for(String usercode : message2){
			params.add(new Object[]{message, usercode, message3, message4, false, pushTime, UN_READ, ""});
			//记录日志信息的相关数据
			String info = "平台给账户为"+usercode+"的用户推送了一条标题为" + message4 +",再"+ message3+"模块下的信息";
			logsParams.add(new Object[]{LogsService.TAG_SYS, info, pushTime});
		}
		int number = DbUtils.getInstance().executeBatch(sql, params);
		LogsService.addBatch(logsParams);
		return number;
	}

	/**
	 * 用户密码重置
	 */
	@Override
	public boolean resetPassword(String usercode, String imsi) {
		int num = DbUtils.getInstance().execute("update sys_user set password = ? where usercode = ? and imsi = ?", new Object[]{StrUtils.MD5("888888"), usercode, imsi});
		LogsService.save(LogsService.TAG_SYS, "用户名为【"+usercode+"】,手机卡为【"+imsi+"】的用户重置了密码， "+(num > 0 ? "重置成功！" : "重置失败！")+"！", sdf.format(new Date()));
		if(num > 0){
			return true;
		} else {
			return false;
		}
	}

}
