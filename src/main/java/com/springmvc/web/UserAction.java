package com.springmvc.web;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzq on 2017/11/3.
 */
@Controller
@RequestMapping("/userAction")
public class UserAction {

    //上传
    @RequestMapping("/uploadFile")
    public String uploadFile(MultipartFile ufile, String uname, Model model, HttpSession session) throws IOException {
        System.out.print("用户名："+uname);
        if(!ufile.isEmpty()){
            //获取服务器路径
            String basePath=session.getServletContext().getRealPath("/images");
            String realName=ufile.getOriginalFilename();//获取真实的名字
            System.out.print("要上传的文件名："+realName);
             //执行上传;
            ufile.transferTo(new File(basePath+"/"+realName));
            System.out.print("上传路径:"+basePath+"/"+realName);
            model.addAttribute("filename",realName);

        }

        return "success";
    }

    //上传多个;
    @RequestMapping("/downloadFile2")
    public String uploadFile(MultipartFile[] ufile,String uname,Model model,HttpSession session){
        System.out.print("userName:"+uname);
         if(ufile!=null&&ufile.length>0){
              System.out.print("要上传的文件格个数:"+ufile.length);
              //获取服务器路径
             String basePath=session.getServletContext().getRealPath("/images");
             List list=new ArrayList();
             for(MultipartFile file:ufile){
                 System.out.print("用户名:"+uname);
                 //获取文件名
                 String realName=file.getOriginalFilename();
                   //上传文件
                 try {
                      file.transferTo(new File(basePath+"/"+realName));
                      list.add(realName);
                 }catch (Exception e){
                     e.printStackTrace();
                     //去到错误页面
                     return "error";
                 }

             }
             model.addAttribute("fileName",list);
         }
        return "success";
    }

    //下载
    @RequestMapping("/downloadFile")
    public ResponseEntity<byte[]> downloadFile(String fileName,HttpSession session)throws Exception{
      if(fileName!=null){
          //获取路径
          String basePath=session.getServletContext().getRealPath("/images");
          String filePath=basePath+"/"+fileName;
          File outFile=new File(filePath);
          if(outFile.exists()){
              //构建一个头文件对象
              HttpHeaders headers=new HttpHeaders();
              //设置文件以下载方式代开
              headers.setContentDispositionFormData("attachment",new String(fileName.getBytes("UTF-8"),"iso-8859-1"));
              //设置文件类型
              headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
              return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(new File(filePath)),headers, HttpStatus.OK);

          }
      }
     return null;

    }





}
