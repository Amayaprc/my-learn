<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<#include './head.ftl'>
<table>
    <tr>
        <td>用户名</td>
        <td>年龄</td>
        <td>性别</td>
        <td>阶段</td>
    </tr>
    <#list users as user>
        <tr>
            <td>${user.username}</td>
            <td>${user.age}</td>
            <td>
                <#switch user.sex>
                    <#case 1>
                        男
                    <#break>
                    <#case 2>
                        女
                    <#break>
                    <#case 3>
                        未知
                    <#break>
                </#switch>
            </td>
            <td>
                <#if user.age < 18>
                    未成年人
                <#else>
                    成年人
                </#if>
            </td>
        </tr>
    </#list>
</table>
</body>
</html>
