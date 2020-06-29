package Socket编程;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Li W
 * @version 1.8
 * @date 2020/6/29 14:13
 */
public class LoadPerporties {

    public static void main(String[] args) throws IOException {

        Properties prop = new Properties();

        InputStream is = new FileInputStream("src/Socket编程/UserInfo.perporties");

        // 加载
        prop.load(is);

        for (Object obj : prop.entrySet()) {
            System.out.println(obj.toString());
        }
    }
}
