<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Мои альбомы</h1>
    <hr>
    <a class="btn btn-primary mt-3 mb-3" style="text-align: center; width: 100%"
       data-toggle="collapse" href="#collapseExample" role="button"
       aria-expanded="true" aria-controls="collapseExample">
        Добавить новый альбом
    </a>
    <div class="collapse mb-3" id="collapseExample" style="">
        <form action="/album/create" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="albumName">Название альбома</label>
                <input type="text" required class="form-control"
                       id="albumName" name="title">
            </div>
            <!--
            <div class="form-group">
                <label for="albumAuthor">Автор(-ы) альбома</label>
                <input type="text" required class="form-control"
                       id="albumAuthor" name="author">
            </div>
            -->
            <div class="form-group">
                <label for="price">Цена альбома</label>
                <div class="input-group mb-3">
                    <input type="number" required class="form-control" id="price" name="price">
                    <div class="input-group-append">
                        <span class="input-group-text">₽</span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="albumGenre">Жанр альбома</label>
                <select id="albumGenre" required name="genre" class="form-control">
                    <option value="Rock">Рок</option>
                    <option value="Electronic">Электроника</option>
                    <option value="Pop">Поп-музыка</option>
                    <option value="Rap">Рэп</option>
                    <option value="Classic">Классическая</option>
                    <option value="Jazz">Джаз, блюз</option>
                    <option value="Dance">Танцевальная</option>
                </select>
            </div>
            <div class="form-group">
                <label for="albumSubgenre">Поджанр альбома</label>
                <input type="text" required class="form-control"
                       id="albumSubgenre" name="subgenre">
            </div>
            <div class="form-group">
                <label for="albumTime">Длина песен в альбоме (в секундах)</label>
                <div class="input-group mb-3">
                    <input type="number" required class="form-control" id="albumTime" name="time">
                    <div class="input-group-append">
                        <span class="input-group-text">сек.</span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="searchType">Тип издания</label>
                <select id="searchType" required name="type" class="form-control">
                    <option value="Lossy">Цифровое издание Lossy</option>
                    <option value="Lossless">Цифровое издание Lossless</option>
                    <option value="Vinyl">Виниловая пластинка</option>
                    <option value="CD">CD-диск</option>
                    <option value="DVD">DVD-диск</option>
                </select>
            </div>
            <div class="form-group">
                <label for="description">Описание альбома</label>
                <textarea required class="form-control" id="description" name="description"></textarea>
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input required type="file" name="file1" id="file1">
                    <label class="custom-file-label" for="file1">Обложка альбома</label>
                </div>
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file2" id="file2">
                    <label class="custom-file-label" for="file2">Дополнительное фото</label>
                </div>
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file3" id="file3">
                    <label class="custom-file-label" for="file3">Дополнительное фото</label>
                </div>
            </div>
            <div class="form-group">
                <label for="quantity">Количество</label>
                <div class="input-group mb-3">
                    <input type="number" required class="form-control" id="quantity" name="quantity">
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>

            <button type="submit" style="width: 100%" class="btn btn-primary">Добавить</button>
        </form>
    </div>
    <div class="card-columns">
        <#list albums as album>
            <a href="/album/${album.id}" class="album-link">
                <div class="card text-white bg-primary my-3 album">
                    <div class="card-top">
                        <form action="/album/delete/${album.id}" method="get">
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <button type="submit" class="btn btn-danger m-3">
                                Удалить
                            </button>
                        </form>
                    </div>
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