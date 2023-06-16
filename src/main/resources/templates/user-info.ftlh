<#import "blocks/template.ftlh" as t>
<@t.template userByPrincipal>
    <h1 style="text-align: center">Информация о пользователе</h1>
    <hr>
    <div style="text-align: center">
            <img src="/static/images/avatar.png"
                 style="border:2px solid black; border-radius: 20px" height="120">
        <br>
        <br>
        <h2>${user.name}</h2>
        <br>
        Номер телефона: <b>${user.phoneNumber}</b>
        <#if user.group??>
        Группа: <b>${user.group.name}</b>
        </#if>
    </div>
    <br>
    <hr>
    <h1 style="text-align: center">Альбомы пользователя ${user.name}</h1>
    <br>
    <div class="card-columns">
        <#list albums as album>
            <a href="/album/${album.id}" class="album-link">
                <div class="card text-white bg-primary my-3 album">

                    <img src="/images/${album.previewImageId}"
                         class="card-img-top">
                    <div class="card-body">
                        <h2 class="card-title">${album.title}</h2>
                        <h5 class="card-text">${album.group.name}</h5>
                        <p class="card-text">${album.price} ₽</p>
                    </div>
                    <div class="card-footer">
                        Жанр: ${album.genre}
                    </div>
                    <div class="card-footer">
                        Поджанр: ${album.subgenre}
                    </div>
                    <div class="card-footer">
                        Тип издания: ${album.type}
                    </div>
                </div>
            </a>
        <#else>
            <h1 style="text-align: center">Не найдено</h1>
        </#list>
    </div>
</@t.template>