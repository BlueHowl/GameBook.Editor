package org.helmo.gbeditor.infrastructures.jdbc;

public class UnableToSetupException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnableToSetupException(Exception ex) {
        super(ex);
    }
}
