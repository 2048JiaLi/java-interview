## 基于TCP的网络编程

- Socket编程
  - Socket（套接字）是网络中的一个通信节点
  - 分为客户端Socket与服务器ServerSocket
  - 通信要求：IP地址 + 端口号

## 开发步骤

- 建立通信连接（会话）：
  - 创建ServerSocket，指定端口号
  - 调用accept等待客户端接入

- 客户端请求服务器
  - 创建Socket，指定服务器IP + 端口号
  - 使用**输出流**，**发送请求数据**给服务器
  - 使用**输入流**，**接收响应数据**到客户端（等待）

- 服务器响应客户端
  - 使用**输入流**，**接收请求数据**到服务器（等待）
  - 使用**输出流**，**发送响应数据**给客户端

```java
public class MyServer {

    public static void main(String[] args) {
        ServerSocket server = new ServerSocket(777);

        // 接入客户端
        Socket currentClient = server.accept();
        
        // 读数据（接收请求）
        InputStreamReader isr = new InputStreamReader(currentClient.getInputStream(), "UTF-8");
        
        BufferedReader reader = new BufferedReader(isr);

        String s = reader.readLine();
        s = s.toUpperCase();  // 代表一次数据处理

        // 写数据（返回响应）

        PrintWriter out = new PrintWriter (new OutputStreamWriter( currentClient.getOutputStream() , "UTF-8") );
        
        out.println(s);
        out.flush();

        currentClient.close();
    }
}


public class MyClient {

    public static void main(String[] args) throws IOException {

        Socket client = new Socket("127.0.0.1", 777);

        // 写数据（发送请求）
        OutputStream os = client.getOutputStream();

        PrintWriter osw = new PrintWriter(os, "UTF-8");

        osw.println("Hello World");
        osw.flush();

        // 读数据（返回响应）

        BufferedReader reader = new BufferedReader(new InputStreamReader (client.getInputStream(), "UTF-8") );

        String s = reader.readLine();

        // 处理数据
        System.out.println(s);

        client.close();
    }
}

```