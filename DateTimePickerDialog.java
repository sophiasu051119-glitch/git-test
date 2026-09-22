import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.KeyStroke;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

/** A small, dependency-free date/time picker for the Git practice exercise. */
public final class DateTimePickerDialog extends JDialog {
    private static final long serialVersionUID = 1L;
    private static final DateTimeFormatter FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final JSpinner year;
    private final JSpinner month;
    private final JSpinner day;
    private final JSpinner hour;
    private final JSpinner minute;
    private LocalDateTime selection;

    public DateTimePickerDialog(Window owner, LocalDateTime initial) {
        super(owner, "选择日期和时间（回退演示）", ModalityType.APPLICATION_MODAL);
        if (initial.getYear() < 1 || initial.getYear() > 9999) {
            throw new IllegalArgumentException("年份必须在 1 至 9999 之间");
        }
        year = spinner(initial.getYear(), 1, 9999, "0000");
        month = spinner(initial.getMonthValue(), 1, 12, "00");
        day = spinner(initial.getDayOfMonth(), 1,
                YearMonth.from(initial).lengthOfMonth(), "00");
        hour = spinner(initial.getHour(), 0, 23, "00");
        minute = spinner(initial.getMinute(), 0, 59, "00");

        JPanel fields = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 10));
        addField(fields, year, "年");
        addField(fields, month, "月");
        addField(fields, day, "日");
        addField(fields, hour, "时");
        addField(fields, minute, "分");
        year.addChangeListener(event -> updateDayLimit());
        month.addChangeListener(event -> updateDayLimit());

        JButton confirm = new JButton("确定");
        confirm.addActionListener(event -> confirmSelection());
        JButton cancel = new JButton("取消");
        cancel.addActionListener(event -> dispose());
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttons.add(cancel);
        buttons.add(confirm);

        JPanel content = new JPanel(new BorderLayout(8, 8));
        content.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        content.add(new JLabel("请选择日期和时间（24 小时制，精确到分钟）"), BorderLayout.NORTH);
        content.add(fields, BorderLayout.CENTER);
        content.add(buttons, BorderLayout.SOUTH);
        setContentPane(content);
        getRootPane().setDefaultButton(confirm);
        getRootPane().registerKeyboardAction(event -> dispose(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(owner);
    }

    private static JSpinner spinner(int value, int min, int max, String pattern) {
        JSpinner spinner = new JSpinner(new SpinnerNumberModel(value, min, max, 1));
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(spinner, pattern);
        editor.getTextField().setColumns(pattern.length());
        spinner.setEditor(editor);
        return spinner;
    }

    private static void addField(JPanel panel, JSpinner spinner, String label) {
        panel.add(spinner);
        panel.add(new JLabel(label));
    }

    private static int number(JSpinner spinner) {
        return ((Number) spinner.getValue()).intValue();
    }

    /** Keep a selected day valid when changing the month or year. */
    private void updateDayLimit() {
        int maxDay = YearMonth.of(number(year), number(month)).lengthOfMonth();
        SpinnerNumberModel model = (SpinnerNumberModel) day.getModel();
        model.setMaximum(maxDay);
        day.setValue(clampDay(number(year), number(month), number(day)));
    }

    static int clampDay(int year, int month, int day) {
        return Math.max(1, Math.min(day, YearMonth.of(year, month).lengthOfMonth()));
    }

    static LocalDateTime createSelection(int year, int month, int day, int hour, int minute) {
        return LocalDateTime.of(year, month, day, hour, minute);
    }

    public static String format(LocalDateTime value) {
        return FORMAT.format(value);
    }

    private void confirmSelection() {
        try {
            // Commit text typed into the fields before reading spinner values.
            year.commitEdit();
            month.commitEdit();
            day.commitEdit();
            hour.commitEdit();
            minute.commitEdit();
            selection = createSelection(number(year), number(month), number(day),
                    number(hour), number(minute));
            dispose();
        } catch (java.text.ParseException | DateTimeException ex) {
            JOptionPane.showMessageDialog(this, "请输入有效的日期和时间。", "输入有误",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    /** Empty means the user cancelled or closed the dialog. Call after setVisible(true). */
    public Optional<LocalDateTime> getSelection() {
        return Optional.ofNullable(selection);
    }

    private static void selfTest() {
        check(clampDay(2024, 2, 31) == 29, "leap-year February");
        check(clampDay(2023, 2, 29) == 28, "normal-year February");
        check(clampDay(2026, 4, 31) == 30, "30-day month");
        check(clampDay(2026, 1, 15) == 15, "valid day remains unchanged");
        LocalDateTime value = createSelection(2024, 2, 29, 9, 5);
        check(format(value).equals("2024-02-29 09:05"), "date/time formatting");
        check(value.getSecond() == 0 && value.getNano() == 0, "minute precision");
        expectInvalid(2023, 2, 29, 9, 5);
        expectInvalid(2024, 2, 29, 24, 0);
        expectInvalid(2024, 2, 29, 12, 60);
        System.out.println("PASS: 9 date/time checks");
    }

    private static void expectInvalid(int year, int month, int day, int hour, int minute) {
        try {
            createSelection(year, month, day, hour, minute);
        } catch (DateTimeException expected) {
            return;
        }
        throw new AssertionError("An invalid date/time was accepted");
    }

    private static void check(boolean condition, String description) {
        if (!condition) {
            throw new AssertionError(description);
        }
    }

    public static void main(String[] args) {
        if (args.length == 1 && "--self-test".equals(args[0])) {
            selfTest();
            return;
        }
        if (GraphicsEnvironment.isHeadless()) {
            System.err.println("图形界面需要桌面环境；可使用 --self-test 运行自检。");
            System.exit(1);
        }
        SwingUtilities.invokeLater(() -> {
            DateTimePickerDialog dialog = new DateTimePickerDialog(null, LocalDateTime.now());
            dialog.setVisible(true);
            dialog.getSelection().ifPresent(value -> JOptionPane.showMessageDialog(null,
                    "你选择的日期和时间：" + format(value), "选择结果",
                    JOptionPane.INFORMATION_MESSAGE));
        });
    }
}
