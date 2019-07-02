# 1. 注册

## 1. 检查ID是否可用

地址：https://cccxu.cn/account/checkUserIdUsable

方式：get

参数：userId

## 2. 注册

地址 ： https://cccxu.cn/account/register

方式：post

`json`格式：

```json
{
    "lgoinInfo":{
        "userId": "6位字母+数字ID",
        "passwordHash": "密码+salt的sh1",
        "salt": "四位随机生成的字母"
    },
    "loginSafe":{
        "userId": "同上",
        "questionId": "1位密保问题编号",
        "answer": "密保问题答案"
    },
    "userInfo": {
        "userId": "同上",
        "userNickName": "用户昵称",
        "headPic": "用户头像，暂时空",
        "email": "用户邮箱",
        "gender": "用户性别，0/1存储，0女，1男，允许为null",
        "phoneNumber": "用户手机号码，检测手机号合法性，允许为null",
    }
}
```



# 2. 登录

## 1. 获取salt

地址： https://cccxu.cn/account/getSalt

方法：get

参数：通过地址传入userId, 格式：../getSalt?userId=

## 2. 登录

地址：  https://cccxu.cn/account/signin

方法：post

`json`格式：

```json
{
	"userId": "用户id",
	"passwordHash": "用户密码加盐MD5"
}
```

# 3. 签到

地址：https://cccxu.cn/user/check

方法： get

参数： 通过地址传入参数，

​			第一个参数： `gainedPoints`: 本次签到加的分, 

​			第二个参数：`userId`: 用户id

# 4. 添加教师

地址： https://cccxu.cn/teacher/addTeacher

方法：post

json格式：

```json
{
	"teacherId": "就是用户id",
	"realName": "教师的真实姓名",
    "teacherIntroduction": "教师个人简介"
}
```

# 5. 修改密码

## 1. 检查用户ID是否存在

同1.1

## 2. 检查密保问题

地址：/account/checkLoginSafe

方法: post

json格式：

```json
{
	"loginSafe":{
        "userId": "同上",
        "questionId": "1位密保问题编号",
        "answer": "密保问题答案"
    }
}
```



## 3. 重设密码和盐

地址：/account/changePassword

方法：post

json格式：

```json
{
    "lgoinInfo":{
            "userId": "6位字母+数字ID",
            "passwordHash": "密码+salt的sh1",
            "salt": "四位随机生成的字母"
        }
}
```

