<#import "blocks/template.ftlh" as t>
<@t.template user>
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <#list images as image>
                <li data-target="#myCarousel" data-slide-to="0" class=""></li>
            </#list>
        </ol>
        <div class="carousel-inner">
            <#list images as image>
                <div class="carousel-item <#if image.previewImage>active</#if>">
                    <svg class="bd-placeholder-img" width="100%" height="100%" xmlns="http://www.w3.org/2000/svg"
                         role="img" aria-label=" :  " preserveAspectRatio="xMidYMid slice" focusable="false">
                        <title></title>
                        <rect width="100%" height="100%" fill="#777"></rect>
                        <text x="50%" y="50%" fill="#777" dy=".3em"></text>
                    </svg>

                    <div class="container">
                        <div class="carousel-caption">
                            <a href="/images/${image.id}">
                                <img class="album" style="height: 100px" src="/images/${image.id}">
                            </a>
                        </div>
                    </div>
                </div>
            </#list>

        </div>
        <a class="carousel-control-prev" href="#myCarousel" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#myCarousel" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
    <p><h1 style="text-align: center">${album.title} | ${album.price} ₽</h1></p>
    <p style="text-align: center">${album.description}</p>
    <hr>
    <div class="list-group">
        <#assign count = 0>
        <#list songs as song>
            <#if song.albumId == album.id>
                    <#assign count = count + 1>
                    <a href="/" class="list-group-item list-group-item-action">
                        <div class="d-flex w-100 justify-content-between">
                            <h5 class="mb-1">${count}. ${song.title}</h5>
                            <small>${song.time} сек.</small>
                        </div>
                        <p class="mb-1">${album.group.name}</p>
                    </a>
            </#if>
        </#list>
    </div>
    <hr>
    <p style="text-align: center">Номер телефона продавца: <b>${authorAlbum.phoneNumber}</b></p>
    <p style="text-align: center">
        <a href="/user/${authorAlbum.id}">
            <img src="/static/images/avatar.png" alt="mdo" width="32" height="32" class="rounded-circle"> ${authorAlbum.name}
        </a>
    </p>
    </hr>
    <div class="text-center">
        <a class="btn btn-primary btn-lg" href="/album/${album.id}/buy" role="button">Купить альбом</a>
        <a class="btn btn-primary btn-lg" href="/album/${album.id}/basket" role="button">Добавить в корзину</a>
    </div>
    <#if con = 1>
        <div class="alert alert-danger alert-dismissible fade show mt-4" role="alert">
            <strong>Ошибка</strong> На вашем счету недостаточно средств
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    <#elseif con = 2>
        <div class="alert alert-danger alert-dismissible fade show mt-4" role="alert">
            <strong>Ошибка</strong> Товар закончился
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    <#elseif con = 3>
        <div class="alert alert-success alert-dismissible fade show mt-4" role="alert">
            <strong>Успех</strong> Товар куплен
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    <#elseif con = 4>
        <div class="alert alert-success alert-dismissible fade show mt-4" role="alert">
            <strong>Успех</strong> Товар добавлен в корзину
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </#if>
    <hr>
    <#if (user.getRole() == "ROLE_ARTIST") && (user.id == album.user.id)>
    <a class="btn btn-primary mt-3 mb-3" style="text-align: center; width: 100%"
       data-toggle="collapse" href="#collapseExample" role="button"
       aria-expanded="true" aria-controls="collapseExample">
        Добавить новую песню
    </a>
    <div class="collapse mb-3" id="collapseExample" style="">
    <form action="/album/${album.id}/add" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="songName">Название песни</label>
            <input type="text" required class="form-control"
                   id="songName" name="title">
        </div>
        <!--
        <div class="form-group">
            <label for="songAuthor">Автор(-ы) песни</label>
            <input type="text" required class="form-control"
                   id="songAuthor" name="author">
        </div>
        -->
        <div class="form-group">
            <label for="songTime">Длина песни (в секундах)</label>
            <div class="input-group mb-3">
                <input type="number" required class="form-control" id="songTime" name="time">
                <div class="input-group-append">
                    <span class="input-group-text">сек.</span>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label for="description">Описание песни</label>
            <textarea required class="form-control" id="description" name="description"></textarea>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" style="width: 100%" class="btn btn-primary">Добавить</button>
    </form>
    </div>
    </#if>
</@t.template>