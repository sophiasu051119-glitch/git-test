# Pull Request 练习记录

本次练习继续使用公开仓库 `git-test`，从 `branch1` 向 `main` 提交并合并 Pull Request。

## 完成情况

| 步骤 | 操作 | 结果 |
| --- | --- | --- |
| 1 | 在 `branch1` 修改 `1.txt`，执行 add、commit | 提交 `e3ff824` |
| 2 | 推送 `branch1` 到远程仓库 | 完成 |
| 3 | 创建从 `branch1` 到 `main` 的 Pull Request | [Pull Request #1](https://github.com/sophiasu051119-glitch/git-test/pull/1) |
| 4 | 本地拉取 PR 并审查 | 拉取到 `origin/pr/1`，完成差异、空白及冲突检查 |
| 5 | 根据审查结果更新文件 | 提交 `d54eee4`，审查结果为通过 |
| 6 | 采纳并合并 Pull Request | 合并提交 `7bd8b0b`，GitHub 状态为 `merged` |
| 7 | 查看 `main` 下的 `1.txt` | 已核对远程文件内容 |

## 审查结果

审查范围为 Pull Request #1 中的全部变更：2 个提交、1 个文件，最终差异为新增 2 行、删除 1 行。

- 变更范围符合练习要求，只修改 `1.txt`。
- `git diff --check` 通过，没有空白或格式错误。
- 本地模拟合并成功，没有冲突。
- 文件内容清晰，没有发现需要修改的问题。

## 分支与合并关系

```text
main
 └─ 7bd8b0b  Merge pull request #1 from sophiasu051119-glitch/branch1
     └─ d54eee4  docs: record successful pull request review
         └─ e3ff824  feat: prepare branch1 pull request exercise
```

## main 分支的最终 1.txt

[在 GitHub 查看 main 分支的 1.txt](https://github.com/sophiasu051119-glitch/git-test/blob/main/1.txt)

```text
Git 本地分支操作练习
当前分支：branch1
第三次练习：通过 Pull Request 将 branch1 的修改合并到 main
审查结果：通过，未发现需要修改的问题
```
