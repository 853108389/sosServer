<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0,minimum-scale=1.0, maximum-scale=1.0,user-scalable=no">
    <link rel="stylesheet" href="css/jquery.mobile-1.4.5.min.css">
    <script src="js/jquery-1.9.1.min.js"></script>
    <script src="js/jquery.mobile-1.4.5.min.js"></script>
    <link rel="stylesheet" href="css/style.css">
    <script type="text/javascript" src="./jquery-1.9.1.min.js"></script>
    <script>
        function test(url,params) {
            $.ajax({
                type:"POST",
                url:url,
                dataType:"json",
                contentType:"application/json",
                data:JSON.stringify(params),
                success:function(data){
                    console.log(JSON.stringify(data))
                },
                error:function(data){
                    console.log(data)
                }
            });
        }


    </script>
</head>
<body>
<p>aaaa</p>
<div>
    <a href="http://localhost:8080/sos/test/test.do">Test Skip</a> <br/>
</div>
<div>
    <a href="http://localhost:8080/sos/test/testJson.do">Test Json</a>
</div>
<div>
    <a href="http://localhost:8080/sos/user/detail.do?id=1">Test User</a>
</div>
<div>
    <a href="http://localhost:8080/sos/user/login.do?stuNo=1&password=a">Test Login</a>
</div>

<div>
    <p onclick="test('http://localhost:8080/sos/user/login.do', {stuNo:1,password:'a'} )">Test Login</p>
</div>
<div>
    <p onclick="test('http://localhost:8080/sos/user/detail.do', {id:1} )">Test User</p>
</div>

</body>
</html>

