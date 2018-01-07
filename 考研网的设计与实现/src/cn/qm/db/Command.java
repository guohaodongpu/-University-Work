package cn.qm.db; 
import java.io.BufferedReader;  
import java.io.DataInputStream;  
import java.io.IOException;  
import java.io.InputStreamReader;  
  
  
public class Command {  
    /*  
    public static void main(String[] args) throws IOException {  
        Command com = new Command();  
        com.backupDatebase("localhost","root","root", "JXC", "D:/jxc.sql");  
    }  
      
    /**  
     * ִ��dos����  
     * @param cmd  
     * @return  
     */  
    public String execCmd(String cmd) {  
        StringBuffer sb = new StringBuffer("");  
        StringBuffer str = new StringBuffer();  
        str.append("cmd.exe /c \"").append(cmd).append("\"");  
        System.out.println(str);        //��ӡִ�е�����  
        Process ls_proc;  
        try {  
            ls_proc = Runtime.getRuntime().exec(str.toString());  
            BufferedReader in = new BufferedReader(  
                                    new InputStreamReader(  
                                        new DataInputStream(ls_proc.getInputStream())));  
            String ss = "";  
            while((ss = in.readLine()) != null) {  
                sb.append(ss).append("\n");  
            }  
            in.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }   
  
        return sb.toString();  
    }  
      
    /**  
     * ִ��mysql���ݿⱸ��  
     * @param ip  
     * @param username  
     * @param password  
     * @param datebaseName  
     * @param filePath  
     * @return  
     */  
    public boolean backupDatebase(String ip, String username, String password,String datebaseName, String filePath) {  
        String strCommand = "mysqldump -h "+ip+" -u" + username + " -p" + password + " " + datebaseName + " > " + filePath;  
        String result = execCmd(strCommand);  
        System.out.println(result);  
        return true;  
    }  
      
    /**  
     * ���ݷ��ؽ����֤�Ƿ�ɹ�  
     * @param result  
     * @return  
     */  
    public boolean check(String result) {  
        return true;  
    }  
}  