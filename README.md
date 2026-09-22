# git-test

基于 Git 的协同开发课堂练习。

本仓库现已公开，老师可直接查看代码、运行截图和练习完成记录。

项目根目录的 `DateTimePickerDialog.java` 是一个独立的 Java 日期时间选择器。默认显示当前时间，支持年、月、日、时、分选择，自动处理闰年和每月天数；点击“确定”显示所选时间，点击“取消”、关闭窗口或按 Esc 退出。

![程序实际运行窗口](docs/date-time-picker.png)

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

在 GitHub 创建 `git-test` 仓库，克隆到本地；添加 Java 文件，完成 `add`、`commit` 和 `push`；随后修改文件并完成 `reset` 回退演示。

完整的步骤、提交编号与回退证据见 [练习完成记录](EXERCISE_LOG.md)。

## 本地分支操作练习

已基于 `main` 创建 `branch1` 和 `branch2`，分别修改并提交 `1.txt`，将 `branch1` 合并回 `main`，并把全部分支推送到远程仓库。

分支结构、提交编号和各分支文件内容见 [本地分支操作练习记录](BRANCH_EXERCISE.md)。
