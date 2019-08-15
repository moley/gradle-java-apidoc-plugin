= Extensions of project ${root.modulename}
Version ${root.version}
:nofooter:
:toc:

link:index.html[Index]


<#list root.containers as container>
== ${container.type}
****
${container.plainDoc} +
<#if (container.getDocTags("author"))??>*Author*: ${container.getDocTags("author")} + </#if>
<#if (container.getDocTags("since"))??>*Since*: ${container.getDocTags("since")} + </#if>
<#if (container.getDocTags("deprecated"))??>#Deprecated: ${container.getDocTags("deprecated")}# + </#if>
****
link:${container.reference}[Javadoc]




<#list container.items as item>
****
${item.plainDoc} +
<#if (item.getDocTags("author"))??>*Author*: ${item.getDocTags("author")} + </#if>
<#if (item.getDocTags("since"))??>*Since*: ${item.getDocTags("since")} + </#if>
<#if (item.getDocTags("deprecated"))??>#Deprecated: ${item.getDocTags("deprecated")}# + </#if>

*${item.code}* +

****

</#list>




</#list>
