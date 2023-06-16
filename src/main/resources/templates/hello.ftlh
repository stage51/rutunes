<#import "blocks/template.ftlh" as t>
<@t.template user>
    <div class="jumbotron">
        <h1 class="display-4">RuTunes</h1>
        <p class="lead">Это каталог песен для покупки в цифровых изданиях, виниловой пластинке, CD или DVD дисках!</p>
        <hr class="my-4">
        <p>Сотни альбомов разных исполнителей и жанров уже ждут своего покупателя!</p>
        <#if user.email??>
            <a class="btn btn-primary btn-sm" href="/album" role="button">Каталог</a>
        <#else>
            <a class="btn btn-primary btn-sm" href="/login" role="button">Войти</a>
            <a class="btn btn-primary btn-sm" href="/registration" role="button">Зарегистрироваться</a>
        </#if>
    </div>
</@t.template>