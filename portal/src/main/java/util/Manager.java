package util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import java.io.File;

public class Manager {
    private static final String endpoint = "http://oss-cn-beijing.aliyuncs.com/";
    private static final String accessKeyId = "LTAI4FfeJ3GN9A9w1bPNLjJ4";
    private static final String accessKeySecret = "RbTkRL440wFkFIk55v9h20ojd2B4BQ";
    private static final String bucketName = "cloudparkin";

    public static boolean upload(String path, File file) {
        OSS client = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            client.putObject(bucketName, path, file);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            client.shutdown();
        }
    }

    public static void main(String[] args) {
        upload("/excel/1.excel", new File("/Users/ke/Downloads/gongdan.xls"));
    }
}
