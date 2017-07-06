package com.commons.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * 生成验证码图片
 * @author Administrator
 *
 */
public class VerifyCode {

	/**
	 * 成员属性
	 */
	private int w=90;
	private int h=35;
	private Random r=new Random();
	//字体{"宋体","华文楷体","黑体","微软雅黑","楷体_GB2312"}
	private String[] fontNames={"宋体","华文楷体","黑体","微软雅黑","楷体_GB2312"};
	private String codes="23456789abcdefghijklmnpqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ";
	private Color bgcolor=new Color(255,255,255);
	private String text;
	
	/**
	 * 返回以个随机颜色【偏黑的】
	 * @return
	 */
	private Color randomColor(){
		int red = r.nextInt(150);
		int green=r.nextInt(150);
		int blue =r.nextInt(150);
		return new Color(red,green,blue);
	}
	 /**
	  * 生成随机字体
	  * @return
	  */
	private Font randomFont(){
		int index=r.nextInt(fontNames.length);
		String fontName=fontNames[index];
		int style =r.nextInt(4);
		int size  =r.nextInt(4)+24;
		return new Font(fontName,style,size);
		
	}
	/**
	 * 随机生成三条干扰线
	 * @param image
	 */
	private void drawLine(BufferedImage image){
		int num =3;
		Graphics2D g2=(Graphics2D) image.getGraphics();
			g2.setColor(Color.blue);
		for(int i=0;i<num;i++)
		{
			
		int x1=r.nextInt(w);
		int y1=r.nextInt(h);
		int x2=r.nextInt(w);
		int y2=r.nextInt(h);
		g2.drawLine(x1, y1, x2, y2);
	
		}
	}	
	/**
	 * 生成随机字符
	 * @return
	 */
	private char randomChar(){
		int index =r.nextInt(codes.length());
		return codes.charAt(index);
	}
	/*
	 * 创建图片缓冲区【初始化】
	 */
	private BufferedImage createImage(){
		BufferedImage bi=new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2=(Graphics2D) bi.getGraphics();
		g2.setColor(this.bgcolor);
		g2.fillRect(0, 0, w, h);
		return bi;
		
	}
	/**
	 * 生成图片验证码
	 * @return
	 */
	public BufferedImage getImage(){
		BufferedImage image =createImage();
		Graphics2D g2 =(Graphics2D) image.getGraphics();
		StringBuilder sb =new StringBuilder();
		//生成5为随机验证码
		for(int i=0;i<5;i++){
			String s =randomChar()+"";
			float x =i*1.0F*w/5;
			sb.append(s);
			g2.setFont(randomFont());
			g2.setColor(randomColor());
			g2.drawString(s, x, h-4	);
			
		}
			this.text=sb.toString();
			drawLine(image);
			return image;
	}
	
	public String getText(){
		return this.text;
	}
	/**
	 * 保存图片到流中
	 * @param image
	 * @param out
	 */
	public static void outPut(BufferedImage image,OutputStream out){
		try {
			ImageIO.write(image, "JPEG", out);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
}
