package org.fugerit.java.demo.quarkus.jvsf;

import java.io.IOException;

import org.fugerit.java.core.jvfs.JFile;

public class JFileDTO {

	private JFile wrapped;
	
	public JFileDTO(JFile wrapped) {
		super();
		this.wrapped = wrapped;
	}

	public JFile unwrap() {
		return this.wrapped;
	}

	public String getPath() {
		return this.wrapped.getPath();
	}
	
	public String getName() {
		return this.wrapped.getName();
	}
	
	public boolean isDirectory() throws IOException {
		return this.wrapped.isDirectory();
	}
	
}
