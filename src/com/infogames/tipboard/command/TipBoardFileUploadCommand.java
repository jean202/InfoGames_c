package com.infogames.tipboard.command;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

public class TipBoardFileUploadCommand implements TipBoardCommandIF {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ServletContext context = request.getSession().getServletContext();
		String realFolder = context.getRealPath("tipboard/fileupload");
		int maxPostSize = 5 * 1024 * 1024;
		String encoding = "utf-8";
		FileRenamePolicy policy = new DefaultFileRenamePolicy();
		MultipartRequest multi = null;
		
		try{
			
			multi = new MultipartRequest(
					request,
					realFolder,
					maxPostSize,
					encoding,
					policy
					);
			
			 Enumeration names = multi.getFileNames();
			 
			 ArrayList<String> originalList = new ArrayList<String>();
			 ArrayList<String> systemList = new ArrayList<String>();
			
			while(names.hasMoreElements()){
				
				String name = (String)names.nextElement();
				String originalFileName = multi.getOriginalFileName(name);
				String systemFileName = multi.getFilesystemName(name);
				if(originalFileName != null) {
					originalList.add(originalFileName);
					systemList.add(systemFileName);
				}
			}
			request.setAttribute("originalList", originalList);
			request.setAttribute("systemList", systemList);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
