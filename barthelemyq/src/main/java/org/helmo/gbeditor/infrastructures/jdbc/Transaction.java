package org.helmo.gbeditor.infrastructures.jdbc;

import org.helmo.gbeditor.repositories.exceptions.UnableToRollbackException;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Classe Transaction de Mr Ludewig
 */
@SuppressWarnings("PMD")

public class Transaction {

    private final Connection con;
    private ExceptionHandle rollbackAction;
    private ActionThrowingException commitAction;

    public static Transaction from(Connection con) {
        try {
            con.setAutoCommit(false);
        } catch (SQLException ex) {
            throw new TransactionNotSupportedException(ex);
        }
        return new Transaction(con);
    }

    public Transaction(Connection con) {
        this.con = con;
    }

    public Transaction commit(ActionThrowingException sequence) {
        this.commitAction = sequence;
        return this;
    }

    public Transaction onRollback(ExceptionHandle sequence) {
        this.rollbackAction = sequence;
        return this;
    }

    public void execute() {
        try {
            commitAction.execute(con);
            con.commit();
        } catch (Exception ex) {
            try {
                con.rollback();
                rollbackAction.handle(ex);
            } catch (SQLException e) {
                throw new UnableToRollbackException(e);
            }
        } finally {
            try {
                con.setAutoCommit(true); //Active la gestion automatique des transactions
            } catch(SQLException ex) {
                throw new TransactionNotSupportedException(ex);
            }
        }
    }
}

@SuppressWarnings("PMD")
class TransactionNotSupportedException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TransactionNotSupportedException(SQLException ex) {
        super("Transaction are not supported by this DBMS or this driver", ex);
    }
}

@SuppressWarnings("PMD")
@FunctionalInterface
interface ActionThrowingException {
    void execute(Connection con) throws Exception;
}

@SuppressWarnings("PMD")
@FunctionalInterface
interface ExceptionHandle {
    void handle(Exception ex);
}
