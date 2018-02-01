package com.husen.servlet;

import com.aliyun.oss.OSSClient;
import com.husen.Util.VerifyFileType;
import com.husen.oss.OssConfigure;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/*
* @author husen
* 上传文件到OSS
* */
@WebServlet("/upload.do")
public class UploadToOSSServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger(UploadToOSSServlet.class);
    private static OssConfigure ossConfigure;
    // 上传文件临时存储目录（文件大于3MB时先存到临时目录）
    private static final String UPLOAD_DIRECTORY = "upload";

    // 上传配置
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 1;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 1; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 4; // 50MB
    private static final String[] TYPES = {"jpg", "png", "gif", "tif", "bmp"};
    static {
        try {
            ossConfigure = new OssConfigure("aliyunoss.properties");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("配置文件读取失败！");
        }
    }
    /**
     * 上传数据及保存文件
     */
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        // 检测是否为多媒体上传
        if (!ServletFileUpload.isMultipartContent(request)) {
            // 如果不是则停止
            PrintWriter writer = response.getWriter();
            writer.println("Error: 表单必须包含 enctype=multipart/form-data");
            writer.flush();
            return;
        }

        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // 设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);

        // 设置最大文件上传值
        upload.setFileSizeMax(MAX_FILE_SIZE);

        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // 中文处理
        upload.setHeaderEncoding("UTF-8");

        // 构造临时路径来存储上传的文件,如果需要的话
        // 这个路径相对当前应用的目录
        String uploadPath = request.getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;

        // 如果目录不存在则创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try{
            //解析请求的内容提取文件数据
            List<FileItem> fileItems = upload.parseRequest(request);
            if(fileItems != null && fileItems.size() > 0){
                /*
                * 迭代表单数据
                * 先判断文件类型
                * */
                for (FileItem item : fileItems){
                    if(!item.isFormField()){
                        String fileName = item.getName();
                        if(fileName != null && !fileName.trim().isEmpty()){
                            /*
                            * 判断文件类型
                            * */
                            String type = VerifyFileType.getFileType(item.getInputStream());
                            if(type != null){
                                if(Arrays.binarySearch(TYPES, type) != -4){
                                    //文件类型错误
                                    request.setAttribute("msg", fileName + "文件不是图片！");
                                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                                    return;
                                }
                            }else {
                                request.setAttribute("msg", fileName + "是不支持的类型！");
                                request.getRequestDispatcher("/index.jsp").forward(request, response);
                                return;
                            }
                        }
                    }
                }
                //用一个list来存上传文件的访问地址
                //上传到阿里云的OSS
                List<String> list = new ArrayList<>();
                OSSClient ossClient = new OSSClient(ossConfigure.getEndpoint(), ossConfigure.getAccessKeyId(), ossConfigure.getAccessKeySecret());
                for (FileItem item : fileItems){
                    //处理不在表单中的字段
                    if(!item.isFormField()){
                        String fileName = item.getName();
                        if(fileName != null && !fileName.trim().isEmpty()){
                            String newFileName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
                            logger.info(newFileName);
                            String filePath = LocalDateTime.now().getMonth().toString() + "/" + newFileName;
                            logger.info(filePath);
                            /*
                            * OSS上的访问路径
                            * */
                            String url = "http://" + ossConfigure.getBucketName() + "." + ossConfigure.getEndpoint() + "/" + filePath;
                            list.add(url);
                            ossClient.putObject(ossConfigure.getBucketName(), filePath, item.getInputStream());
                        }else {
                            request.setAttribute("msg", "上传的文件有为空的！");
                            request.getRequestDispatcher("/index.jsp").forward(request, response);
                        }
                    }
                }
                ossClient.shutdown();
                request.setAttribute("img", list);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }catch(FileUploadBase.FileSizeLimitExceededException e) {
            request.setAttribute("msg", "单个文件上传大小不能超过40M！");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }catch(FileUploadBase.SizeLimitExceededException e){
            request.setAttribute("msg", "总表单大小不能超过50M！");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }catch (FileUploadException e) {
            request.setAttribute("msg", "解析上传内容失败，请重新试一下！");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}