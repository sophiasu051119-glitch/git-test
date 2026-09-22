# 本地分支操作练习记录

本次练习在现有 `git-test` 仓库中完成。仓库默认分支为 `main`，对应课件第 5 步中的 `master`。

## 完成情况

| 步骤 | 操作 | 结果 |
| --- | --- | --- |
| 1 | 基于 `main` 创建 `branch1` | 完成 |
| 2 | 在 `branch1` 修改 `1.txt`，执行 add、commit | 提交 `558a36f` |
| 3 | 返回 `main`，基于 `main` 创建 `branch2` | 完成 |
| 4 | 在 `branch2` 修改 `1.txt`，执行 add、commit | 提交 `a5a5a32` |
| 5 | 切换到 `main`，合并 `branch1` | 合并提交 `650a7ce` |
| 6 | 推送所有本地分支到远程仓库 | `main`、`branch1`、`branch2` 均已推送 |

## 分支关系

```text
*   650a7ce (main) merge: integrate branch1 into main
|\
| * 558a36f (branch1) feat: update 1.txt on branch1
|/
| * a5a5a32 (branch2) feat: update 1.txt on branch2
|/
* 649ebae chore: add branch exercise base file
```

`branch1` 和 `branch2` 都直接基于提交 `649ebae` 创建，因此两条分支起点相同。`main` 合并了 `branch1`，所以 `main` 与 `branch1` 中的 `1.txt` 内容相同；`branch2` 保留另一份修改。

## 远程分支下的 1.txt

- [main 分支的 1.txt](https://github.com/sophiasu051119-glitch/git-test/blob/main/1.txt)
- [branch1 分支的 1.txt](https://github.com/sophiasu051119-glitch/git-test/blob/branch1/1.txt)
- [branch2 分支的 1.txt](https://github.com/sophiasu051119-glitch/git-test/blob/branch2/1.txt)

### main

```text
Git 本地分支操作练习
当前分支：branch1
修改内容：完成 branch1 的本地提交
```

### branch1

```text
Git 本地分支操作练习
当前分支：branch1
修改内容：完成 branch1 的本地提交
```

### branch2

```text
Git 本地分支操作练习
当前分支：branch2
修改内容：完成 branch2 的本地提交
```

关键练习提交：

```text
合并 branch1  650a7ce6ff3b96c3e451bcfad926f3e0df8a84a2
branch1 提交  558a36f92a7eb95d97485983ae296790f82bc97b
branch2 提交  a5a5a324a0a701628995f2d301e69cde16ce3942
```
