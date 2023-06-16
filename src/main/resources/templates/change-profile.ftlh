<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Изменить профиль</h1>
    <hr>
    <div class="container">
    <form action="/profile/change/name" method="post">
        <div class="form-row align-items-center mb-4">
            <div class="col">
                <input type="text" class="form-control" required id="name" name="name" placeholder="Имя пользователя">
            </div>
            <div class="col-auto">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button type="submit" class="btn btn-primary">Изменить</button>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
    <form action="/profile/change/email" method="post">
        <div class="form-row align-items-center mb-4">
            <div class="col">
                <input type="email" class="form-control" required name="email" name="email" placeholder="Электронная почта">
            </div>
            <div class="col-auto">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button type="submit" class="btn btn-primary">Изменить</button>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
    <form action="/profile/change/phone" method="post">
        <div class="form-row align-items-center mb-4">
            <div class="col">
                <input type="text" class="form-control" required name="phoneNumber" name="phoneNumber" placeholder="Номер телефона">
            </div>
            <div class="col-auto">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button type="submit" class="btn btn-primary">Изменить</button>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
        <form action="/profile/change/address" method="post">
            <div class="form-row align-items-center mb-4">
                <div class="col">
                    <input type="text" class="form-control" required name="address" name="address" placeholder="Адрес">
                </div>
                <div class="col-auto">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <button type="submit" class="btn btn-primary">Изменить</button>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
        </form>
    <form action="/profile/change/password" method="post">
        <div class="form-row align-items-center mb-4">
            <div class="col">
                <input type="password" class="form-control" required name="password" name="password" placeholder="Пароль">
            </div>
            <div class="col-auto">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button type="submit" class="btn btn-primary">Изменить</button>
            </div>
        </div>
    </form>
        <#if user.getRole() == "ROLE_ARTIST">
            <form action="/profile/change/group" method="post">
                <div class="form-row align-items-center mb-4">
                    <div class="form-group">
                        <label for="group">Группа</label>
                        <select id="group" required name="group" class="form-control">
                            <#list groups as group>
                                <option value=${group.id}>${group.name}</option>
                            </#list>
                        </select>
                    </div>
                    <div class="col-auto">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <button type="submit" class="btn btn-primary">Изменить</button>
                    </div>
                </div>
            </form>
        </#if>
        <hr>
        <h2 style="text-align: center">Изменить аватар</h2>
        <hr>
        <form action="/profile/change/avatar" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <div class="custom-file">
                    <label class="custom-file-label" for="file">Аватар</label>
                    <input required type="file" name="file" id="file">
                </div>
            </div>
            <div class="col-auto">
                <button type="submit" style="width: 100%" class="btn btn-primary">Изменить аватар</button>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
        </form>
        <#if errorMessage??>
        <h2 style="color: red">${errorMessage}</h2>
    </#if>
    </div>
</@t.template>