= Properties of project ${root.modulename}
Version ${root.version}
:nofooter:
:toc:

link:index.html[Index]

<#list root.containers as container>
== ${container.type}
link:${container.reference}[Javadoc]

<#list container.items as item>

****
*${item.value}* (${item.code}) +

${item.plainDoc} +
<#if (item.getDocTags("author"))??>*Author*: ${item.getDocTags("author")} + </#if>
<#if (item.getDocTags("since"))??>*Since*: ${item.getDocTags("since")} + </#if>
<#if (item.getDocTags("deprecated"))??>#Deprecated: ${item.getDocTags("deprecated")}# </#if>
****


</#list>


</#list>
