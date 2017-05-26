<#macro attributeUI formName="" formType="" formLabel="" value="" options="" required=false>
    <#assign redP = "<p style='color: red;display: inline'>*</p>">
    <#if formType=="checkbox">
        <div class="layui-form-item">
            <label class="layui-form-label">是否可用</label>
            <div class="layui-input-block">
                <#list options?split(",") as option>
                    <input type="checkbox" name="${formName!}" value="${option!}" title="${option!}" >
                </#list>
            </div>
        </div>
    <#elseif formType == "radio">
        <div class="layui-form-item">
            <label class="layui-form-label">是否可用</label>
            <div class="layui-input-block">
                <#list options?split(",") as option>
                    <input type="radio" name="${formName!}" value="${option!}" title="${option!}" >
                </#list>
            </div>
        </div>
    <#elseif formType == "date">
        <div class="layui-form-item">
            <label class="layui-form-label"><#if required>${redP!}</#if>${formLabel!}</label>
            <div class="layui-input-block">
                <input type="text" name="${formName!}" value="${value!}" <#if required>lay-verify="required"</#if> autocomplete="off" class="layui-input" onclick="layui.laydate({elem: this, festival: true})">
            </div>
        </div>
    <#else>
        <div class="layui-form-item">
            <label class="layui-form-label"><#if required>${redP!}</#if>${formLabel!}</label>
            <div class="layui-input-block">
                <input type="text" name="${formName!}" value="${value!}" <#if required>lay-verify="required"</#if> autocomplete="off" class="layui-input">
            </div>
        </div>
    </#if>
</#macro>