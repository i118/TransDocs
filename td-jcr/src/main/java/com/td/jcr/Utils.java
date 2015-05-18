package com.td.jcr;

import javax.jcr.Repository;

/**
 * Created by konstantinchipunov on 15.09.14.
 */
public class Utils {
    public static boolean supportsTransactions(Repository repository) {
        return "true".equals(repository.getDescriptor(Repository.OPTION_TRANSACTIONS_SUPPORTED));
    }

    public static boolean supportsVersioning(Repository repository) {
        return "true".equals(repository.getDescriptor(Repository.OPTION_VERSIONING_SUPPORTED));
    }

    public static boolean supportsObservation(Repository repository) {
        return "true".equals(repository.getDescriptor(Repository.OPTION_OBSERVATION_SUPPORTED));
    }

    public static boolean supportsLocking(Repository repository) {
        return "true".equals(repository.getDescriptor(Repository.OPTION_LOCKING_SUPPORTED));
    }
}
