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
            background-color: #c58d60c6;
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
    <p>Страница добавления правила</p>
</head>

<body>
    <form method="post" enctype="multipart/form-data">

    <body style="background-color: #1c1e1c1f"></body>
    <div id="left">
        <p><a class="button1">Задание правил проверки</a></p>
        <p><a href="jar-init.ftlh" class="button">Загрузка jar-файла</a></p>
    </div>
    <div id="right">
        <thread>
            <tr style="text-align: right;">
                <div id="rectangle">
                    <p>
                        <input type="text" name="name" placeholder="Введите название правила" size="30" />
                    </p>
                    <p>файл(ы) input</p>
                    <p><input multiple type="file" name="input_file"/></p>
                    <p>файл output</p>
                    <p><input name type="file" name="output_file"/></p>

                </div>
                <input type="button" class="button2" value="Добавить правило" />
                <div>Добавленные правила</div>
                <#list rules as rule>
                    <div>
                        <b>${rule.id}</b>
                        <span>${rule.name}</span>
                    </div>
                    <#else>
                        No rules
                </#list>
            </tr>
        </thread>
    </div>
</body>

</html>
</@c.page>