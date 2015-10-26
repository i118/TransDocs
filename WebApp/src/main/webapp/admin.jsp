<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head><title>TransDocs</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="Content-Type" content="application/json; charset=utf-8"/>
  <link rel="stylesheet" type="text/css"
        href="${pageContext.request.contextPath}/extjs/ext-theme-classic/build/resources/ext-theme-classic-all.css"/>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/button-icon.css"/>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/tree.css"/>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/form.css"/>
  <script type="text/javascript" src="${pageContext.request.contextPath}/extjs/ext-all-debug.js"></script>

</head>




<body>
<div id="page-loader">
  <img style="position:absolute; width:32px; height: 32px; left:50%; top:50%; margin-left:-64px; margin-top: -7px;"
       src="extjs/ext-theme-classic/build/resources/images/shared/blue-loading.gif"/>
</div>
<script language="JavaScript">

  var applicationContext;
  Ext.Loader.setConfig({enabled: true});
  Ext.application({
    name: 'TransDocs',
    appFolder: 'app',
    requires: ['TransDocs.view.AdminViewPort',
      "TransDocs.util.ApplicationContext",
      "TransDocs.util.WindowManager",
      "TransDocs.service.PermitActionService",
      "TransDocs.service.RenderFactory",
      "TransDocs.service.UserService",
      "TransDocs.overrides.SessionOverride",
      "TransDocs.view.trigger.PersonInfoTrigger",
      "TransDocs.view.trigger.SearchTrigger",
      "TransDocs.service.DictionaryFactory"
    ],
    controllers: [
      'TransDocs.controller.AdminWorkSpaceController'
    ],
    launch: function () {
      applicationContext = Ext.create("TransDocs.util.ApplicationContext", {
        sessionId: "<%=session.getId().toString()%>",
        baseUrl: "${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}"
      });
      Ext.create('TransDocs.view.AdminViewPort');
      if (Ext.get('page-loader')) {
        Ext.get('page-loader').remove();
      }
    }
  });
</script>

<object classid="java:com.td.filetransfer.FileTransfer.class" type="application/x-java-applet"
        archive="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/web/file-transfer.jar"
        width="0" height="0" id="fileTransfer">
  <PARAM name="sessionId" value="<%=session.getId().toString()%>">
  <PARAM name="host"
         value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}">
</object>

<iframe id="downloadFileFrame" name="downloadFileFrame" src='about:blank' style='display:none;'>
</iframe>
</body>
</html>