package com.example.shi.myapplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberAddressAndAgent {
	
	URL zsURL = null;
	BufferedReader zsBR = null;
	/**
	 * @param args
	 * @throws IOException
	 */
	static String foo(String s) {
		Pattern p = Pattern.compile("([^&]*)&nbsp;(.*)");
		Matcher m = p.matcher(s);
		if (m.find()) {
			return m.group(1) + " " + m.group(2);
		}
		return null;
	}
	public void close() throws IOException{
		if(zsBR!=null)
			zsBR.close();
	}
	public PhoneNumberAddressAndAgent(String number) throws IOException{
		zsURL = new URL("http://www.ip138.com:8080/search.asp?action=mobile&mobile="+ number);
		zsBR = new BufferedReader(new InputStreamReader(zsURL.openStream(),"GBK"));
	}
	String getAddress() throws IOException{
		
		String zsS = "";
		Pattern zsP1 = Pattern.compile("卡号归属地");
		Pattern zsP2 = Pattern
				.compile("[^&]*>([^<&]+&[^<]*)</TD>");
		Matcher zsM;
		String s;
		while ((zsS = zsBR.readLine()) != null) {
			//System.out.println("1!");
			//System.out.println(zsS);
			zsM = zsP1.matcher(zsS);
			if (zsM.find()) {
				zsM = zsP2.matcher(zsS);
				if (zsM.find()) {
					//System.out.println(zsM.group(1));
					s = foo(zsM.group(1));
					//System.out.println(s);
					return s;
				} else {
					zsM = zsP2.matcher(zsBR.readLine());
					if (zsM.find()) {
						s = foo(zsM.group(1));
						//System.out.println(s);
						return s;
					}
				}
			}
		}
		return null;
	}
	String getAgent() throws IOException{
		Pattern zsP1 = Pattern.compile("卡&nbsp;类&nbsp;型");
		Pattern zsP2 = Pattern.compile(">([^<>]+卡)");
		Matcher zsM = null;
		String zsS = "";
		while ((zsS = zsBR.readLine()) != null) {
			//System.out.println("1!");
			//System.out.println(zsS);
			zsM = zsP1.matcher(zsS);
			if (zsM.find()) {
				zsM = zsP2.matcher(zsS);
				if (zsM.find()) {
					return zsM.group(1);
				} else {
					zsM = zsP2.matcher(zsBR.readLine());
					if (zsM.find()) {
						return zsM.group(1);
					}
				}
			}
		}
		return null;
		
	}
	public static void main(String args[]) throws IOException{
		PhoneNumberAddressAndAgent zs = new PhoneNumberAddressAndAgent("15556925");
		System.out.println(zs.getAddress()+":"+zs.getAgent());
		zs.close();
	}
}
