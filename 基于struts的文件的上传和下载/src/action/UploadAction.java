package action;

import java.io.File;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import util.FileSize;
import util.OVLoadProperties;
import util.PageListData;
import util.StringHandler;
import bean.FileInfo;

import com.opensymphony.xwork2.ActionSupport;

import dao.FileDAO;

public class UploadAction extends ActionSupport
{
	private File[] upload;// ʵ���ϴ��ļ�
	private String[] uploadContentType; // �ļ�����������
	private String[] uploadFileName; // �ϴ��ļ���
    private FileDAO dao=new FileDAO();
    protected PageListData pageListData;//��ҳ���
    protected int currentPage=1;//��ǰҳ
	protected int pageSize=2;//ÿҳ��ʾ����Ŀ
	public String upload() throws Exception
	{
		try
		{
			String targetDirectory = ServletActionContext.getServletContext()
					.getRealPath("/"+ OVLoadProperties.getInstance().getProperties("uploadFilePath").trim());// ���·��
			for (int i = 0; i < upload.length; i++)
			{
				String fileName = uploadFileName[i];// �ϴ����ļ���
				String type = uploadContentType[i];// �ļ�����
				String saveName = UUID.randomUUID().toString()+ getExt(fileName);// ������ļ����ƣ�ʹ��UUID+��׺���б���

				File target = new File(targetDirectory, saveName);
				FileUtils.copyFile(upload[i], target);// �ϴ�����������Ŀ¼��һ�㶼��������,// �ڰ�·��д�����ݿ⼴��
                FileInfo file = new FileInfo();// �����ļ�
                file.setFileIp(ServletActionContext.getRequest().getRemoteAddr());
                file.setFileName(fileName);
                file.setFileSize(FileSize.FormetFileSize(FileSize.getFileSizes(target)));
                file.setFileSavename(saveName);
                file.setFileType(type);
                file.setFileTimes(0);
				file.setFileUptime(StringHandler.timeTostr(new Date()));
				dao.saveFile(file);//���ļ���Ϣ���浽���ݿ���
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			addActionError(e.getMessage());

			return INPUT;
		}

		return SUCCESS;

	}
	//��ѯ�ļ��б�
	public String query() throws Exception{
		pageListData = dao.queryFile(currentPage, pageSize);
		return "query";
	}
	//ɾ���ļ�
	public String delete() throws Exception{
		String fileId=ServletActionContext.getRequest().getParameter("fileId");
		FileInfo file=dao.findFileById(fileId);
		java.io.File f = new java.io.File(ServletActionContext.getServletContext()
				.getRealPath("/"+ OVLoadProperties.getInstance().getProperties("uploadFilePath").trim())+"/"+file.getFileSavename());
		if(!dao.deleteFile(fileId)){
		   System.out.println("ɾ�����ݿ��е����ݳ���!");
		}
		if(!f.delete()) System.out.println("ɾ�������ļ�������!");
		return "success";
	}
	public File[] getUpload()
	{
		return upload;
	}

	public void setUpload(File[] upload)
	{
		this.upload = upload;
	}

	public PageListData getPageListData() {
		return pageListData;
	}
	public void setPageListData(PageListData pageListData) {
		this.pageListData = pageListData;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String[] getUploadContentType()
	{
		return uploadContentType;
	}

	public void setUploadContentType(String[] uploadContentType)
	{
		this.uploadContentType = uploadContentType;
	}

	public String[] getUploadFileName()
	{
		return uploadFileName;
	}

	public void setUploadFileName(String[] uploadFileName)
	{
		this.uploadFileName = uploadFileName;
	}

	public static String getExt(String fileName)
	{
		return fileName.substring(fileName.lastIndexOf("."));
	}

}
