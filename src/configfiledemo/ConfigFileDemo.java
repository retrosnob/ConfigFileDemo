package configfiledemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigFileDemo {
    public static void main(String[] args) {
        System.out.println(Config.get("parameter1"));
        System.out.println(Config.get("parameter2"));
    }
}

class Config {
    private static final java.util.Map<String, String> map = new java.util.HashMap<>();
    private static final Config instance = new Config();
    
    private Config() {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("config.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        try {
            String line = reader.readLine();
            while (line != null) {
                // System.out.println(line);
                String[] keyval = line.split("=");
                instance.map.put(keyval[0], keyval[1]);
                line = reader.readLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(ConfigFileDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    static String get(String key) {
        return instance.map.get(key);
    }
}
