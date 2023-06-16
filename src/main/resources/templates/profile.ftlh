<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Личный кабинет</h1>
    <hr>
    <br>
    <div style="text-align: center">
        <#if user.avatar??>
            <img src="images/${user.getAvatar().id}"
                 style="border:2px solid black; border-radius: 20px" height="120">
        <#else>
            <img src="/static/images/avatar.png"
                style="border:2px solid black; border-radius: 20px" height="120">
        </#if>
        <br>
        <br>
        <h2>${user.name}</h2>
        <br>
        Электоронная почта: <b>${user.email}</b>
        <br>
        Номер телефона: <b>${user.phoneNumber}</b>
        <br>
        Адрес: <b>${user.address}</b>
        <br>
        <#if user.group??>
            Группа: <b>${user.group.name}</b>
        </#if>
        <br>
        <br>
        <#if user.isAdmin()>
            <a class="btn btn-primary mb-3" style="text-align: center; width: 100%"
               href="/admin">
                Панель администратора
            </a><br>
        </#if>
        <#if user.getRole() == "ROLE_ARTIST">
        <a class="btn btn-primary mb-3" style="text-align: center; width: 100%"
           href="/my/albums">
            Мои альбомы
        </a>
        <a class="btn btn-primary mb-3" style="text-align: center; width: 100%"
           href="/create/group">
            Создать группу
        </a>
        </#if>
        <a class="btn btn-primary mb-3" style="text-align: center; width: 100%"
           href="/profile/change">
            Изменить профиль
        </a>
        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" style="text-align: center; width: 100%" class="btn btn-danger mb-2">
                Выйти из аккаунта
            </button>
        </form>
    </div>
</@t.template>