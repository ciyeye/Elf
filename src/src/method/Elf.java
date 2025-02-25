package src.method;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLOutput;

/**
 * 重载robot，重载基础函数
 * click(code)：键鼠单击
 * nest(preCode, postCode)：嵌套按键，preCode只能是键盘码
 * mouseMove(x, y)：移动鼠标并添加固定延迟
 * ctrlClick(code)：ctrl+code嵌套
 * shiftClick(code)：shift+code嵌套
 * ctrlShiftClick(code)：ctrl+shift+code嵌套
 */
public class Elf extends Robot {
    private static final int DELAY = 100;
    public static final int E_A = KeyEvent.VK_A;
    public static final int E_B = KeyEvent.VK_B;
    public static final int E_C = KeyEvent.VK_C;
    public static final int E_D = KeyEvent.VK_D;
    public static final int E_E = KeyEvent.VK_E;
    public static final int E_F = KeyEvent.VK_F;
    public static final int E_G = KeyEvent.VK_G;
    public static final int E_H = KeyEvent.VK_H;
    public static final int E_I = KeyEvent.VK_I;
    public static final int E_J = KeyEvent.VK_J;
    public static final int E_K = KeyEvent.VK_K;
    public static final int E_L = KeyEvent.VK_L;
    public static final int E_M = KeyEvent.VK_M;
    public static final int E_N = KeyEvent.VK_N;
    public static final int E_O = KeyEvent.VK_O;
    public static final int E_P = KeyEvent.VK_P;
    public static final int E_Q = KeyEvent.VK_Q;
    public static final int E_R = KeyEvent.VK_R;
    public static final int E_S = KeyEvent.VK_S;
    public static final int E_T = KeyEvent.VK_T;
    public static final int E_U = KeyEvent.VK_U;
    public static final int E_V = KeyEvent.VK_V;
    public static final int E_W = KeyEvent.VK_W;
    public static final int E_X = KeyEvent.VK_X;
    public static final int E_Y = KeyEvent.VK_Y;
    public static final int E_Z = KeyEvent.VK_Z;
    public static final int E_0 = KeyEvent.VK_0;
    public static final int E_1 = KeyEvent.VK_1;
    public static final int E_2 = KeyEvent.VK_2;
    public static final int E_3 = KeyEvent.VK_3;
    public static final int E_4 = KeyEvent.VK_4;
    public static final int E_5 = KeyEvent.VK_5;
    public static final int E_6 = KeyEvent.VK_6;
    public static final int E_7 = KeyEvent.VK_7;
    public static final int E_8 = KeyEvent.VK_8;
    public static final int E_9 = KeyEvent.VK_9;
    public static final int E_ctrl = KeyEvent.VK_CONTROL;
    public static final int E_shift = KeyEvent.VK_SHIFT;
    public static final int E_ALT = KeyEvent.VK_ALT;
    public static final int E_TAB = KeyEvent.VK_TAB;
    public static final int E_ENTER = KeyEvent.VK_ENTER;
    public static final int E_LEFT_BUTTON = InputEvent.BUTTON1_DOWN_MASK;
    public static final int E_RIGHT_BUTTON = InputEvent.BUTTON2_DOWN_MASK;
    public static final int E_MIDDLE_BUTTON = InputEvent.BUTTON3_DOWN_MASK;

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
        nest(E_ctrl, code);
    }

    // 组合键shift单击，并添加延迟
    public void shiftClick(int code) {
        nest(E_shift, code);
    }

    // 组合键shift+ctrl单击，并添加延迟
    public void ctrlShiftClick(int code) {
        keyPress(KeyEvent.VK_SHIFT);
        keyPress(KeyEvent.VK_CONTROL);
        click(code);
        keyRelease(KeyEvent.VK_CONTROL);
        keyRelease(KeyEvent.VK_SHIFT);
        delay(DELAY);
    }

    @Override
    public void mouseMove(int x, int y) {
        super.mouseMove(x, y);
        delay(DELAY);
    }
}
