<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
    Login page
    <@l.login "/login"/>
    <div><a href="/registration">Registration</a></div>
</@c.page>