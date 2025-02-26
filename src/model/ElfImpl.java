package model;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import method.Elf;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * 被触发的函数声明，二次封装
 */
public class ElfImpl implements NativeKeyListener {
    private Elf elf;
    private int triggerNum = 43;    // 触发键位，默认反斜杠

    public ElfImpl() throws AWTException {
        this.elf = new Elf();
    }

    // 监听函数
    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        // 监听函数1
        if (e.getKeyCode() == triggerNum) {
            System.out.println("反斜杠按下，run...");
            test();
        }
    }

    // 测试触发函数
    public void test() {
        elf.print("你好");
    }

    // 传奇取出
    public void legendPop() throws IOException, UnsupportedFlavorException {
        // 初始鼠标位置
        Point position = MouseInfo.getPointerInfo().getLocation();
        int x = position.x;
        int y = position.y;
        int delay = 150;

        elf.ctrlClick(Elf.C);
        // 传奇1仓库标签位置
        elf.mouseMove(976, 598);
        elf.click(Elf.LEFT_BUTTON);
        elf.mouseMove(x, y);
        elf.ctrlShiftClick(Elf.LEFT_BUTTON);

        // 剪贴板字符串替换
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        // 获取剪贴板中的文本内容
        String clipboardText = (String) clipboard.getData(DataFlavor.stringFlavor);
        if (clipboardText != null) {
            // 按行分割文本内容
            String[] lines = clipboardText.split("\\R"); // "\\R" 匹配任何Unicode换行符
            if (lines.length >= 3) {
                String thirdLine = lines[2]; // 第三行（索引从0开始）
                StringSelection selection = new StringSelection(thirdLine);
                clipboard.setContents(selection, null);
            } else {
                System.out.println("剪贴板内容不足三行。");
            }
        } else {
            System.out.println("剪贴板中没有文本数据。");
        }

        // 搜索栏输入
        elf.mouseMove(682, 1197);
        elf.click(Elf.LEFT_BUTTON);
        elf.ctrlClick(Elf.A);
        elf.ctrlClick(Elf.V);

        // 首位装备位置取出
        elf.mouseMove(161, 520);
        elf.ctrlClick(Elf.LEFT_BUTTON);

        // 重复，传奇2仓库取出
        elf.mouseMove(976, 629);
        elf.click(Elf.LEFT_BUTTON);
        elf.mouseMove(x, y);
        elf.ctrlShiftClick(Elf.LEFT_BUTTON);
        elf.mouseMove(161, 520);
        elf.ctrlClick(Elf.LEFT_BUTTON);

        // 鼠标回到原位
        elf.mouseMove(x, y);
    }
}