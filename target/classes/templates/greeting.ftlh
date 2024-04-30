<#import "parts/common.ftl" as c>
<@c.page>
<!DOCTYPE html>
<html>

<head>
    <style>
        .button {
            width: 300px;
            height: 60px;
            background-color: #2c2e2f55;
            border: none;
            color: black;
            padding: 15px 32px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 36px;
            margin: 4px 2px;
            cursor: pointer;
        }

        .button1 {
            width: 300px;
            height: 60px;
            background-color: #2c2e2f55;
            border: none;
            color: black;
            padding: 15px 32px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 30px;
            margin: 4px 2px;
            cursor: pointer;
        }

        .button2 {
            width: 1100px;
            height: 70px;
            background-color: #de8d4ac6;
            border: none;
            color: black;
            padding: 15px 32px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 36px;
            margin: 8px 0px;
            cursor: pointer;
        }

        .button:focus {
            background-color: rgb(197, 173, 130);
        }

        body {
            font-size: 24px;
        }

        #rectangle {
            width: 1100px;
            height: 250px;
            background: #a5bd4e7e;
            /*#0080ff*/
        }

        #left {
            width: 400px;
            float: left;
        }

        #right {
            margin-left: 400px;
            /* Change this to whatever the width of your left column is*/
        }

        .selcls {
            padding: 3px;
            border: solid 1px #5154556e;
            outline: 0;
            font-size: 18px;
            box-shadow: rgba(0, 0, 0, 0.1) 0px 0px 8px;
            -moz-box-shadow: rgba(0, 0, 0, 0.1) 0px 0px 8px;
            -webkit-box-shadow: rgba(0, 0, 0, 0.1) 0px 0px 8px;
            width: 200px;
        }

        ol {
            padding-left: 40px;
            margin-top: 1em;
            margin-bottom: 1em;
        }
    </style>
    <p>Страница с результатами проверки программы</p>
</head>

<body>

    <body style="background-color: #1c1e1c1f"></body>
    <div id="left">
        <p><a href="rules.html" class="button1">Задание правил проверки</a></p>
        <p><a href="jar-init.html" class="button">Загрузка jar-файла</a></p>
    </div>
    <div id="right">
        <thead>
            <tr style="text-align: right;">
                <div id="rectangle">
                    <!--<p>Данные output-файла правила:</p>
                    <p>**************</p>
                    <p>Результат работы программы:</p>
                    <p>**************&#10004;&#10060;</p> -->

                    <!--<a href="/automatic_verification/">Main page</a>-->

                </div>
            </tr>
        </thead>
    </div>
</body>

</html>
</@c.page>