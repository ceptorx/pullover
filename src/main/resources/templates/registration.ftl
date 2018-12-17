<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
    Registration
    ${message}
    <div>Add new user</div>
<@l.login "/registration"/>
</@c.page>