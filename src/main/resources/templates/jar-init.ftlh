<#import "parts/common.ftl" as c>
<@c.page>
<head>
  <title>Загрузка jar</title>
  <style>
    .button {
    width:300px;
    height:60px;
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
    width:300px;
    height:60px;
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
    width:1000px;
    height:70px;
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
    .button:focus{
    background-color:rgb(197, 173, 130);
    }
    body{
    font-size: 24px;
    }
    .center-screen {
  	display: flex;
  	flex-direction: column;
  	justify-content: center;
  	align-items: center;
  	text-align: center;
    }

    #rectangle {
    width: 1000px;
    height: 150px;
    background: #a5bd4e7e;/*#0080ff*/
    border-radius: 10px;
    }
    #left {
    width: 400px;
    float: left;
    }
    #right {
    margin-left: 360px;
    /* Change this to whatever the width of your left column is*/
    }
    .selcls {
    padding: 3px;
    border: solid 1px #5154556e;
    outline: 0;
    font-size: 18px;
    box-shadow: rgba(0,0,0, 0.1) 0px 0px 8px;
    -moz-box-shadow: rgba(0,0,0, 0.1) 0px 0px 8px;
    -webkit-box-shadow: rgba(0,0,0, 0.1) 0px 0px 8px;
    width:200px;
    }
    ol {
       padding-left: 40px;
       margin-top: 1em;
       margin-bottom: 1em;
    }
  </style>
<div class ="center-screen">
</div>
</head>
<body>
    <body style="background-color: #1c1e1c1f"></body>

  <div class ="center-screen">
  <thead>
  <tr style="text-align: right;">
  <div id="rectangle">
  <p>файл *.jar</p>
<form method="post" enctype="multipart/form-data">
      <label>
      <div id="left">
        <input type="file" name="jar_file" accept=".jar">
      </div>
        <select name="rule_id" id="right">
            <#list rules?if_exists as rule>
                 <option value="${rule.id}">${rule.name}
                 </option>
            </#list>
        </select>
      </label>
      </div>

      <button class="button2" type="submit">Проверить программу</button>
    </form>

  </tr>
  </thead>
  </div>
</body>
</@c.page>