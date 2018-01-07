package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//����ģʽʵ�ֶ�ȡxxx.properties�ļ�������
public class OVLoadProperties {
	// ����һ���Լ���ʵ��
	private static OVLoadProperties instance = new OVLoadProperties();
	final static String fileName = "/upload.properties";
	// ���ظ�ʵ��
	public static synchronized OVLoadProperties getInstance() {
		return instance;
	}

	// ��ȡkey����Ӧ��ֵ
	public String getProperties(String key) {
		Properties p = new Properties();
		InputStream is = null;
		try {
			//xxx.properties�ļ�����srcĿ¼���±�
			is = OVLoadProperties.class.getResourceAsStream(fileName);
			if (is == null)
				is = new FileInputStream(fileName);
			p.load(is);
		} catch (Exception e) {
			System.out.println("�����ļ�������!" + e.getMessage());
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
			}
		}
		return p.getProperty(key);
	}
}