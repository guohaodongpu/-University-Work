package action;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import bean.FileInfo;

import com.opensymphony.xwork2.ActionSupport;

import dao.FileDAO;

import org.apache.struts2.ServletActionContext;

import util.OVLoadProperties;

public class DownloadAction extends ActionSupport {
	private static final long serialVersionUID = 6329383258366253255L;
	private String fileName;
	private String fileSavename;
    private String fileId; 
    private FileDAO dao=new FileDAO();
	public String execute() throws Exception {
		return SUCCESS;
	}
	/*
	 * @getDownloadFile �˷�����Ӧ����struts.xml�ļ��еģ� <param
	 * name="inputName">downloadFile</param> ���������ļ����������Բο�struts2��Դ��
	 */
	public InputStream getDownloadFile() {
		FileInfo file=dao.findFileById(fileId);
		dao.addTimes(file.getFileTimes(),file.getFileId());
		return ServletActionContext.getServletContext().getResourceAsStream(
				"/"
						+ OVLoadProperties.getInstance()
								.getProperties("uploadFilePath").trim() + "/"
						+ fileSavename);
	}

	public String getFileSavename() {
		return fileSavename;
	}

	public void setFileSavename(String fileSavename)
			throws UnsupportedEncodingException {
		fileSavename = new String(fileSavename.getBytes("ISO-8859-1"), "gbk");
		this.fileSavename = fileSavename;
	}

	/*
	 * @getFileName �˷�����Ӧ����struts.xml�ļ��еģ� <param
	 * name="contentDisposition">attachment;filename="${fileName}"</param>
	 * ����������õ������ع��������ļ�ʱ��ʾ���ļ����� Ҫ����ȷ����ʾ�����ļ�����������Ҫ��fileName�ٴα���
	 * �����������ļ����������룬���޷����ص����
	 */
	public String getFileName() throws UnsupportedEncodingException {

		fileName = new String(fileName.getBytes(), "ISO-8859-1");

		return fileName;
	}

	public void setFileName(String fileName)
			throws UnsupportedEncodingException {
		fileName = new String(fileName.getBytes("ISO-8859-1"), "gbk");
		this.fileName = fileName;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getFileId() {
		return fileId;
	}

}