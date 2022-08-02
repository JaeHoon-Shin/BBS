package test;

import java.util.ArrayList;

import bbs.Bbs;

public class array {
	
	
	public static void main(String[] args) {
		ArrayList<Bbs> list = new ArrayList<Bbs>();
		Bbs bbs = new Bbs();
		
		bbs.setBbsID(1);
		bbs.setBbsTitle("ssss");
		bbs.setBbsContent("ssss");
		bbs.setUserID("s");
		list.add(bbs);
		bbs.setBbsID(1);
		bbs.setBbsTitle("ssss");
		bbs.setBbsContent("ssss");
		bbs.setUserID("s");
		list.add(bbs);
		
		bbs.setBbsID(1);
		bbs.setBbsTitle("ssss");
		bbs.setBbsContent("ssss");
		bbs.setUserID("s");
		list.add(bbs);
		bbs.setBbsID(1);
		bbs.setBbsTitle("ssss");
		bbs.setBbsContent("ssss");
		bbs.setUserID("s");
		list.add(bbs);
		System.out.println(list.size());
	}
}
