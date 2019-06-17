# 后端接口文档

 ## ~~地址 http://74.121.149.107:8082~~ 服务器被ban了

-注册 /api/register  method:POST

输入数据
```json
{
    "name": "hsfy",
    "phone": "13345678901",
    "password": "test password"
}
```
返回数据为一个完整的User实体
```json
{
    "id": 1,
    "name": "hsfy",
    "aid": 1,
    "role": 0,
    "phone": "13345678901"
}
```
- 返回所有用户 /api/admin/users method:GET

返回数据样例
```json
[
    {
        "id": 1,
        "name": "hsfy",
        "aid": 1,
        "role": 1,
        "phone": "13345678901",
        "insertTime": "2019-05-26 17:58:43",
        "updateTime": "2019-05-26 18:09:25"
    }
]
```
- 登录 /api/login method:POST  

输入数据
```json
{
  "phone":"13345678901",
  "password":"test password"
}
```
返回数据样例
```json
{
    "uInfo": {
        "id": 1,
        "name": "hsfy",
        "aid": 1,
        "role": 0,
        "phone": "13345678901",
        "userIvgs": [],
        "userTasks": [],
        "insertTime": "2019-05-26 17:58:43",
        "updateTime": "2019-05-26 17:58:43"
    },
    "token": "-- 过长已忽略 --"
}
```
**说明:token要放在请求头的"Authorization"中**
- 管理员直接添加用户  /api/admin/user  method:POST  

输入数据
```json
{
    "name": "hsfy",
    "phone": "13345678902",
    "password": "test password"
}
```
返回数据为一个完整的User实体
```json
{
    "id": 2,
    "name": "hsfy",
    "aid": 1,
    "role": 0,
    "phone": "13345678902"
}
```
- 获取本人信息 /api/me method:GET  

返回数据样例
```json
{
    "id": 1,
    "name": "hsfy",
    "aid": 1,
    "role": 1,
    "phone": "13345678901",
    "description": null,
    "insertTime": "2019-05-26 17:58:43",
    "updateTime": "2019-05-26 18:09:25"
}
```
- 管理员删除用户 /api/admin/user/{userId}  method:DELETE  

输入数据
```
无
```
输出数据样例
```json
true
```
- 管理员更改用户信息 /api/admin/user/{userId}  method:PATCH  

输入数据
```json
{
    "name": "hsfy2",
    "aid": 1,
    "role": 0,
    "phone": "13345678903",
    "password":"must modify password"
}
```
输出数据样例
```json
{
    "id": 3,
    "name": "hsfy2",
    "aid": 1,
    "role": 0,
    "phone": "13345678903"
}
```
- 用户修改个人信息 /api/user/{userid}/modidfyUserInfo method:PATCH
```
该部分输入输出省略
```
- 列出所有监考信息 /api/ivgs method:GET

输出数据样例
```json
[
    {
        "id": 2,
        "name": "系统程序设计",
        "beginTime": "2019-05-26 15:30:00",
        "duration": "01:30:00",
        "location": 1,
        "numbersOfTeacher": 2,
        "insertTime": "2019-05-27 09:10:21",
        "updateTime": "2019-05-27 09:10:21"
    },
    {
        "id": 4,
        "name": "系统程序设计3",
        "beginTime": "2019-05-26 15:30:00",
        "duration": "01:30:00",
        "location": 1,
        "numbersOfTeacher": 3,
        "insertTime": "2019-05-27 09:24:24",
        "updateTime": "2019-05-27 09:24:24"
    }
    //忽略
]
```

- 添加监考信息 /api/admin/ivg method:POST

输入数据
```json
{
    "name":"系统程序设计",
    "beginTime":"2019-05-26 15:30:00",
    "duration":"01:30:00",
    "location":1,
    "numbersOfTeacher":2
}
```
输出数据样例
```json
{
    "id": 2,
    "name": "系统程序设计",
    "beginTime": "2019-05-26 15:30:00",
    "duration": "01:30:00",
    "location": 1,
    "numbersOfTeacher": 2
}
```
- 删除监考信息 /api/admin/ivg/{ivgId} method:DELETE
输入数据
```
无
```
输出数据样例
```json
true
```
- 更改监考信息 /api/admin/ivg/{ivgId} method:PATCH
输入数据
```json
{
    "name":"系统程序设计3",
    "beginTime":"2019-05-26 15:30:00",
    "duration":"01:30:00",
    "location":1,
    "numbersOfTeacher":3
}
```
输出数据样例
```json
{
    "id": 1,
    "name": "系统程序设计3",
    "beginTime": "2019-05-26 15:30:00",
    "duration": "01:30:00",
    "location": 1,
    "numbersOfTeacher": 3
}
```
- 获取考试已分配的监考人数 /api/admin/user_ivg/{id}/countIsSetIvgs method:GET

返回对应id的考试已分配的监考数  

- 设置监考人员 /api/admin/user_ivg method:POST

输入数据
```json
{
    "uId":1,
    "ivgId":2
}
```
输出数据样例
```json
{
    "id": 1,
    "user": {
        "id": 1,
        "aid": 1,
        "role": 0
    },
    "ivg": {
        "id": 2
    }
}
```
- 删除监考人员 /api/admin/user_ivg/{userIvgId} method:DELETE

输入数据
```
无
```
输出数据样例
```
true
```
- 列出所有任务信息 /api/tasks method:GET  

输出数据样例
```json
[
    {
        "id": 1,
        "type": 1,
        "description": "这是一次作业测试,你看明白了吗",
        "name": "作业测试",
        "ddl": "2019-05-20 00:00:00",
        "isOpen": false,
        "insertTime": "2019-05-27 09:47:56",
        "updateTime": "2019-05-27 09:57:02"
    }
]
```
- 添加任务信息 /api/admin/task method:POST

输入数据
```json
{
	"type":1,
	"description":"这是一次作业测试,你看明白了吗",
    "name": "作业测试",
    "ddl":"2019-05-20 00:00:00",
    "isOpen":true
}
```
输出数据样例
```json
{
    "id": 1,
    "type": 1,
    "description": "这是一次作业测试,你看明白了吗",
    "name": "作业测试",
    "ddl": "2019-05-20 00:00:00",
    "isOpen": true
}
```
- 修改任务信息 /api/admin/task/{taskId} method:PATCH

输入输出省略  

- 删除任务信息 /api/admin/task/{id} method:DELETE

输入数据
```
无
```
输出数据样例
```
true
```
- 关闭任务 /api/admin/task/{id}/close method:GET

输入数据
```
无
```
输出数据样例
```json
{
    "id": 1,
    "type": 1,
    "description": "这是一次作业测试,你看明白了吗",
    "name": "作业测试",
    "ddl": "2019-05-20 00:00:00",
    "isOpen": false,
    "userTasks": [],
    "insertTime": "2019-05-27 09:47:56",
    "updateTime": "2019-05-27 09:57:02"
}
```
- 删除用户已提交的任务 /api/admin/user_task/{id} method:DELETE

输入数据
```
无
```
输出数据样例
```json

```
- 查找一个考试已分配的用户 /api/findUserByIvgId method:GET

输入参数  

| 参数名 |   类型   |  说明  |
|:-----:|:-------:|:------:|  
|  id   | Integer | 考试的id|
输出数据样例
```json
[
    {
        "id": 1,
        "user": {
            "id": 1,
            "name": "hsfy",
            "aid": 1,
            "role": 1,
            "phone": "13345678901",
            "insertTime": "2019-05-26 17:58:43",
            "updateTime": "2019-05-26 18:09:25"
        },
        "ivg": {
            "id": 2,
            "name": "系统程序设计",
            "beginTime": "2019-05-26 15:30:00",
            "duration": "01:30:00",
            "location": 1,
            "numbersOfTeacher": 2,
            "insertTime": "2019-05-27 09:10:21",
            "updateTime": "2019-05-27 09:10:21"
        },
        "insertTime": "2019-05-27 09:32:26",
        "updateTime": "2019-05-27 09:32:26"
    },
    {
        "id": 4,
        "user": {
            "id": 2,
            "name": "hsfy3",
            "aid": 1,
            "role": 0,
            "phone": "13345678904",
            "insertTime": "2019-05-27 13:16:24",
            "updateTime": "2019-05-27 13:16:24"
        },
        "ivg": {
            "id": 2,
            "name": "系统程序设计",
            "beginTime": "2019-05-26 15:30:00",
            "duration": "01:30:00",
            "location": 1,
            "numbersOfTeacher": 2,
            "insertTime": "2019-05-27 09:10:21",
            "updateTime": "2019-05-27 09:10:21"
        },
        "insertTime": "2019-05-27 13:16:44",
        "updateTime": "2019-05-27 13:16:44"
    }
]
```
- 查找一个用户已经被分配在那些考试中 /api/findIvgByUser method:GET  

输入参数  

| 参数名 |   类型   |  说明  |
|:-----:|:-------:|:------:|  
|  id   | Integer | 用户的id|

输出数据样例
```json
[
    {
        "id": 1,
        "user": {
            "id": 1,
            "name": "hsfy",
            "aid": 1,
            "role": 1,
            "phone": "13345678901",
            "insertTime": "2019-05-26 17:58:43",
            "updateTime": "2019-05-26 18:09:25"
        },
        "ivg": {
            "id": 2,
            "name": "系统程序设计",
            "beginTime": "2019-05-26 15:30:00",
            "duration": "01:30:00",
            "location": 1,
            "numbersOfTeacher": 2,
            "insertTime": "2019-05-27 09:10:21",
            "updateTime": "2019-05-27 09:10:21"
        },
        "insertTime": "2019-05-27 09:32:26",
        "updateTime": "2019-05-27 09:32:26"
    },
    {
        "id": 2,
        "user": {
            "id": 1,
            "name": "hsfy",
            "aid": 1,
            "role": 1,
            "phone": "13345678901",
            "insertTime": "2019-05-26 17:58:43",
            "updateTime": "2019-05-26 18:09:25"
        },
        "ivg": {
            "id": 5,
            "name": "系统程序设计3",
            "beginTime": "2019-05-26 15:30:00",
            "duration": "01:30:00",
            "location": 1,
            "numbersOfTeacher": 3,
            "insertTime": "2019-05-27 09:24:55",
            "updateTime": "2019-05-27 09:24:55"
        },
        "insertTime": "2019-05-27 13:14:13",
        "updateTime": "2019-05-27 13:14:13"
    }
]
```
- 用户提交任务 /api/submitTask method:POST 

输入数据
```json
{
	"user":{
		"id":1
	},
	"task":{
		"id":2
	},
	"content":"提交作业接口测试",
	"timeOut":false
}
```
输出数据样例
```json
{
    "id": 2,
    "user": {
        "id": 1,
        "aid": 1,
        "role": 0
    },
    "task": {
        "id": 2
    },
    "content": "提交作业接口测试",
    "timeOut": true
}
```
- 查看任意用户完成的任意任务的信息 /api/findTaskInfoByUserIdAndTaskId method:GET

输入参数  

| 参数名 |   类型   |  说明  |
|:-----:|:-------:|:------:|  
|  uId   | Integer | 用户的id|
|  tId   | Integer | 任务的id|

输出数据样例
```json
{
    "id": 2,
    "user": {
        "id": 1,
        "name": "hsfy",
        "aid": 1,
        "role": 1,
        "phone": "13345678901",
        "insertTime": "2019-05-26 17:58:43",
        "updateTime": "2019-05-26 18:09:25"
    },
    "task": {
        "id": 2,
        "type": 1,
        "description": "23123",
        "name": "测试作业",
        "ddl": "2019-05-22 00:00:00",
        "isOpen": true,
        "insertTime": "2019-05-27 13:26:04",
        "updateTime": "2019-05-27 13:26:04"
    },
    "content": "提交作业接口测试",
    "timeOut": true,
    "insertTime": "2019-05-27 13:27:15",
    "updateTime": "2019-05-27 13:27:15"
}
```
- 查看某一位用户提交过的版本 /api/getUserTask method:GET  

输入参数  

| 参数名 |   类型   |  说明  |
|:-----:|:-------:|:------:|  
|  id   | Integer | 用户的id|
返回数据实例  
```json
[
    {
        "id": 1,
        "user": {
            "id": 7,
            "name": "Hsfy",
            "aid": 0,
            "role": 0,
            "phone": "15546313503",
            "description": null,
            "count": null,
            "insertTime": "2019-06-11 11:25:45",
            "updateTime": "2019-06-11 11:26:17"
        },
        "task": {
            "id": 2,
            "type": 1,
            "description": "test",
            "name": "过期测试",
            "ddl": "2019-05-27 00:00:00",
            "isOpen": true,
            "insertTime": "2019-06-11 11:33:12",
            "updateTime": "2019-06-11 11:33:12"
        },
        "content": "123",
        "timeOut": true,
        "insertTime": "2019-06-11 11:36:38",
        "updateTime": "2019-06-11 11:36:38"
    },
    {
        "id": 2,
        "user": {
            "id": 7,
            "name": "Hsfy",
            "aid": 0,
            "role": 0,
            "phone": "15546313503",
            "description": null,
            "count": null,
            "insertTime": "2019-06-11 11:25:45",
            "updateTime": "2019-06-11 11:26:17"
        },
        "task": {
            "id": 1,
            "type": 1,
            "description": "请回复",
            "name": "明天考试",
            "ddl": "2019-06-20 00:00:00",
            "isOpen": true,
            "insertTime": "2019-06-11 11:32:47",
            "updateTime": "2019-06-11 11:32:47"
        },
        "content": "123",
        "timeOut": false,
        "insertTime": "2019-06-11 11:36:46",
        "updateTime": "2019-06-11 11:36:46"
    },
    {
        "id": 3,
        "user": {
            "id": 7,
            "name": "Hsfy",
            "aid": 0,
            "role": 0,
            "phone": "15546313503",
            "description": null,
            "count": null,
            "insertTime": "2019-06-11 11:25:45",
            "updateTime": "2019-06-11 11:26:17"
        },
        "task": {
            "id": 1,
            "type": 1,
            "description": "请回复",
            "name": "明天考试",
            "ddl": "2019-06-20 00:00:00",
            "isOpen": true,
            "insertTime": "2019-06-11 11:32:47",
            "updateTime": "2019-06-11 11:32:47"
        },
        "content": "123",
        "timeOut": false,
        "insertTime": "2019-06-11 12:35:14",
        "updateTime": "2019-06-11 12:35:14"
    }
]
```
- 查看某一任务的提交结果 /api/getTaskUser method:GET  

输入参数  

| 参数名 |   类型   |  说明  |
|:-----:|:-------:|:------:|  
|  id   | Integer | 任务的id|
返回数据实例
```json

```