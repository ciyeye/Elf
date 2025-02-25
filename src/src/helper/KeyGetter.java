package src.helper;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 获取键盘码，辅助程序
 */
public class KeyGetter implements NativeKeyListener {
    // 创建一个 JFrame 窗口
    private JFrame frame;
    // 创建一个 JTextArea 用于显示按键信息
    private JTextArea textArea;

    public KeyGetter() {
        // 初始化窗口
        initGUI();
    }

    private void initGUI() {
        frame = new JFrame("键盘监听器");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null); // 窗口居中

        textArea = new JTextArea();
        textArea.setEditable(false); // 不可编辑
        JScrollPane scrollPane = new JScrollPane(textArea); // 添加滚动条

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        updateTextArea("Key Pressed: " + getKeyText(e.getKeyCode()) + " | KeyCode: " + e.getKeyCode() +
                " | Time: " + getFormattedTime());
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
        // 可选：处理键入事件
    }

    // 更新文本区域的内容
    private void updateTextArea(String message) {
        SwingUtilities.invokeLater(() -> textArea.append(message + "\n"));
    }

    // 获取按键的文本描述
    private String getKeyText(int keyCode) {
        return NativeKeyEvent.getKeyText(keyCode);
    }

    // 获取格式化的当前时间
    private String getFormattedTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
        return sdf.format(new Date());
    }

    public static void main(String[] args) {
        // 创建监听器实例
        KeyGetter listener = new KeyGetter();

        try {
            // 注册全局钩子
            GlobalScreen.registerNativeHook();
            System.out.println("全局键盘监听器已启动。按下 ESC 键退出程序。");
        } catch (NativeHookException ex) {
            System.err.println("无法注册全局钩子: " + ex.getMessage());
            System.exit(1);
        }

        // 添加监听器到全局屏幕
        GlobalScreen.addNativeKeyListener(listener);

        // 添加一个钩子移除的监听器，以便在程序退出时注销钩子
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                GlobalScreen.unregisterNativeHook();
                System.out.println("全局键盘监听器已注销。");
            } catch (NativeHookException e) {
                e.printStackTrace();
            }
        }));
    }
}