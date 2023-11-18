<#import "parts/common.ftl" as c>
<@c.page>
<div>

    <form method="post" enctype="multipart/form-data">
      <label>
        <input type="file" name="jar_file" accept=".jar">
        <div>List of rules</div>
              <#list rules as rule>
                <div>
                  <input type="checkbox" name="rule_id" value="${rule.id}"><b>${rule.id}</b>
                  <span>${rule.name}</span>
                </div>
              <#else>
              No rules
              </#list>
      </label>

      <button type="submit">Process</button>
    </form>
  </div>

</@c.page>