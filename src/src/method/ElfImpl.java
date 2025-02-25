package src.method;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * 被触发的函数声明
 */
public class ElfImpl {
    private Elf elf;

    public ElfImpl() throws AWTException {
        this.elf = new Elf();
    }

    // 传奇取出
    public void legendPop() {
        // 初始鼠标位置
        Point position = MouseInfo.getPointerInfo().getLocation();
        int x = position.x;
        int y = position.y;
        int delay = 150;

        elf.ctrlClick(Elf.E_C);
        // 传奇1仓库标签位置
        elf.mouseMove(976, 598);
        elf.click(Elf.E_LEFT_BUTTON);
        elf.mouseMove(x, y);
        elf.ctrlShiftClick(Elf.E_LEFT_BUTTON);

        // 搜索栏输入
        clipboardLegendChange();
        elf.mouseMove(682, 1197);
        elf.click(Elf.E_LEFT_BUTTON);
        elf.ctrlClick(Elf.E_A);
        elf.ctrlClick(Elf.E_V);

        // 首位装备位置取出
        elf.mouseMove(161, 520);
        elf.ctrlClick(Elf.E_LEFT_BUTTON);

        // 重复，传奇2仓库取出
        elf.mouseMove(976, 629);
        elf.click(Elf.E_LEFT_BUTTON);
        elf.mouseMove(x, y);
        elf.ctrlShiftClick(Elf.E_LEFT_BUTTON);
        elf.mouseMove(161, 520);
        elf.ctrlClick(Elf.E_LEFT_BUTTON);

        // 鼠标回到原位
        elf.mouseMove(x, y);
    }

    // 根据ctrl+c获取第三行装备名称，并将剪贴板内容替换为装备名称
    private void clipboardLegendChange() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        try {
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
        } catch (UnsupportedFlavorException | IOException e) {
            e.printStackTrace();
        }
    }

    // 测试函数
    public void test() {
        elf.nest(Elf.E_ALT, Elf.E_TAB);
    }
}
