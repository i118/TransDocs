package com.td.jcr.jca;

import javax.resource.ResourceException;
import javax.resource.spi.ConnectionManager;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ManagedConnectionFactory;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.Transaction;
import javax.transaction.TransactionManager;

/**
 * Created by konstantinchipunov on 25.11.14.
 */
public class XAConnectionManager implements ConnectionManager {

    private static final long serialVersionUID = 1479445982219812432L;

    private final TransactionManager transactionManager;

    public XAConnectionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public Object allocateConnection(ManagedConnectionFactory mcf, ConnectionRequestInfo cri)
            throws ResourceException {
        ManagedConnection mc = mcf.createManagedConnection(null, cri);
        try {
            Transaction transaction = transactionManager.getTransaction();
            if(transaction==null) throw new SystemException("transaction not found");
                transaction.enlistResource(mc.getXAResource());
        } catch (SystemException e) {
            throw new ResourceException(e);
        }  catch (RollbackException e) {
            throw new ResourceException(e);
        }
        return mc.getConnection(null, cri);
    }
}
