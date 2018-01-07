
package servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class TextBufferedImage extends HttpServlet
{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        //�����������
    	resp.setHeader("Cache-Control","no-cache");
    	HttpSession session = req.getSession();
        String randomPassword = getRandom(4);
        System.out.println("randomPassword="+randomPassword);
    	session.setAttribute("randomCode", randomPassword);
        OutputStream out = resp.getOutputStream();

        try
        {
            JPEGImageEncoder encode = JPEGCodec.createJPEGEncoder(out);
            BufferedImage bi = CreateBufferedImage(randomPassword);
            encode.encode(bi);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * �������д��ͼƬ�Ϸ���
     * 
     * @param randomPassword //�����
     * @return
     */
    private BufferedImage CreateBufferedImage(String randomPassword)
    {
        int width = 80;
        int height = 20;

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.clearRect(0, 0, width, height);
        // �趨����ɫ
        g2d.setColor(getRandColor(200,250));
        g2d.fillRect(0, 0, width, height);
        
        
        // �������155�������ߣ�ʹͼ���е���֤�벻�ױ���������̽�⵽
        Random random = new Random();
        g2d.setColor(getRandColor(160,200));
        g2d.setFont(getFont());
        for (int i=0;i<155;i++)
        {
        	int x = random.nextInt(width);
        	int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g2d.drawLine(x,y,x+xl,y+yl);
        }

        // ȡ�����������֤��(4λ����)
        String sRand="";
        for (int i=0;i<4;i++){
            String rand = randomPassword.substring(i, i+1);
            sRand+=rand;
            // ����֤����ʾ��ͼ����
            g2d.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));//���ú�����������ɫ��ͬ����������Ϊ����̫�ӽ�������ֻ��ֱ������
            g2d.drawString(rand,14*i+12,17);
        }
        
        g2d.dispose();
        return bufferedImage;

    }
    private Color getRandColor(int fc, int bc) {// ������Χ��������ɫ
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
    
    private Font getFont() {
		Random random = new Random();
		Font font[] = new Font[5];
		font[0] = new Font("Ravie", Font.PLAIN, 20);
		font[1] = new Font("Antique Olive Compact", Font.PLAIN, 20);
		font[2] = new Font("Forte", Font.PLAIN, 20);
		font[3] = new Font("Wide Latin", Font.PLAIN, 20);
		font[4] = new Font("Gill Sans Ultra Bold", Font.PLAIN, 20);
		return font[random.nextInt(5)];
	}
    /**
	 * ���������(��ĸ+����)
	 * 
	 * @param length
	 *            ����볤��
	 * @return �����
	 */
	private  String getRandom(int length) {

		String randomCode = "";
		for (int i = 0; i < length; i++) {
			randomCode += getRandChar();
		}

		return randomCode;
	}
	public  String getRandChar() {
		int rand = (int) Math.round(Math.random() * 2);
		long itmp = 0;
		char ctmp = '\u0000';
		// ����rand��ֵ������������һ����д��ĸ��Сд��ĸ������
		switch (rand) {
		// ���ɴ�д��ĸ
		case 1:
			itmp = Math.round(Math.random() * 25 + 65);
			ctmp = (char) itmp;
			return String.valueOf(ctmp);
			// ����Сд��ĸ
		case 2:
			itmp = Math.round(Math.random() * 25 + 97);
			ctmp = (char) itmp;
			return String.valueOf(ctmp);
			// ��������
		default:
			itmp = Math.round(Math.random() * 9);
			return String.valueOf(itmp);
		}
	}
}