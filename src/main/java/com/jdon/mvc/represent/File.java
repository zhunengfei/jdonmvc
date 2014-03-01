package com.jdon.mvc.represent;

import com.jdon.mvc.core.Env;
import com.jdon.mvc.core.ComponentHolder;

import javax.servlet.http.HttpServletResponse;
import java.io.*;


public class File implements Represent {

	private static final String TYPE = MediaType.APPLICATION_OCTET_STREAM;

	private java.io.File file;

	public File(java.io.File file) {
		this.file = file;
	}

	@Override
	public void render(ComponentHolder holder) throws RepresentationRenderException {
		if (file == null || !file.isFile() || file.length() > Integer.MAX_VALUE) {
			try {
				Env.res().sendError(HttpServletResponse.SC_NOT_FOUND);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		Env.res().setContentType(TYPE);
		Env.res().setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
		Env.res().setContentLength((int) file.length());
		InputStream input = null;
		try {
			input = new BufferedInputStream(new FileInputStream(file));
			OutputStream output = Env.res().getOutputStream();
			byte[] buffer = new byte[4096];
			for (;;) {
				int n = input.read(buffer);
				if (n == (-1))
					break;
				output.write(buffer, 0, n);
			}
			output.flush();
			output.close();
		} catch (IOException e) {
			throw new RepresentationRenderException(
					"can't render the resource to the formant of file", e);
		} finally {
			try {
				if (input != null) {
					input.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
