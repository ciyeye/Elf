package src.model;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import src.method.ElfImpl;

import java.awt.*;

/**
 * 触发函数和触发按键设置
 */
public class Trigger implements NativeKeyListener {
    private ElfImpl elfImpl;
    private int triggerNum = 43; // 默认触发键为反斜杠

    public Trigger() throws AWTException {
        elfImpl = new ElfImpl();
    }

    // 触发函数
    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        // 监听函数1
        if (e.getKeyCode() == triggerNum) {
            System.out.println("反斜杠被按下，开始模拟输入...");
            elfImpl.test();
        }
    }
}