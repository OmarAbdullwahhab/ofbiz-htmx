<#if requestAttributes.options?has_content>
 <#list requestAttributes.options as opt>
        <option value="${opt.id}">
            ${opt.text}
        </option>
 </#list>
 <#else>
 <option>....no thing...</option>
</#if>