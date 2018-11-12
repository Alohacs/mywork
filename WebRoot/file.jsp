<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=GB18030">
    <title>File upload</title>
</head>
<body>
<!--  // action="fileupload"对应web.xml中<servlet-mapping>中<url-pattern>的设置. -->
    <form name="myform" action="fileupload/doPost" method="post"
       enctype="multipart/form-data">
       File:<br>
       <input type="file" name="myfile"><br>
       <br>
       <input type="submit" name="submit" value="Commit">
    </form>
</body>
</html>