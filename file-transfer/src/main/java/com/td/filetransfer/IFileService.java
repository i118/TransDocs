package com.td.filetransfer;

/**
 * Created by konstantinchipunov on 07.10.14.
 */
public interface IFileService {

    public OperationResult checkoutFile(String fileId);

    public OperationResult checkInFile(String fileId);

    public void vewFile(String fileId);

    public void cancelEdit(String fileId);
}
