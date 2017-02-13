package nic;

/**
 * Created by james on 2017/2/13.
 */
public class SiteInfoBean {

    private String sSiteURL;
    private String sFilePath;
    private String sFileName;
    private int nSplitter;

    public SiteInfoBean(){
        this("","","",5);
    }

    public SiteInfoBean(String sURL,String sPath,String sName,int nSpiltter)
    {
        sSiteURL= sURL;
        sFilePath = sPath;
        sFileName = sName;
        this.nSplitter = nSpiltter;
    }

    public String getsSiteURL() {
        return sSiteURL;
    }

    public void setsSiteURL(String sSiteURL) {
        this.sSiteURL = sSiteURL;
    }

    public String getsFilePath() {
        return sFilePath;
    }

    public void setsFilePath(String sFilePath) {
        this.sFilePath = sFilePath;
    }

    public String getsFileName() {
        return sFileName;
    }

    public void setsFileName(String sFileName) {
        this.sFileName = sFileName;
    }

    public int getnSplitter() {
        return nSplitter;
    }

    public void setnSplitter(int nSplitter) {
        this.nSplitter = nSplitter;
    }
}
