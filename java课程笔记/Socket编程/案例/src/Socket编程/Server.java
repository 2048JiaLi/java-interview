package Socket编程;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

/**
 * @author Li W
 * @version 1.8
 * @date 2020/6/29 13:08
 */
public class Server {

    public static void main(String[] args) throws IOException {

        try {
            ServerSocket server = new ServerSocket(8888);

            // 接入客户端
            Socket client = server.accept();

            // 获得请求对象
            BufferedReader request = new BufferedReader(new InputStreamReader(client.getInputStream(),"UTF-8"));

            // 接收客户端请求数据
            String userInfo = request.readLine();

            // 保存用户结果
            String result = saveUserInfo(userInfo);

            // 获得响应对象
            PrintWriter response = new PrintWriter(new OutputStreamWriter(client.getOutputStream(),"UTF-8"));

            // 将操作结果响应给客户端
            response.println(result);
            response.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将用户的数据保存在服务器文件中
     * @param userInfo
     */
    public static String saveUserInfo(String userInfo) {

        try {
            // 创建流，执行一个properties文件
            PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream("src/Socket编程/UserInfo.perporties",true),"UTF-8"));

            // 解析出id的值作为key，直接将userInfo作为value
            String id = getID(userInfo);
            // System.out.println(id);

            // 保存用户信息到文件中
            Properties prop = new Properties(); // 使用Properties类
            prop.setProperty(id, userInfo);

            prop.store(out,"");

            //System.out.println("保存完毕！"); // 客户端不知道保存完毕

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Successful";
    }

    public static String getID(String userInfo) {

        int startIndex = userInfo.indexOf(":")+2;
        int endIndext = userInfo.indexOf(",");

        return userInfo.substring(startIndex,endIndext);
    }
}
