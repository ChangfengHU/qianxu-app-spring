package com.qianxu.controller;

import com.qianxu.pojo.Author;
import com.qianxu.pojo.Book;
import com.qianxu.pojo.Users;
import com.qianxu.pojo.bo.UsersBO;
import com.qianxu.pojo.vo.UsersVO;
import com.qianxu.service.UserService;
import com.qianxu.utils.*;
import com.qianxu.utils.dictionary.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by sang on 2018/7/4.
 */
@RestController
@RequestMapping("u")
public class HelloController {

//    @Autowired
//    private FastDFSClient fastDFSClient;
    @Autowired
    private UserService userService;

    @GetMapping("/book1")
    public void hello(Model model) {
        Map<String, Object> map = model.asMap();
        Set<String> strings = map.keySet();
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Object value = map.get(key);
            System.out.println(key+"...."+value);
        }
    }

    @GetMapping("/init")
    @ResponseBody
    public String book(@ModelAttribute("b") Book book, @ModelAttribute("a") Author author) {
        return book.toString() + ">>>" + author.toString();
    }
    /**
     * @Description: �û�ע��/��¼
     */
    @RequestMapping("/registOrLogin")
    public IMoocJSONResult registOrLogin( Users user) throws Exception {
        System.out.println("�����11"+user.getUsername()+"::"+user.getPassword());
        // 0. �ж��û��������벻��Ϊ��
        if (StringUtils.isBlank(user.getUsername())
                || StringUtils.isBlank(user.getPassword())) {
            return IMoocJSONResult.errorMsg("user or password is empty...");
        }

        // 1. �ж��û����Ƿ���ڣ�������ھ͵�¼�������������ע��
        boolean usernameIsExist = userService.queryUsernameIsExist(user.getUsername());
        Users userResult = null;
        if (usernameIsExist) {
            // 1.1 ��¼
            userResult = userService.queryUserForLogin(user.getUsername(),
                    MD5Utils.getMD5Str(user.getPassword()));
            if (userResult == null) {
                return IMoocJSONResult.errorMsg("user or password is error...");
            }
        } else {
            // 1.2 ע��
            user.setNickname(user.getUsername());
            user.setFaceImage("");
            user.setFaceImageBig("");
            user.setPassword(MD5Utils.getMD5Str(user.getPassword()));

            userResult = userService.saveUser(user);
        }

        UsersVO userVO = new UsersVO();
        BeanUtils.copyProperties(userResult, userVO);

        return IMoocJSONResult.ok(userVO);
    }

    /**
     * @Description: �ϴ��û�ͷ��
     */
    @RequestMapping("/uploadFaceBase64")
    public IMoocJSONResult uploadFaceBase64(@RequestBody UsersBO userBO) throws Exception {
        System.out.println("�����"+userBO.getUserId()+"::"+userBO.getFaceData());
        // ��ȡǰ�˴�������base64�ַ���, Ȼ��ת��Ϊ�ļ��������ϴ�
        String base64Data = userBO.getFaceData();
        String userFacePath = "C:\\" + userBO.getUserId() + "userface64.png";
        FileUtils.base64ToFile(userFacePath, base64Data);

        // �ϴ��ļ���fastdfs
        MultipartFile faceFile = FileUtils.fileToMultipart(userFacePath);
        System.err.println(faceFile.getBytes().length);
//        String url = fastDFSClient.uploadBase64(faceFile);
        System.err.println(faceFile.getBytes().length);
        System.err.println(faceFile.getOriginalFilename());
        String url="111";
//		"dhawuidhwaiuh3u89u98432.png"
//		"dhawuidhwaiuh3u89u98432_80x80.png"
        // ���ļ��ϴ����ֲ�ʽ�ļ�ϵͳ���������ļ��Ĵ洢·��������
        String uploadFile = FastDFSTool.uploadFile(faceFile.getBytes(),
                userFacePath);
        // ����json��ʽ���ַ�����ͼƬ�ľ��������ŵ�ַ��
//        HashMap<String, String> hashMap = new HashMap<String, String>();
//        hashMap.put("path", Constants.FDFS_SERVER + uploadFile);
        String imagePath=Constants.FDFS_SERVER + uploadFile;
//        // ��ȡ����ͼ��url
//        String thump = "_80x80.";
//        String arr[] = uploadFile.split("\\.");
//        String thumpImgUrl = arr[0] + thump + arr[1];
//        System.err.println(thumpImgUrl);
        // ��ϸ�û�ͷ��
        Users user = new Users();
        user.setId(userBO.getUserId());
        user.setFaceImage(imagePath);
        user.setFaceImageBig(imagePath);

        Users result = userService.updateUserInfo(user);

        return IMoocJSONResult.ok(result);
    }


}
