package method;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * 重载robot，重载基础函数
 * click(code)：键鼠单击
 * nest(preCode, postCode)：嵌套按键，preCode只能是键盘码
 * mouseMove(x, y)：移动鼠标并添加固定延迟
 * ctrlClick(code)：ctrl+code嵌套
 * shiftClick(code)：shift+code嵌套
 * ctrlShiftClick(code)：ctrl+shift+code嵌套
 * print(str)：模拟输出字符串
 */
public class Elf extends Robot {
    private static final int DELAY = 100;
    public static final int A = KeyEvent.VK_A;
    public static final int B = KeyEvent.VK_B;
    public static final int C = KeyEvent.VK_C;
    public static final int D = KeyEvent.VK_D;
    public static final int E = KeyEvent.VK_E;
    public static final int F = KeyEvent.VK_F;
    public static final int G = KeyEvent.VK_G;
    public static final int H = KeyEvent.VK_H;
    public static final int I = KeyEvent.VK_I;
    public static final int J = KeyEvent.VK_J;
    public static final int K = KeyEvent.VK_K;
    public static final int L = KeyEvent.VK_L;
    public static final int M = KeyEvent.VK_M;
    public static final int N = KeyEvent.VK_N;
    public static final int O = KeyEvent.VK_O;
    public static final int P = KeyEvent.VK_P;
    public static final int Q = KeyEvent.VK_Q;
    public static final int R = KeyEvent.VK_R;
    public static final int S = KeyEvent.VK_S;
    public static final int T = KeyEvent.VK_T;
    public static final int U = KeyEvent.VK_U;
    public static final int V = KeyEvent.VK_V;
    public static final int W = KeyEvent.VK_W;
    public static final int X = KeyEvent.VK_X;
    public static final int Y = KeyEvent.VK_Y;
    public static final int Z = KeyEvent.VK_Z;
    public static final int _0 = KeyEvent.VK_0;
    public static final int _1 = KeyEvent.VK_1;
    public static final int _2 = KeyEvent.VK_2;
    public static final int _3 = KeyEvent.VK_3;
    public static final int _4 = KeyEvent.VK_4;
    public static final int _5 = KeyEvent.VK_5;
    public static final int _6 = KeyEvent.VK_6;
    public static final int _7 = KeyEvent.VK_7;
    public static final int _8 = KeyEvent.VK_8;
    public static final int _9 = KeyEvent.VK_9;
    public static final int CONTROL = KeyEvent.VK_CONTROL;
    public static final int SHIFT = KeyEvent.VK_SHIFT;
    public static final int ALT = KeyEvent.VK_ALT;
    public static final int TAB = KeyEvent.VK_TAB;
    public static final int ENTER = KeyEvent.VK_ENTER;
    public static final int LEFT_BUTTON = InputEvent.BUTTON1_DOWN_MASK;
    public static final int RIGHT_BUTTON = InputEvent.BUTTON2_DOWN_MASK;
    public static final int MIDDLE_BUTTON = InputEvent.BUTTON3_DOWN_MASK;

    public Elf() throws AWTException {
    }

    // 鼠标/键盘单击，并延迟
    public void click(int code) {
        // 鼠标左键、鼠标右键、鼠标中键
        if ((code == 1024)||(code==2048)||(code==4096)) {
            mousePress(code);
            System.out.println("mouse pressed: " + code);
            mouseRelease(code);
            System.out.println("mouse released: " + code);
        } else {
            keyPress(code);
            System.out.println("key pressed: " + code);
            keyRelease(code);
            System.out.println("key released: " + code);
        }
    }

    // 嵌套函数，preCode只能是键盘操作
    // 按下preCode，单击postCode，松开preCode
    public void nest(int preCode, int postCode) {
        keyPress(preCode);
        System.out.println("key pressed: " + preCode);
        delay(DELAY);
        click(postCode);
        delay(DELAY);
        keyRelease(preCode);
        System.out.println("key released: " + preCode);
        delay(DELAY);
    }

    // 组合键ctrl单击，并添加延迟
    public void ctrlClick(int code) {
        nest(CONTROL, code);
    }

    // 组合键shift单击，并添加延迟
    public void shiftClick(int code) {
        nest(SHIFT, code);
    }

    // 组合键shift+ctrl单击，并添加延迟
    public void ctrlShiftClick(int code) {
        keyPress(SHIFT);
        keyPress(CONTROL);
        click(code);
        keyRelease(CONTROL);
        keyRelease(SHIFT);
        delay(DELAY);
    }

    @Override
    public void mouseMove(int x, int y) {
        super.mouseMove(x, y);
        delay(DELAY);
    }

    // 模拟输出字符串，具体原理为剪贴板复制粘贴
    public void print(String str) {
        // 将字符串复制到剪贴板
        StringSelection stringSelection = new StringSelection(str);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

        // 模拟 Ctrl+V 粘贴
        ctrlClick(V);

        System.out.println("连续输出字符串: " + str);
    }
}
