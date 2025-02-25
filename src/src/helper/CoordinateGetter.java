package src.helper;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseInputListener;

import javax.swing.*;
import java.awt.*;

/**
 * 获取鼠标坐标，辅助函数
 */
public class CoordinateGetter implements NativeMouseInputListener {

    private JLabel coordinatesLabel;
    private Integer lastX; // 用于保存最后一次的X坐标
    private Integer lastY; // 用于保存最后一次的Y坐标

    public CoordinateGetter(JLabel label) {
        this.coordinatesLabel = label;
    }

    @Override
    public void nativeMouseClicked(NativeMouseEvent e) {
        if (e.getButton() == NativeMouseEvent.BUTTON3) { // 中键点击
            lastX = e.getX();
            lastY = e.getY();

            // 更新界面显示
            SwingUtilities.invokeLater(() ->
                    coordinatesLabel.setText("全局中键点击坐标: (" + lastX + ", " + lastY + ")"));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("全局鼠标信息获取器");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 200);
            frame.setLocationRelativeTo(null);

            // 创建组件
            JLabel coordinatesLabel = new JLabel("等待中键点击...", SwingConstants.CENTER);
            coordinatesLabel.setFont(new Font("Serif", Font.BOLD, 16));

            JTextField textField = new JTextField(20);
            JButton outputButton = new JButton("输出信息");

            // 布局管理
            frame.setLayout(new BorderLayout());
            frame.add(coordinatesLabel, BorderLayout.NORTH);

            JPanel bottomPanel = new JPanel(new FlowLayout());
            bottomPanel.add(textField);
            bottomPanel.add(outputButton);
            frame.add(bottomPanel, BorderLayout.SOUTH);

            // 注册全局监听器
            CoordinateGetter getter = new CoordinateGetter(coordinatesLabel);
            try {
                GlobalScreen.registerNativeHook();
                GlobalScreen.addNativeMouseListener(getter);
            } catch (NativeHookException ex) {
                System.err.println("无法注册全局钩子: " + ex.getMessage());
                System.exit(1);
            }

            // 添加按钮事件监听器
            outputButton.addActionListener(e -> {
                // 获取输入文本
                String inputText = textField.getText();

                // 输出上次坐标（如果有的话）
                if (getter.lastX != null && getter.lastY != null) {
                    System.out.println("// " + inputText + "： " + getter.lastX + ", " + getter.lastY);
                    System.out.println("mouseMove(" + getter.lastX + ", " + getter.lastY + ");");
                } else {
                    System.out.println("尚未检测到中键点击");
                }
            });

            // 设置窗口始终置顶
            frame.setAlwaysOnTop(true); // 新增功能：窗口始终保持在最前面

            frame.setVisible(true);
        });
    }
}