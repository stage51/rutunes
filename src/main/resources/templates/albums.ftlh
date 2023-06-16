<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Поиск альбомов</h1><hr>
    <form method="get" action="/album">
        <div class="form-row">
            <div class="form-group col-md-8">
                <input type="text" name="searchWord" <#if searchWord?has_content>value="${searchWord}"</#if> class="form-control" id="searchWord" placeholder="Поиск по названию">
            </div>
            <div class="form-group col-md-4">
                <button type="submit" class="btn btn-primary">Поиск</button>
            </div>
        </div>
    </form>
    <form method="get" action="/album">
        <div class="form-row">
            <div class="form-group col-md-8">
                <input type="text" name="searchGroup" <#if searchGroup??>value="${searchGroup}"</#if> class="form-control" id="searchGroup" placeholder="Поиск по группе">
            </div>
            <div class="form-group col-md-4">
                <button type="submit" class="btn btn-primary">Поиск</button>
            </div>
        </div>
    </form>
    <hr>
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