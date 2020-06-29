package Socket编程;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Li W
 * @version 1.8
 * @date 2020/6/29 13:09
 *
 * 客户端
 * 完成注册与登录功能
 */
public class Client {

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        Socket client = null;

        try {
            client = new Socket("172.23.6.87",8888);

            // 采集用户信息
            String userInfo = registMenu();

            // 向服务器发送请求数据（整理完毕的用户信息字符串）
            //System.out.println(userInfo);

            // 获得请求对象
            PrintWriter request = new PrintWriter(new OutputStreamWriter(client.getOutputStream(),"UTF-8"));

            // 向服务器发送请求数据
            request.println(userInfo);
            request.flush();

            // 接收服务器响应结果
            BufferedReader response = new BufferedReader(new InputStreamReader(client.getInputStream(),"UTF-8"));
            String result = response.readLine();

            System.out.println(result);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 收集用户信息
     * @return 整理后的字符串
     */

    public static String registMenu() {

        System.out.print("请输入学号：");
        String id = input.next();

        System.out.print("请输入密码：");
        String pwd = input.next();

        System.out.print("请输入姓名：");
        String name = input.next();

        System.out.print("请输入年龄：");
        Integer age = input.nextInt();

        System.out.print("请输入分数：");
        Double score = input.nextDouble();

        // 整理并返回 （约定 ---> 协议）
        return toJSON(id,pwd,name,age,score);
    }

    /**
     * 将零散数据整合成一个有格式的字符串
     *
     * @param id
     * @param pwd
     * @param name
     * @param age
     * @param score
     * @return
     */
    public static String toJSON(String id, String pwd, String name, Integer age, Double score) {
        String json = "{id : "+id+", pwd : "+pwd+", name : "+name+", age : "+age+", score : "+score+"}";
        return json;
    }
}
