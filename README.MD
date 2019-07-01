# 1. 注册

地址 ： https://cccxu.cn/account/register

方式：POST

`json`格式：

```json
{
    "lgoinInfo":{
        "userId": "6位字母+数字ID",
        "passwordHash": "密码+salt的MD5",
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

