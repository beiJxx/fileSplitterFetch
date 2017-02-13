package nic;

/**
 * Created by james on 2017/2/13.
 */
public class FileSplitterFetch extends Thread{


    String sURL;//File URL
    long nStartPos;//File Snippet Start Position
    long nEndPos;//File Snippet End Position
    int nThreadID;//Thread's ID
    boolean bDownOver = false;//Downing is over
    boolean bStop = false;//stop identical
    FileAccessI fileAccessI = null;//file Access interface




}
