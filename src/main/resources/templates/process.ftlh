<#import "parts/common.ftl" as c>
<@c.page>

<head>
  <title>Результат проверки</title>
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
font-size: 30px;
margin: 4px 2px;
cursor: pointer;
border-radius: 10px;
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
border-radius: 10px;
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
border-radius: 10px;
}

.button:focus {
background-color: rgb(197, 173, 130);
}

body {
font-size: 24px;
}

#rectangle {
width: 1100px;
height: 400px;
background: #a5bd4e7e;
/*#0080ff*/
border-radius: 10px;
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
<div>

<body style="background-color: #1c1e1c1f"></body>

<div id="left">
<p><a href="http://localhost:8091/automatic_verification/api/rule" class="button1">Задание правил проверки</a></p>
<p><a href="http://localhost:8091/automatic_verification/api/jar" class="button">Загрузка jar-файла</a></p>
</div>

<div id="right">
<thead>
<tr style="text-align: right;">
<div id="rectangle" class="center-screen">
<label>
<div>Данные output-файла правила:</div>
<#list expected as exp>
<div>
<span>${exp}</span>
</div>
</#list>
</label>

<label>
<p></p><div>Результат работы программы:</div>
<#list actual as act>
<div>
<span>${act}</span>
</div>
</#list>
</label>

<label>
<p></p><div>Результат</div>
<div>
<span>${result}</span>
</div>
</label>

</div>
</tr>
</thead>
</div>

</div>

</@c.page>