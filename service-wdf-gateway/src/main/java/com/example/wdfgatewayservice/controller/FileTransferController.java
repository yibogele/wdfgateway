package com.example.wdfgatewayservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: Will Fan
 * Description:
 * Date: Created in 14:16 2018/8/17
 * Modified By:
 */
//@RestController
//@RequestMapping("/file0")
public class FileTransferController {

//    @RequestMapping(value = "/hi")
    public void handle(){

    }
    // servlet实现上传(注意需要commons-fileupload jar包)文件:
    // 1. controler方法如下:
    //
    // //原始的文件httpservlet上传

//    @RequestMapping("downfile3")
//    public String downLoadFile(Model model, HttpServletResponse response,
//                               String descFile) throws IOException {
//        File file = new File("f://web//" + descFile);
//        if (descFile == null || !file.exists()) {
//            model.addAttribute("msg", "亲,您要下载的文件" + descFile + "不存在");
//            return "load";
//        }
//        System.out.println(descFile);
//        try {
//            response.reset();
//            //设置ContentType
//            response.setContentType("application/octet-stream; charset=utf-8");
//            //处理中文文件名中文乱码问题
//            String fileName = new String(file.getName().getBytes("utf-8"), "ISO-8859-1");
//            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
//            IOUtils.copy(new FileInputStream(file), response.getOutputStream());
//            return null;
//        } catch (Exception e) {
//            model.addAttribute("msg", "下载失败");
//            return "load";
//        }
//    }
    //第二种方式springmvc官方推荐,做了一定的封装
    //controler方法代码如下:
    //
    //    }    //springmvc中支持的下载

//    @RequestMapping("/downfile2")
//    public ResponseEntity<byte[]> download() throws IOException {
//        File file = new File("c://ajaxutils.txt");
//        //处理显示中文文件名的问题
//        String fileName = new String(file.getName().getBytes("utf-8"), "ISO-8859-1");
//        //设置请求头内容,告诉浏览器代开下载窗口
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentDispositionFormData("attachment", fileName);
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
//                headers, HttpStatus.CREATED);
//    }

    //最后再说点题外话,springmvc文档中提到了这个方法:
    //这个方法会直接以流的形式向页面输出数据,可以用来处理文档,和验证码问题
    //
    ////直接返回输出流,因为没有设置请求头所以不会显示

//    @RequestMapping("/downfile")
//    public StreamingResponseBody handle2() {
//        return new StreamingResponseBody() {
//            @Override
//            public void writeTo(OutputStream outputStream) throws IOException {
//                HttpHeaders headers = new HttpHeaders();
//                FileUtils.copyFile(new File("c://磊哥.png"), outputStream);
//            }
//        };
//    }
}
