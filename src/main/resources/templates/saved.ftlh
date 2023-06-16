<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Медиатека</h1>
    <hr>
    <div class="card-columns">
        <#list savedAlbums as savedAlbum>
            <a href="/album/${savedAlbum.getAlbum().id}" class="album-link">
                <div class="card text-white bg-primary my-3 album">
                    <div class="card-top">
                        <form action="/saved/delete/${savedAlbum.id}" method="get">
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <button type="submit" class="btn btn-danger m-3">
                                Удалить
                            </button>
                        </form>
                    </div>
                    <img src="/images/${savedAlbum.getAlbum().previewImageId}"
                         class="card-img-top">
                    <div class="card-body">
                        <h2 class="card-title">${savedAlbum.getAlbum().title}</h2>
                        <h5 class="card-text">${savedAlbum.getAlbum().group.name}</h5>
                        <p class="card-text">${savedAlbum.getAlbum().price} ₽</p>
                    </div>
                    <div class="card-footer">
                        Жанр: ${savedAlbum.getAlbum().genre}
                    </div>
                    <div class="card-footer">
                        Поджанр: ${savedAlbum.getAlbum().subgenre}
                    </div>
                    <div class="card-footer">
                        Тип издания: ${savedAlbum.getAlbum().type}
                    </div>
                </div>
            </a>
        <#else>
            <h1 style="text-align: center">Не найдено</h1>
        </#list>
    </div>
</@t.template>