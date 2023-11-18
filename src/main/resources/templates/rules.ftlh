<#import "parts/common.ftl" as c>
<@c.page>
<div>
    <form method="post" enctype="multipart/form-data">
      <label>
        <input type="text" name="name" placeholder="Add rule">
        <input type="file" name="input_file" multiple accept=".csv">
        <input type="file" name="output_file" accept=".csv">
      </label>
      <button type="submit">Add</button>
    </form>
  </div>
<div>List of rules</div>
<#list rules as rule>
  <div>
    <b>${rule.id}</b>
    <span>${rule.name}</span>
  </div>
<#else>
No rules
</#list>
</@c.page>