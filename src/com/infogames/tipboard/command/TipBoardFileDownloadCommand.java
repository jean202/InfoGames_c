package com.infogames.tipboard.command;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TipBoardFileDownloadCommand implements TipBoardCommandIF {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		FileInputStream in = null;
		ServletOutputStream sout = null;
		try {
			String systemFileName = request.getParameter("systemFileName");
			String originalFileName = request.getParameter("originalFileName");

			ServletContext context = request.getSession().getServletContext();
			String realFolder = context.getRealPath("tipboard/fileupload");
			String downloadFilePath = realFolder + File.separator + systemFileName;
			String fileType = "application/octet-stream";
			response.setContentType(fileType);

			String encFileName = URLEncoder.encode(originalFileName, "utf-8");
			response.setHeader("content-Disposition", "attachment; filename=" + encFileName);

			File srcFile = new File(downloadFilePath);
			in = new FileInputStream(srcFile);
			sout = response.getOutputStream();

			byte[] buff = new byte[5 * 1024 * 1024];
			int numRead = 0;
			int totalRead = 0;

			while ((numRead = in.read(buff, 0, buff.length)) != -1) {
				totalRead += numRead;
				sout.write(buff, 0, numRead);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				sout.flush();
				sout.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}
}
