<#import "parts/common.ftl" as c>
<@c.page>


<head>
  <title>Добавление правил</title>
    <style>
            .button {
                width: 300px;
                height: 60px;
                vertical-align: middle;
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

                border-radius:10px;
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
                border-radius:10px;
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
                border-radius:10px;
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
                border-radius:10px;
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
            .center-screen {
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
                text-align: center;
            }

            ol {
                padding-left: 40px;
                margin-top: 1em;
                margin-bottom: 1em;
            }
        </style>

    </head>

    <body>
        <form method="post" enctype="multipart/form-data">

        <body style="background-color: #1c1e1c1f"></body>
        <div id="left">
            <p><a class="button1">Задание правил проверки</a></p>
            <p><a href="http://localhost:8888/automatic_verification/api/jar" class="button">Загрузка jar-файла</a></p>
        </div>
        <div id="right">
            <thread>
                <tr style="text-align: right;">
                    <div id="rectangle" class="center-screen">
                    <label>
                        <p>
                            <input type="text" name="name" placeholder="Введите название правила" size="30">
                        </p>
                        <p>файл(ы) input</p>
                        <p><input type="file" name="input_file" multiple accept=".csv"></p>
                        <p>файл output</p>
                        <p><input type="file" name="output_file" accept=".csv"></p>
                    <label>
                    </div>
                    <button class="button2" value="Добавить правило" type="submit">Добавить правило</button>
                    </form>
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

    </@c.page>