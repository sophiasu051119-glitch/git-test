# git-test

基于 Git 的协同开发课堂练习，使用 Codex 完成，无需安装 Trae。

项目根目录的 `DateTimePickerDialog.java` 是一个独立的 Java 日期时间选择器。默认显示当前时间，支持年、月、日、时、分选择，自动处理闰年和每月天数；点击“确定”显示所选时间，点击“取消”、关闭窗口或按 Esc 退出。

## 运行

需要 JDK 8 或更新版本。图形界面需要桌面环境，无第三方依赖。

```sh
javac -encoding UTF-8 DateTimePickerDialog.java
java DateTimePickerDialog
```

运行无需图形界面的自检，验证闰年、月份天数、时间格式和非法日期时间：

```sh
java -Djava.awt.headless=true DateTimePickerDialog --self-test
```

预期输出：`PASS: 9 date/time checks`。

## 练习内容

在 GitHub 创建 `git-test` 仓库，克隆到本地；添加 Java 文件，完成 `add`、`commit` 和 `push`；随后通过 Codex 对话进行修改和 `reset` 回退演示。

提交记录与实际操作说明用于核对练习完成情况。
