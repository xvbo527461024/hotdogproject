package cn.hot.hotdog.controller;

import cn.hot.hotdog.util.AjaxResult;
import cn.hot.hotdog.util.FastDfsApiOpr;
import org.apache.commons.io.FilenameUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/common")
public class fastdfaController {
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public AjaxResult upload(@RequestBody MultipartFile file){
        //System.out.println("multipartFile:"+file);
        if (file==null){
            return AjaxResult.me().setSuccess(true).setMsg("请重新选择文件");
        }
        //原始名字
        String originalFilename = file.getOriginalFilename();
        String extName = FilenameUtils.getExtension(originalFilename);//获取后缀名

        try {
            byte[] bytes = file.getBytes();
            String filePath = FastDfsApiOpr.upload(bytes, extName);


            return AjaxResult.me().setSuccess(true).setMsg("上传成功").setObject(filePath.substring(1) );

        } catch (IOException e) {
            e.printStackTrace();
            return AjaxResult.me().setMsg("上传失败："+e.getMessage()).setSuccess(false);
        }

    }
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public AjaxResult delete(@RequestParam("filePath") String filePath){
        System.out.println("filePath:"+filePath);
        //String filePath2 = filePath.substring(1);
        String groupName = filePath.substring(0, filePath.indexOf("/"));
        String fileName = filePath.substring(filePath.indexOf("/") + 1);
      System.out.println("groupName删除:"+groupName);
       System.out.println("fileName:"+fileName);


        try {FastDfsApiOpr.delete(groupName, fileName);

            return AjaxResult.me().setSuccess(true).setMsg("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMsg("删除失败："+e.getMessage()).setSuccess(false);
        }


    }


 /*public static void main(String[] args) {
        // 1、加载配置文件，配置文件中的内容就是 tracker 服务的地址。
        String[] strings = new String[0];
        try {
            ClientGlobal.init("C:\\Users\\DELL\\hotdog_parent\\hotdog_common_parent\\hotdog_redis_service_8003\\src\\main\\resources\\fdfs_client.conf");
            // 2、创建一个 TrackerClient 对象。直接 new 一个。
            TrackerClient trackerClient = new TrackerClient();
            // 3、使用 TrackerClient 对象创建连接，获得一个 TrackerServer 对象。
            TrackerServer trackerServer = trackerClient.getConnection();
            // 4、创建一个 StorageServer 的引用，值为 null
            StorageServer storageServer = null;
            // 5、创建一个 StorageClient 对象，需要两个参数 TrackerServer 对象、StorageServer 的引用
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
            // 6、使用 StorageClient 对象上传图片。
            //扩展名不带“.”
            strings = storageClient.upload_file("C:\\Users\\DELL\\Desktop\\桌面文件\\作业图片\\1866517_203725083948_2.jpg", "jpg",
                    null);
            // 7、返回数组。包含组名和图片的路径。
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        for (String string : strings) {
            System.out.println(string);
        }

    }
*/


 }


