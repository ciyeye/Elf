import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import model.ElfImpl;

import java.awt.*;

public class Main implements NativeKeyListener {
    public static void main(String[] args) throws AWTException {
        // 监听键盘事件
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("无法注册全局钩子: " + ex.getMessage());
            System.exit(1);
        }
        GlobalScreen.addNativeKeyListener(new ElfImpl());
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException e) {
                e.printStackTrace();
            }
        }));
    }
}