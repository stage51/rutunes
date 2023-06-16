<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Корзина</h1>
    <hr>
    <div class="card-columns">
        <#list userBaskets as userBasket>
            <a href="/album/${userBasket.getAlbum().id}" class="album-link">
                <div class="card text-white bg-primary my-3 album">
                    <div class="card-top">
                        <form action="/basket/delete/${userBasket.id}" method="get">
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <button type="submit" class="btn btn-danger m-3">
                                Удалить
                            </button>
                        </form>
                    </div>
                    <img src="/images/${userBasket.getAlbum().previewImageId}"
                         class="card-img-top">
                    <div class="card-body">
                        <h2 class="card-title">${userBasket.getAlbum().title}</h2>
                        <h5 class="card-text">${userBasket.getAlbum().group.name}</h5>
                        <p class="card-text">${userBasket.getAlbum().price} ₽</p>
                    </div>
                    <div class="card-footer">
                        Жанр: ${userBasket.getAlbum().genre}
                    </div>
                    <div class="card-footer">
                        Поджанр: ${userBasket.getAlbum().subgenre}
                    </div>
                    <div class="card-footer">
                        Тип издания: ${userBasket.getAlbum().type}
                    </div>
                </div>
            </a>
        <#else>
            <h1 style="text-align: center">Не найдено</h1>
        </#list>
    </div>
</@t.template>