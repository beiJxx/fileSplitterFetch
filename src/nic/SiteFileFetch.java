package nic;

import java.io.*;

/**
 * Created by james on 2017/2/13.
 */
public class SiteFileFetch extends Thread {


    SiteInfoBean siteInfoBean = null;//文件信息 Bean
    long[] nStartPos;//开始位置
    long[] nEndPos;//结束为止
    FileSplitterFetch[] fileSplitterFetch;//子线程对象
    long nFileLength;//文件长度
    boolean bFirst = true; //是否第一次取出
    boolean bStop = false; //停止标志
    File tmpFile;//文件下载的临时信息
    DataOutputStream output;//输出到文件的输出流
    public SiteFileFetch(SiteInfoBean bean) throws IOException{
        siteInfoBean = bean;
        tmpFile = new File(bean.getsFilePath() + File.separator + bean.getsFileName()+".info");
        if(tmpFile.exists()){
            bFirst = false;
            read_nPos();
        }else{
            nStartPos = new long[bean.getnSplitter()];
            nEndPos = new long[bean.getnSplitter()];
        }
                
    }

    @Override
    public void run() {
        //获得文件长度
        //分割文件
        //实例 FileSplitterFetch
        //启动 FileSplitterFetch线程
        //等待子线程返回
        try {
            if(bFirst){
                nFileLength = getFileSize();
                if(nFileLength == -1){
                    System.err.println("File Length is not know!");
                }else if(nFileLength == -2){
                    System.err.println("File is not access");
                }else{
                    for (int i = 0; i<nStartPos.length;i++){
                        nStartPos[i] = (long)(i*(nFileLength/nStartPos.length));
                    }
                    for (int i = 0; i<nEndPos.length-1;i++){
                        nEndPos[i] = nStartPos[i+1];
                    }
                    nEndPos[nEndPos.length-1] = nFileLength;
                }
            }
            //启动子线程
            fileSplitterFetch = new FileSplitterFetch[nStartPos.length];
            for(int i = 0; i<nStartPos.length; i++){
                fileSplitterFetch[i] = new FileSplitterFetch(siteInfoBean.getsSiteURL(),siteInfoBean.getsFilePath()+File.separator+siteInfoBean.getsFileName(),nStartPos[i],nEndPos[i],i);
            }
        }
    }

    //读取保存的下载信息（文件指针位置）
    private void read_nPos() {

        try {
            DataInputStream input = new DataInputStream(new FileInputStream(tmpFile));
            int nCount = input.readInt();
            nStartPos = new long[nCount];
            nEndPos = new long[nCount];
            for(int i = 0; i<nStartPos.length;i++){
                nStartPos[i] = input.readLong();
                nEndPos[i] = input.readLong();
            }
            input.close();
        }catch (IOException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
