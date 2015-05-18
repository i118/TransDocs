import java.io.IOException;

/**
 * Created by konstantinchipunov on 07.10.14.
 */
public class FileServiceImpl implements IFileService {

    private final IFileWrapperDao fileInfoDao;


    public FileServiceImpl(IFileWrapperDao fileInfoDao) {
        this.fileInfoDao = fileInfoDao;
    }

    @Override
    public OperationResult checkoutFile(String fileId){
        try {
            if(fileId==null) new IllegalStateException("fileId can not be null");
            IFileWrapper fileInfo = fileInfoDao.getById(fileId);
            fileInfo.edit();
        }catch (Exception e){
            e.printStackTrace();
            return OperationResult.ERROR;
        }
        return OperationResult.COMPLETE;
    }

    @Override
    public OperationResult checkInFile(String fileId){
        try {
            if(fileId==null) new IllegalStateException("fileId can not be null");
            IFileWrapper fileInfo = fileInfoDao.getById(fileId);
            fileInfo.save();
        }catch (Exception e){
            e.printStackTrace();
            return OperationResult.ERROR;
        }
       return OperationResult.COMPLETE;
    }

   @Override
   public void vewFile(String fileId){
       try {
           if (fileId == null) new IllegalStateException("fileId can not be null");
           IFileWrapper fileInfo = fileInfoDao.getById(fileId);
           fileInfo.view();
       }catch (IOException e){
           new IllegalStateException(e);
       }
   }

   @Override
   public void cancelEdit(String fileId){
       try {
           if (fileId == null) new IllegalStateException("fileId can not be null");
           IFileWrapper fileInfo = fileInfoDao.getById(fileId);
           fileInfo.cancelCheckout();
       }catch (IOException e){
           new IllegalStateException(e);
       }
   }
}
