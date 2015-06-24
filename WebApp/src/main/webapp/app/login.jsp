
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>TransDocs</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/extjs/ext-theme-classic/build/resources/ext-theme-classic-all.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/form.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/extjs/ext-all-debug.js"></script>
</head>


<body>
<spring:url var="authUrl" value="/static/j_spring_security_check"/>
<script language="JavaScript">
    Ext.onReady(function () {
        var loginForm = Ext.create("Ext.form.Panel", {
            title:'Форма авторизации',
            width:300,
            height:200,
            layout:'anchor',
            method: 'post',
            url: '${authUrl}',
            defaults:{
                anchor:'80%'
            },
            cls:'panel-margin',
            items:[
                {
                    xtype:'textfield',
                    fieldLabel:'Логин',
                    name:'username',
                    id: 'username',
                    labelAlign:'top',
                    cls:'field-margin',
                    flex:1
                },
                {
                    xtype:'textfield',
                    fieldLabel:'Пароль',
                    name:'password',
                    id: 'password',
                    labelAlign:'top',
                    cls:'field-margin',
                    inputType: 'password',
                    flex:1
                }
            ],
            bbar:['->', {
                text:'Войти',
                handler:function () {
                    loginForm.getForm().doAction('standardsubmit',{
                        url: '${authUrl}',
                        standardSubmit: true,
                        method: 'post'
                    });
                }
            }]
        });

        Ext.create('Ext.container.Viewport', {
            loyout:{  type:'fit',
                      align: 'stretch'},
            items:[  loginForm]
        });


    });
</script>
</body>
</html>