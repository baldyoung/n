package n.baldyoung.Configuration;
import java.io.*;
import java.util.Properties;
/**
配置单元的工厂类

@author baldyoung
@version V0.5
*/
public class ConfigurationModule{
	/** 配置文件的类型 */
	public static enum Type{
		/**  */
		XML, 
		/**  */
		PROPERTIES
	}
	/** 配置单元。只能由N_ConfigurationModule.getInstance调用生成 */
	public static class ConfigurationUnit{
		private String fileLocation;
		private Type type;
		private Properties properties;
		protected ConfigurationUnit(String fileAbsolutePath, Type type, Properties pro){
			this.fileLocation = fileAbsolutePath;
			this.type = type;
			this.properties = pro;
		}
		public Properties getProperties(){
			return this.properties;
		}
		public Type getType(){
			return this.type;
		}
		public String getFileLocation(){
			return this.fileLocation;
		}
	}
	/** 
	按指定的类型读取给定的文件内容，如果成功则返回一个配置单元，否则返回null。 
	@param fileAbsolutePath 指定文件路径，如：D:/test/test.properties 
	@param type 指定加载类型，如：N_ConfigurationModule.Type.PROPERTIES
	@return 加载成功返回一个N_ConfigurationModule.ConfigurationUnit实例，否则返回null 
	@throws IOException 可能抛出IOException异常 
	@throws IOException 可能抛出IOException异常 
	@exception FileNotFoundException 文件未找到异常
	@exception FileNotFoundException 文件未找到异常
	*/
	public static ConfigurationUnit getInstance(String fileAbsolutePath, Type type) throws IOException{
		ConfigurationUnit unit = null;
		Reader input = new InputStreamReader(new FileInputStream(fileAbsolutePath), "UTF-8");
		Properties pro = new Properties();
		switch(type){
			//case XML : pro.loadFromXML(input); break;
			case PROPERTIES : pro.load(input); break;
			default : pro = null; break;
		}
		if(null != pro){
			unit = new ConfigurationUnit(fileAbsolutePath, type, pro);
		}
		return unit;		
	}
	
	
}
	